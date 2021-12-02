package co.com.mercadolibre.fire_quazar.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SatelliteResponseNotFound implements ApiResponse{
    private String message;
}
