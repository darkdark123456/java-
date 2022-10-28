/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.MapeerJoin;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class OrderMapper extends Mapper<LongWritable,Text,Text,OrderBean>{
    private String fileName;
    private Text outK=new Text();
    private OrderBean outV=new OrderBean();
    @Override
    protected void setup(Mapper<LongWritable, Text, Text, OrderBean>.Context context)
            throws IOException, InterruptedException {
        InputSplit inputSplit=context.getInputSplit();
        FileSplit fileSplit=(FileSplit) inputSplit;
        fileName=fileSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, OrderBean>.Context context)
            throws IOException, InterruptedException {
        String line=value.toString();
        /**处理order数据 */
        if(fileName.contains("order")){
            String [] words=line.split("\t");
            /**设置map的输出key */
            outK.set(words[1]);
            /**设置map的输出value */
            outV.setId(words[0]);
            outV.setPid(words[1]);
            outV.setAmount(Integer.parseInt(words[2]));
            outV.setPname("");
            outV.setFlag("order");
        }
        /**处理pd数据 */
        else{
            /**封装key */
            String [] words=line.split("\t");
            outK.set(words[0]);
            outV.setId("");
            outV.setPid(words[0]);
            outV.setAmount(0);
            outV.setPname(words[1]);
            outV.setFlag("pd");
        }
        context.write(outK, outV);
    }   
    
}
