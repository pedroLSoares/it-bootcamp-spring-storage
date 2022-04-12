package com.pedrolsoares.hql.controller;


import com.pedrolsoares.hql.model.Episode;
import com.pedrolsoares.hql.service.EpisodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/series/episodes")
@AllArgsConstructor
public class EpisodeController {

    private final EpisodeService service;

    @GetMapping
    public ResponseEntity<List<Episode>> list(@RequestParam Map<String, Object> params) {

        List<Episode> episodes = service.listAll();

        return ResponseEntity.ok(episodes);
    }

    @GetMapping("/actor/{actorId}")
    public ResponseEntity<List<Episode>> listByActor(@PathVariable Long actorId){
        List<Episode> episodes = service.listAllByActorId(actorId);

        return ResponseEntity.ok(episodes);
    }
}
