package com.pedrolsoares.cascadingfetchtypes.service;

import com.pedrolsoares.cascadingfetchtypes.dto.request.MovieDTO;
import com.pedrolsoares.cascadingfetchtypes.model.Actor;
import com.pedrolsoares.cascadingfetchtypes.model.Genre;
import com.pedrolsoares.cascadingfetchtypes.model.Movie;
import com.pedrolsoares.cascadingfetchtypes.repository.ActorRepository;
import com.pedrolsoares.cascadingfetchtypes.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreService genreService;
    private final ActorRepository actorRepository;

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
}
