package com.atguigu.flume;

import org.apache.avro.util.Utf8;
import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

import java.nio.charset.StandardCharsets;
import java.sql.Time;

//自定义flume的source
public class Mysource extends AbstractSource implements Configurable, PollableSource {

    private  int i;
    private  long time;
    private String name;

    /**
     * 核心方法，flume主动拉去数据，并将数据存储Channel中
     * @return
     * @throws EventDeliveryException
     */
    @Override
    public Status process() throws EventDeliveryException {
        Status status=null;
        try{
            //拉取数据
            Event event=getSomeData();
            //存到channel中
            getChannelProcessor().processEvent(event);
            status=Status.READY;
        }
        catch (Exception e){

            status=Status.BACKOFF;
            e.printStackTrace();
        }
        return status;
    }
    //自定义的源中拉去需要的数据
    private Event getSomeData() throws InterruptedException {
        double random = Math.random() * 1000;
        Event event = new SimpleEvent();
        event.setBody((Double.toString(random).substring(1,10)+name).getBytes(StandardCharsets.UTF_8));
        i++;
        if(i%10==0){
            throw new RuntimeException("每10次出现一次异常");
        }
        while (i%100==0){
            throw new Error("到100次就报错！");
        }
        Thread.sleep(time);
        return event;
    }

    /**
     * 每次event失败重新开始的间隔
     * @return
     */
    @Override
    public long getBackOffSleepIncrement() {
        return 1000;
    }

    @Override
    public long getMaxBackOffSleepInterval() {
        return 10000;
    }

    /**
     * 通过配置文件定义source
     * @param context
     */
    @Override
    public void configure(Context context) {
        time = context.getLong("time",1000L);
        name=context.getString("name"," ");
    }
}
