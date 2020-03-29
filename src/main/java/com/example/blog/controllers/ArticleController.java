package com.example.blog.controllers;

import com.example.blog.models.Article;
import com.example.blog.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository repository;

    @GetMapping("/articles")
    public List<Article> list() {
        return repository.findAll();
    }

    @GetMapping("/articles/{id}")
    public Article findById(@PathVariable("id") Integer id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/articles")
    public Article create(@RequestParam("title") String title,
                          @RequestParam("body") String body) {
        Calendar calendar = Calendar.getInstance();

        Article article = new Article();
        article.setTitle(title);
        article.setBody(body);
        article.setCreateDate(calendar.getTime());

        return repository.save(article);
    }

    @PutMapping("/articles/{id}")
    public Article update(@PathVariable("id") Integer id,
                          @Valid @RequestParam("title") String title,
                          @RequestParam("body") String body) {
        Optional<Article> optionalArticle = repository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setTitle(title);
            article.setBody(body);
            return repository.save(article);
        } else {
            return null;
        }
    }

    @DeleteMapping("/articles/{id}")
    public void delete(@PathVariable("id") Integer id) {
        Optional<Article> optionalArticle = repository.findById(id);
        optionalArticle.ifPresent((article) -> repository.delete(article));
    }
}
