package com.dailylog.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigProperties {

	private static final String DEFAULT_PROPERTY_FILE = "dailylog";
	private Properties properties = null;
	private static Map<String, ConfigProperties> instanceMap = new HashMap<String, ConfigProperties>();
	private String propertyFileName;
	private Log log = LogFactory.getLog(ConfigProperties.class);

	protected ConfigProperties(String propertyFileName) {
		this.propertyFileName = propertyFileName;
		loadProperties();
	}

	public static ConfigProperties getInstance() {
		return getInstance(DEFAULT_PROPERTY_FILE);
	}

	/**
	 * Retrieve the singleton instance of the configuration properties class.
	 * 
	 * @return ConfigProperties instance
	 */
	public static ConfigProperties getInstance(String propertyFileName) {
		if (instanceMap.get(propertyFileName) != null) {
			return (ConfigProperties) instanceMap.get(propertyFileName);
		}

		ConfigProperties instance = new ConfigProperties(propertyFileName);
		instanceMap.put(propertyFileName, instance);

		return instance;
	}

	/**
	 * Retrieves the property value as a String for the specified property name
	 * 
	 * @param propertyName
	 * @param defaultValue
	 * @return
	 */
	public String getPropertyAsString(String propertyName, String defaultValue) {
		try {
			if (properties == null) {
				loadProperties();
			}
			return properties.getProperty(propertyName, defaultValue);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public String getPropertyAsString(String propertyName) {
		return getPropertyAsString(propertyName, null);
	}

	/**
	 * Retrieves the property value as an integer for the specified property
	 * name
	 * 
	 * @param propertyName
	 * @param defaultValue
	 * @return
	 */
	public int getPropertyAsInt(String propertyName, int defaultValue) {
		try {
			if (properties == null) {
				loadProperties();
			}

			String sProperty = properties.getProperty(propertyName);

			return Integer.parseInt(sProperty);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public int getPropertyAsInt(String propertyName) {
		return getPropertyAsInt(propertyName, 0);
	}

	/**
	 * Loads the properties fromt he config file.
	 */
	protected void loadProperties() {
		try {
			properties = new Properties();

			ClassLoader oClassLoader = Thread.currentThread().getContextClassLoader();
			InputStream is = oClassLoader.getResourceAsStream(propertyFileName + ".properties");

			if (is != null) {
				properties.load(is);
				is.close();
			}

			is = null;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
