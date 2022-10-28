/*
 * @author: MENG ZHEN CHUAN
 * @date: do not edit
 * @description: HADOOP Project
 * @method: note
 */
package com.mapreduce.wordCountWithTool;

import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class wordCountWithToolDriver {
    private static Tool tool;
    public static void main(String[] args) {
        Configuration configuration=new Configuration();
        switch(args[0]){
            case "wordCountWithTool":
            tool=new wordCountWithTools();
            break;
            default:
                throw new RuntimeException("没有tool接口");
        }
        try {
            int runResult=ToolRunner.run(configuration, tool,Arrays.copyOfRange(args,1, args.length));
            System.exit(runResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
