package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class HeroAssembler implements RepresentationModelAssembler<Hero, EntityModel<Hero>> {

    @Override
    public EntityModel<Hero> toModel(Hero hero) {
        return new EntityModel<>(hero, //
                linkTo(methodOn(HeroController.class).getOneHero(hero.getId().toString())).withSelfRel(),
                linkTo(methodOn(HeroController.class).getAllHeroes()).withRel("heroes"));
    }
}