package br.com.sea.resources;

import br.com.sea.dto.InsertWorkerDTO;
import br.com.sea.dto.WorkerDTO;
import br.com.sea.services.WorkerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/workers")
@Api(tags = "Workers Resources")
public class WorkerResource {

    @Autowired
    private WorkerService service;

    @GetMapping
    @ApiOperation("Find all workers")
    public ResponseEntity<List<WorkerDTO>> findAll() {
        List<WorkerDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{cpf}")
    @ApiOperation("Find worker by cpf")
    public ResponseEntity<WorkerDTO> findById(@PathVariable String cpf) {
        WorkerDTO dto = service.findByCpf(cpf);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    @ApiOperation("Insert worker")
    public ResponseEntity<InsertWorkerDTO> insert(@Valid @RequestBody InsertWorkerDTO dto) {
        dto = service.insert(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{cpf}")
    @ApiOperation("Update worker")
    public ResponseEntity<WorkerDTO> update(@PathVariable String cpf, @RequestBody InsertWorkerDTO dto) {
        WorkerDTO obj = service.update(cpf, dto);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{cpf}")
    @ApiOperation("Delete worker")
    public ResponseEntity<Void> delete(@PathVariable String cpf) {
        service.delete(cpf);
        return ResponseEntity.noContent().build();
    }
}
