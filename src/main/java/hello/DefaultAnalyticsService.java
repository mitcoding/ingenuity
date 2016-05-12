package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.adobe.analytics.client.AnalyticsClient;
import com.adobe.analytics.client.AnalyticsClientBuilder;
import com.adobe.analytics.client.domain.CompanyReportSuite;
import com.adobe.analytics.client.domain.CompanyReportSuites;
import com.adobe.analytics.client.methods.ReportSuiteMethods;

@Service
public class DefaultAnalyticsService implements AnalyticsService {
	
    private static String USERNAME = "";
    private static String PASSWORD = "";
	
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
	
	@Override
	public String getAnalytics() {

		AnalyticsClient client = new AnalyticsClientBuilder()
		.setEndpoint("api2.omniture.com")
		.authenticateWithSecret(USERNAME, PASSWORD)
		.withProxy("", 0)
		.build();

		StringBuilder builder = new StringBuilder();

		ReportSuiteMethods suiteMethods = new ReportSuiteMethods(client); //client is created as above
		CompanyReportSuites reportSuites;
		try {
			reportSuites = suiteMethods.getReportSuites();
			for (CompanyReportSuite suite : reportSuites.getReportSuites()) {
				System.out.println(suite.toString());
				builder.append(suite.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return builder.toString();
	}
}
