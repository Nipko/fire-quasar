package co.com.mercadolibre.fire_quazar.exceptions;

public class SatelliteNotFoundException extends RuntimeException {
    public SatelliteNotFoundException(final String message) {
        super(message);
    }
}
