package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AnaylticsController {
	
	@Autowired
	private AnalyticsService service;

    @RequestMapping("/analytics")
    public AnalyticsReport index() throws IOException, InterruptedException {
		List<String> pageNames = new ArrayList<String>();
		pageNames.add("advisor &gt; INVESTMENTS");
		pageNames.add("products &gt; American Funds Insurance Series®");
		pageNames.add("news &gt; Capital Gain Distributions for AMBAL, FI and ICA");
		pageNames.add("market-commentary &gt; Positioning Income-Oriented Portfolios for Rising Rates");
		List<String> dates = new ArrayList<String>();
		dates.add("2016-01-04");
		dates.add("2016-01-05");
		dates.add("2016-01-06");
		dates.add("2016-01-07");
		dates.add("2016-01-08");
		// PageView pageView = service.getPageName(date, pageNames);
		// List<PageView> pageViews = service.getPageViews(dates, pageNames);
		AnalyticsReport report = service.getReport(dates, pageNames);
		return report;
    }

}
