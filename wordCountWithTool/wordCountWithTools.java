/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.wordCountWithTool;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

public class wordCountWithTools implements Tool{
    private Configuration configuration;
    @Override
    public void setConf(Configuration conf) {
        this.configuration=conf;
        
    }

    @Override
    public Configuration getConf() {
        return this.configuration;
    }

    @Override
    public int run(String[] args) throws Exception {
        Job job=Job.getInstance(configuration);
        job.setJarByClass(wordCountWithToolDriver.class);
        job.setMapperClass(wordCountWithToolMapper.class);
        job.setReducerClass(wordCountWithToolReduce.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        return job.waitForCompletion(true) ? 0 :1;
    }
    
}
