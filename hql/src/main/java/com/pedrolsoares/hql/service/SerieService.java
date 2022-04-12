package com.pedrolsoares.hql.service;

import com.pedrolsoares.hql.dto.request.SerieDTO;
import com.pedrolsoares.hql.model.Genre;
import com.pedrolsoares.hql.model.Serie;
import com.pedrolsoares.hql.repository.SerieRepository;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SerieService {

    private final SerieRepository serieRepository;
    private final GenreService genreService;

    public Serie create(SerieDTO serieDTO){
        Genre genre = genreService.findGenreById(serieDTO.getGenreId());

        Serie serieToSave = new Serie(
                serieDTO.getTitle(),
                genre,
                serieDTO.getReleaseDate(),
                serieDTO.getEndDate()
        );

        return serieRepository.save(serieToSave);
    }

    public List<Serie> listAll(){

        return serieRepository.findAll();
    }

    public Serie findById(Long id){
        Optional<Serie> optionalSerie = serieRepository.findById(id);

        if(optionalSerie.isEmpty()){
            throw new PropertyNotFoundException("Serie with id " + id + " not found!");
        }

        return optionalSerie.get();
    }
}
