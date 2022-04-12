package com.pedrolsoares.esarticles.repository;

import com.pedrolsoares.esarticles.domain.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article, Integer> {
}
