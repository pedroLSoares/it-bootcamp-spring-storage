package com.pedrolsoares.hql.service;

import com.pedrolsoares.hql.dto.request.SeasonDTO;
import com.pedrolsoares.hql.model.Episode;
import com.pedrolsoares.hql.model.Season;
import com.pedrolsoares.hql.model.Serie;
import com.pedrolsoares.hql.repository.SeasonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SeasonService {

    private final SeasonRepository seasonRepository;
    private final EpisodeService episodeService;

    public List<Season> createMany(Serie serie, List<SeasonDTO> seasonDTOList){
        List<Season> seasons = seasonDTOList
                .stream()
                .map(s -> {
                    Season seasonToSave = new Season(
                            s.getTitle(),
                            s.getNumber(),
                            s.getReleaseDate(),
                            s.getEndDate(),
                            new ArrayList<>(),
                            serie);

                    Season saved = seasonRepository.save(seasonToSave);

                    if(!s.getEpisodes().isEmpty()){
                        List<Episode> episodesCreated = episodeService.createMany(saved, s.getEpisodes());
                        saved.setEpisodes(episodesCreated);
                    }

                    return saved;
                })
                .collect(Collectors.toList());

        return seasonRepository.saveAll(seasons);
    }

}
