package br.com.brainweb.interview.core.features.hero;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsController;
import br.com.brainweb.interview.model.Hero;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class HeroAssembler implements RepresentationModelAssembler<Hero, EntityModel<Hero>> {

    @Override
    public EntityModel<Hero> toModel(Hero hero) {
        return new EntityModel<>(
                hero, //
                linkTo(methodOn(HeroController.class).getOneHero(hero.getId())).withSelfRel(),
                linkTo(methodOn(PowerStatsController.class).getOnePowerStats(hero.getPowerStats().getId()))
                        .withRel("powerStats"),
                linkTo(methodOn(HeroController.class).getAllHeroes(null)).withRel("heroes")
        );
    }
}