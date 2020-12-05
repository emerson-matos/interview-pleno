package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.core.features.hero.HeroController;
import br.com.brainweb.interview.model.PowerStats;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class PowerStatsAssembler implements RepresentationModelAssembler<PowerStats, EntityModel<PowerStats>> {

    @Override
    public EntityModel<PowerStats> toModel(PowerStats ps) {
        return new EntityModel<>(ps, //
                WebMvcLinkBuilder.linkTo(methodOn(HeroController.class).getOneHero(ps.getId().toString())).withSelfRel(),
                linkTo(methodOn(HeroController.class).getAllHeroes()).withRel("heroes"));
    }
}