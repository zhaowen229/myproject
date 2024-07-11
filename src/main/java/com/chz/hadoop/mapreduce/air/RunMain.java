package com.chz.hadoop.mapreduce.air;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class RunMain {

	public static void main(String[] args) {
		System.setProperty("HADOOP_USER_NAME", "root");
		Configuration conf = new Configuration();
		FileSystem fs;
		Job job = null;
		try {
			fs = FileSystem.get(conf);

			String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
			if (otherArgs.length != 2) {
				System.err.println("Usage: air <in> <out>");
				System.exit(2);
			}
			job = Job.getInstance(conf, "air");
			// 主方法
			job.setJarByClass(RunMain.class);
			job.setMapperClass(MyMapper.class);

			// KeyValueTextInputFormat 对输入数据进行分割 1949-10-01 14:00:00 34
			// 默认的分割符是"\t" 这里就是使用\t
			job.setInputFormatClass(KeyValueTextInputFormat.class);

			job.setReducerClass(MyReducer.class);
			job.setPartitionerClass(MyPartitioner.class);

			job.setSortComparatorClass(MySort.class);
			job.setGroupingComparatorClass(MyGroup.class);
			job.setNumReduceTasks(3);

			// Map 输出的 key 类型
			job.setOutputKeyClass(MyKey.class);
			// Map 输出的 value 类型
			job.setOutputValueClass(Text.class);

			FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
			Path path = new Path(otherArgs[1]);
			if (fs.exists(path)) {
				fs.delete(path, true);
			}
			FileOutputFormat.setOutputPath(job, path);

			System.exit(job.waitForCompletion(true) ? 0 : 1);
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
