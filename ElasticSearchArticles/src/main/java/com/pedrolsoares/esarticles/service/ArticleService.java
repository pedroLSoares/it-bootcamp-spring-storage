package com.pedrolsoares.esarticles.service;

import com.pedrolsoares.esarticles.domain.Article;
import com.pedrolsoares.esarticles.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article createArticle(Article article){
        return articleRepository.save(article);
    }
}
