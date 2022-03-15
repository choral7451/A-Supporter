package com.example.asupporter.crawler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.asupporter.dao.CrawlerMapper;
import com.example.asupporter.dto.CrawlerDTO;

@Controller
public class GwangjuCitySymphonyOrchestraCrawler {

	CrawlerDTO dto = new CrawlerDTO();

    @Autowired(required = false)
    private CrawlerMapper mapper;
    
    public void gwangjuCitySymphonyOrchestraCrawler() {
		
    	try {
	    	String originalURL = "http://gjart.gwangju.go.kr/gso/cmd.do?opencode=pg_0501";
	    	Connection conn = Jsoup.connect(originalURL);
	        Document document = conn.get();
	        Elements element = document.select("tr");  
	        int count = 0;
	        
	        DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date());
	        String referenceDate = dataFormat.format(cal.getTime());
	        
	        for(Element elements : element) {
	        	String title = elements.select("td:nth-child(2)").text();
				if (title.length() > 2) {
					Date now = new Date();
					String tmpPostedYear = "20"+elements.select("td:nth-child(3)").text().substring(0,2)+"-"; // 편의를 위한 년도 하드코딩(20), 2100년부터는 에러 발생
					String currentYear = now.toString().substring(24)+"-";
					if (tmpPostedYear.equals(currentYear)) { // 현재 년도 비교
						String tmpPostedMonthDate = elements.select("td:nth-child(3)").text().substring(3);
						String postedDate = currentYear+tmpPostedMonthDate;
						if (referenceDate.compareTo(postedDate) <= 0) { // 기준일 이후일 때에는 데이터를 저장한다.
							String tmpUrlFirst = "http://gjart.gwangju.go.kr";
							String tmpUrlSecond = elements.getElementsByAttribute("href").attr("href");
							String URL = tmpUrlFirst+tmpUrlSecond;
							dto.setCname("광주시립교향악단");
							dto.setTitle(title);
							dto.setUrl(URL);
							dto.setDate(postedDate);
							mapper.write(dto);
							count++;
						} 
					}
				}
			}
	        System.out.println("*-----------------------------------------*");
            System.out.println("|      광주시립교향악단     |"+"         " +count+"          |");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
