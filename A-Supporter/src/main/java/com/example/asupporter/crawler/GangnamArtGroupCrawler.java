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
public class GangnamArtGroupCrawler {

	CrawlerDTO dto = new CrawlerDTO();

    @Autowired(required = false)
    private CrawlerMapper mapper;
    
    public void gangnamArtGroupCrawler() {
    	try {
	    	String originalURL = "https://www.gangnam.go.kr/office/gfac/board/gfac_staffrec/list.do?mid=gfac_staffRec";
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
				if (referenceDate.compareTo(postedDate) <= 0) { // 기준일 이후일 때에는 데이터를 저장한다.
					String title = elements.select("td:nth-child(2)").text();
					String tmpUrlFirst = "https://www.gangnam.go.kr";
					String tmpUrlSecond = elements.getElementsByAttribute("href").attr("href");
					String url = tmpUrlFirst+ tmpUrlSecond;
					dto.setCname("강남문화재단");
		            dto.setTitle(title);
		            dto.setUrl(url);
		            dto.setDate(postedDate);
		            mapper.write(dto);
		            count++;
				}
			}
	        System.out.println("*-----------------------------------------*");
            System.out.println("|      강남문화재단     |"+"         " +count+"          |");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
