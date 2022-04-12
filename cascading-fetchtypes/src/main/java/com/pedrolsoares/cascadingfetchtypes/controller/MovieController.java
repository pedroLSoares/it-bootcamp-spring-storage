package com.pedrolsoares.cascadingfetchtypes.controller;

import com.pedrolsoares.cascadingfetchtypes.dto.request.MovieDTO;
import com.pedrolsoares.cascadingfetchtypes.model.Movie;
import com.pedrolsoares.cascadingfetchtypes.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<Object> createMovie(@RequestBody @Valid MovieDTO movieDTO, UriComponentsBuilder uriBuilder) {

        Movie created = movieService.createMovie(movieDTO);

        URI uri = uriBuilder.path("/api/v1/movies/{id}").buildAndExpand(created.getId()).toUri();


        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Movie>> list(){
        List<Movie> movies = movieService.listAll();

        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeMovie(@PathVariable Long id){
        Movie removed = movieService.removeMovieById(id);

        return ResponseEntity.ok(removed);
    }
}
