package co.com.mercadolibre.fire_quazar.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("satellite")
@NoArgsConstructor//(access = AccessLevel.PRIVATE)
public class SatelliteDocument {


    @Id
    private String name;
    private float distance;
    private List<String> message;
    private Coordinate coordinate;

    public SatelliteDocument(final Satellite satellite) {
        this.name = satellite.getName().toUpperCase();
        this.distance = satellite.getDistance();
        this.message = satellite.getMessage();
        this.coordinate=satellite.getCoordinate();

    }

    public Satellite toDomain() {
        Satellite satellite = new Satellite();
        satellite.setName(this.name);
        satellite.setDistance(this.distance);
        satellite.setMessage(this.message);
        satellite.setCoordinate(this.coordinate);
        return satellite;
    }
}
