package com.cardboardcode.util;

import java.io.File;

public class ApplicationPath {
	
	/**
	 * Fetch root application path
	 * 
	 * @return
	 */
	public static String getApplicationPath() {
		return getApplicationPath("");
	}
	
	/**
	 * Fetch path relative to the application path. Does not give any guarantees
	 * whether the requested path exists 
	 * 
	 * @param subPath
	 * @return
	 */
	public static String getApplicationPath(String subPath) {
		ClassLoader cl = ApplicationPath.class.getClassLoader();
		String applicationPath = new File(cl.getResource("").getPath()).getParent();
		
		if (subPath.isEmpty()) {
			return applicationPath;
		} else {
			return applicationPath + File.separator + subPath;
		}
		
	}
	
	/**
	 * Fetch File relative to the application path. Does not give any guarantees
	 * whether the requested path exists 
	 * 
	 * @param subPath
	 * @return
	 */
	public static File getApplicationFile(String subPath) {
		return new File(getApplicationPath(subPath));
	}
	
}
