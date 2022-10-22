/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
/**如果你想要将job提交到自定义的队列，并且在hadoop报input 或者output路径异常时，你应该试着完善tool接口*/
package com.mapreduce.wordCountWithTool;

import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class wordCountWithToolDriver {
    /**声明一个tool接口*/
    private static Tool tool;
    public static void main(String[] args) {
        Configuration configuration=new Configuration();
        switch(args[0]){
            case "wordCountWithTool":/**在这里修改你的main方法所在的类*/
            tool=new wordCountWithTools();/**实例化一个tool接口*/
            break;
            default:
                throw new RuntimeException("没有tool接口");
        }
        try {/**尝试用实例化的tool接口调用重写的run方法*/
            /**在使用hadoop jar jar包 mainClass -D mapreduce.job.queuename=hive /input /output ---> array存放的是[mainClass,/input /output]*/
            int runResult=ToolRunner.run(configuration, tool,Arrays.copyOfRange(args,1, args.length));
            System.exit(runResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
