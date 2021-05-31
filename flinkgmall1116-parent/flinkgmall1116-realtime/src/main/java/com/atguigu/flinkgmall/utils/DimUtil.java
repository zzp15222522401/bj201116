package com.atguigu.flinkgmall.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.flink.api.java.tuple.Tuple2;
import redis.clients.jedis.Jedis;

import java.util.List;

//TODO 维度查询的工具
public class DimUtil {
    //查询维度表的数据
    //在phoenxix 基础上进一步查询  查询具体的维度数据,没有使用缓存优化的方法
    public static JSONObject getdiminfonocache(String tablename,Tuple2<String,String>... columnsnameandvalue){
        //select * from 表名 where id =??? and ...,当条件为多个时 参数就需要多个
        StringBuilder querysql=new StringBuilder("select * from "+tablename+" where ");
        //根据phoenix工具类查询表的所有字段
        for (int i = 0; i < columnsnameandvalue.length; i++) {
            Tuple2<String, String> column = columnsnameandvalue[i];
            String name= column.f0;
            String value = column.f1;
            if (i>0) {
                querysql.append(" and ");
            }
            //sql查询维度表的语句
            querysql.append(name+" = "+"'"+value+"'");
            System.out.println("查询数据的sql语句："+querysql);
        }
        //使用phoenix工具查询表数据
        List<JSONObject> list = PhoenixUtil.querydata(querysql.toString(), JSONObject.class);
        JSONObject jsonObject=null;
        //查到了数据
        if(list!=null&&list.size()>0){
            //维度查询的结果只有一条数据
            jsonObject = list.get(0);

        }else{
            System.out.println("没有查询到数据："+querysql);
        }
        return jsonObject;
    }
    //使用redis缓存优化的方法,先从缓存中查询数据，缓存中查不到数据就从数据库中差，查到后将数据缓存到redis中，查不到报错
    public static JSONObject getdiminfo(String tablename,Tuple2<String,String>... columnsnameandvalue){
        JSONObject jsonObject=null;

        StringBuilder querysql=new StringBuilder("select * from "+tablename+" where ");

        StringBuilder rediskey=new StringBuilder("dim:"+tablename.toLowerCase()+":");


        for (int i = 0; i < columnsnameandvalue.length; i++) {
            Tuple2<String, String> tuple2 = columnsnameandvalue[i];
            String columnname = tuple2.f0;
            String value = tuple2.f1;
            if(i>0){
               querysql.append(" and ");
                rediskey.append("_");
            }

            querysql.append(columnname+" = "+"'"+value+"'");
            rediskey.append(value);

        }


        Jedis jedis=null;
        String getfromredis=null;
        //先从缓存中查
        try {
            //获取jedis对象
            jedis = RedisUtil.getjedis();
            //在redis中根据key查询value
            getfromredis= jedis.get(rediskey.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("从缓存中查询失败");
        }
        if(getfromredis!=null&&getfromredis.length()>0){
            //说明从缓存中给查询到了数据,将数据转换成json
            jsonObject = JSON.parseObject(getfromredis);
        }
        //从缓存中没有查到，那么就去数据库中查
        else {
            System.out.println("查询数据库的sql语句："+querysql);
            //实用工具类查询数据
            List<JSONObject> list = PhoenixUtil.querydata(querysql.toString(), JSONObject.class);
            //查到了数据，把查到的数据缓存到redis中
            if(list!=null&&list.size()>0){
                jsonObject = list.get(0);
                if(jedis!=null){
                    //将数据缓存redis中，设置超时时间，防止redis崩溃
                    jedis.setex(rediskey.toString(),3600*24*2,jsonObject.toJSONString());
                }
            }
            else {

                System.out.println("没有查询到维度数据："+querysql);
            }

        }
        //释放资源
        if(jedis!=null){
            jedis.close();

        }
        return jsonObject;
    }
    public static JSONObject getdiminfo(String tablename,String id){
        JSONObject jsonObject = getdiminfo(tablename, Tuple2.of("id", id));
            return jsonObject;
    }
    //当数据发生变化时，需要把redis中的缓存清除掉(根据redis的key清除)
    public static void deleteredis(String tablename,String id){
        String rediskey="dim:"+tablename.toLowerCase()+":"+id;

        try {
            Jedis jedis = RedisUtil.getjedis();
            jedis.del(rediskey);
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("清除缓存异常");
        }

    }
   /* //方法测试
    public static void main(String[] args) {
        //JSONObject object = getdiminfonocache("DIM_BASE_TRADEMARK", Tuple2.of("id", "12"));
        //JSONObject object = getdiminfo("DIM_BASE_TRADEMARK", Tuple2.of("id", "12"));
        JSONObject object = getdiminfo("DIM_BASE_TRADEMARK", "12");
        //deleteredis("DIM_BASE_TRADEMARK","12");
        System.out.println(object);
    }*/
}
