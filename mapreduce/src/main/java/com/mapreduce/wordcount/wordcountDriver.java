/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.wordcount;

import java.io.IOException;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;




public class wordcountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1创建一个job对象
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration);
        //2 关联Driver程序的jar包
        job.setJarByClass(wordcountDriver.class);
        //3 map  , reducer 的jar
        job.setMapperClass(wordcountMapper.class);
        job.setReducerClass(wordcountReducer.class);
        //4 设置map的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //5 设置最终输出的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // 6 set input ,output path
        FileInputFormat.setInputPaths(job, new Path(args[1]));
        FileOutputFormat.setOutputPath(job,new Path(args[2]));
       // 提交作业
        boolean result=job.waitForCompletion(true);
        System.exit(result ? 0 :1);
    }   
}
