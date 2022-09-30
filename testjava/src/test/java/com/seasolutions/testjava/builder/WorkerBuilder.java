package com.seasolutions.testjava.builder;

import com.seasolutions.testjava.entities.Role;
import com.seasolutions.testjava.entities.Sector;
import com.seasolutions.testjava.entities.Worker;
import com.seasolutions.testjava.repositories.WorkerRepository;
import com.seasolutions.testjava.services.dto.WorkerDTO;
import com.seasolutions.testjava.services.mappers.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Collection;

public class WorkerBuilder extends ConstrutorDeEntidade<Worker> {

    @Autowired
    private WorkerRepository repository;

    @Autowired
    private WorkerMapper mapper;

    @Override
    protected Worker construirEntidade(){
        Worker worker = new Worker();
        worker.setCpf("649.777.170-07");
        worker.setName("Alan Turing");
        Sector sector = new Sector();
        sector.setId(1L);
        Role role = new Role();
        role.setId(1L);
        worker.setSector(sector);
        worker.setRole(role);
        return worker;
    }

    @Override
    protected Worker persistir(Worker entidade) {
        return repository.save(entidade);
    }

    @Override
    protected Collection<Worker> obterTodos() {
        return repository.findAll();
    }

    @Override
    protected Worker obterPorId(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Worker construir() throws ParseException {
        return super.construir();
    }

    public WorkerDTO construirDTO() throws ParseException {
        return mapper.toDto(construir());
    }

    public WorkerDTO construirObjDTO() {
        return mapper.toDto(construirEntidade());
    }

    public void delete() {
        repository.deleteAll();
    }

}
