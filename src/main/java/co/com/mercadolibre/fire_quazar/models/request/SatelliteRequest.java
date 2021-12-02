package co.com.mercadolibre.fire_quazar.models.request;

import co.com.mercadolibre.fire_quazar.models.Satellite;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SatelliteRequest {

    private String name;
    private float distance;
    @NotEmpty
    private List<String> message;

    public Satellite toDomain() {
        Satellite satellite=new Satellite();
        satellite.setName(this.name);
        satellite.setDistance(this.distance);
        satellite.setMessage(this.message);
        return satellite;
    }
}
