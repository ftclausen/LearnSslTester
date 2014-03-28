package com.blackboard.mh;

import java.io.IOException;

/**
 * 
 * Author: Friedrich Clausen <friedrich.clausen@blackboard.com>
 * 
 * Super simple SSL tester for Blackboard Learn installations. This just
 * attempts to connect and retrieve the Learn version so verify the SSL side 
 * of things is working properly.
 * 
 *  Based off the example code at
 *  
 *  http://hc.apache.org/httpcomponents-client-ga/httpclient/examples/org/apache/http/examples/client/ClientWithResponseHandler.java
 *  
 */
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;



public class LearnSslTester {

	String url = null;
	
    public LearnSslTester(String url) {
    	if (url != null) {
    		this.url = url;
    		if ( ! url.startsWith("https")) {
    			throw new IllegalArgumentException("ERROR: This utility only checks \"https\" URLs");
    		}
    	} else {
    		throw new IllegalArgumentException("URL cannot be null");
    	}
    }
        
    public String getHeader() throws IOException {
    	CloseableHttpClient httpclient = HttpClients.createDefault();
    	String learnHeader = null;
    	
        try {
            HttpGet httpget = new HttpGet(url);
            
            // Create a response handler to return the Learn header
            ResponseHandler<String> getLearnHeader = new ResponseHandler<String>() {
            	public String handleResponse(
            			final HttpResponse response) throws ClientProtocolException, IOException {
            		int status = response.getStatusLine().getStatusCode();
            		if (status >= 200 && status < 300) {
            			String learnHeader = null;
            			try {
            				learnHeader = response.getFirstHeader("X-Blackboard-product").toString();
            			} catch (NullPointerException e) {
            				System.err.println("ERROR: This does not appear to be a Learn server");
            			}
            			if (learnHeader != null) {
            				return learnHeader.toString();
            			} else {
            				return null;
            			}
            		} else {
            			throw new ClientProtocolException("ERROR: Server error, received status " + status);
            		}
            	}
            };
            
            learnHeader = httpclient.execute(httpget, getLearnHeader);
            
        } catch (Exception e) {
        	System.err.println("ERROR: Could not connect : " + e.getMessage());
        } finally {
        	httpclient.close();
        }
        return learnHeader;
    }

}
