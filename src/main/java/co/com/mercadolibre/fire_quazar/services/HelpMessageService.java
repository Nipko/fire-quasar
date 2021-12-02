package co.com.mercadolibre.fire_quazar.services;

import co.com.mercadolibre.fire_quazar.exceptions.HelpMessageException;
import co.com.mercadolibre.fire_quazar.exceptions.LocationException;
import co.com.mercadolibre.fire_quazar.models.Bearer;
import co.com.mercadolibre.fire_quazar.models.Coordinate;
import co.com.mercadolibre.fire_quazar.models.SatelliteOperation;
import co.com.mercadolibre.fire_quazar.models.SpaceShip;
import co.com.mercadolibre.fire_quazar.processors.HelpMessageProcess;
import co.com.mercadolibre.fire_quazar.processors.LocationProcess;
import co.com.mercadolibre.fire_quazar.repositories.HelpRepository;
import co.com.mercadolibre.fire_quazar.utils.CoordinateUtils;
import co.com.mercadolibre.fire_quazar.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class HelpMessageService implements HelpRepository {

    @Autowired
    LocationProcess locationService;
    @Autowired
    HelpMessageProcess messageService;
    @Value("${satellites.numbers}")
    private int numSatellites;


    @Autowired
    CoordinateUtils coordinateUtils;


    public SpaceShip getSpaceShip(SatelliteOperation satelliteOperation) throws HelpMessageException, LocationException {

        Optional<SatelliteOperation> satelliteOperationOptional = Optional.ofNullable(satelliteOperation);
        if (satelliteOperationOptional.get().getMessages().size() < 2)
            throw new HelpMessageException(Messages.INSUFFICIENT_NUM_MESSAGES);
        String message = messageService.getMessage(satelliteOperation.getMessages());

        uploadPositions(satelliteOperationOptional.get());
        if ((satelliteOperationOptional.get().getPositions().length < 2) || (validatePositions(satelliteOperationOptional.get().getDistances())))
            throw new LocationException(Messages.INSUFFICIENT_NUM_POSITIONS_DISTANCES);
        double[] points = locationService.getLocation(satelliteOperation.getPositions(), satelliteOperation.getDistances());
        Coordinate coordinate = new Coordinate(points);

        return new Bearer(coordinate, message);
    }

    public void uploadPositions(SatelliteOperation satelliteOperation) {

        if (satelliteOperation.getPositions()[0] == null) {
            double[][] pointsList = new double[numSatellites][];
            String[] satellitePos;
            for (int i = 0; i < satelliteOperation.getSatellites().size(); i++) {
                satellitePos = coordinateUtils.getCoordinate(satelliteOperation.getSatellites().get(i).getName());
                pointsList[i] = Arrays.stream(satellitePos)
                        .map(Double::valueOf)
                        .mapToDouble(Double::doubleValue)
                        .toArray();
            }
            satelliteOperation.setPositions(pointsList);
        }
    }

    public boolean validatePositions(double[] distances){
        if(distances.length>2){
            int cont=0;
            for (double distance : distances) {
                if (distance <= 0) {
                    cont++;
                }
            }
            return cont > 0;
        }else{
            return true;
        }
    }

}
