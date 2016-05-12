package hello;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.adobe.analytics.client.methods.ReportMethods;

@Service
public interface AnalyticsService {

	public String queryDate(String date, String[] pageNames) throws IOException, InterruptedException;
	
	public int getPageName(String date, List<String> pageNames, ReportMethods reportMethods) throws IOException, InterruptedException;
	
	public List<PageView> getPageViews(List<String> dates, List<String> pageNames) throws IOException, InterruptedException;
	
	public AnalyticsReport getReport(List<String> dates, List<String> pageNames) throws IOException, InterruptedException;
	
	// public String getAnalytics();

}
