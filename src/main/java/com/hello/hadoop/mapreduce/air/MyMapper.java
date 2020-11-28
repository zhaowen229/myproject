package com.hello.hadoop.mapreduce.air;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 数据结构：
 * 
 * 1949-10-01 14:00:00 34
 *
 */
public class MyMapper extends Mapper<Text, Text, MyKey, Text> {

	protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {

		System.out.println("key==" + key.toString() + "==key");
		String[] str = key.toString().split("-");
		MyKey myKey = new MyKey();
		myKey.setYear(Integer.valueOf(str[0]));
		myKey.setMonth(Integer.valueOf(str[1]));

		System.out.println("value==" + value.toString() + "==value");

		myKey.setAir(Double.parseDouble(value.toString().trim()));

		context.write(myKey, new Text(key.toString() + "\t" + value));// "\t" 相当于Tab键
	}
}
