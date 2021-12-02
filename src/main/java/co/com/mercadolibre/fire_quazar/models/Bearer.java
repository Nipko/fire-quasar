package co.com.mercadolibre.fire_quazar.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Bearer extends SpaceShip {

    private String message;

    public Bearer(Coordinate coordinate, String message) {
        this.setCoordinate(coordinate);
        this.message = message;
    }

    public Bearer(String message) {
        this.message = message;
    }
}
