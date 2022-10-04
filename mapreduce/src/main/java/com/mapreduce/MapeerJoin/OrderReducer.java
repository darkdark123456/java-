/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.MapeerJoin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class OrderReducer extends Reducer<Text,OrderBean,OrderBean,NullWritable>{
    @Override
    protected void reduce(Text key, Iterable<OrderBean> values,
            Reducer<Text, OrderBean, OrderBean, NullWritable>.Context context)
            throws IOException, InterruptedException {
        ArrayList<OrderBean> arrayList=new ArrayList<OrderBean>();
        OrderBean pdOrderBean=new OrderBean();
        for(OrderBean value:values){
            if("order".equals(value.getFlag())){
                OrderBean tempBean=new OrderBean();
                try {
                    BeanUtils.copyProperties(tempBean, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                arrayList.add(tempBean);
            }
            else{
                try {
                    BeanUtils.copyProperties(pdOrderBean, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        for (OrderBean orderBean : arrayList) {
            orderBean.setPname(pdOrderBean.getPname());
            context.write(orderBean,NullWritable.get());
        }
    }
    
}
