package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
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
@RequestMapping("/heroes")
public class HeroController {

    private HeroService heroService;
    private HeroAssembler heroAssembler;

    @Autowired
    public HeroController(HeroService heroService, HeroAssembler heroAssembler) {
        this.heroService = heroService;
        this.heroAssembler = heroAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Hero>> getAllHeroes(@RequestParam(required = false) String name) {
        List<EntityModel<Hero>> heroes = heroService.findAll(name).stream()
                .map(heroAssembler::toModel).collect(Collectors.toList());

        return new CollectionModel<>(heroes,
                linkTo(methodOn(HeroController.class).getAllHeroes(null)).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Hero>> getOneHero(@PathVariable UUID id) {
        Hero hero = heroService.findHeroBy(id);
        return ResponseEntity.ok().body(heroAssembler.toModel(hero));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Hero>> createHero(@RequestBody Hero hero) {
        Hero newHero = heroService.save(hero);
        return ResponseEntity.created(linkTo(methodOn(HeroController.class).getOneHero(newHero.getId())).toUri())
                .body(heroAssembler.toModel(newHero));
    }
}
