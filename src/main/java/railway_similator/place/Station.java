package railway_similator.place;

import java.util.Random;

public class Station extends RailwayPlace {
    public Station(String name, double length) {
        super(name, length);
        capacity = new Random().nextInt(3);
    }

    public double traversalDurationForTrainOfSpeed(double speed){
        return 5 + (length / speed);
    }
}
