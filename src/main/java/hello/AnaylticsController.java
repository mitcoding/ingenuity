package hello;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class AnaylticsController {
	
	@Autowired
	private AnalyticsService service;

    @RequestMapping("/analytics")
    public String index() throws IOException, InterruptedException {
		String[] pageNames = {"advisor &gt; INVESTMENTS", "products &gt; American Funds Insurance Series"};
		String date = "2016-05-10";
		String response = service.queryDate(date, pageNames);
		return response;
    }

}
