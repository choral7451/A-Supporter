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

@Controller
public class KBSCrawler {
    CrawlerDTO dto = new CrawlerDTO();

    @Autowired(required = false)
    private CrawlerMapper mapper;

    public void kbsCrawler() {
        try {
            String URL = "https://www.kbssymphony.org/ko/info/recruit.php";
            Connection conn = Jsoup.connect(URL);

            Document document = conn.get();

            Elements tbody = document.select("tbody");
            Elements tr = tbody.select("tr");
            int count = 0;

            String nowDate = "2021-10-21";
//                    LocalDateTime.now().minusDays(6).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            for (int i = 1 ; i <= tr.size() ; i++) {
                String getDate = tbody.select("tr:nth-child("+i+")").select("td:nth-child(3)").text();
                if ( getDate.equals(nowDate)) {
                    String getTitle = tbody.select("tr:nth-child("+i+")").select("td:nth-child(2)").text();
                    String getURL = tbody.select("tr:nth-child(" + i + ")").select("td:nth-child(2)").select("a").attr("href");

                    dto.setCname("KBS교향악단");
                    dto.setTitle(getTitle);
                    dto.setDate(getDate);
                    dto.setUrl("https://www.kbssymphony.org/"+getURL);
                    mapper.write(dto);

                    count++;
                }
            }
            System.out.println("*-----------------------------------------*");
            System.out.println("|      KBS교향악단     |"+"         " +count+"          |");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

