package com.example.asupporter.crawler;

import com.example.asupporter.dao.BoardMapper;
import com.example.asupporter.dto.BoardDTO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class BoardCrawler {
    BoardDTO dto = new BoardDTO();

    @Autowired(required = false)
    private BoardMapper mapper;

    public void boardCrawler() {
        try {
            String URL = "http://localhost:8080/admin/";
            Connection conn = Jsoup.connect(URL);

            Document document = conn.get();

            Elements tbody = document.select("tbody");
            Elements tr = tbody.select("tr");
            int count = 0;

            String nowDate = "2022-02-18";
//                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            for (int i = 1 ; i <= tr.size() ; i++) {
                String getType = tbody.select("tr:nth-child("+i+")").select("td:nth-child(2)").text();
                String getCname = tbody.select("tr:nth-child("+i+")").select("td:nth-child(3)").text();
                String getTitle = tbody.select("tr:nth-child(" + i + ")").select("td:nth-child(4)").select("a").text();
                String getDate = tbody.select("tr:nth-child("+i+")").select("td:nth-child(5)").text();
                String getURL = tbody.select("tr:nth-child(" + i + ")").select("td:nth-child(4)").select("a").attr("href");

                System.out.println(tbody.select("tr:nth-child(" + i + ")").select("td:nth-child(2)").select("input.changeInput"));

                dto.setType(getType);
                dto.setCname(getCname);
                dto.setTitle(getTitle);
                dto.setDate(getDate);
                dto.setUrl(getURL);
                mapper.write(dto);

                count++;

            }
            System.out.println("총 "+count+"건 저장되었습니다.");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
