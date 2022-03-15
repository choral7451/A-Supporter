package com.example.asupporter.crawler;

import com.example.asupporter.dao.CrawlerMapper;
import com.example.asupporter.dto.CrawlerDTO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class BusanCrawler {
    CrawlerDTO dto = new CrawlerDTO();

    @Autowired(required = false)
    private CrawlerMapper mapper;

    public void busanCrawler() {
        try {
            String URL = "https://www.bscc.or.kr/05_community/?mcode=0405010000&mode=1&no=&page=1&hd=%EC%B1%84%EC%9A%A9%EA%B3%B5%EA%B3%A0&mgb=&kd=all&kw=%EB%8B%A8%EC%9B%90";
            Connection conn = Jsoup.connect(URL);

            Document document = conn.get();

            Elements tbody = document.select("tbody");
            Elements tr = tbody.select("tr");
            int count = 0;

            DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date());
	        String referenceDate = dataFormat.format(cal.getTime());

            for (int i = 1 ; i <= tr.size() ; i++) {
                String postedDate = tbody.select("tr:nth-child("+i+")").select("td:nth-child(6)").text();
                if (referenceDate.compareTo(postedDate) <= 0) {
                    String getTitle = tbody.select("tr:nth-child("+i+")").select("td:nth-child(2)").text();
                    String getURL = tbody.select("tr:nth-child(" + i + ")").select("td:nth-child(2)").select("a").attr("href");

                    dto.setCname("부산시립예술단");
                    dto.setTitle(getTitle);
                    dto.setDate(postedDate);
                    dto.setUrl("https://www.bscc.or.kr/"+getURL);
                    mapper.write(dto);

                    count++;
                }
            }
            System.out.println("*-----------------------------------------*");
            System.out.println("|        단체명        |       데이터 수      |");
            System.out.println("*-----------------------------------------*");
            System.out.println("|     부산시립예술단     |"+"         " +count+"          |");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

