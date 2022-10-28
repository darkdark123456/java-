/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.JoinInMap;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class JoinInMapDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, 
                                                  InterruptedException, URISyntaxException {
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration);
        job.setJarByClass(JoinInMapDriver.class);
        job.setMapperClass(JoinInMapMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.addCacheFile(new URI("file:///C:/hadoopInput/input6/pd.txt"));
        job.setNumReduceTasks(0);
        FileInputFormat.setInputPaths(job, new Path("C:\\hadoopInput\\input6"));
        FileOutputFormat.setOutputPath(job, new Path("C:\\hadoopOutput\\output008"));
        boolean result=job.waitForCompletion(true);
        System.exit(result ? 0:1);
    }
}
