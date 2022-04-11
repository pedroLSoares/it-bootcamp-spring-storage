package com.pedrolsoares.cascadingfetchtypes.controller;

import com.pedrolsoares.cascadingfetchtypes.dto.request.ActorDTO;
import com.pedrolsoares.cascadingfetchtypes.model.Actor;
import com.pedrolsoares.cascadingfetchtypes.service.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/actors")
@AllArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid ActorDTO actorDTO, UriComponentsBuilder uriBuilder){
        Actor created = actorService.createActor(actorDTO);

        URI uri = uriBuilder.path("/api/v1/actors/{id}").buildAndExpand(created.getId()).toUri();


        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid ActorDTO actorDTO){
        Actor updated = actorService.updateActor(id, actorDTO);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable Long id){
        Actor removed = actorService.removeActorById(id);

        return ResponseEntity.ok(removed);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable Long id) {
        Actor found = actorService.findActorById(id);

        return ResponseEntity.ok(found);
    }

    @GetMapping
    public ResponseEntity<List<Actor>> listAll(){
        List<Actor> actorList = actorService.listAll();

        return ResponseEntity.ok(actorList);
    }
}
