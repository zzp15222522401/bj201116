package com.atguigu.flinkgmall.app.dws;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.flinkgmall.bean.*;
import com.atguigu.flinkgmall.funs.DimAsyncFunction;
import com.atguigu.flinkgmall.utils.ClickhouseUtil;
import com.atguigu.flinkgmall.utils.DateTimeUtil;
import com.atguigu.flinkgmall.utils.MyKafkaUtil;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import javax.ws.rs.GET;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * 商品主题宽表的程序流程
 */
public class ProductStatsApp {
    public static void main(String[] args) throws Exception {
        //TODO 1.创建环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);
        //TODO 2.设置检查点
        /*env.enableCheckpointing(5000L, CheckpointingMode.EXACTLY_ONCE);
        env.getCheckpointConfig().setCheckpointTimeout(60000L);
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(3,5000L));
        env.setStateBackend(new FsStateBackend("hdfs://hadoop102:8020/flink/check"));
        System.setProperty("HADOOP_USER_NAME","atguigu");*/
        //TODO 3.设置kafka消费主题和消费者组
        String groupId = "product_stats_app";

        String pageViewSourceTopic = "dwd_page_log"; //页面日志
        String orderWideSourceTopic = "dwm_order_wide";//订单宽表
        String paymentWideSourceTopic = "dwm_payment_wide";//支付宽表
        String cartInfoSourceTopic = "dwd_cart_info";//加购信息
        String favorInfoSourceTopic = "dwd_favor_info";//收藏信息
        String refundInfoSourceTopic = "dwd_order_refund_info";//退单
        String commentInfoSourceTopic = "dwd_comment_info";//评论信息

        //TODO 4.从kafka中获取数据
        FlinkKafkaConsumer<String> pageLogSource = MyKafkaUtil.getkafkalog(pageViewSourceTopic, groupId);
        FlinkKafkaConsumer<String> orderWideSource = MyKafkaUtil.getkafkalog(orderWideSourceTopic, groupId);
        FlinkKafkaConsumer<String> paymentWideSource = MyKafkaUtil.getkafkalog(paymentWideSourceTopic, groupId);
        FlinkKafkaConsumer<String> cartInfoSource = MyKafkaUtil.getkafkalog(cartInfoSourceTopic, groupId);
        FlinkKafkaConsumer<String> favorInfoSource = MyKafkaUtil.getkafkalog(favorInfoSourceTopic, groupId);
        FlinkKafkaConsumer<String> refundInfoSource = MyKafkaUtil.getkafkalog(refundInfoSourceTopic, groupId);
        FlinkKafkaConsumer<String> commentInfoSource = MyKafkaUtil.getkafkalog(commentInfoSourceTopic, groupId);
        //TODO 5.将数据封装到流中
        DataStreamSource<String> pageLogDS = env.addSource(pageLogSource);
        DataStreamSource<String> orderWideDS = env.addSource(orderWideSource);
        DataStreamSource<String> paymentWideDS = env.addSource(paymentWideSource);
        DataStreamSource<String> cartInfoDS = env.addSource(cartInfoSource);
        DataStreamSource<String> favorInfoDS = env.addSource(favorInfoSource);
        DataStreamSource<String> refundInfoDS = env.addSource(refundInfoSource);
        DataStreamSource<String> commentInfoDS = env.addSource(commentInfoSource);
        //TODO 6.将流中的数据进行格式转换(ProductStats)
        //6.1将页面日志数据进行格式转换
        SingleOutputStreamOperator<ProductStats> pageLogStatsDS = pageLogDS.process(
                new ProcessFunction<String, ProductStats>() {
                    @Override
                    public void processElement(String jsonStr, Context ctx, Collector<ProductStats> out) throws Exception {
                        JSONObject jsonObj = JSON.parseObject(jsonStr);
                        //商品页面信息
                        JSONObject page = jsonObj.getJSONObject("page");
                        String pageId = page.getString("page_id");
                        Long ts = jsonObj.getLong("ts");
                        if ("good_detail".equals(pageId)) {
                            Long sku_id = page.getLong("item");
                            ProductStats productstats = ProductStats
                                    .builder()
                                    .sku_id(sku_id)
                                    .click_ct(1L)
                                    .ts(ts)
                                    .build();
                            out.collect(productstats);

                        }
                        //曝光数据
                        JSONArray displays = jsonObj.getJSONArray("displays");
                        if (displays != null && displays.size() > 0) {
                            for (int i = 0; i < displays.size(); i++) {
                                JSONObject displayobj = displays.getJSONObject(i);
                                if (displayobj.getString("item_type").equals("sku_id")) {
                                    Long sku_id = displayobj.getLong("item");
                                    ProductStats productStats = ProductStats
                                            .builder()
                                            .sku_id(sku_id)
                                            .display_ct(1L)
                                            .ts(ts)
                                            .build();
                                    out.collect(productStats);
                                }
                            }

                        }

                    }
                }
        );
        //6.2下单行为处理
        SingleOutputStreamOperator<ProductStats> orderWideStatsDS = orderWideDS.map(
                new MapFunction<String, ProductStats>() {
                    @Override
                    public ProductStats map(String jsonstr) throws Exception {
                        OrderWide orderWide = JSON.parseObject(jsonstr, OrderWide.class);
                        ProductStats productStats = ProductStats
                                .builder()
                                .sku_id(orderWide.getSku_id())
                                .orderIdSet(new HashSet(Collections.singleton(orderWide.getOrder_id())))
                                .order_sku_num(orderWide.getSku_num())
                                .order_amount(orderWide.getSplit_total_amount())
                                .ts(DateTimeUtil.ToTS(orderWide.getCreate_time()))
                                .build();
                        return productStats;
                    }
                }
        );
        //6.3 支付数据转换
        SingleOutputStreamOperator<ProductStats> paymentWideStatsDS = paymentWideDS.map(
                new MapFunction<String, ProductStats>() {
                    @Override
                    public ProductStats map(String jsonstr) throws Exception {
                        PaymentWide paymentWide = JSON.parseObject(jsonstr, PaymentWide.class);

                        ProductStats productStats = ProductStats
                                .builder()
                                .sku_id(paymentWide.getSku_id())
                                .paidOrderIdSet(Collections.singleton(paymentWide.getOrder_id()))
                                .order_amount(paymentWide.getSplit_total_amount())
                                .ts(DateTimeUtil.ToTS(paymentWide.getPayment_create_time()))
                                .build();
                        return productStats;
                    }
                }
        );
        SingleOutputStreamOperator<ProductStats> cartInfoStatsDS = cartInfoDS.map(
                new MapFunction<String, ProductStats>() {
                    @Override
                    public ProductStats map(String jsonstr) throws Exception {
                        JSONObject jsonObj = JSON.parseObject(jsonstr);
                        ProductStats productStats = ProductStats
                                .builder()
                                .sku_id(jsonObj.getLong("sku_id"))
                                .cart_ct(1L)
                                .ts(DateTimeUtil.ToTS(jsonObj.getString("create_time")))
                                .build();
                        return productStats;
                    }
                }
        );
        SingleOutputStreamOperator<ProductStats> favorInfoStatsDS = favorInfoDS.map(
                new MapFunction<String, ProductStats>() {
                    @Override
                    public ProductStats map(String jsonstr) throws Exception {
                        JSONObject jsonObj = JSON.parseObject(jsonstr);
                        ProductStats productStats = ProductStats
                                .builder()
                                .sku_id(jsonObj.getLong("sku_id"))
                                .favor_ct(1L)
                                .ts(DateTimeUtil.ToTS(jsonObj.getString("create_time")))
                                .build();
                        return productStats;
                    }
                }
        );
        SingleOutputStreamOperator<ProductStats> refundInfoStatsDS = refundInfoDS.map(
                new MapFunction<String, ProductStats>() {
                    @Override
                    public ProductStats map(String jsonstr) throws Exception {
                        JSONObject jsonObj = JSON.parseObject(jsonstr);
                        ProductStats productStats = ProductStats
                                .builder()
                                .sku_id(jsonObj.getLong("sku_id"))
                                .refundOrderIdSet(Collections.singleton(jsonObj.getLong("order_id")))
                                .refund_amount(jsonObj.getBigDecimal("refund_amount"))
                                .ts(DateTimeUtil.ToTS(jsonObj.getString("create_time")))
                                .build();
                        return productStats;
                    }
                }
        );
        SingleOutputStreamOperator<ProductStats> commentInfoStatsDS = commentInfoDS.map(
                (MapFunction<String, ProductStats>) jsonstr -> {
                    JSONObject jsonObj = JSON.parseObject(jsonstr);
                    long goodsum = GmallConstant.APPRAISE_GOOD.equals(jsonObj.getLong("appraise")) ? 1L : 0L;
                    ProductStats productStats = ProductStats
                            .builder()
                            .sku_id(jsonObj.getLong("sku_id"))
                            .comment_ct(1L)
                            .good_comment_ct(goodsum)
                            .ts(DateTimeUtil.ToTS(jsonObj.getString("create_time")))
                            .build();
                    return productStats;
                }
        );

        //TODO 7.将转换后的流合并在一起union
        DataStream<ProductStats> union =
                pageLogStatsDS.union(orderWideStatsDS, paymentWideStatsDS, cartInfoStatsDS, favorInfoStatsDS, refundInfoStatsDS, commentInfoStatsDS);
        //TODO 8.设置水位线
        SingleOutputStreamOperator<ProductStats> productStatsWithWatermarkDS = union.assignTimestampsAndWatermarks(
                WatermarkStrategy.<ProductStats>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                        .withTimestampAssigner(
                                new SerializableTimestampAssigner<ProductStats>() {
                                    @Override
                                    public long extractTimestamp(ProductStats productStats, long recordTimestamp) {
                                        return productStats.getTs();
                                    }
                                }
                        )
        );
        //TODO 9.对合并后的流进行分组
        KeyedStream<ProductStats, Long> productStatsKeyedDS = productStatsWithWatermarkDS.keyBy(
                productStats->productStats.getSku_id()

        );
        //TODO 10.开窗，聚合
        WindowedStream<ProductStats, Long, TimeWindow> windowDS = productStatsKeyedDS.window(
                TumblingEventTimeWindows.of(Time.seconds(10))
        );
        //聚合
        SingleOutputStreamOperator<ProductStats> reduceDS = windowDS.reduce(
                new ReduceFunction<ProductStats>() {
                    @Override
                    public ProductStats reduce(ProductStats stats1, ProductStats stats2) throws Exception {
                        stats1.setDisplay_ct(stats1.getDisplay_ct() + stats2.getDisplay_ct());
                        stats1.setClick_ct(stats1.getClick_ct() + stats2.getClick_ct());
                        stats1.setCart_ct(stats1.getCart_ct() + stats2.getCart_ct());
                        stats1.setFavor_ct(stats1.getFavor_ct() + stats2.getFavor_ct());
                        stats1.setOrder_amount(stats1.getOrder_amount().add(stats2.getOrder_amount()));
                        stats1.getOrderIdSet().addAll(stats2.getOrderIdSet());
                        stats1.setOrder_ct(stats1.getOrderIdSet().size() + 0L);
                        stats1.setOrder_sku_num(stats1.getOrder_sku_num() + stats2.getOrder_sku_num());
                        stats1.setPayment_amount(stats1.getPayment_amount().add(stats2.getPayment_amount()));

                        stats1.getRefundOrderIdSet().addAll(stats2.getRefundOrderIdSet());
                        stats1.setRefund_order_ct(stats1.getRefundOrderIdSet().size() + 0L);
                        stats1.setRefund_amount(stats1.getRefund_amount().add(stats2.getRefund_amount()));

                        stats1.getPaidOrderIdSet().addAll(stats2.getPaidOrderIdSet());
                        stats1.setPaid_order_ct(stats1.getPaidOrderIdSet().size() + 0L);

                        stats1.setComment_ct(stats1.getComment_ct() + stats2.getComment_ct());
                        stats1.setGood_comment_ct(stats1.getGood_comment_ct() + stats2.getGood_comment_ct());

                        return stats1;

                    }
                },
                new ProcessWindowFunction<ProductStats, ProductStats, Long, TimeWindow>() {
                    @Override
                    public void process(Long aLong, Context context, Iterable<ProductStats> elements, Collector<ProductStats> out) throws Exception {
                        for (ProductStats productStats : elements) {
                            productStats.setStt(DateTimeUtil.toYMDHms(new Date(context.window().getStart())));
                            productStats.setEdt(DateTimeUtil.toYMDHms(new Date(context.window().getEnd())));
                            productStats.setTs(new Date().getTime());
                            out.collect(productStats);
                        }
                    }
                }
        );
        //TODO 11.补全信息
        //11.1补全商品的维度信息
        SingleOutputStreamOperator<ProductStats> productStatsWithSkuDS = AsyncDataStream.unorderedWait(
                reduceDS,
                new DimAsyncFunction<ProductStats>("DIM_SKU_INFO") {
                    @Override
                    public void join(ProductStats productStats, JSONObject jsonobj) {
                        productStats.setSpu_id(jsonobj.getLong("SPU_ID"));
                        productStats.setCategory3_id(jsonobj.getLong("CATEGORY3_ID"));
                        productStats.setSku_name(jsonobj.getString("SKU_NAME"));
                        productStats.setSku_price(jsonobj.getBigDecimal("PRICE"));
                        productStats.setTm_id(jsonobj.getLong("TM_ID"));
                    }

                    @Override
                    public String getkey(ProductStats productStats) {
                        return productStats.getSku_id().toString();
                    }
                },
                60,
                TimeUnit.SECONDS
        );
        //11.2 补全spu维度信息
        SingleOutputStreamOperator<ProductStats> productStatsWithSpuDS = AsyncDataStream.unorderedWait(

                productStatsWithSkuDS,
                new DimAsyncFunction<ProductStats>("DIM_SPU_INFO") {
                    @Override
                    public void join(ProductStats productStats, JSONObject jsonobj) {
                        productStats.setSpu_name(jsonobj.getString("SPU_NAME"));
                    }

                    @Override
                    public String getkey(ProductStats productStats) {
                        return productStats.getSku_id().toString();
                    }
                },
                60,
                TimeUnit.SECONDS
        );
        //11.3补全品类维度信息
        //补充品类维度
        SingleOutputStreamOperator<ProductStats> productStatsWithCategory3Dstream =
                AsyncDataStream.unorderedWait(productStatsWithSpuDS,
                        new DimAsyncFunction<ProductStats>("DIM_BASE_CATEGORY3") {
                            @Override
                            public void join(ProductStats productStats, JSONObject jsonObject){
                                productStats.setCategory3_name(jsonObject.getString("NAME"));
                            }
                            @Override
                            public String getkey(ProductStats productStats) {
                                return String.valueOf(productStats.getCategory3_id());
                            }
                        }, 60, TimeUnit.SECONDS);

        //补充品牌维度
        SingleOutputStreamOperator<ProductStats> productStatsWithTmDstream =
                AsyncDataStream.unorderedWait(productStatsWithCategory3Dstream,
                        new DimAsyncFunction<ProductStats>("DIM_BASE_TRADEMARK") {
                            @Override
                            public void join(ProductStats productStats, JSONObject jsonObject){
                                productStats.setTm_name(jsonObject.getString("TM_NAME"));
                            }
                            @Override
                            public String getkey(ProductStats productStats) {
                                return String.valueOf(productStats.getTm_id());
                            }
                        }, 60, TimeUnit.SECONDS);

        //TODO 12.将数据写到clickhouse
        productStatsWithTmDstream.print(">>>>>");
        productStatsWithTmDstream.addSink(ClickhouseUtil.getjdbcsink("insert into product_stats_1116 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"));
        env.execute();
    }
}
