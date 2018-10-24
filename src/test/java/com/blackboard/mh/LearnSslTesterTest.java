package com.blackboard.mh;

import java.io.IOException;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import static org.junit.Assert.*;

import com.blackboard.mh.LearnSslTester;

public class LearnSslTesterTest {
	
	// Naive yes, the site may not be up but it'll do for this learning exercise   
	@Test
	public void validUrlTest() {
		LearnSslTester target = new LearnSslTester("https://bbuniversity.blackboard.com/webapps/portal/healthCheck");
		try {
			assertTrue(target.getHeader().startsWith("X-Blackboard-product:"));
		} catch (IOException e) {
			System.err.println("Cannot connect : " + e.getMessage());
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void invalidUrlTest() {
		LearnSslTester target = new LearnSslTester("bbuniversity.blackboard.com");
	}
	
	@Test(expected=ClientProtocolException.class)
	public void invalidRequest() throws IOException {
		LearnSslTester target = new LearnSslTester("https://files.blackboard.com/not_found_here");
		target.getHeader();
	}
}
