package hello;

import static org.junit.Assert.assertNotNull;

import hello.SiteCatalyst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Ignore;
import org.junit.Test;

public class SiteCatalystTest {
		
	@Ignore @Test
	public void testCallMethod() throws IOException {
	      Map<String, Object> map = new HashMap<String, Object>();
	        map.put("rs_type", new String[]{"standard"});
	        map.put("sp", "");

	        String response = SiteCatalyst.callMethod("Company.GetReportSuites", JSONObject.fromObject(map).toString());
	        assertNotNull(response);
//	        JSONObject jsonObj = new JSONObject().getJSONObject(response);
//	        JSONArray jsonArry = jsonObj.getJSONArray("report_suites");
//
//	        for(int i = 0; i < jsonArry.length(); i++) {
//	            System.out.println("Report Suite ID: " + jsonArry.getJSONObject(i).get("rsid"));
//	            System.out.println("Site Title: " + jsonArry.getJSONObject(i).get("site_title"));
//	            System.out.println();
//	        }
	    }
	
//	{
//	    "reportDescription":{
//	        "reportSuiteID":"capgroupintstg",
//	        "date":"2016-05-10",
//	        "metrics":[
//	            {
//	                "id":"pageViews"
//	            }
//	        ],
//	        "elements":[
//	            {
//	                "id":"page",
//	                "selected":["advisor &gt; INVESTMENTS", "products &gt; American Funds Insurance Series®"]
//	            }
//	        ]
//	    }
//	}
	
	@Test
	public  void getPageNamesByDate()  throws IOException, InterruptedException {
		String[] pageNames = {"advisor &gt; INVESTMENTS", "products &gt; American Funds Insurance Series"};
		String date = "2016-05-10";
		String response = queryDate(date, pageNames);
	    assertNotNull(response);
	    System.out.println("response = " + response);
	}
	
		
	public String queryDate(String date, String[] pageNames) throws IOException, InterruptedException {
	      Map<String, Object> map = new HashMap<String, Object>();
	      Map<String, Object> reportMap = new HashMap<String, Object>();
	      
	      reportMap.put("date", date);
	      reportMap.put("reportSuiteID", "capgroupint");
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
	      System.out.println("reportData = " + reportData);
	      String response = SiteCatalyst.callMethod("Report.Queue", reportData);
	      assertNotNull(response);
//	        JSONObject jsonObj = new JSONObject().getJSONObject(response);
//	        JSONArray jsonArry = jsonObj.getJSONArray("report_suites");
//
//	        for(int i = 0; i < jsonArry.length(); i++) {
//	            System.out.println("Report Suite ID: " + jsonArry.getJSONObject(i).get("rsid"));
//	            System.out.println("Site Title: " + jsonArry.getJSONObject(i).get("site_title"));
//	            System.out.println();
//	        }
	        
	       Thread.sleep(5000);
		   System.out.println("responseJson = " + response);
	       String response2 = SiteCatalyst.callMethod("Report.Get", response);
	       return response2;
	        
	}
	
	
	@Ignore @Test
	public void reportDescription() throws IOException, InterruptedException {
	      Map<String, Object> map = new HashMap<String, Object>();
	      Map<String, Object> reportMap = new HashMap<String, Object>();
	      
	      reportMap.put("dateTo", "2016-05-10");
	      reportMap.put("dateFrom","2016-05-03");
	      reportMap.put("reportSuiteID", "capgroupintstg");
	      Map<String, Object> metricMap = new HashMap<String, Object>();
	      metricMap.put("id", "pageViews");
	      ArrayList<Map<String,Object>> metricArray = new ArrayList<Map<String,Object>>();
	      metricArray.add(metricMap);
	      reportMap.put("metrics", metricArray.toArray());
	      Map<String, Object> elementMap = new HashMap<String, Object>();
	      elementMap.put("id", "page");
	      elementMap.put("top", "8000");
	      ArrayList<Map<String,Object>> elementArray = new ArrayList<Map<String,Object>>();
	      elementArray.add(elementMap);
	      reportMap.put("elements", elementArray.toArray());
	      map.put("reportDescription", reportMap);
	      String reportData = JSONObject.fromObject(map).toString();
	      String response = SiteCatalyst.callMethod("Report.Queue", reportData);
	      assertNotNull(response);
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

	       assertNotNull(response2);
	        
	        
	    }
	
	
	

	@Ignore @Test
	public void testGetHeader() {
		String header = SiteCatalyst.getHeader();
		assertNotNull(header);
	}

}
