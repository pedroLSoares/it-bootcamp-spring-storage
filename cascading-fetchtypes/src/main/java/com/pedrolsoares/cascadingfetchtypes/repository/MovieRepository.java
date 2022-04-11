package com.pedrolsoares.cascadingfetchtypes.repository;

import com.pedrolsoares.cascadingfetchtypes.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
