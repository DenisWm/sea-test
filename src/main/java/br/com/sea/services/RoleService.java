package br.com.sea.services;

import br.com.sea.dto.RoleDTO;
import br.com.sea.entities.Role;
import br.com.sea.entities.Sector;
import br.com.sea.repositories.RoleRepository;
import br.com.sea.repositories.SectorRepository;
import br.com.sea.services.exceptions.AssignmentRoleException;
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
public class RoleService {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private SectorRepository sectorRepository;

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        List<Role> list = repository.findAll();
        return list.stream().map(RoleDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Integer id) {
        Optional<Role> obj = repository.findById(id);
        Role entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
        return new RoleDTO(entity);
    }

    @Transactional
    public RoleDTO insert(RoleDTO dto) {
        Role entity = new Role();
        entity.setName(dto.getName());
        Sector sector = sectorRepository.getOne(dto.getSector().getId());
        entity.setSector(sector);
        entity = repository.save(entity);
        return new RoleDTO(entity);
    }

    @Transactional
    public RoleDTO update(Integer id, RoleDTO dto) {
        try {
            Role entity = repository.getOne(id);

            if (!entity.getSector().getId().equals(dto.getSector().getId())) {
                throw new AssignmentRoleException("O cargo não pode ser atribuído a outro setor");
            }

                entity.setName(dto.getName());
                Sector sector = sectorRepository.getOne(dto.getSector().getId());
                entity.setSector(sector);
                entity = repository.save(entity);

            return new RoleDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado " + id);
        } catch (AssignmentRoleException e) {
            throw new DatabaseException(e.getMessage());
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
