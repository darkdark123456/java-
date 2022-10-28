/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class wordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text mapOutputText=new Text();
    private IntWritable mapOutputIntWritable=new IntWritable(1);
    /* (non-Javadoc)
     * @see org.apache.hadoop.mapreduce.Mapper#map(java.lang.Object, java.lang.Object, org.apache.hadoop.mapreduce.Mapper.Context)
     */
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
            throws IOException, InterruptedException {
        // 1 将mapper传来的一行文本改变为String类

        String lineText=value.toString();
        //  2 将这一行文本分割为一个个单词放在数组中，组成一个字符串数组
        String[] wordsArray=lineText.split(" ");
        //  3 遍历这个字符串数组，用context输出，形式为(w1,1),(w2,1)....(wn,1) 
        for (String word : wordsArray) {
            mapOutputText.set(word);
        //  4 context 将文本变为(w1,(1,1,1))形式？作为reduce的输入
            context.write(mapOutputText, mapOutputIntWritable);
        }
    }
}
