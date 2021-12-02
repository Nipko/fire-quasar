package co.com.mercadolibre.fire_quazar.repositories;

import co.com.mercadolibre.fire_quazar.models.Coordinate;
import co.com.mercadolibre.fire_quazar.models.Satellite;
import co.com.mercadolibre.fire_quazar.models.SatelliteDocument;
import co.com.mercadolibre.fire_quazar.utils.CoordinateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SatelliteMongoDataGateway implements SatelliteDataGateway {
    @Autowired
    CoordinateUtils coordinateUtils;

    private final SatelliteMongoRepository repository;

    @Override
    public Optional<Satellite> findBySatelliteName(final String name) {
        return repository.findByNameIgnoreCase(name)
                .map(SatelliteDocument::toDomain);
    }

    @Override
    public Satellite save(final Satellite satellite) {
        String []points=coordinateUtils.getCoordinate(satellite.getName());
        Coordinate coordinate=new Coordinate();
        coordinate.setX(Double.valueOf(points[0]));
        coordinate.setX(Double.valueOf(points[1]));
        satellite.setCoordinate(coordinate);
        return repository.save(new SatelliteDocument(satellite)).toDomain();
    }
}
