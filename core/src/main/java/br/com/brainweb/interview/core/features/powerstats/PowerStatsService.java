package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.core.exception.NotFoundException;
import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PowerStatsService {
    private PowerStatsRepository repository;

    @Autowired
    public PowerStatsService(PowerStatsRepository heroRepository) {
        this.repository = heroRepository;
    }

    public PowerStats save(PowerStats ps) {
        return repository.save(ps);
    }

    public List<PowerStats> findAll() {
        return repository.findAll();
    }

    public PowerStats findOne(UUID id) throws NotFoundException {
        Optional<PowerStats> result = repository.findById(id);
        result.orElseThrow(() -> new NotFoundException(id));
        return result.get();
    }
}
