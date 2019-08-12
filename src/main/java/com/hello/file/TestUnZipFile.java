package com.hello.file;

import java.io.InputStream;
import java.nio.file.Paths;

import com.hello.utils.FileUtils;

public class TestUnZipFile {

	public static void main(String[] args) throws Exception {
		InputStream inputStream = TestUnZipFile.class.getResourceAsStream("/com/hello/test.zip");
		FileUtils.unzip(inputStream, Paths.get("testzip"));

	}
}
