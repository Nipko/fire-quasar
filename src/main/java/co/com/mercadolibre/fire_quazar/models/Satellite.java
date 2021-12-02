package co.com.mercadolibre.fire_quazar.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Satellite extends SpaceShip {


    private String name;
    private List<String> message;
    private float distance;

}
