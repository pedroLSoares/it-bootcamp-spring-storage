package com.pedrolsoares.cascadingfetchtypes.repository;

import com.pedrolsoares.cascadingfetchtypes.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
