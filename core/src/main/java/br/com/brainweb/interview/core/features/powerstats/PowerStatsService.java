package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.exception.NotFoundException;
import br.com.brainweb.interview.model.PowerStats;

import java.util.List;
import java.util.UUID;

interface PowerStatsService {

    PowerStats save(PowerStats ps);

    List<PowerStats> findAll();

    PowerStats findOne(UUID id) throws NotFoundException;
}
