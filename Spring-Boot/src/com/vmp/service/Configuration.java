package com.vmp.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	private static Configuration instance = new Configuration();
	private Properties properties;
	
	private Configuration() {
		initProperties();
	}
	
	public void initProperties() {
		properties = new Properties();
		FileInputStream stream;
		try {
			stream = new FileInputStream("./conf/config.ini");
			properties.load(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String get(String key) {
		return instance.properties.getProperty(key);
	}
}
