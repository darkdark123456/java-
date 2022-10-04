/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.flowPartation;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class flowProcessDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1 创建job对象
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration);
        //2 关联类
        job.setJarByClass(flowProcessDriver.class);
        job.setMapperClass(flowProcessMapper.class);
        job.setReducerClass(flowProcessReduce.class);
        //3 设置map的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(flowBean.class);
        //4 设置最终的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(flowBean.class);
        //  自定义分区
        job.setPartitionerClass(provincePartition.class);
        //  设置相应reduce task的数量
        job.setNumReduceTasks(5);
        //5 设置输入输出路径
        FileInputFormat.setInputPaths(job, new Path("C:\\hadoopInput\\input2"));
        FileOutputFormat.setOutputPath(job, new Path("C:\\hadoopOutput\\output4"));
        //6 提交job
        boolean result=job.waitForCompletion(true);
        System.exit(result ? 0:1);
    }
}
