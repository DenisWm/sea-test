package br.com.sea.services;

import br.com.sea.dto.SectorDTO;
import br.com.sea.entities.Sector;
import br.com.sea.repositories.SectorRepository;
import br.com.sea.services.exceptions.DatabaseException;
import br.com.sea.services.exceptions.ResourceNotFoundException;
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
public class SectorService {

    @Autowired
    private SectorRepository repository;

    @Transactional(readOnly = true)
    public List<SectorDTO> findAll() {
        List<Sector> list = repository.findAll();
        return list.stream().map(SectorDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SectorDTO findById(Integer id) {
        Optional<Sector> obj = repository.findById(id);
        Sector entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
        return new SectorDTO(entity);
    }

    @Transactional
    public SectorDTO insert(SectorDTO dto) {
        Sector entity = new Sector();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new SectorDTO(entity);
    }

    @Transactional
    public SectorDTO update(Integer id, SectorDTO dto) {
        try {
            Sector entity = repository.getOne(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new SectorDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado " + id);
        }
    }

    @Transactional
    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id não encontrado " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade");
        }
    }
}
