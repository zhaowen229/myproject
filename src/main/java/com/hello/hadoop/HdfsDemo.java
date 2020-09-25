package com.hello.hadoop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * run on aliyun
 * 
 * hdfs://ip:9720 conf on eclipse argument parameter
 */
public class HdfsDemo {
	private static Logger logger = LogManager.getLogger(HdfsDemo.class);
	private static FileSystem fsSystem = null;

	public static void createFile() {
		try {
			Path fsPath = new Path("/hadoop/data");
			fsSystem.mkdirs(fsPath);
			logger.info("File create successful.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void uploadFile() {
		try {
			Path localPath = new Path("/Volumes/DiskOne/doc/wc.txt");
			Path fsPath = new Path("/hadoop/mapreduce/input/wordcount");
			fsSystem.copyFromLocalFile(localPath, fsPath);
			logger.info("File upload successful.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void downloadFile() {
		try {
			Path localPath = new Path("/Volumes/DiskOne/doc/wc-get.txt");
			Path fsPath = new Path("/hadoop/mapreduce/input/wordcount/wc.txt");
			fsSystem.copyToLocalFile(true, fsPath, localPath);
			logger.info("File download successful.");
		} catch (IOException e) {
			logger.info("File download failed.");
			e.printStackTrace();
		}
	}

	public static void listFile(Path path) {
		try {
			FileStatus[] fileStatusArray = fsSystem.listStatus(path);

			for (int i = 0; i < fileStatusArray.length; i++) {
				FileStatus fileStatus = fileStatusArray[i];
				if (fileStatus.isDirectory()) {
					logger.info("当前路径是：" + fileStatus.getPath());
					listFile(fileStatus.getPath());
				} else {
					logger.info("当前路径是：" + fileStatus.getPath());
				}
			}
			logger.info("File display successful.");
		} catch (IOException e) {
			logger.info("File display failed.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.setProperty("HADOOP_USER_NAME", "root");
		Configuration conf = new Configuration();
		try {
			fsSystem = FileSystem.get(new URI(args[0]), conf);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

//		createFile();
//		uploadFile();
		downloadFile();

//		Path fspath = new Path("/usr");
//		listFile(fspath);
	}

}
