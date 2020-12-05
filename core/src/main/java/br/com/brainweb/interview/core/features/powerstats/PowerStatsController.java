package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/powerstats")
public class PowerStatsController {

    private PowerStatsService service;
    private PowerStatsAssembler assembler;

    @Autowired
    public PowerStatsController(PowerStatsService service, PowerStatsAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<PowerStats>> getAllPowerStats() {
        List<EntityModel<PowerStats>> heroes =
                service.findAll().stream()
                        .map(assembler::toModel).collect(Collectors.toList());
        return new CollectionModel<>(heroes, linkTo(methodOn(PowerStatsController.class).getAllPowerStats()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PowerStats>> getOnePowerStats(@PathVariable UUID id) {
        PowerStats ps = service.findOne(id);
        return ResponseEntity.ok().body(assembler.toModel(ps));
    }

    @PostMapping
    public ResponseEntity<EntityModel<PowerStats>> createHero(@RequestBody PowerStats powerStats) {
        PowerStats newPowerStats = service.save(powerStats);
        return ResponseEntity //
                .created(linkTo(methodOn(PowerStatsController.class).getOnePowerStats(newPowerStats.getId())).toUri()) //
                .body(assembler.toModel(newPowerStats));
    }
}
