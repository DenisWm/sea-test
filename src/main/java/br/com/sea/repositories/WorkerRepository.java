package br.com.sea.repositories;

import br.com.sea.entities.Sector;
import br.com.sea.entities.Worker;
import br.com.sea.entities.pk.WorkerPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, WorkerPK> {

    public Optional<Worker> findByCpf(String cpf);
    public void deleteByCpf(String cpf);
}
