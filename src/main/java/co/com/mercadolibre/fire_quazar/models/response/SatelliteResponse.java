package co.com.mercadolibre.fire_quazar.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SatelliteResponse implements ApiResponse{
    private LocationResponse coordinate;
    private String message;
}
