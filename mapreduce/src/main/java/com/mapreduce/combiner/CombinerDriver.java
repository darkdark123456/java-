/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.combiner;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.Text;


public class CombinerDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration);

        job.setJarByClass(CombinerDriver.class);
        job.setMapperClass(CombinerMapper.class);
        job.setReducerClass(CombinerReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        /**
         * combiner 后可降低数据在磁盘的存储空间 
         * 方法一 自定义一个combiner类继承reduce类完成逻辑
         */
        //job.setCombinerClass(Combiner.class);

        /**
         * 方法二 在设置中如果combiner类的实现方法与其他类的逻辑相同，直接用其他类的逻辑
         */
        job.setCombinerClass(CombinerReduce.class);
        FileInputFormat.setInputPaths(job, new Path("C:\\hadoopInput\\input3"));
        FileOutputFormat.setOutputPath(job,new Path("C:\\hadoopOutput\\output005"));

        Boolean result=job.waitForCompletion(true);
        System.exit(result ? 0:1);
        
    }
}
