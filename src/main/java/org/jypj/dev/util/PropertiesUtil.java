package org.jypj.dev.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	private Properties p = new Properties(); 

	public PropertiesUtil(String propertiesName) {
		InputStream in = this.getClass().getClassLoader()
				.getResourceAsStream(propertiesName);
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getValue(String key) {
		return p.getProperty(key);
	}
}
