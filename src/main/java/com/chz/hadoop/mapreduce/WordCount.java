package com.chz.hadoop.mapreduce;

import java.io.IOException;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * upload jar to cloud-server
 * 
 * command : hadoop jar /usr/hadoop-3.1.3/share/hadoop/mapreduce/wc.jar
 * com.hello.hadoop.mapreduce.WordCount /hadoop/mapreduce/input/wordcount/wc
 * /hadoop/mapreduce/output/wordcount/result
 *
 */
public class WordCount {

	public static void main(String args[]) {
		Properties properties = System.getProperties();
		properties.setProperty("HADOOP_USER_NAME", "root");

		Configuration conf = new Configuration();
		String[] otherArgs = null;
		Job job = null;

		try {
			otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
			if (otherArgs.length != 2) {
				System.err.println("Usage: wordcount <in> <out>");
				System.exit(2);
			}

			job = Job.getInstance(conf, "wordcount");
		} catch (IOException e) {
			e.printStackTrace();
		}

		job.setJarByClass(WordCount.class);
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setSortComparatorClass(MyDecreasingComparator.class);

		try {
//			GenericOptionsParser parser command line argument
			FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
			FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
			boolean res = job.waitForCompletion(true);
			System.exit(res ? 0 : 1);
		} catch (IllegalArgumentException | ClassNotFoundException | IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}
}
