package com.seasolutions.testjava.services.dto;

import com.seasolutions.testjava.entities.Role;
import com.seasolutions.testjava.entities.Sector;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerDTO {

    private Long id;
    private String name;
    private String cpf;
    private Role role;
    private Sector sector;

    public WorkerDTO (){
    }

}
