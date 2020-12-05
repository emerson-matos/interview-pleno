package br.com.brainweb.interview.core.features.hero;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final HeroService heroService;
    private final HeroAssembler heroAssembler;

    @Autowired
    public HeroController(HeroService heroService, HeroAssembler heroAssembler) {
        this.heroService = heroService;
        this.heroAssembler = heroAssembler;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Hero>>> getAllHeroes(
            @RequestParam(required = false) String name
                                                                          ) {
        List<EntityModel<Hero>> heroes =
                heroService.findAll(name).stream().map(heroAssembler::toModel).collect(Collectors.toList());
        CollectionModel<EntityModel<Hero>> entityModels =
                new CollectionModel<>(heroes, linkTo(methodOn(HeroController.class).getAllHeroes(null)).withSelfRel());
        return ResponseEntity.ok().body(entityModels);
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

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<Hero>> updateHero(
            @PathVariable UUID id, @RequestBody Map<String, String> heroMap
                                                       ) {
        Hero hero = heroService.findHeroBy(id);
        hero = heroService.patchHero(hero, heroMap);
        Hero updatedHero = heroService.update(hero);
        return ResponseEntity.accepted().lastModified(hero.getUpdatedAt().toInstant())
                .location(linkTo(methodOn(HeroController.class).getOneHero(updatedHero.getId())).toUri())
                .body(heroAssembler.toModel(updatedHero));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<Hero>> eraseHero(@PathVariable UUID id) {
        Hero hero = heroService.findHeroBy(id);
        heroService.delete(hero);
        return ResponseEntity.noContent().build();
    }
}
