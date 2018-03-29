package com.cardboardcode.util;

import java.io.File;

public class NativeLibraryPath {
	
	/**
	 * Set the native library paths that are required; requires the native.library.path 
	 * property/VM argument to be set to the native-libs directory containing the platform
	 * native libraries.
	 */
	public static void setLibraryPath() {
		
		// Get library path from VM arguments
		String nativeLibraryPath = System.getProperty("native.library.path");
		if (nativeLibraryPath == null) {
			System.err.println("Failed to set native library path, as it was not supplied. Use java -Dnative.library.path=/path/to/native-libs -jar MainClass to set it, or add it to the VM arguments");
			return;
		}
		
		// Get platform directory within the native library path
		String platformDir = getPlatformDir();
		if (platformDir == null) {
			return;
		}
		
		// Set JNA library path
		File jnaLibraryPath = new File(nativeLibraryPath + File.separator + platformDir);
		if (jnaLibraryPath.exists() && jnaLibraryPath.isDirectory()) {
			System.setProperty("jna.library.path", jnaLibraryPath.getAbsolutePath());
		} else {
			System.err.println("Failed to locate JNA library path; expected it at '" + jnaLibraryPath.getAbsolutePath() + "'");
		}
		
		// Set GStreamer library path
		File gStreamerLibraryPath = new File(nativeLibraryPath + File.separator + platformDir);
		if (gStreamerLibraryPath.exists() && gStreamerLibraryPath.isDirectory()) {
			System.setProperty("gstreamer.library.path", gStreamerLibraryPath.getAbsolutePath());
		} else {
			System.err.println("Failed to locate GStreamer library path; expected it at '" + gStreamerLibraryPath.getAbsolutePath() + "'");
		}
		
		// Set GStreamer plugin path
		File gStreamerPluginPath = new File(nativeLibraryPath + File.separator + platformDir + File.separator + "plugins");
		if (gStreamerPluginPath.exists() && gStreamerPluginPath.isDirectory()) {
			System.setProperty("gstreamer.plugin.path", gStreamerPluginPath.getAbsolutePath());
		} else {
			System.err.println("Failed to locate GStreamer plugin path; expected it at '" + gStreamerPluginPath.getAbsolutePath() + "'");
		}

	}
	
	/**
	 * Get the directory for the current platform native libraries
	 * or null if the platform is not supported
	 * 
	 * @return
	 */
	private static String getPlatformDir() {
		
		// Fetch reported OS and architecture
		String reportedOsName = System.getProperty("os.name").toLowerCase();
		String reportedOsArch = System.getProperty("os.arch").toLowerCase();
		
		// Find library paths supported platforms
		if (reportedOsName.startsWith("win")) {
			
			// Windows platforms
			if (reportedOsArch.equals("amd64")) {
				return "windows64";
			} else if (reportedOsArch.equals("x86")) {
				return "windows32";
			}
			
		} else if (reportedOsName.startsWith("mac")) {
			
			// MacOS/OSX platforms
			if (reportedOsArch.equals("amd64")) {
				return "macosx64";
			}
			
		} else if (reportedOsName.startsWith("linux")) {
			
			// Linux platforms
			if (reportedOsArch.equals("amd64") || reportedOsArch.equals("x86_64")) {
				return "linux64";
			} else if (reportedOsArch.equals("x86") || reportedOsArch.equals("i386") || reportedOsArch.equals("i686")) {
				return "linux32";
			} else if (reportedOsArch.equals("arm")) {
				return "linux-armv6hf";
			}
		}
		
		System.err.println("Warning: possibly unsupported platform " + reportedOsName + " " + reportedOsArch);
		return null;
	}

}
