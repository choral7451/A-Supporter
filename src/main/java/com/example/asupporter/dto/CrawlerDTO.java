package com.example.asupporter.dto;

import lombok.Data;

@Data
public class CrawlerDTO {
    private int id;
    private String cname;
    private String title;
    private String date;
    private String url;
    private String type;
}
