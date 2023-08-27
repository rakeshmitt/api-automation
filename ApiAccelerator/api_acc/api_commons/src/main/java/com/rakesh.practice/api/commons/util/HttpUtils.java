package com.rakesh.practice.api.commons.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import com.rakesh.practice.api.commons.model.RemoteServiceResponse;

/**
 * Various Http helper routines
 */
public class HttpUtils {
	
	private static final Logger logger = LogManager.getLogger(HttpUtils.class);

	
	public static String urlEncode(String url, boolean keepPathSlash) {
		String encoded;
		try {
			encoded = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UTF-8 encoding is not supported.", e);
		}
		if (keepPathSlash) {
			encoded = encoded.replace("%2F", "/");
		}
		return encoded;
	}
	
}
