package br.com.sea.dto;

import br.com.sea.entities.Role;
import br.com.sea.entities.Sector;
import br.com.sea.entities.Worker;
import br.com.sea.services.validation.WorkerInsert;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@WorkerInsert
public class InsertWorkerDTO implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 5, max = 120, message = "o nome deve conter entre 5 e 120 caracteres")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cpf;
    private Integer sectorId;
    private Integer roleId;

    public InsertWorkerDTO() {
    }

    public InsertWorkerDTO(String name, String cpf, Integer sectorId, Integer roleId) {
        this.name = name;
        this.cpf = cpf;
        this.sectorId = sectorId;
        this.roleId = roleId;
    }

    public InsertWorkerDTO(Worker entity) {
        name = entity.getName();
        cpf = entity.getCpf();
        sectorId = entity.getSector().getId();
        roleId = entity.getRole().getId();
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

    public Integer getSectorId() {
        return sectorId;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
