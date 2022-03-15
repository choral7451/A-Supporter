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
public class SymphonysongCrawler {

	CrawlerDTO dto = new CrawlerDTO();

    @Autowired(required = false)
    private CrawlerMapper mapper;
    
    public void symphonysongCrawler() {
    	try {
    		String originalURL = "http://symphonysong.com/expo2/wizard/frames/server_sub.html?home_id=symphonysong&menu_seq=61&"
								+ "menu_seq_open=&tic=1631762326&siteId=symphonysong&SITE_ID=symphonysong&boardId=&BOARD_ID=";
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
					String title = elements.select("td:nth-child(3)").text();
					String tmpUrlFirst = "http://symphonysong.com/expo2/wizard/frames/server_sub.html?\r\n" + 
		            		"home_id=symphonysong&menu_seq=61&menu_seq_open=&tic=632040194\r\n" + 
		            		"&command=view&listhome_id=symphonysong&listhandle=4&handle=4\r\n" + 
		            		"&board_id=4&boardSeq=";
				    String tmpUrlSecond = elements.getElementsByAttribute("onclick").attr("onclick").substring(44, 47); // onclick의 고유번호 가져오기
				    String tmpUrlThird = "&connect_status=&connect_yn=&connect_site=\r\n" + 
				    						"&connect_board=&connect_seq=&userpw=&depth=&pos=";
				    String tmpUrlFourth = "&refSeq=";
				    String tmpUrlFifth = "&famSeq=&column=&search=&categoryId=&categoryDepth=&mcategory=\r\n" + 
				    						"&page=1&parent=Y&siteId=symphonysong&SITE_ID=symphonysong&boardId=4&BOARD_ID=4";
				    String url = tmpUrlFirst+tmpUrlSecond+tmpUrlThird+tmpUrlSecond+tmpUrlFourth+tmpUrlSecond+tmpUrlFifth;
					dto.setCname("심포니송");
		            dto.setTitle(title);
		            dto.setUrl(url);
		            dto.setDate(postedDate);
		            mapper.write(dto);
		            count++;
				}
			}
	        System.out.println("*-----------------------------------------*");
            System.out.println("|      심포니송     |"+"         " +count+"          |");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
