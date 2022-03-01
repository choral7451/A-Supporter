package com.example.asupporter.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainCrawler {

    @Autowired(required = false)
    private NckCrawler nck;

    @Autowired(required = false)
    private BusanCrawler busan;

    @Autowired(required = false)
    private UlsanCrawler ulsan;

    @Autowired(required = false)
    private KBSCrawler kbs;

    public void mainCrawler() {
        busan.busanCrawler();
        nck.nckCrawler();
        kbs.kbsCrawler();
        ulsan.ulsanCrawler();

    }
}
