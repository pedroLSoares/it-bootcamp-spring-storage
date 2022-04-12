package com.pedrolsoares.hql.controller;


import com.pedrolsoares.hql.model.Actor;
import com.pedrolsoares.hql.model.Movie;
import com.pedrolsoares.hql.dto.request.MovieDTO;
import com.pedrolsoares.hql.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<Object> createMovie(@RequestBody @Valid MovieDTO movieDTO, UriComponentsBuilder uriBuilder) {

        Movie created = movieService.createMovie(movieDTO);

        URI uri = uriBuilder.path("/api/v1/movies/{id}").buildAndExpand(created.getId()).toUri();


        return ResponseEntity.created(uri).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeMovie(@PathVariable Long id){
        Movie removed = movieService.removeMovieById(id);

        return ResponseEntity.ok(removed);
    }

    @GetMapping("/{id}/actors")
    public ResponseEntity<List<Actor>> listMovieActors(@PathVariable Long id){
        Movie movie = movieService.findMovieById(id);

        return ResponseEntity.ok(movie.getActors());
    }

    @GetMapping("/actors")
    public ResponseEntity<List<Movie>> listByActorRating(@RequestParam BigDecimal rating){
        List<Movie> movies = movieService.listAllByActorRating(rating);

        return ResponseEntity.ok(movies);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> list(@RequestParam Map<String, Object> params) {
        List<Movie> movies = params.isEmpty() ? movieService.listAll() : movieService.findWhere(params);

        return ResponseEntity.ok(movies);

    }
}
