package com.example.service.impl;

import com.example.mapper.ArticleMapper;
import com.example.pojo.Article;
import com.example.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        articleMapper.add(article);
    }

    @Override
    public List<Article> selectAll() {
        return null;
    }

    @Override
    public Article selectByArticleId(Integer articleId) {
        return articleMapper.selectByArticleId(articleId);
    }
}
