package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public EntityModel<PowerStats> getAllHeroes() {
        return null;
    }

    @GetMapping("/{id}")
    public EntityModel<PowerStats> getOneHero(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<EntityModel<PowerStats>> createHero(@RequestBody PowerStats powerStats) {
        PowerStats newPowerStats = service.save(powerStats);
        return ResponseEntity //
                .created(linkTo(methodOn(PowerStatsController.class).getOneHero(newPowerStats.getId().toString())).toUri()) //
                .body(assembler.toModel(newPowerStats));
    }
}
