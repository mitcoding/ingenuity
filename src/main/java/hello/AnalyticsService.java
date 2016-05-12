package hello;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public interface AnalyticsService {

	public String queryDate(String date, String[] pageNames) throws IOException, InterruptedException;

}
