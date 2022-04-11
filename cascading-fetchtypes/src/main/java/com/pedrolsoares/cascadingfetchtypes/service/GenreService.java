package com.pedrolsoares.cascadingfetchtypes.service;

import com.pedrolsoares.cascadingfetchtypes.model.Genre;
import com.pedrolsoares.cascadingfetchtypes.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Genre saveGenre(Genre genre){
        return genreRepository.save(genre);
    }

    public List<Genre> listAll(){
        return genreRepository.findAll();
    }

    public Genre findGenreById(Long id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);

        if(optionalGenre.isEmpty()) {
            throw new PropertyNotFoundException("Genre not found");
        }

        return optionalGenre.get();
    }

    public Genre removeGenreById(Long id) {
        Genre genre = findGenreById(id);

        genreRepository.deleteById(id);

        return genre;
    }
}
