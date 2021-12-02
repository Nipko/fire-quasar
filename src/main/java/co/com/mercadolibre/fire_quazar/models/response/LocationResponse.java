package co.com.mercadolibre.fire_quazar.models.response;

import co.com.mercadolibre.fire_quazar.models.Coordinate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationResponse {
    double x;
    double y;

    public LocationResponse(final Coordinate location) {
        this.x = location.getX();
        this.y = location.getY();
    }
}
