package com.chz.hadoop.mapreduce;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ETLMapper extends Mapper<LongWritable, Text, NullWritable, Text> {

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		String lineContent = value.toString();
		String[] strArray = lineContent.split(",");
		String conString = "";

		for (int i = 0; i < strArray.length; i++) {
			if (i == 3) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String str = sdf.format(Long.parseLong(strArray[i]));
				conString = conString + str + ",";
			} else {
				conString = conString + strArray[i] + ",";
			}
		}
		context.write(NullWritable.get(), new Text(conString));
	}
}
