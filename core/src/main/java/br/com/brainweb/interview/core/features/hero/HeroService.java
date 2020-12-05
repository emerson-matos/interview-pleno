package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.exception.NotFoundException;
import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HeroService {

    private HeroRepository heroRepository;

    @Autowired
    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public Hero save(Hero hero) {
        return heroRepository.save(hero);
    }

    public Hero findHeroBy(UUID id) {
        Optional<Hero> result = heroRepository.findById(id);
        result.orElseThrow(() -> new NotFoundException(id));
        return result.get();
    }

    public Optional<Hero> findHeroBy(String name) {
        return heroRepository.findHeroByNameIgnoreCase(name);
    }

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
}
