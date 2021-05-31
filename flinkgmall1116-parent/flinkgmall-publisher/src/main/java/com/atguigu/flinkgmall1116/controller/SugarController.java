package com.atguigu.flinkgmall1116.controller;

import com.atguigu.flinkgmall1116.bean.KeywordStats;
import com.atguigu.flinkgmall1116.bean.ProductStats;
import com.atguigu.flinkgmall1116.bean.ProvinceStats;
import com.atguigu.flinkgmall1116.bean.VisitorStats;
import com.atguigu.flinkgmall1116.service.KeywordStatsService;
import com.atguigu.flinkgmall1116.service.ProductStatsService;
import com.atguigu.flinkgmall1116.service.ProvinceStatsService;
import com.atguigu.flinkgmall1116.service.VisitorStatsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.UnsupportedDataTypeException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/sugar")
public class SugarController {
    @Autowired
    ProductStatsService productStatsService;
    @Autowired
    ProvinceStatsService provinceStatsService;
    @Autowired
    VisitorStatsService visitorStatsService;
    @Autowired
    KeywordStatsService keywordStatsService;

    //TODO 某天每个地区的销售总额
    @RequestMapping("province")
    public String getProvincetotal(@RequestParam(value = "date",defaultValue = "0") Integer date){
        if(date==0) date=now();
        List<ProvinceStats> provinceStatsList = provinceStatsService.getProvinceStats(date);
        StringBuilder jsonStr=new StringBuilder("{\"status\": 0,\"data\": {\"mapData\": [");
        for (int i = 0; i < provinceStatsList.size(); i++) {
            ProvinceStats provinceStats = provinceStatsList.get(i);
            jsonStr.append("{\"name\": \""+provinceStats.getProvince_name()+"\",\"value\": "+provinceStats.getOrder_amount()+"}");
            if(i<provinceStatsList.size()-1){
                jsonStr.append(",");
            }
        }
        jsonStr.append("],\"valueName\": \"商品销售总额\"}}");
        return  jsonStr.toString();
    }
    //TODO 销售总额
    @RequestMapping("/gmv")
    public String getGMV(@RequestParam(value = "date",defaultValue = "0") Integer date){
        if(date==0){
            date=now();
        }
        BigDecimal gmv = productStatsService.getGMV(date);
        String json="{\"status\": 0,\"data\": "+gmv+"}";
        return json;
    }
    //TODO 某天每个品牌的销售总额
    @RequestMapping("/trademark")
    public String getTrademark(
            @RequestParam(value = "date",defaultValue = "0") Integer date,
            @RequestParam(value = "limit",defaultValue = "10") Integer limit){
        if(date==0) date=now();

        List<ProductStats> categoryList = productStatsService.getTrademark(date, limit);
        StringBuilder jsonStr=new StringBuilder("{\"status\": 0,\"data\": {\"categories\": [");
        for (int i = 0; i < categoryList.size(); i++) {
            ProductStats productStats = categoryList.get(i);
            jsonStr.append("\""+productStats.getTm_name()+"\"");
            if(i<categoryList.size()-1){
                jsonStr.append(",");
            }
        }
        jsonStr.append("],\"series\": [{\"name\": \"销售总额\",\"data\": [");
        for (int i = 0; i < categoryList.size(); i++) {
            ProductStats productStats = categoryList.get(i);
            jsonStr.append("\""+productStats.getOrder_amount()+"\"");
            if(i<categoryList.size()-1){
                jsonStr.append(",");
            }
        }
        jsonStr.append("]}]}}");

        return jsonStr.toString();
    }

    //TODO 某天每个品类的销售总额
    @RequestMapping("category3")
    public String getCategory(
            @RequestParam(value = "date",defaultValue = "0") Integer date,
            @RequestParam(value = "limit",defaultValue = "10") Integer limit){
        if(date==0) date=now();

        List<ProductStats> categoryList = productStatsService.getCategory(date, limit);
        StringBuilder jsonStr=new StringBuilder("{\"status\": 0,\"data\": [");
        for (int i = 0; i < categoryList.size(); i++) {
            ProductStats productStats = categoryList.get(i);

            jsonStr.append("{\"name\": \""+productStats.getCategory3_name()+"\",\"value\": "+productStats.getOrder_amount()+"}");
            if(i<categoryList.size()-1){
                jsonStr.append(",");
            }
        }
        jsonStr.append("]}");
        return jsonStr.toString();
    }

    @RequestMapping("spu")
    public String getSpu(
            @RequestParam(value = "date",defaultValue = "0")Integer date,
            @RequestParam(value = "limit",defaultValue = "20") Integer limit){
        if (date==0){
            date=now();
        }
        List<ProductStats> spuList = productStatsService.getSpu(date, limit);
        StringBuilder jsonStr=new StringBuilder("{\"status\": 0,\"msg\": \"\",\"data\": {\"columns\": [{\"name\": \"商品名称\",\"id\": \"spu_name\"},{\"name\": \"交易额\",\"id\": \"amount\"},{\"name\": \"订单数\",\"id\": \"ct\"}],\"rows\": [");
        for (int i = 0; i < spuList.size(); i++) {
            ProductStats productStats = spuList.get(i);
            jsonStr.append("{\"spu_name\": \""+productStats.getSpu_name()+"\",\"amount\": \""+productStats.getOrder_amount()+"\",\"ct\": \""+productStats.getOrder_ct()+"\"}");
            if(i<spuList.size()-1){
                jsonStr.append(",");
            }
        }
        jsonStr.append("]}}");

        return jsonStr.toString();
    }



    @RequestMapping("visitor")
    public String getVistsIsNew(@RequestParam(value = "date",defaultValue = "0") Integer date){
        if(date==0) date=now();
        List<VisitorStats> visitorStatsList = visitorStatsService.getVisitorIsNew(date);
        VisitorStats listnew = new VisitorStats();
        VisitorStats listold = new VisitorStats();
        for (VisitorStats visitorStats : visitorStatsList) {
            if(visitorStats.getIs_new().equals("1")){
                listnew=visitorStats;
            }else {
                listold = visitorStats;
            }
        }
        String str="{\"status\":0,\"data\":{\"combineNum\":1,\"columns\":" +
                "[{\"name\":\"类别\",\"id\":\"type\"}," +
                "{\"name\":\"新用户\",\"id\":\"new\"}," +
                "{\"name\":\"老用户\",\"id\":\"old\"}]," +
                "\"rows\":" +
                "[{\"type\":\"用户数(人)\"," +
                "\"new\": " + listnew.getUv_ct() + "," +
                "\"old\":" + listold.getUv_ct() + "}," +
                "{\"type\":\"总访问页面(次)\"," +
                "\"new\":" + listnew.getPv_ct() + "," +
                "\"old\":" + listold.getPv_ct() + "}," +
                "{\"type\":\"跳出率(%)\"," +
                "\"new\":" + listnew.getUj() + "," +
                "\"old\":" + listold.getUj() + "}," +
                "{\"type\":\"平均在线时长(秒)\"," +
                "\"new\":" + listnew.getAvgDur() + "," +
                "\"old\":" + listold.getAvgDur() + "}," +
                "{\"type\":\"平均访问页面数(人次)\"," +
                "\"new\":" + listnew.getAvgSv() + "," +
                "\"old\":" + listold.getAvgSv()
                + "}]}}";
        return str;
    }

    @RequestMapping("/hour")
    public String getVisitorStatsByHour(@RequestParam(value = "date",defaultValue = "0") Integer date){
        if(date == 0) date =now();

        List<VisitorStats> visitorStatsList = visitorStatsService.getVisitorStatsByHour(date);
        //定义一个数组，用于存放0~23点的数据
        VisitorStats[] visitorStatsArr = new VisitorStats[24];
        //遍历访客统计集合
        for (VisitorStats visitorStats : visitorStatsList) {
            //将遍历的结果赋给数组中的元素
            visitorStatsArr[visitorStats.getHr()] = visitorStats;
        }
        //定义几个List集合，分别用来存放小时、pv、uv、newUv
        List<String> hrList = new ArrayList<>();
        List<Long> pvList = new ArrayList<>();
        List<Long> uvList = new ArrayList<>();
        List<Long> newMidUvList = new ArrayList<>();

        //遍历数组，得到0~23点的数据
        for (int hr = 0; hr < visitorStatsArr.length; hr++) {
            VisitorStats visitorStats = visitorStatsArr[hr];
            //判断当前小时是否有访问
            if(visitorStats!=null){
                pvList.add(visitorStats.getPv_ct());
                uvList.add(visitorStats.getUv_ct());
                newMidUvList.add(visitorStats.getNew_uv());
            }else{
                pvList.add(0L);
                uvList.add(0L);
                newMidUvList.add(0L);
            }
            hrList.add(String.format("%02d",hr));
        }

        //拼接字符串
        String json = "{\"status\":0,\"data\":{" + "\"categories\":" +
                "[\""+ StringUtils.join(hrList,"\",\"")+ "\"],\"series\":[" +
                "{\"name\":\"uv\",\"data\":["+ StringUtils.join(uvList,",") +"]}," +
                "{\"name\":\"pv\",\"data\":["+ StringUtils.join(pvList,",") +"]}," +
                "{\"name\":\"新用户\",\"data\":["+ StringUtils.join(newMidUvList,",") +"]}]}}";
        return  json;

    }

    @RequestMapping("/keyword")
    public String getKeywordStats(
            @RequestParam(value = "date", defaultValue = "0") Integer date,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        if (date == 0) date = now();

        List<KeywordStats> keywordStatsList = keywordStatsService.getKeywordStats(date, limit);
        StringBuilder jsonB = new StringBuilder("{\"status\": 0,\"data\": [");
        for (int i = 0; i < keywordStatsList.size(); i++) {
            KeywordStats keywordStats = keywordStatsList.get(i);
            jsonB.append("{\"name\": \""+keywordStats.getKeyword()+"\",\"value\": "+keywordStats.getCt()+"}");
            if(i < keywordStatsList.size() - 1){
                jsonB.append(",");
            }
        }
        jsonB.append("]}");
        return jsonB.toString();
    }

    //没有输入日期的话，默认设定为今天
    public int now(){
        String currentday= DateFormatUtils.format(new Date(),"yyyyMMdd");
        return Integer.parseInt(currentday);
    }
}
