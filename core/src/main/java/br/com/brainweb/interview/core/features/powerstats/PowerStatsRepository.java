package br.com.brainweb.interview.core.features.powerstats;

import java.util.UUID;

import br.com.brainweb.interview.model.PowerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerStatsRepository extends JpaRepository<PowerStats, UUID> {
}
