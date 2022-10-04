package com.mapreduce.log;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.Text;


public class LogOutputFormat extends FileOutputFormat<Text,NullWritable>{

    @Override
    public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext job)
            throws IOException, InterruptedException {
        /**返回一个用于写记录的流对象*/
        LogRecorderWriter logRecorderWriter=new LogRecorderWriter(job);
        return logRecorderWriter;
    }
}
