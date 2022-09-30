package com.seasolutions.testjava.services;

import com.seasolutions.testjava.entities.Worker;
import com.seasolutions.testjava.repositories.WorkerRepository;
import com.seasolutions.testjava.services.dto.WorkerDTO;
import com.seasolutions.testjava.services.exceptions.DataIntegrityViolationException;
import com.seasolutions.testjava.services.exceptions.ResourceNotFoundException;
import com.seasolutions.testjava.services.mappers.WorkerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository repository;
    private final WorkerMapper mapper;

    public List<WorkerDTO> findAll(){
        return mapper.toDto(repository.findAll());
    }

    public WorkerDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).
                orElseThrow(() -> new ResourceNotFoundException("Worker not found!"));
    }

    public WorkerDTO save(WorkerDTO dto) {
        Worker entity = mapper.toEntity(dto);
        validationCpf(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    private void validationCpf(WorkerDTO dto) {
        Optional<Worker> obj = repository.findByCpf(dto.getCpf());
        if (obj.isPresent() && !Objects.equals(obj.get().getId(), dto.getId())) {
            throw new DataIntegrityViolationException("CPF already registered in the system!");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (ResourceNotFoundException resultadoEx) {
            throw new ResourceNotFoundException("Worker not found!");
        }
    }
}
