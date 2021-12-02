package co.com.mercadolibre.fire_quazar.exceptions;

public class SatelliteWithoutCoordinates extends NullPointerException {
    public SatelliteWithoutCoordinates(final String message) {
        super(message);
    }
}
