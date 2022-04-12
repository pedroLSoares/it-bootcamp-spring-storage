package com.pedrolsoares.hql.service;


import com.pedrolsoares.hql.model.Actor;
import com.pedrolsoares.hql.model.Movie;
import com.pedrolsoares.hql.repository.ActorRepository;
import com.pedrolsoares.hql.dto.request.ActorDTO;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ActorService {

    private final ActorRepository actorRepository;
    private final MovieService movieService;

    public Actor updateActor(Long id, ActorDTO actorDTO) {
        Actor actor = buildActor(actorDTO);
        actor.setId(id);

        return saveActor(actor);
    }

    public Actor createActor(ActorDTO actorDTO){
        Actor actorToSave = buildActor(actorDTO);

        return saveActor(actorToSave);
    }

    public Actor saveActor(Actor actor){
        return actorRepository.save(actor);
    }

    public List<Actor> listAll(){
        return actorRepository.findAll();
    }

    public List<Actor> listAllWithAssignedFavoriteMovie(){
        return actorRepository.findAllByFavoriteMovieIdIsNotNull();

    }

    public Actor findActorById(Long id) {
        Optional<Actor> optionalActor = actorRepository.findById(id);

        if(optionalActor.isEmpty()) {
            throw new PropertyNotFoundException("Actor not found");
        }

        return optionalActor.get();
    }

    public Actor removeActorById(Long id) {
        Actor actor = findActorById(id);

        actor.getMovies().forEach(m -> {
            m.getActors().remove(actor);
            movieService.saveMovie(m);
        });

        actorRepository.deleteById(id);

        return actor;
    }

    private Actor buildActor(ActorDTO actorDTO){
        Movie favoriteMovie = actorDTO.getFavoriteMovieId() != null ? movieService.findMovieById(actorDTO.getFavoriteMovieId()) : null;

        return new Actor(
                actorDTO.getFirstName(),
                actorDTO.getLastName(),
                actorDTO.getRating(),
                favoriteMovie
        );

    }


}
