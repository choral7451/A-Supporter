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
public class NckCrawler {
    CrawlerDTO dto = new CrawlerDTO();

    @Autowired(required=false)
    private CrawlerMapper mapper;

    public void nckCrawler() {
        try {
            String URL = "http://nationalchorus.or.kr/notice-2/?mode=list&board_name=notice_new&order_by=fn_pid&order_type=desc&category1=&category2=&category3=&search_field=fn_title&search_text=%EB%8B%A8%EC%9B%90";
            Connection conn = Jsoup.connect(URL);

            Document document = conn.get();

            Elements tbody = document.select("tbody#notice_new_board_body");
            Elements tr = tbody.select("tr");
            int count = 0;
            for (int i = 1 ; i <= tr.size() ; i++) {
                String nowDate = "2022-02-22";
//                        LocalDateTime.now().minusDays(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String getDate = tbody.select("tr#mb_notice_new_tr_"+i).select("td:nth-child(4)").select("span").text();
                if ( getDate.equals(nowDate)) {
                    String getTitle = tbody.select("tr#mb_notice_new_tr_" + i).select("td:nth-child(2)").select("a").attr("title");
                    String getURL = tbody.select("tr#mb_notice_new_tr_" + i).select("td:nth-child(2)").select("a").attr("href");

                    dto.setCname("국립합창단");
                    dto.setTitle(getTitle);
                    dto.setDate(getDate);
                    dto.setUrl(getURL);
                    mapper.write(dto);
                    count++;
                }
            }
            System.out.println("*-----------------------------------------*");
            System.out.println("|       국립합창단      |"+"         " +count+"          |");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
