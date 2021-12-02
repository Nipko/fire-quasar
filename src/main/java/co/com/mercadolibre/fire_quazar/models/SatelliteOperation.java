package co.com.mercadolibre.fire_quazar.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class SatelliteOperation {

    private List<Satellite> satellites;


    public double[] getDistances() {

        double[] distances = new double[satellites.size()];
        for (int i = 0; i < satellites.size(); i++) {
            distances[i] = satellites.get(i).getDistance();
        }
        return distances;
    }

    public double[][] getPositions() {
        double[][] positions = new double[satellites.size()][];
        String[] points;
        for (int i = 0; i < satellites.size(); i++) {
            if (satellites.get(i).getCoordinate() != null) {
                points = satellites.get(i).getCoordinate().toString().split(",");
                positions[i] = Arrays.stream(points)
                        .map(Double::valueOf)
                        .mapToDouble(Double::doubleValue)
                        .toArray();
            }
        }
        return positions;
    }

    public void setPositions(double[][] pointsList) {
        Coordinate coordinate;
        for (int i = 0; i < pointsList.length; i++) {
            coordinate = new Coordinate(pointsList[i]);
            satellites.get(i).setCoordinate(coordinate);
        }
    }

    public List<List<String>> getMessages() {
        List<List<String>> messages = new ArrayList<List<String>>();
        for (Satellite s : satellites) {
            messages.add(s.getMessage());
        }
        return messages;
    }


}
