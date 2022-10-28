package com.mapreduce.flowSort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FlowSortMapper extends Mapper<LongWritable,Text,FlowBean,Text> {
    FlowBean flowBeanV=new FlowBean();
    Text textK=new Text();
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, FlowBean,Text>.Context context)
            throws IOException, InterruptedException {
        // 1 get line string
        String line=value.toString();
        String[] words=line.split("\t");
        // 2 get phoneNumber , flow  informatation
        String phoneNumber=words[0];
        String downFlow=words[1];
        String upFlow=words[2];
        String totalFlow=words[3];
        // 3 construct bean object
        textK.set(phoneNumber);
        flowBeanV.setDownFlow(Long.parseLong(downFlow));
        flowBeanV.setUpFlow(Long.parseLong(upFlow));
        flowBeanV.setTotalFlow(Long.parseLong(totalFlow));
        // 4 write out 
        context.write(flowBeanV,textK);
    }
    
}
