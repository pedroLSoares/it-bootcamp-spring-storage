package com.pedrolsoares.hql.controller;

import com.pedrolsoares.hql.dto.request.SerieDTO;
import com.pedrolsoares.hql.model.Serie;
import com.pedrolsoares.hql.service.SerieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/series")
@AllArgsConstructor
public class SerieController {

    private final SerieService service;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid SerieDTO serieDTO, UriComponentsBuilder uriBuilder) {
        Serie created = service.create(serieDTO);

        URI uri = uriBuilder.path("api/v1/series/{id}").buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Serie>> list(@RequestParam Map<String, Object> params) {

        List<Serie> series = params.isEmpty() ? service.listAll() : service.findWhere(params);

        return ResponseEntity.ok(series);
    }
}
