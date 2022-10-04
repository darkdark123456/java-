package com.mapreduce.folwprocess;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class flowProcessMapper extends Mapper<LongWritable,Text,Text,flowBean>{

    Text outPhoneNumberKey=new Text();
    flowBean outFlowBeanValue=new flowBean();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, flowBean>.Context context)
            throws IOException, InterruptedException {
        //1 获取并处理信息
        String line=value.toString();
        String [] infoStrings=line.split("\t");
        String phoneNumber=infoStrings[1];
        String downFlow=infoStrings[infoStrings.length-3];
        String upFlow=infoStrings[infoStrings.length-2];
        //2 设置write参数
        outPhoneNumberKey.set(phoneNumber);
        outFlowBeanValue.setDownFlow(Long.parseLong(downFlow));
        outFlowBeanValue.setUpFlow(Long.parseLong(upFlow));
        outFlowBeanValue.setTotalFlow();
        //3 写出map
        context.write(outPhoneNumberKey,outFlowBeanValue);
    }
    
}
