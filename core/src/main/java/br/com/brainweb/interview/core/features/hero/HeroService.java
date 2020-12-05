package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;

import java.util.List;
import java.util.Map;
import java.util.UUID;

interface HeroService {

    Hero save(Hero hero);

    Hero findHeroBy(UUID id);

    List<Hero> findAll(String name);

    Hero update(Hero hero);

    Hero patchHero(Hero hero, Map<String, String> heroMap);
}
