/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.MapeerJoin;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;


public class OrderBean implements Writable{

    private String id;/**订单id */
    private String pid;/**产品id */
    private int    amount;/**订单数量 */
    private String pname;/**产品名称 */
    private String flag;/**来源于哪个文件 */

    /**必须给一个无参构造方法 */
    public OrderBean(){
        
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag){
        this.flag=flag;
    }

    @Override
    public String toString() {
        return id+"\t"+pname+"\t"+amount;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(pid);
        out.writeInt(amount);
        out.writeUTF(pname);
        out.writeUTF(flag);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.id=in.readUTF();
        this.pid=in.readUTF();
        this.amount=in.readInt();
        this.pname=in.readUTF();
        this.flag=in.readUTF();
    }
}
