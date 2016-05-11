package com.ingenuity;

import static org.junit.Assert.assertNotNull;

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
	
	@Test
	public void reportDescription() throws IOException, InterruptedException {
	      Map<String, Object> map = new HashMap<String, Object>();
	      Map<String, Object> reportMap = new HashMap<String, Object>();
	      
	      reportMap.put("dateTo", "2016-05-10");
	      reportMap.put("dateFrom","2016-05-03");
	      reportMap.put("reportSuiteID", "");
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

	        String response = SiteCatalyst.callMethod("Report.Queue", JSONObject.fromObject(map).toString());
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
