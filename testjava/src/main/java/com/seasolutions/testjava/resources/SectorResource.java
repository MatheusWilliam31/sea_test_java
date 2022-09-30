package com.seasolutions.testjava.resources;

import com.seasolutions.testjava.services.SectorService;
import com.seasolutions.testjava.services.dto.SectorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sectors")
public class SectorResource {

    private final SectorService service;

    @GetMapping
    public ResponseEntity<List<SectorDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SectorDTO> findById(@PathVariable Long id){
        SectorDTO sectorDTO = service.findById(id);
        return ResponseEntity.ok().body(sectorDTO);
    }

    @PostMapping
    public ResponseEntity<SectorDTO> create(@RequestBody SectorDTO dto) {
        dto = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SectorDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
