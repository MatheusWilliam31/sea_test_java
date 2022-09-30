package com.seasolutions.testjava.services.mappers;

import com.seasolutions.testjava.entities.Worker;
import com.seasolutions.testjava.services.dto.WorkerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerMapper extends EntityMapper<WorkerDTO, Worker>{
}
