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
public class UlsanCrawler {
    CrawlerDTO dto = new CrawlerDTO();

    @Autowired(required = false)
    private CrawlerMapper mapper;

    public void ulsanCrawler() {
        try {
            String URL = "https://ucac.ulsan.go.kr/spp/bbs/BBS_000000000000001/list/postList.do?searchCondition=0&searchKeyword=%EB%8B%A8%EC%9B%90&bbsId=BBS_000000000000001&bbsType=list&pageIndex=1";
            Connection conn = Jsoup.connect(URL);

            Document document = conn.get();

            Elements ul = document.select("ul.table_list");
            Elements li = ul.select("li");
            int count = 0;

            DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date());
	        String referenceDate = dataFormat.format(cal.getTime());

            for (int i = 7 ; i < 12 ; i++) {
                String postedDate = ul.select("li:nth-child(" + i + ")").first().children().select("p:nth-child(4)").text();
                if (referenceDate.compareTo(postedDate) <= 0) {
                    String getTitle = ul.select("li:nth-child(" + i + ")").first().children().select("p:nth-child(2)").select("a").text();
                    String getURL = ul.select("li:nth-child(" + i + ")").first().children().select("p:nth-child(2)").select("a").attr("href");

                    dto.setCname("울산시립예술단");
                    dto.setTitle(getTitle);
                    dto.setDate(postedDate);
                    dto.setUrl(getURL);
                    mapper.write(dto);

                    count++;
                }
            }
            System.out.println("*-----------------------------------------*");
            System.out.println("|     울산시립예술단    |"+"         " +count+"           |");
            System.out.println("*-----------------------------------------*");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
