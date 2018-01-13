package com.dsw.getback.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.dsw.getback.constants.Constants;
import com.dsw.getback.dao.LoginLogDaoTest;

public class FileUtils {
	private static Logger logger = LogManager.getLogger(LoginLogDaoTest.class);

	public static byte[] file2Bytes(String filePath, String patType) {

		Resource res = null;
		if (patType.equals(Constants.TYPE_PATH_CLASSPATH)) {
			res = new ClassPathResource(filePath);
		} else if (patType.equals(Constants.TYPE_PATH_FILESYSTEM)) {
			res = new FileSystemResource(filePath);
		} else {
			logger.error("path type is not exsts, default use FileSystem Type");
			res = new FileSystemResource(filePath);
		}
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		InputStream is = null;
		byte[] buffer = new byte[1024];
		try {
			is = res.getInputStream();
			int len = 0;
			while (-1 != (len = is.read(buffer, 0, 1024))) {
				bos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}

		return bos.toByteArray();
	}

}
