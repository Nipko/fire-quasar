package co.com.mercadolibre.fire_quazar.services;

import co.com.mercadolibre.fire_quazar.exceptions.SatelliteNotFoundException;
import co.com.mercadolibre.fire_quazar.models.Coordinate;
import co.com.mercadolibre.fire_quazar.models.Satellite;
import co.com.mercadolibre.fire_quazar.repositories.SatelliteDataGateway;
import co.com.mercadolibre.fire_quazar.utils.CoordinateUtils;
import co.com.mercadolibre.fire_quazar.utils.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindSatelliteByName {

    private final SatelliteDataGateway satelliteDataGateway;
    @Autowired
    CoordinateUtils coordinateUtils;

    public Satellite execute(@NotBlank final String name) throws SatelliteNotFoundException{
        Optional<Satellite> satellite=satelliteDataGateway.findBySatelliteName(name);
        if(satellite.isPresent()){
            Coordinate coordinate=new Coordinate();
            String []points=coordinateUtils.getCoordinate(name);
            coordinate.setX(Double.valueOf(points[0]));
            coordinate.setY(Double.valueOf(points[1]));
            satellite.get().setCoordinate(coordinate);

        }else{
            throw new SatelliteNotFoundException("satellite: "+name+","+Messages.SATELLITE_WITHOUT_COORDINATES);
        }

        return satellite.get();
    }
}
