package com.hello.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtils {
	private static final Logger logger = LogManager.getFormatterLogger(FileUtils.class);

	public static void unzip(InputStream instream, Path targetPath) throws IOException {
		int len;
		byte[] buffer = new byte[1024];
		try (ZipInputStream zis = new ZipInputStream(instream)) {
			ZipEntry zipEntry = zis.getNextEntry();
			while (zipEntry != null) {
				if (!zipEntry.isDirectory()) {
					File newFile = targetPath.resolve(zipEntry.getName()).toFile();
					File parent = newFile.getParentFile();
					if (!parent.exists() && !parent.mkdirs()) {
						throw new IOException("Cannot create dir " + parent.getPath());
					}
					try (FileOutputStream fos = new FileOutputStream(newFile)) {
						while ((len = zis.read(buffer)) > 0) {
							fos.write(buffer, 0, len);
						}
					}
					logger.info("Successfully unzip file {} to {}", zipEntry.getName(), newFile);
				}
				zipEntry = zis.getNextEntry();
			}
		}
	}

}
