/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.log;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.Text;



public class LogDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration);

        job.setJarByClass(LogDriver.class);
        job.setMapperClass(LogMapper.class);
        job.setReducerClass(LogReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        /**
         * 设置自定义输出类
         */
        job.setOutputFormatClass(LogOutputFormat.class);
        FileInputFormat.setInputPaths(job,new Path("C:\\hadoopInput\\input5"));
        /** 
         * 仍需要FileOutputFormat类来指定_SUCCESS的文件路径
        */
        FileOutputFormat.setOutputPath(job,new Path("C:\\hadoopOutput\\output007"));

        Boolean result=job.waitForCompletion(true);
        System.exit(result ? 1:0);
        

    }
    
}
