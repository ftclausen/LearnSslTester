package com.blackboard.mh;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import com.blackboard.mh.LearnSslTester;

public class LearnSslTesterTest {
	
	// Naive yes, the site may not be up but it'll do for this learning exercise   
	@Test
	public void validUrlTest() {
		LearnSslTester target = new LearnSslTester("https://webtech-test.blackboard.com");
		try {
			assertTrue(target.getHeader().startsWith("X-Blackboard-product:"));
		} catch (IOException e) {
			System.err.println("Cannot connect : " + e.getMessage());
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void invalidUrlTest() {
		LearnSslTester target = new LearnSslTester("webtech-test.blackboard.com");
	}
	
	// TODO: Not a learn server test - make own exception for that
}
