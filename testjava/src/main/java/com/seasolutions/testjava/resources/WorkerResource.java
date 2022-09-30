package com.seasolutions.testjava.resources;

import com.seasolutions.testjava.services.WorkerService;
import com.seasolutions.testjava.services.dto.WorkerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workers")
public class WorkerResource {
    private final WorkerService service;

    @GetMapping
    public ResponseEntity<List<WorkerDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerDTO> findById(@PathVariable Long id){
        WorkerDTO workerDTO = service.findById(id);
        return ResponseEntity.ok().body(workerDTO);
    }

    @PostMapping
    public ResponseEntity<WorkerDTO> create(@RequestBody WorkerDTO dto) {
        dto = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<WorkerDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
