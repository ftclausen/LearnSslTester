package com.blackboard.mh;

import java.io.IOException;

public class SslTesterApp {

	public static void main(String[] args) {
    	String url = null;
    	if (args.length > 0) {
    		url = args[0];
    		if ( ! url.startsWith("https")) {
    			System.err.println("ERROR: This utility only checks \"https\" URLs");
    			System.exit(1);
    		}
    	} else {
    		System.err.println("Usage: sslTester.jar <URL>");
    		System.exit(1);
    	}
    	
    	SslTester target = new SslTester(url);
    	String header = null;
    	try {
			header = target.getHeader();
		} catch (IOException e) {
			System.err.println("ERROR :" + e.getMessage());
			System.exit(1);
		}
    	
    	if (header != null) {
    		System.out.println("SUCCESS: Connected to \"" + header + "\"");
    	}
	}
}
