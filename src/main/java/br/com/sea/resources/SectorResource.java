package br.com.sea.resources;

import br.com.sea.dto.SectorDTO;
import br.com.sea.services.SectorService;
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
@RequestMapping(value = "/sectors")
@Api(tags = "Sectors Resources")
public class SectorResource {

    @Autowired
    private SectorService service;

    @GetMapping
    @ApiOperation("Find all sectors")
    public ResponseEntity<List<SectorDTO>> findAll() {
        List<SectorDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Find sector by id")
    public ResponseEntity<SectorDTO> findById(@PathVariable Integer id) {
        SectorDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    @ApiOperation("Insert sector")
    public ResponseEntity<SectorDTO> insert(@Valid @RequestBody SectorDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Update sector")
    public ResponseEntity<SectorDTO> update(@PathVariable Integer id, @RequestBody SectorDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Delete sector")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
