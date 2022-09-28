package br.com.sea.services;

import br.com.sea.dto.InsertWorkerDTO;
import br.com.sea.dto.WorkerDTO;
import br.com.sea.entities.Role;
import br.com.sea.entities.Sector;
import br.com.sea.entities.Worker;
import br.com.sea.repositories.WorkerRepository;
import br.com.sea.services.exceptions.DatabaseException;
import br.com.sea.services.exceptions.ResourceNotFoundException;
import org.hibernate.sql.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository repository;

    @Transactional(readOnly = true)
    public List<WorkerDTO> findAll() {
        List<Worker> list = repository.findAll();
        return list.stream().map(WorkerDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public WorkerDTO findByCpf(String cpf) {
        Optional<Worker> obj = repository.findByCpf(cpf);
        Worker entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
        return new WorkerDTO(entity);
    }



    @Transactional
    public InsertWorkerDTO insert(InsertWorkerDTO dto) {
        Worker entity = new Worker();
        entity.setCpf(dto.getCpf());
        entity.setName(dto.getName());
        Sector sector = new Sector();
        sector.setId(dto.getSectorId());
        Role role = new Role();
        role.setId(dto.getRoleId());
        entity.setSector(sector);
        entity.setRole(role);
        entity = repository.save(entity);
        return new InsertWorkerDTO(entity);
    }

    @Transactional
    public WorkerDTO update(String cpf, InsertWorkerDTO dto) {
        try {
            Optional<Worker> obj = repository.findByCpf(cpf);
            Worker entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
            entity.setName(dto.getName());
            entity.setName(dto.getName());

            entity = repository.save(entity);
            return new WorkerDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cpf não encontrado " + cpf);
        }
    }

    @Transactional
    public void delete(String cpf) {
        try {
            repository.deleteByCpf(cpf);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Cpf não encontrado " + cpf);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade");
        }
    }
}
