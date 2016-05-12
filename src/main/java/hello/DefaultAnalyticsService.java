package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.adobe.analytics.client.AnalyticsClient;
import com.adobe.analytics.client.AnalyticsClientBuilder;
import com.adobe.analytics.client.ApiException;
import com.adobe.analytics.client.domain.ReportDescription;
import com.adobe.analytics.client.domain.ReportDescriptionElement;
import com.adobe.analytics.client.domain.ReportResponse;
import com.adobe.analytics.client.methods.ReportMethods;

@Service
public class DefaultAnalyticsService implements AnalyticsService {
	

	@Override
	public String queryDate(String date, String[] pageNames) throws IOException, InterruptedException {
	      Map<String, Object> map = new HashMap<String, Object>();
	      Map<String, Object> reportMap = new HashMap<String, Object>();
	      
	      reportMap.put("date", date);
	      reportMap.put("reportSuiteID", "");
	      Map<String, Object> metricMap = new HashMap<String, Object>();
	      metricMap.put("id", "pageViews");
	      ArrayList<Map<String,Object>> metricArray = new ArrayList<Map<String,Object>>();
	      metricArray.add(metricMap);
	      reportMap.put("metrics", metricArray.toArray());
	      Map<String, Object> elementMap = new HashMap<String, Object>();
	      elementMap.put("id", "page");
	      elementMap.put("selected", pageNames);
	      ArrayList<Map<String,Object>> elementArray = new ArrayList<Map<String,Object>>();
	      elementArray.add(elementMap);
	      reportMap.put("elements", elementArray.toArray());
	      map.put("reportDescription", reportMap);
	      String reportData = JSONObject.fromObject(map).toString();
	      String response = SiteCatalyst.callMethod("Report.Queue", reportData);
//	        JSONObject jsonObj = new JSONObject().getJSONObject(response);
//	        JSONArray jsonArry = jsonObj.getJSONArray("report_suites");
//
//	        for(int i = 0; i < jsonArry.length(); i++) {
//	            System.out.println("Report Suite ID: " + jsonArry.getJSONObject(i).get("rsid"));
//	            System.out.println("Site Title: " + jsonArry.getJSONObject(i).get("site_title"));
//	            System.out.println();
//	        }
	        
	       Thread.sleep(5000);
	       String response2 = SiteCatalyst.callMethod("Report.Get", response);
	       return response2;
	}
	
//	public String getJsonFromDate(String date, String[] pageNames) throws IOException, InterruptedException {
//		String response = queryDate(date, pageNames);
//	}
	
	public int getPageName(String date, List<String> pageNames, ReportMethods reportMethods) throws IOException, InterruptedException {
		ReportDescription desc = new ReportDescription();
		desc.setReportSuiteID(Constants.REPORT_SUITE_ID);
		desc.setDate(date);
		//desc.setDateFrom("2016-05-05");
		//desc.setDateTo("2016-05-10");
		desc.setMetricIds("pageViews");
		//desc.setElementIds("page");
		ReportDescriptionElement element = new ReportDescriptionElement();
		element.setSelected(pageNames);
		element.setId("page");
		// desc.setDateGranularity(ReportDescriptionDateGranularity.DAY);
		List<ReportDescriptionElement> elementList = new ArrayList<ReportDescriptionElement>();
		elementList.add(element);
		desc.setElements(elementList);
		int reportId = reportMethods.queue(desc);
		return reportId;
	}
	
	public PageView getPageViewForReportId(int reportId, String date, ReportMethods reportMethods) throws InterruptedException, IOException {
		ReportResponse response = getResponse(reportId, reportMethods);
		return new AnalyticsReportMapper().fromReportResponse(date, response);
	}
	
	public List<PageView> getPageViews(List<String> dates, List<String> pageNames) throws IOException, InterruptedException {
		AnalyticsClient client = getClient();
		ReportMethods reportMethods = new ReportMethods(client); //client is created as above
		List<PageView> pageViews = new ArrayList<PageView>();
		int[] reportIds = new int[dates.size()];
		int i = 0;
		for (String date : dates) {
			reportIds[i++] = getPageName(date, pageNames, reportMethods);
		}
		i = 0;
		for (String date : dates) {
			PageView pageView = getPageViewForReportId(reportIds[i++], date, reportMethods);
			pageViews.add(pageView);
		}
		return pageViews;
	}
	
	public AnalyticsReport getReport(List<String> dates, List<String> pageNames) throws IOException, InterruptedException {
		AnalyticsReport report = new AnalyticsReport();
		List<PageView> pageViews = getPageViews(dates, pageNames);
		report.setPageViews(pageViews);
		return report;
	}

	
	
	public ReportResponse getResponse(int reportId, ReportMethods reportMethods) throws InterruptedException, IOException {
		ReportResponse response = null;
		while (response == null) {
			try {
				response = reportMethods.get(reportId);
			} catch (ApiException e) {
				if ("report_not_ready".equals(e.getError())) {
					System.err.println("Report not ready yet.");
					Thread.sleep(3000);
					continue;
				}
				throw e;
			}
		}
		return response;
	}
	
	
	public AnalyticsClient getClient() {
		AnalyticsClientBuilder builder = new AnalyticsClientBuilder()
		.setEndpoint("api2.omniture.com")
		.authenticateWithSecret(Constants.USERNAME, Constants.PASSWORD);
		
		if (Constants.PROXY_ENABLED) {
			builder.withProxy(Constants.PROXY_NAME, Constants.PROXY_PORT);
		}
		
		return builder.build();
}
	
}
