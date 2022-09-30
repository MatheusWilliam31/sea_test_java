package com.seasolutions.testjava.services.dto;

import com.seasolutions.testjava.entities.Role;
import com.seasolutions.testjava.entities.Sector;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {

    private Long id;
    private String name;
    private Sector sector;

    public RoleDTO (){

    }
}
