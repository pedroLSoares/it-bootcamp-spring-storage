package com.pedrolsoares.hql.service;


import com.pedrolsoares.hql.model.Actor;
import com.pedrolsoares.hql.model.Genre;
import com.pedrolsoares.hql.model.Movie;
import com.pedrolsoares.hql.repository.ActorRepository;
import com.pedrolsoares.hql.repository.MovieRepository;
import com.pedrolsoares.hql.dto.request.MovieDTO;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreService genreService;
    private final ActorRepository actorRepository;
    private final EntityManager entityManager;

    public Movie createMovie(MovieDTO movie){
        Genre genre = genreService.findGenreById(movie.getGenreId());
        List<Actor> actors = actorRepository.findAllById(movie.getActorsIdList());

        Movie movieToSave = new Movie(
                movie.getTitle(),
                movie.getRating(),
                movie.getAwards(),
                movie.getReleaseDate(),
                movie.getLength(),
                actors,
                genre
        );

        return saveMovie(movieToSave);
    }

    public Movie saveMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public List<Movie> listAll(){
        return movieRepository.findAll();
    }

    public List<Movie> listAllByActorRating(BigDecimal rating) {
        return movieRepository.findAllByActorsRating(rating);
    }

    public Movie findMovieById(Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if(optionalMovie.isEmpty()) {
            throw new PropertyNotFoundException("Movie not found");
        }

        return optionalMovie.get();
    }

    public Movie removeMovieById(Long id) {
        Movie movie = findMovieById(id);

        movieRepository.deleteById(id);

        return movie;
    }

    public List<Movie> findWhere(Map<String, Object> params){


        String query = "SELECT e FROM Movie e";
        query = query.concat(" WHERE ");

        for(var param: params.entrySet()){

            String q = param.getKey() + " = '" + param.getValue() + "' AND ";
            query = query.concat(q);
        }

        query = query.replaceAll("AND $", "");


        return (List<Movie>) entityManager.createQuery(query).getResultList();



    }
}
