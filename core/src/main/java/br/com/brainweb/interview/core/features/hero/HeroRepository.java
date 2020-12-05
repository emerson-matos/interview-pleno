package br.com.brainweb.interview.core.features.hero;

import java.util.Optional;
import java.util.UUID;

import br.com.brainweb.interview.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, UUID> {
    Optional<Hero> findHeroByNameIgnoreCase(String name);
}
