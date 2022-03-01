package com.example.asupporter.controller;

import com.example.asupporter.dao.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class MainController {
    @Autowired(required = false)
    private BoardMapper boardMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/recruit", method = RequestMethod.GET)
    public String recruit(Model model, HttpServletRequest req) {
        model.addAttribute("countPage", boardMapper.countList());
        if(req.getParameter("id") != null) {
            int queryString = Integer.parseInt(req.getParameter("id"));
            int num1 = boardMapper.countList() - ((queryString - 1) * 10);
            int num2 = num1-9;
            if(num2 <= 0) {
                num2 = 1;
            }
            model.addAttribute("pageList", boardMapper.pageList(num1, num2));
        } else if (req.getParameter("search") != null) {
            boardMapper.deleteAll();
            int countList = boardMapper.searchCountList(req.getParameter("search"));
            ArrayList<String> searchGetId = boardMapper.searchList(req.getParameter("search"));

            for (int i = 0 ; i < countList ; i++ ) {
                boardMapper.saveId(searchGetId.get(i));
            }
        }  else  {
            model.addAttribute("pageList", boardMapper.pageList(boardMapper.countList(), boardMapper.countList()-9));
        }

        return "recruit";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "about";
    }
}

