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
public class SuwonCityChorusCrawler {

	CrawlerDTO dto = new CrawlerDTO();

    @Autowired(required = false)
    private CrawlerMapper mapper;
    
    public void suwonCityChorusCrawler() {
		
    	try {
	    	String originalURL = "http://www.artsuwon.or.kr/?p=49";
			Connection conn = Jsoup.connect(originalURL);
	        Document document = conn.get();
	        Elements element = document.select("tr");  
	        int count = 0;
	        
	        DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date());
	        String referenceDate = dataFormat.format(cal.getTime());
	        
			for(Element elements : element) {
				String postedDate = elements.select("td:nth-child(5)").text();
				String postNo = elements.select("td:nth-child(1)").text(); // 공지사항이 번호인 것만 받을 수 있게한다. 상단 고정 공지와의 중복 저장 방지
				if (referenceDate.compareTo(postedDate) <= 0 && postNo != "공지") { // 기준일 이후일 때에는 데이터를 저장한다.
					String title = elements.getElementsByClass("subject").text();
					String tmpUrl = elements.getElementsByAttribute("href").attr("href");
					String URL = originalURL+tmpUrl;
					dto.setCname("수원시립합창단");
		            dto.setTitle(title);
		            dto.setUrl(URL);
		            dto.setDate(postedDate);
					mapper.write(dto);
					count++;
				} 
			}
            System.out.println("|      수원시립합창단     |"+"         " +count+"          |");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
