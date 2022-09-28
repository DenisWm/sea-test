package br.com.sea.dto;

import br.com.sea.services.validation.Sector;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Sector
public class SectorDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 5, max = 120, message = "O nome deve conter entre 5 e 120 caracteres")
    private String name;


    public SectorDTO(){

    }

    public SectorDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public SectorDTO(br.com.sea.entities.Sector entity){
        id = entity.getId();
        name = entity.getName();
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
}
