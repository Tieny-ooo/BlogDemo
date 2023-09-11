package com.example.controller;


import com.example.pojo.Article;
import com.example.pojo.Result;
import com.example.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/add")
    public Result add(@RequestBody Article article,HttpSession session){
        Integer uid=(Integer) session.getAttribute("uid");
        article.setUid(uid);
        Date date=new Date();
        DateFormat df=new SimpleDateFormat("yyy-MM-dd  HH:mm:ss");
        article.setPublishedTime(df.format(date));
        try {
            articleService.add(article);
            return new Result(200,"文章发布成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(500,"文章发布失败",e);
        }

    }
    @RequestMapping("/articleAll")
    public Result selecAll(){
        try {
            List<Article> articles=articleService.selectAll();
            System.out.println(articles);
            return new Result(200,"查询成功",articles);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(500,"查询失败",e);
        }

    }
    @RequestMapping("/selectByArticleId")
    public Result selectByArticleId(String articleId){
        try {
            Article article = articleService.selectByArticleId(Integer.valueOf(articleId));
            return new Result(200,"查询成功",article);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return new Result(500,"查询失败",e);
        }
    }



}
