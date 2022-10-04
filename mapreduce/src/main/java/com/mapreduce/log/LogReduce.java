package com.mapreduce.log;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class LogReduce extends Reducer<Text,NullWritable,Text,NullWritable>{
    @Override
    protected void reduce(Text key, Iterable<NullWritable> values,
            Reducer<Text, NullWritable, Text, NullWritable>.Context context) throws IOException, InterruptedException {
                for (NullWritable nullWritable : values) {
                    /**此时迭代器里存放了两个null 防止有重复的数据被漏写*/
                    context.write(key, nullWritable);
                    
                }
    }
}
