package co.com.mercadolibre.fire_quazar.repositories;

import co.com.mercadolibre.fire_quazar.models.SatelliteDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SatelliteMongoRepository extends MongoRepository<SatelliteDocument, String> {
    Optional<SatelliteDocument> findByNameIgnoreCase(String name);
}
