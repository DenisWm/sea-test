package br.com.sea.dto;

import br.com.sea.entities.Role;
import br.com.sea.entities.Sector;
import br.com.sea.entities.Worker;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import java.io.Serializable;

public class WorkerDTO implements Serializable {

    private String name;
    private String cpf;
    private Sector sector;
    private Role role;

    public WorkerDTO() {
    }

    public WorkerDTO(String name, String cpf, Sector sector, Role role) {
        this.name = name;
        this.cpf = cpf;
        this.sector = sector;
        this.role = role;
    }

    public WorkerDTO(Worker entity) {
        name = entity.getName();
        cpf = entity.getCpf();
        sector = entity.getSector();
        role = entity.getRole();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @JsonIgnore
    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
