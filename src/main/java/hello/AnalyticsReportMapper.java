package hello;

import java.util.List;

import com.adobe.analytics.client.domain.ReportData;
import com.adobe.analytics.client.domain.ReportResponse;

public class AnalyticsReportMapper {
	
	public PageView fromReportResponse(String date, ReportResponse response) {
		PageView pageView = new PageView();
		pageView.setDate(date);
		List<ReportData> reportDataList = response.getReport().getData();
		String count = getPageViewCount(reportDataList);
		pageView.setCount(count);
		return pageView;
	}
	
	
	public String getPageViewCount(List<ReportData> reportDataList) {
		double count = 0.0;
		for (ReportData reportData : reportDataList) {
			count = count + getTotalCount(reportData.getCounts());
		}
		return String.valueOf(count);
	}
	
	
	public double getTotalCount(List<Double> counts) {
		double total = 0.0;
		for (Double count : counts) {
			total = total + count;
		}
		return total;
	}
}
