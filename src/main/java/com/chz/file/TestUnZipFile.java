package com.chz.file;

import java.io.InputStream;
import java.nio.file.Paths;

import com.chz.utils.FileUtils;

public class TestUnZipFile {

	public static void main(String[] args) throws Exception {
		InputStream inputStream = TestUnZipFile.class.getResourceAsStream("/com/chz/test.zip");
		FileUtils.unzip(inputStream, Paths.get("testzip"));

	}
}
