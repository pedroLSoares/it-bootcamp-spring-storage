package com.pedrolsoares.hql.repository;


import com.pedrolsoares.hql.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    List<Actor> findAllByFavoriteMovieIdIsNotNull();
}
