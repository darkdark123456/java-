/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.flowPartation;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class flowProcessReduce extends Reducer<Text,flowBean,Text,flowBean>{
    flowBean fBeanValue=new flowBean();
    @Override
    protected void reduce(Text key, Iterable<flowBean> values, Reducer<Text, flowBean, Text, flowBean>.Context context)
            throws IOException, InterruptedException {
                // 1 定义某个手机号的总下行流量与总上行流量
                long totalUpFlow=0;
                long totalDownFlow=0;
                // 2 遍历flowBean对象，得到某个手机号的总上下行流量
                for (flowBean fBean : values) {
                    totalDownFlow+=fBean.getDownFlow();
                    totalUpFlow+=fBean.getUpFlow(); 
                }
                // 3 封装为reduce的输出方式
                fBeanValue.setDownFlow(totalDownFlow);
                fBeanValue.setUpFlow(totalUpFlow);
                fBeanValue.setTotalFlow();
                context.write(key, fBeanValue);
    }
    
}
