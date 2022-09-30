package com.seasolutions.testjava.builder;

import com.seasolutions.testjava.entities.Sector;
import com.seasolutions.testjava.repositories.SectorRepository;
import com.seasolutions.testjava.services.dto.SectorDTO;
import com.seasolutions.testjava.services.mappers.SectorMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Collection;

public class SectorBuilder extends ConstrutorDeEntidade<Sector> {

    @Autowired
    private SectorRepository repository;

    @Autowired
    private SectorMapper mapper;

    @Override
    protected Sector construirEntidade() {
        Sector sector = new Sector();
        sector.setName("RH");
        return sector;
    }

    @Override
    protected Sector persistir(Sector entidade) {
        return repository.save(entidade);
    }

    @Override
    protected Collection<Sector> obterTodos() {
        return repository.findAll();
    }

    @Override
    protected Sector obterPorId(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Sector construir() throws ParseException {
        return super.construir();
    }

    public SectorDTO construirDTO() throws ParseException {
        return mapper.toDto(construir());
    }

    public SectorDTO construirObjDTO() {
        return mapper.toDto(construirEntidade());
    }

    public void delete() {
        repository.deleteAll();
    }

}
