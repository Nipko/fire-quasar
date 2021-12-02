package co.com.mercadolibre.fire_quazar.controllers;


import co.com.mercadolibre.fire_quazar.exceptions.HelpMessageException;
import co.com.mercadolibre.fire_quazar.exceptions.LocationException;
import co.com.mercadolibre.fire_quazar.exceptions.SatelliteNotFoundException;
import co.com.mercadolibre.fire_quazar.exceptions.SatelliteWithoutCoordinates;
import co.com.mercadolibre.fire_quazar.models.Bearer;
import co.com.mercadolibre.fire_quazar.models.Satellite;
import co.com.mercadolibre.fire_quazar.models.SatelliteOperation;
import co.com.mercadolibre.fire_quazar.models.SpaceShip;
import co.com.mercadolibre.fire_quazar.models.request.SatelliteRequest;
import co.com.mercadolibre.fire_quazar.models.response.ApiResponse;
import co.com.mercadolibre.fire_quazar.models.response.LocationResponse;
import co.com.mercadolibre.fire_quazar.models.response.SatelliteResponse;
import co.com.mercadolibre.fire_quazar.models.response.SatelliteResponseNotFound;
import co.com.mercadolibre.fire_quazar.repositories.HelpRepository;
import co.com.mercadolibre.fire_quazar.repositories.SatelliteDataGateway;
import co.com.mercadolibre.fire_quazar.services.FindSatelliteByName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.apache.commons.lang3.StringUtils.join;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "${api.version}")
public class SatelliteController {

    @Autowired
    private HelpRepository repository;


    private static final String WHITE_BLANK = " ";
    private final SatelliteDataGateway satelliteDataGateway;

    private final FindSatelliteByName findSatelliteByName;

    @PostMapping(value = {"${api.context.path.get.location}"},
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SpaceShip> responseTopSecret(@RequestBody SatelliteOperation satelliteOperation)  {
        try{
            SpaceShip response = repository.getSpaceShip(satelliteOperation);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (HelpMessageException | LocationException |SatelliteWithoutCoordinates e ){
            log.error("error: "+e,e.getMessage());
            return ResponseEntity.status(404).body(new Bearer(e.getMessage()));
        }

    }


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, value = "${api.context.path.get.satellite}/{satellite_name}")
    public ResponseEntity<ApiResponse> sendMessageToSatellite(
            @PathVariable(value = "satellite_name")
            String satelliteName,
            @RequestBody  SatelliteRequest request) {
        request.setName(satelliteName);
        try{
            final Satellite satellite = satelliteDataGateway.save(request.toDomain());
            return ResponseEntity.ok().body(new SatelliteResponse(new LocationResponse(satellite.getCoordinate()), join(satellite.getMessage(), WHITE_BLANK)));
        }catch (SatelliteWithoutCoordinates e){
            log.error("error: "+e,e.getMessage());
            return  ResponseEntity.status(404).body(new SatelliteResponseNotFound(e.getMessage()));
        }

    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, value = "${api.context.path.get.satellite}/{satellite_name}")
    public ResponseEntity<ApiResponse> getMessageFromSatellite(
            @PathVariable(value = "satellite_name")  String satelliteName) {
        try{
            final Satellite satellite = findSatelliteByName.execute(satelliteName);
            return  ResponseEntity.ok().body(new SatelliteResponse(new LocationResponse(satellite.getCoordinate()), join(satellite.getMessage(), WHITE_BLANK)));
        }catch (SatelliteNotFoundException e){
            log.error("error: "+e,e.getMessage());
            return  ResponseEntity.status(404).body(new SatelliteResponseNotFound(e.getMessage()));
        }



    }

}
