package br.com.sea.repositories;

import br.com.sea.entities.Sector;
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
public class SectorRepositoryTests {

    @Autowired
    private SectorRepository repository;

    private Integer existingId;
    private Integer nonExistingId;
    private Integer countTotalSectors;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1;
        nonExistingId = 1000;
        countTotalSectors = 2;
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {

        Sector sector = Factory.createSector();
        sector.setId(null);

        sector = repository.save(sector);
        Optional<Sector> result = repository.findById(sector.getId());

        Assertions.assertNotNull(sector.getId());
        Assertions.assertEquals(countTotalSectors + 1, sector.getId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertSame(result.get(), sector);
    }

    @Test
    public void findAllShouldReturnListOfSector() {

        List<Sector> sectors = repository.findAll();

        Assertions.assertEquals(countTotalSectors, sectors.size());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        repository.deleteById(existingId);

        Optional<Sector> result = repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }
}
