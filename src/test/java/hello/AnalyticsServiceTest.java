package hello;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	public void getPageName() throws IOException, InterruptedException {
		List<String> pageNames = new ArrayList<String>();
		pageNames.add("advisor &gt; INVESTMENTS");
		pageNames.add("products &gt; American Funds Insurance Series");
		List<String> dates = new ArrayList<String>();
		dates.add("2016-05-10");
		List<PageView> pageViews = service.getPageViews(dates, pageNames);
	    assertNotNull(pageViews);
	    assertFalse(pageViews.isEmpty());
	    PageView pageView = pageViews.iterator().next();
	    System.out.println("response = " + pageView.getCount());
	}

}
