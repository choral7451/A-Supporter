package com.example.asupporter.dao;

import com.example.asupporter.dto.CrawlerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CrawlerMapper {
    public List<CrawlerDTO> list();
    public List<CrawlerDTO> updateList(int id);
    public void update(CrawlerDTO dto);
    public void deleteAll();
    public void write(CrawlerDTO dto);
    public void delete(CrawlerDTO dto);
}
