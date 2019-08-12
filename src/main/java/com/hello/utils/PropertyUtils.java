package com.hello.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

	private static PropertyUtils instance = new PropertyUtils();
	private Properties prop = new Properties();

	private PropertyUtils() {
		try (InputStream inputStream = PropertyUtils.class.getResourceAsStream("/hello.properties")) {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PropertyUtils getInstance() {
		return instance;
	}

	public Properties getProperties() {
		return prop;
	}

}
