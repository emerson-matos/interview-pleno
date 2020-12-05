package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerStatsService {
    private PowerStatsRepository repository;

    @Autowired
    public PowerStatsService(PowerStatsRepository heroRepository) {
        this.repository = heroRepository;
    }

    public PowerStats save(PowerStats ps){
        return repository.save(ps);
    }
}
