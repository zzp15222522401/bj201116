package com.atguigu.flinkgmall1116.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Author: Felix
 * Desc: 访客流量统计实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorStats {


    private String stt;
    private String edt;
    private String vc;
    private String ch;
    private String ar;
    private String is_new;
    private Long uv_ct = 0L;
    private Long pv_ct = 0L;
    private Long sv_ct = 0L;
    private Long uj_ct = 0L;
    private Long dur_sum = 0L;
    private Long new_uv = 0L;
    private Long ts;
    private int hr;

    public BigDecimal getUj(){//计算用户跳出率  跳出次数*100/访问次数
        if(sv_ct!=0){
            BigDecimal ujcount = BigDecimal.valueOf(uj_ct);
          return   ujcount.multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(sv_ct),2, RoundingMode.HALF_UP);
        }
        else
            return BigDecimal.ZERO;
    }
    public BigDecimal getAvgDur(){//计算平均每次在线时常  总时长/访问次数  时常按秒算
        if(sv_ct!=0){
            return BigDecimal.valueOf(dur_sum)
                    .divide(BigDecimal.valueOf(sv_ct),0,RoundingMode.HALF_UP)
                    .divide(BigDecimal.valueOf(1000),1,RoundingMode.HALF_UP);
        }
        else
            return BigDecimal.ZERO;
    }
    public  BigDecimal getAvgSv(){ //计算平均访问页面数  访问页面数/访问次数
        if(sv_ct!=0){
           return BigDecimal.valueOf(pv_ct).divide(BigDecimal.valueOf(sv_ct),2,RoundingMode.HALF_UP);
        }
        else
            return BigDecimal.ZERO;
    }
}
