package com.hello.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class LearnFile {
	private static final String path = "/zhaochunhui/workspace/a.txt";

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		File file1 = new File(path);

		File file2 = Paths.get("/zhaochunhui/workspace", "a.txt").toFile();
		File file22 = Paths.get(path).toFile();

		Path path = Paths.get("/zhaochunhui/workspace");
		File file3 = path.resolve("a.txt").toFile();

	}

	// @Test
	public void test() {
		try (InputStream inputStream = LearnFile.class.getResourceAsStream("/hello.properties")) {
			Properties properties = new Properties();
			properties.load(inputStream);
			properties.forEach((key, value) -> {
				System.out.println(key);
				System.out.println(value);
			});
		} catch (IOException e) {
		}
	}

	@SuppressWarnings("resource")
	public static void method() throws IOException {
		InputStream inputStream = LearnFile.class.getResourceAsStream("/hello.properties");
		byte[] buffer = new byte[8];
		int len;
		File file = new File("/zhaochunhui/file/file.txt");
		FileOutputStream out = new FileOutputStream(file);
		while ((len = inputStream.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
	}

}
