/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.JoinInMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class JoinInMapMapper extends Mapper<LongWritable,Text,Text,NullWritable>{
    private Map<String,String> pdMap=new HashMap<>();
    private Text outKey=new Text();
    @Override
    protected void setup(Mapper<LongWritable, Text, Text, NullWritable>.Context context)
            throws IOException, InterruptedException {
        URI[] cacheFiles=context.getCacheFiles();
        Path path=new Path(cacheFiles[0]);
        /**利用当前配置信息获取一个文件系统对象 */
        FileSystem fileSystem=FileSystem.get(context.getConfiguration());
        FSDataInputStream fInputStream=fileSystem.open(path);
        /**将当前文件读入buffer */
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(fInputStream,"UTF-8"));
        String line;
        while (StringUtils.isNotEmpty(line=bufferedReader.readLine())) {
            String[] words=line.split("\t");
            pdMap.put(words[0], words[1]);
        }
        /**关闭流 */
        IOUtils.closeStream(bufferedReader);


    }
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
            throws IOException, InterruptedException {
        /**在map运行之前，pd.txt数据已经被读到map中 */
        /**读取order表的数据 */
        String line=value.toString();
        String[] words=line.split("\t");
        String pName=pdMap.get(words[1]);
        outKey.set(words[0]+"\t"+pName+"\t"+words[2]);
        context.write(outKey, NullWritable.get()); 
    }
}
