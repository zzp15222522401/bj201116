package com.atguigu.mapreduce.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * keyin    reduce输入的key数据类型    Text
 * valuein  reduce输入value数据类型   Intwritable
 *          reduce输入的数据类型就是mapper中输出数据的数据类型
 *
 * keyout   reduce 输出key数据的类型  Text
 * valueout reduce 输出value数据类型  Intwritable
 */
public class WCReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum=0;
        //这里的values表示的是{1，1，1，1，1...}这样的数据
        // 经过mapper输出过来传到reduce的数据是这个样子的（这其中还有suffer过程--排序，组合的过程）  a {1,1,1,1,....}
        //而我们经过reduce过程需要的是  a-->n    b-->m 这样的结果，所以我们需要把values进行遍历累加
           /* for (IntWritable value : values){
            int i = value.get();//把value（IntWritable）转换成int类型
            sum+=i;
        }*/
        Iterator<IntWritable> iterator = values.iterator();
            if(iterator.hasNext()){
                IntWritable next = iterator.next();
                int i = next.get();
                sum+=i;

            }
        IntWritable writable = new IntWritable(sum);//转换成IntWritable类型
        context.write(key,writable);
    }
}
