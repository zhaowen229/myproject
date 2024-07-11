package com.chz.hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ETLReducer extends Reducer<NullWritable, Text, NullWritable, Text> {

	@Override
	protected void reduce(NullWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		for (Text content : values) {
			context.write(NullWritable.get(), new Text(content));
		}
	}
}
