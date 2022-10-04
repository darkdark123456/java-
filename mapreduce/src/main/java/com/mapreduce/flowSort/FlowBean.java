/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.flowSort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


import org.apache.hadoop.io.WritableComparable;

public class FlowBean implements WritableComparable<FlowBean>{
    private long downFlow;
    private long upFlow;
    private long totalFlow;

    public FlowBean(){

    }

    public void setDownFlow(long downFlow){
        this.downFlow=downFlow;
    }

    public void setUpFlow(long upFlow){
        this.upFlow=upFlow;
    }

    public void setTotalFlow(long totalFlow){
        this.totalFlow=totalFlow;
    }

    public void setTotalFlow(){
        this.totalFlow=this.upFlow+this.downFlow;
    }

    public long getDownFlow(){
        return this.downFlow;
    }

    public long getUpFlow(){
        return this.upFlow;
    }

    public long getTotalFlow(){
        return this.totalFlow;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(downFlow);
        out.writeLong(upFlow);
        out.writeLong(totalFlow);
    }

    @Override
    public int compareTo(FlowBean o) {
        if(this.totalFlow>o.getTotalFlow()){
            return -1;
        }
        return 1;   
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.downFlow=in.readLong();
        this.upFlow=in.readLong();
        this.totalFlow=in.readLong();
        
    }  
    // 在reduce最后阶段调用context.write()时，如果写入一个bean对象则会调入重写的此方法，输入一段String
    @Override
    public String toString() {
        return upFlow +"\t"+downFlow+"\t"+totalFlow;
    }
}
