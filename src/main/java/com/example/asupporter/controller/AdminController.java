package com.example.asupporter.controller;

import com.example.asupporter.crawler.BoardCrawler;
import com.example.asupporter.crawler.MainCrawler;
import com.example.asupporter.dao.CrawlerMapper;
import com.example.asupporter.dto.CrawlerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
    @Autowired(required=false)
    private MainCrawler crawler;

    @Autowired(required = false)
    private CrawlerMapper crawlerMapper;

    @Autowired(required = false)
    private BoardCrawler boardCrawler;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("list", crawlerMapper.list());
        return "admin";
    }

    @RequestMapping(value = "/admin/crawler.do", method = RequestMethod.GET)
    public String crawler() {
        crawlerMapper.deleteAll();
        crawler.mainCrawler();
        return "redirect:";
    }

    @RequestMapping(value = "/admin/delete.do", method = RequestMethod.GET)
    public String adminDelete(CrawlerDTO dto) {
        dto.setId(dto.getId());
        crawlerMapper.delete(dto);
        return "redirect:";
    }

    @RequestMapping(value = "/admin/save.do", method = RequestMethod.GET)
    public String adminSave() {
        boardCrawler.boardCrawler();
        return "redirect:../recruit";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.GET)
    public String adminUpdate(Model model, int id) {
        model.addAttribute("list", crawlerMapper.updateList(id));
        return "update";
    }

    @RequestMapping(value = "/admin/update.do", method = RequestMethod.POST)
    public String adminUpdateDo(CrawlerDTO dto) {
        System.out.println();
        crawlerMapper.update(dto);
        return "redirect:";
    }

    @RequestMapping(value = "/admin/cancel.do", method = RequestMethod.GET)
    public String adminCancelDo() {
        return "redirect:";
    }

}

