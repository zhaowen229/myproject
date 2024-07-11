package com.chz.hadoop.mapreduce.air;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer extends Reducer<MyKey, Text, NullWritable, Text> {

	@Override
	protected void reduce(MyKey key, Iterable<Text> value, Context context) throws IOException, InterruptedException {
		int count = 0;
		for (Text text : value) {
			count++;
			if (count > 3) {
				break;
			} else {
				context.write(NullWritable.get(), new Text(text));
			}
		}
	}

}
