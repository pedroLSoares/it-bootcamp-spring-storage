package com.pedrolsoares.jewelry.controller;


import com.pedrolsoares.jewelry.dto.JewelBodyDTO;
import com.pedrolsoares.jewelry.model.Jewel;
import com.pedrolsoares.jewelry.service.JewelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/jewels")
@AllArgsConstructor
public class JewelController {

    private final JewelService jewelService;

    @PostMapping
    public ResponseEntity<Object> insert(@RequestBody @Valid JewelBodyDTO newJewel) {
        Jewel created = jewelService.saveJewel(newJewel.dtoToModel());


        return new ResponseEntity<>(Map.of(
                "message", "Registro criado com sucesso",
                "id", created.getId()
        ), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Jewel>> listAll() {
        return ResponseEntity.ok(jewelService.listAll());
    }

    @DeleteMapping()
    public ResponseEntity<Object> remove(@RequestParam Long id) {
        Jewel removed = jewelService.removeJewel(id);

        return ResponseEntity.ok(removed);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody @Valid JewelBodyDTO jewel, @RequestParam Long id) {
        Jewel jewelToUpdate = jewel.dtoToModel();
        jewelToUpdate.setId(id);
        Jewel updated = jewelService.saveJewel(jewelToUpdate);

        return ResponseEntity.ok(updated);
    }
}
