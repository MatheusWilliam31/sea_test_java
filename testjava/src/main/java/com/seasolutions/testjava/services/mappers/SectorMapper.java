package com.seasolutions.testjava.services.mappers;

import com.seasolutions.testjava.entities.Sector;
import com.seasolutions.testjava.services.dto.SectorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SectorMapper extends EntityMapper<SectorDTO, Sector> {
}
