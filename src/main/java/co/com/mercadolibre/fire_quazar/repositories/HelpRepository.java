package co.com.mercadolibre.fire_quazar.repositories;

import co.com.mercadolibre.fire_quazar.exceptions.HelpMessageException;
import co.com.mercadolibre.fire_quazar.exceptions.LocationException;
import co.com.mercadolibre.fire_quazar.models.SatelliteOperation;
import co.com.mercadolibre.fire_quazar.models.SpaceShip;

public interface HelpRepository {

    SpaceShip getSpaceShip(SatelliteOperation satelliteOperation) throws HelpMessageException, LocationException;
}
