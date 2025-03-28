package com.demoblaze.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class LoggerUtility {



	private static final Logger logger = LogManager.getLogger(LoggerUtility.class);

	public void info(String message) {
		logger.info(message);
	}

	public void error(String message) {
		logger.error(message);
	}
}
