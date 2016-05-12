package hello;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AnalyticsServiceTest {
	
	private AnalyticsService service;
	
	@Before
	public void setUp() {
		this.service = new DefaultAnalyticsService();
	}
	
	@After
	public void tearDown() {
		service = null;
	}
	
	@Test
	public void getAnalytics() {
		String response = service.getAnalytics();
		assertNotNull(response);
		System.out.println(response);
	}

}
