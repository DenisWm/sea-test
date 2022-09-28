package br.com.sea.repositories;

import br.com.sea.entities.Worker;
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
public class WorkerRepositoryTests {

    @Autowired
    private WorkerRepository repository;

    private String existingCpf;

    private String nonExistingCpf;

    private Integer countTotalWorkers;

    @BeforeEach
    void setUp() throws Exception {
        countTotalWorkers = 4;
        existingCpf = "1205236585";
        nonExistingCpf = "4987498549";
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {

        Worker Worker = Factory.createWorker();

        Worker = repository.save(Worker);
        Optional<Worker> result = repository.findByCpf(Worker.getCpf());

        Assertions.assertNotNull(Worker.getCpf());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertSame(result.get(), Worker);
    }

    @Test
    public void findAllShouldReturnListOfWorker() {

        List<Worker> Workers = repository.findAll();

        Assertions.assertEquals(countTotalWorkers, Workers.size());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        repository.deleteByCpf(existingCpf);

        Optional<Worker> result = repository.findByCpf(existingCpf);

        Assertions.assertFalse(result.isPresent() );
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteByCpf(nonExistingCpf);
        });
    }
}
