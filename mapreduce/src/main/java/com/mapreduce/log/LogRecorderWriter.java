package com.mapreduce.log;

import java.io.IOException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class LogRecorderWriter extends RecordWriter<Text,NullWritable>{
    private FSDataOutputStream atguiguOut;
    private FSDataOutputStream otherOut;
    /**将当前的job信息传进来，与job产生关联 */
    public LogRecorderWriter(TaskAttemptContext job){
        try {
            FileSystem fSystem=FileSystem.get(job.getConfiguration());
            atguiguOut=fSystem.create(new Path("C:\\hadoopOutput\\output006\\atguiguOut.log"));
            otherOut=fSystem.create(new Path("C:\\hadoopOutput\\output006\\otherOut.log" ));


        } catch (IOException e) {
            
            e.printStackTrace();
        }
        

    }

    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        String line=key.toString();
        if(line.contains("atguigu")){
            atguiguOut.writeBytes(line+"\n");
        }
        else{
            otherOut.writeBytes(line+"\n");
        }
        
    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(atguiguOut);
        IOUtils.closeStream(otherOut);
        
    }
    
}
