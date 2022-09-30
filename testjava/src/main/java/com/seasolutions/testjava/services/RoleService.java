package com.seasolutions.testjava.services;

import com.seasolutions.testjava.entities.Role;
import com.seasolutions.testjava.repositories.RoleRepository;
import com.seasolutions.testjava.services.dto.RoleDTO;
import com.seasolutions.testjava.services.exceptions.ResourceNotFoundException;
import com.seasolutions.testjava.services.mappers.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;

    public List<RoleDTO> findAll(){
        return mapper.toDto(repository.findAll());
    }
    public RoleDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).
                orElseThrow(() -> new ResourceNotFoundException("Role not found!"));
    }
    public RoleDTO save(RoleDTO dto) {
        Role entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (ResourceNotFoundException resultadoEx) {
            throw new ResourceNotFoundException("Role not found!");
        }
    }

}
