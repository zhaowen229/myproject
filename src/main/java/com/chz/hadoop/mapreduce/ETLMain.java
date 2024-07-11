package com.chz.hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

//hadoop jar etl-commandline.jar com.hello.hadoop.mapreduce.ETLMain /hadoop/mapreduce/input/ETL/etl /hadoop/mapreduce/output/ETL/result-commandline
public class ETLMain {

	public static void main(String[] args) {
		System.setProperty("HADOOP_USER_NAME", "root");
		Configuration conf = new Configuration();

		String[] otherArgs = null;
		Job job;
		try {
			otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
			if (otherArgs.length != 2) {
				System.err.println("Usage: ETL <in> <out>");
				System.exit(2);
			}

			job = Job.getInstance();
			job.setJarByClass(ETLMain.class);
			job.setMapperClass(ETLMapper.class);
			job.setReducerClass(ETLReducer.class);

			job.setOutputKeyClass(NullWritable.class);
			job.setOutputValueClass(Text.class);

			FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
			FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
			try {
				boolean result = job.waitForCompletion(true);
				System.exit(result ? 0 : 1);
			} catch (ClassNotFoundException | InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
