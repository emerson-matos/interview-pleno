package br.com.brainweb.interview.core.features.hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import br.com.brainweb.interview.exception.NotFoundException;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.RACE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;

    @Autowired
    public HeroServiceImpl(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public Hero save(Hero hero) {
        return heroRepository.save(hero);
    }

    @Override
    public Hero findHeroBy(UUID id) {
        Optional<Hero> result = heroRepository.findById(id);
        result.orElseThrow(() -> new NotFoundException(id));
        return result.get();
    }

    @Override
    public List<Hero> findAll(String name) {
        List<Hero> result;
        if (name == null) {
            result = heroRepository.findAll();
        } else {
            result = new ArrayList<>();
            findHeroBy(name).ifPresent(result::add);
        }
        return result;
    }

    private Optional<Hero> findHeroBy(String name) {
        return heroRepository.findHeroByNameIgnoreCase(name);
    }

    @Override
    public Hero update(Hero hero) {
        return heroRepository.save(hero);
    }

    @Override
    public Hero patchHero(Hero hero, Map<String, String> heroMap) {
        heroMap.forEach((key, value) -> {
            if (value != null) {
                switch (key) {
                    case "name":
                        hero.setName(key);
                        break;
                    case "race":
                        hero.setRace(RACE.valueOf(value));
                        break;
                    case "enabled":
                        hero.setEnabled(Boolean.valueOf(value));
                        break;
                    case "powerStats":
                        hero.setPowerStats(value);
                        break;
                }
            }
        });
        return hero;
    }
}
