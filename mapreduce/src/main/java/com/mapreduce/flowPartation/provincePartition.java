package com.mapreduce.flowPartation;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class provincePartition extends Partitioner<Text,flowBean>{

    @Override
    public int getPartition(Text key, flowBean value, int numPartitions) {
        String phoneNumber=key.toString();
        String prePhoneNumber=phoneNumber.substring(0,3);
        int partition;
        if("136".equals(prePhoneNumber)){
            partition=0;
        }
        else if("137".equals(prePhoneNumber)){
            partition=1;
        }
        else if("138".equals(prePhoneNumber)){
            partition=2;
        }
        else if("139".equals(prePhoneNumber)){
            partition=3;
        }
        else{
            partition=4;
        }
        return partition;
    }
    
}
