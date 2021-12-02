package co.com.mercadolibre.fire_quazar.utils;

import co.com.mercadolibre.fire_quazar.exceptions.SatelliteWithoutCoordinates;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Component
public  class CoordinateUtils {

    @Autowired
    private Environment environment;

    public  String[] getCoordinate(String nameSatellite) {
        try{
            return environment.getProperty("satellite." + nameSatellite + ".position.x.y").split(",");
        }catch (NullPointerException e){
            throw new SatelliteWithoutCoordinates("satellite "+nameSatellite+" " +Messages.NO_SATELLITE_INFORMATION);
        }
    }
}
