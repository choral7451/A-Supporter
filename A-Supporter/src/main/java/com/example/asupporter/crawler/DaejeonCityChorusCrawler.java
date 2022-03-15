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
public class DaejeonCityChorusCrawler {

	CrawlerDTO dto = new CrawlerDTO();

    @Autowired(required = false)
    private CrawlerMapper mapper;
    
    public void daejeonCityChorusCrawler() {
    	try {
    		String originalURL = "https://djpc.artdj.kr/djpc/?a_idx=03_01_01";
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
	        	if (!title.contains("로그인 회원가입 ENG") && title.length() > 0) {
	        		Date now = new Date();
					String currentYear = now.toString().substring(24)+"-";
					String postedYear = "20"+elements.select("td:nth-child(3)").text().substring(0, 2)+"-";
					if (postedYear.equals(currentYear)) {
						String tmpPostedDate = elements.select("td:nth-child(3)").text().substring(3).replace(".", "-");
						String postedDate = postedYear+tmpPostedDate;
						if (referenceDate.compareTo(postedDate) <= 0) { // 기준일 이후일 때에는 데이터를 저장한다.
							String tmpUrlFirst = "https://djpc.artdj.kr/djpc";
							String tmpUrlSecond = elements.getElementsByAttribute("href").attr("href").substring(2);
							String url = tmpUrlFirst+tmpUrlSecond;
							dto.setCname("대전시립합창단");
							dto.setTitle(title);
							dto.setUrl(url);
							dto.setDate(postedDate);
							mapper.write(dto);
							count++;
						} 
					}
	        	}
			}
	        System.out.println("*-----------------------------------------*");
            System.out.println("|     대전시립합창단     |"+"         " +count+"          |");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
