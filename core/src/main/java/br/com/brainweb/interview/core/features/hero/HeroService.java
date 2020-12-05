package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.exception.NotFoundException;
import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Hero getOne(UUID id) {
        Optional<Hero> result = heroRepository.findById(id);
        result.orElseThrow(() -> new NotFoundException(id));
        return result.get();
    }

    public List<Hero> findAll() {
        return heroRepository.findAll();
    }
}
