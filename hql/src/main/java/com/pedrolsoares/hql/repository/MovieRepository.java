package com.pedrolsoares.hql.repository;


import com.pedrolsoares.hql.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT u FROM Movie u WHERE u.actors IN (SELECT a FROM Actor a WHERE a.rating >= :rating)")
    List<Movie> findAllByActorsRating(BigDecimal rating);
//
//    @Query("SELECT u FROM Movie u WHERE (:query)")
//    List<Movie> findAllWhere(String query);
}
