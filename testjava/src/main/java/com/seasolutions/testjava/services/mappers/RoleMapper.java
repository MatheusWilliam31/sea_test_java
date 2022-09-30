package com.seasolutions.testjava.services.mappers;

import com.seasolutions.testjava.entities.Role;
import com.seasolutions.testjava.services.dto.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role>{
}
