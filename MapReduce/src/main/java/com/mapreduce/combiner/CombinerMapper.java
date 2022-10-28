package com.mapreduce.combiner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CombinerMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
    Text outKey=new Text();
    IntWritable outValue=new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
            throws IOException, InterruptedException {
                String line=value.toString();
                String[] wordsArraay=line.split(" ");
                for (String word : wordsArraay) {
                    outKey.set(word);
                    context.write(outKey, outValue); 
                }
    }
}
