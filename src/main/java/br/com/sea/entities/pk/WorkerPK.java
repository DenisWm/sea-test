package br.com.sea.entities.pk;

import br.com.sea.entities.Role;
import br.com.sea.entities.Sector;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class WorkerPK implements Serializable {

    @OneToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public WorkerPK() {
    }

    public WorkerPK(Sector sector, Role role) {
        this.sector = sector;
        this.role = role;
    }

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
