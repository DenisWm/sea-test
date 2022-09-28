package br.com.sea.repositories;

import br.com.sea.entities.Role;
import br.com.sea.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository repository;

    private Integer existingId;
    private Integer nonExistingId;
    private Integer countTotalRoles;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1;
        nonExistingId = 1000;
        countTotalRoles = 4;
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {

        Role Role = Factory.createRole();
        Role.setId(null);

        Role = repository.save(Role);
        Optional<Role> result = repository.findById(Role.getId());

        Assertions.assertNotNull(Role.getId());
        Assertions.assertEquals(countTotalRoles + 1, Role.getId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertSame(result.get(), Role);
    }

    @Test
    public void findAllShouldReturnListOfRole() {

        List<Role> Roles = repository.findAll();

        Assertions.assertEquals(countTotalRoles, Roles.size());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        repository.deleteById(existingId);

        Optional<Role> result = repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }
}
