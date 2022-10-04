/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.flowSort;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

public class FlowSortDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 1 create job object
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration);
        // 2 关联jar包
        job.setJarByClass(FlowSortDriver.class);
        job.setMapperClass(FlowSortMapper.class);
        job.setReducerClass(FlowSortReducer.class);
        // 3 设置map的输出类型
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);
        // 4 设置最终的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        // 5设置输出路径
        FileInputFormat.setInputPaths(job, new Path("C:\\hadoopInput\\input2"));
        FileOutputFormat.setOutputPath(job, new Path("C:\\hadoopOutput\\output6"));
        // 6 提交作业
        boolean result=job.waitForCompletion(true);
        System.exit(result ? 1:0); 
    }
}
