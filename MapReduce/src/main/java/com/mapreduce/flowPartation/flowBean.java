/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.flowPartation;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class flowBean implements Writable {
    private long upFlow;
    private long downFlow;
    private long totalFlow;

    public flowBean(){
        
    }

    public void setDownFlow(long downFlow){
        this.downFlow=downFlow;
    }

    public void setUpFlow(long upFlow){
        this.upFlow=upFlow;
    }

    public void setTotalFlow(long tatalFlow){
        this.totalFlow=totalFlow;
    }

    public void setTotalFlow(){
        this.totalFlow=this.downFlow+this.upFlow;
    }



    public long getUpFlow(){
        return this.upFlow;
    }

    public long getDownFlow(){
        return this.downFlow;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(downFlow);
        out.writeLong(upFlow);
        out.writeLong(totalFlow);  
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.downFlow =in.readLong();
        this.upFlow   =in.readLong();
        this.totalFlow=in.readLong(); 
    }

    @Override
    public String toString() {
        return downFlow+"\t"+upFlow+"\t"+totalFlow;
    }
}
