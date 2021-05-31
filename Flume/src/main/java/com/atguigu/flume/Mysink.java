package com.atguigu.flume;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//自定义sink阶段  输出到文件还是就控制台
public class Mysink extends AbstractSink implements Configurable {
    private boolean file;
    private String filename;

    @Override
    public Status process() throws EventDeliveryException {
        //sink需要自己指定事务
        Status status=null;
        Channel channel = getChannel();
        Transaction transaction = channel.getTransaction();
        transaction.begin();
        try {
            Event event=channel.take();
            saveevent(event);//自定义保存event
            transaction.commit();
            status=Status.READY;
        }catch (Throwable e){
            transaction.rollback();
            status=Status.BACKOFF;


            if(e instanceof Error){
                throw (Error)e;

            }
        }finally {
            transaction.close();
        }

            return status;
        }

    /**
     * 如何存储事件
     * @param event
     */
    private void saveevent(Event event) throws IOException {
        if(file){
            //如果是文件存储
            if(filename==null){
                throw new FileNotFoundException();
            }else{
                FileOutputStream output= new FileOutputStream(filename,true);
                output.write(event.getBody());
                output.close();
            }
        }else{//否则直接写到控制台
            System.out.write(event.getBody());
        }
    }

    /**
     * 自定义配置定义sink
     * @param context
     * 这里我们定义两个参数配置 判断是否为文件，以及文件路径
     */
    @Override
    public void configure(Context context) {
        file = context.getBoolean("isfile",false);
        filename=context.getString("filename");
    }
}
