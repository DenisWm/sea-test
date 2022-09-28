package br.com.sea.repositories;

import br.com.sea.entities.Role;
import br.com.sea.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
