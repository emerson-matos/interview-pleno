package br.com.brainweb.interview.core.features.hero;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import br.com.brainweb.interview.model.Hero;

interface HeroService {

    Hero save(Hero hero);

    Hero findHeroBy(UUID id);

    List<Hero> findAll(String name);

    Hero update(Hero hero);

    Hero patchHero(Hero hero, Map<String, String> heroMap);
}
