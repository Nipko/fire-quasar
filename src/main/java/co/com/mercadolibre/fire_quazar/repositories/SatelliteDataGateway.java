package co.com.mercadolibre.fire_quazar.repositories;

import co.com.mercadolibre.fire_quazar.models.Satellite;

import java.util.Optional;

public interface SatelliteDataGateway {
    Optional<Satellite> findBySatelliteName(String name);

    Satellite save(Satellite satellite);
}
