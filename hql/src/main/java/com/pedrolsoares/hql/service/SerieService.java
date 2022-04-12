package com.pedrolsoares.hql.service;

import com.pedrolsoares.hql.dto.request.SerieDTO;
import com.pedrolsoares.hql.model.*;
import com.pedrolsoares.hql.repository.SerieRepository;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SerieService {

    private final SerieRepository serieRepository;
    private final GenreService genreService;
    private final EntityManager entityManager;
    private final SeasonService seasonService;

    public Serie create(SerieDTO serieDTO){
        Genre genre = genreService.findGenreById(serieDTO.getGenreId());

        Serie serieToSave = new Serie(
                serieDTO.getTitle(),
                genre,
                serieDTO.getReleaseDate(),
                serieDTO.getEndDate()
        );

        Serie saved = serieRepository.save(serieToSave);

        if(!serieDTO.getSeasons().isEmpty()){
            List<Season> createdSeasons = seasonService.createMany(saved, serieDTO.getSeasons());
            saved.setSeasons(createdSeasons);
        }


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

    public List<Serie> findWhere(Map<String, Object> params){
        String query = "SELECT e FROM Serie e";
        query = query.concat(" WHERE ");

        for(var param: params.entrySet()){
            if(param.getKey().contains("seasons")) {
                String q = "(SELECT COUNT(*) FROM Season s WHERE serie_id = e.id) >= " + param.getValue();
                query = query.concat(q);
                continue;
            }

            String q = param.getKey() + " = '" + param.getValue() + "' AND ";
            query = query.concat(q);
        }

        query = query.replaceAll("AND $", "");


        return (List<Serie>) entityManager.createQuery(query).getResultList();



    }
}
