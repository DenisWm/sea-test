package br.com.sea.dto;

import br.com.sea.entities.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class RoleDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 5, max = 120, message = "O nome deve conter entre 5 e 120 caracteres")
    private String name;

    private SectorDTO sector;

    public RoleDTO() {
    }

    public RoleDTO(Integer id, String name, SectorDTO sector) {
        this.id = id;
        this.name = name;
        this.sector = sector;
    }

    public RoleDTO(Role entity) {
        id = entity.getId();
        name = entity.getName();
        sector = new SectorDTO(entity.getSector());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SectorDTO getSector() {
        return sector;
    }

    public void setSector(SectorDTO sector) {
        this.sector = sector;
    }
}
