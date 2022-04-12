package com.pedrolsoares.hql.controller;


import com.pedrolsoares.hql.model.Genre;
import com.pedrolsoares.hql.dto.request.GenreDTO;
import com.pedrolsoares.hql.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
@AllArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid GenreDTO genreDTO, UriComponentsBuilder uriBuilder) {
        Genre created = genreService.saveGenre(genreDTO.dtoToModel());

        URI uri = uriBuilder.path("/api/v1/genres/{id}").buildAndExpand(created.getId()).toUri();


        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Genre>> list(){
        List<Genre> genres = genreService.listAll();

        return ResponseEntity.ok(genres);
    }

}
