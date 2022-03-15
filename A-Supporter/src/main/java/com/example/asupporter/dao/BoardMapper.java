package com.example.asupporter.dao;

import com.example.asupporter.dto.BoardDTO;
import com.example.asupporter.dto.CrawlerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BoardMapper {
    public int countList();
    public void write(BoardDTO dto);
    public List<CrawlerDTO> pageList(int num1, int num2);

    public int searchCountList(String search);
    public ArrayList<String> searchList(String search);
    public void deleteAll();
    public void saveId(String searchGetId);
}
