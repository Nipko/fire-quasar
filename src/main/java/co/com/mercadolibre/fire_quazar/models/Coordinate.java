package co.com.mercadolibre.fire_quazar.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Coordinate {

    private double x;
    private double y;

    public Coordinate(double[] coordinate) {
        this.x = coordinate[0];
        this.y = coordinate[1];
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

}
