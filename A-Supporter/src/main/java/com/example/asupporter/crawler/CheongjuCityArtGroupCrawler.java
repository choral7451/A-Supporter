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
public class CheongjuCityArtGroupCrawler {

	CrawlerDTO dto = new CrawlerDTO();

    @Autowired(required = false)
    private CrawlerMapper mapper;
    
    public void cheongjuCityArtGroupCrawler() {
    	try {
    		String originalURL = "http://www.cheongju.go.kr/ac/selectBbsNttList.do?key=16220&bbsNo=903&integrDeptCode=&searchCnd=SJ&searchKrwd=%EB%8B%A8%EC%9B%90&x=0&y=0";
			Connection conn = Jsoup.connect(originalURL);
	        Document document = conn.get();
	        Elements element = document.select("tr");  
	        int count = 0;
	        
	        DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date());
	        String referenceDate = dataFormat.format(cal.getTime());
	        
	        for(Element elements : element) {
	        	String title = elements.select("td:nth-child(3)").text();
	        	if (title.length() > 0) {
					String postedDate = elements.select("td:nth-child(7)").text().replace(".", "-");
					if (referenceDate.compareTo(postedDate) <= 0) { // 기준일 이후일 때에는 데이터를 저장한다.
						String tmpUrlFirst = "http://www.cheongju.go.kr/ac";
						String tmpUrlSecond = elements.getElementsByAttribute("href").attr("href").substring(1);
						String url = tmpUrlFirst+tmpUrlSecond;
						dto.setCname("청주시립예술단");
			            dto.setTitle(title);
			            dto.setUrl(url);
			            dto.setDate(postedDate);
			            System.out.println(dto);
			            mapper.write(dto);
			            count++;
					} 
	        	}
			}
	        System.out.println("*-----------------------------------------*");
            System.out.println("|     청주시립예술단     |"+"         " +count+"          |");
            System.out.println("*-----------------------------------------*");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
