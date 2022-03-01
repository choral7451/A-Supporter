package com.example.asupporter.dto;

import lombok.Data;

@Data
public class BoardDTO {
    private int id;
    private String cname;
    private String title;
    private String date;
    private String type;
    private String url;
}