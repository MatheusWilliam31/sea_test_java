package com.seasolutions.testjava.services;

import com.seasolutions.testjava.entities.Sector;
import com.seasolutions.testjava.entities.Worker;
import com.seasolutions.testjava.repositories.SectorRepository;
import com.seasolutions.testjava.services.dto.SectorDTO;
import com.seasolutions.testjava.services.dto.WorkerDTO;
import com.seasolutions.testjava.services.exceptions.DataIntegrityViolationException;
import com.seasolutions.testjava.services.exceptions.ResourceNotFoundException;
import com.seasolutions.testjava.services.mappers.SectorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository repository;
    private final SectorMapper mapper;

    public List<SectorDTO> findAll(){
        return mapper.toDto(repository.findAll());
    }

    public SectorDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).
                orElseThrow(() -> new ResourceNotFoundException("Sector not found!"));
    }

    public SectorDTO save(SectorDTO dto) {
        Sector entity = mapper.toEntity(dto);
        validationName(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (ResourceNotFoundException resultadoEx) {
            throw new ResourceNotFoundException("Sector not found!");
        }
    }

    private void validationName(SectorDTO dto) {
        Optional<Sector> obj = repository.findByName(dto.getName());
        if (obj.isPresent() && !Objects.equals(obj.get().getId(), dto.getId())) {
            throw new DataIntegrityViolationException("Sector already registered in the system!");
        }
    }

}
