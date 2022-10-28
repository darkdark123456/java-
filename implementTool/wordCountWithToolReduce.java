package com.mapreduce.wordCountWithTool;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class wordCountWithToolReduce extends Reducer<Text,IntWritable,Text,IntWritable>{
    private int sum;
    private IntWritable outputSum=new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values,
            Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        sum=0;
        // 接收的参数为(w1,(1,1,1))形式
        // 1 遍历后续的集合，统计w1的个数
        for (IntWritable number : values) {
            sum+=number.get();
        }
        // 2 统计结果，以(w1,3)的形式将结果写入磁盘中
        outputSum.set(sum);
        context.write(key, outputSum);
        
    } 
    }
