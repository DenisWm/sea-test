package br.com.sea.entities;

import br.com.sea.dto.WorkerDTO;
import br.com.sea.entities.pk.WorkerPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_worker")
public class Worker implements Serializable {

    @EmbeddedId
    private WorkerPK id = new WorkerPK();

    private String name;

    @Column(unique = true)
    private String cpf;

    public Worker() {
    }

    public Worker(Sector sector, Role role, String name, String cpf) {
        id.setSector(sector);
        id.setRole(role);
        this.name = name;
        this.cpf = cpf;
    }

    public Worker(WorkerDTO dto){
        id.setSector(dto.getSector());
        id.setRole(dto.getRole());
        this.name = dto.getName();
        this.cpf = dto.getCpf();
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

    public Sector getSector(){
        return id.getSector();
    }

    public void setSector(Sector sector){
        id.setSector(sector);
    }


    public Role getRole(){
        return id.getRole();
    }

    public void setRole(Role role){
        id.setRole(role);
    }
}
