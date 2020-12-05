package br.com.brainweb.interview.core.features.powerstats;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/powerstats")
public class PowerStatsController {

    private final PowerStatsService service;
    private final PowerStatsAssembler assembler;

    @Autowired
    public PowerStatsController(PowerStatsService service, PowerStatsAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<PowerStats>>> getAllPowerStats() {
        List<EntityModel<PowerStats>> heroes =
                service.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        CollectionModel<EntityModel<PowerStats>> response = new CollectionModel<>(heroes, linkTo(methodOn(
                PowerStatsController.class).getAllPowerStats()).withSelfRel());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PowerStats>> getOnePowerStats(@PathVariable UUID id) {
        PowerStats ps = service.findOne(id);
        return ResponseEntity.ok().body(assembler.toModel(ps));
    }

    @PostMapping
    public ResponseEntity<EntityModel<PowerStats>> createPowerStats(@RequestBody PowerStats powerStats) {
        PowerStats newPowerStats = service.save(powerStats);
        return ResponseEntity
                .created(linkTo(methodOn(PowerStatsController.class).getOnePowerStats(newPowerStats.getId())).toUri())
                .body(assembler.toModel(newPowerStats));
    }
}
