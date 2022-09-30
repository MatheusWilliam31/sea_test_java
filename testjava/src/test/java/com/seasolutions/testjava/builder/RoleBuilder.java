package com.seasolutions.testjava.builder;

import com.seasolutions.testjava.entities.Role;
import com.seasolutions.testjava.entities.Sector;
import com.seasolutions.testjava.repositories.RoleRepository;
import com.seasolutions.testjava.services.dto.RoleDTO;
import com.seasolutions.testjava.services.mappers.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Collection;

public class RoleBuilder extends ConstrutorDeEntidade<Role> {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private RoleMapper mapper;

    @Override
    protected Role construirEntidade()  {
        Role role = new Role();
        role.setName("Developer");
        Sector sector = new Sector();
        sector.setId(1L);
        role.setSector(sector);
        return role;
    }

    @Override
    protected Role persistir(Role entidade) {
        return repository.save(entidade);
    }

    @Override
    protected Collection<Role> obterTodos() {
        return repository.findAll();
    }

    @Override
    protected Role obterPorId(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Role construir() throws ParseException {
        return super.construir();
    }

    public RoleDTO construirDTO() throws ParseException {
        return mapper.toDto(construir());
    }

    public RoleDTO construirObjDTO() throws ParseException {
        return mapper.toDto(construirEntidade());
    }

    public void delete() {
        repository.deleteAll();
    }

}
