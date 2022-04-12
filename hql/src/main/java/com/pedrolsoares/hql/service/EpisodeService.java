package com.pedrolsoares.hql.service;

import com.pedrolsoares.hql.dto.request.EpisodeDTO;
import com.pedrolsoares.hql.model.Actor;
import com.pedrolsoares.hql.model.Episode;
import com.pedrolsoares.hql.model.Season;
import com.pedrolsoares.hql.repository.ActorRepository;
import com.pedrolsoares.hql.repository.EpisodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EpisodeService {

    private final EpisodeRepository episodeRepository;
    private final ActorRepository actorRepository;
    private final ActorService actorService;

    public List<Episode> createMany(Season season, List<EpisodeDTO> episodes) {
        List<Episode> episodesToSave = episodes.stream().map(e -> {
            List<Actor> actors = actorRepository.findAllById(e.getActorsIdList());


            return new Episode(
                    e.getTitle(),
                    e.getNumber(),
                    e.getReleaseDate(),
                    season,
                    actors
            );
        }).collect(Collectors.toList());

        return episodeRepository.saveAll(episodesToSave);
    }

    public List<Episode> listAll(){
        return episodeRepository.findAll();
    }

    public List<Episode> listAllByActorId(Long actorId){
        Actor actor = actorService.findActorById(actorId);

        return actor.getEpisodes();
    }


}
