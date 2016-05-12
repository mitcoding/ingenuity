package hello;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsReport {
	
	private List<PageView> pageViews = new ArrayList<PageView>();
	
	public void setPageViews(List<PageView> pageViews) {
		this.pageViews = pageViews;
	}

	public void addPageView(PageView pageView) {
		this.pageViews.add(pageView);
	}
	
	public List<PageView> getPageViews() {
		return pageViews;
	}
}
