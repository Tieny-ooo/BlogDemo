package com.example.mapper;


import com.example.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    void add(Article article);
    List<Article> selectAll();

    Article selectByArticleId(Integer articleId);
}
