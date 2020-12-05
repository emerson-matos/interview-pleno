package br.com.brainweb.interview.core.features.hero;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hero")
public class HeroController {

    private HeroService heroService;
    private HeroAssembler heroAssembler;

    @Autowired
    public HeroController(HeroService heroService, HeroAssembler heroAssembler) {
        this.heroService = heroService;
        this.heroAssembler = heroAssembler;
    }

    @GetMapping
    public EntityModel<Hero> getAllHeroes() {
        return null;
    }

    @GetMapping("/{id}")
    public EntityModel<Hero> getOneHero(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Hero>> createHero(@RequestBody Hero hero) {
        Hero newHero = heroService.save(hero);
        return ResponseEntity //
                .created(linkTo(methodOn(HeroController.class).getOneHero(newHero.getId().toString())).toUri()) //
                .body(heroAssembler.toModel(newHero));
    }
}
