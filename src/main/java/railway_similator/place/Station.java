package railway_similator.place;

import railway_similator.RailwayNetwork;

import java.util.Random;

public class Station extends RailwayPlace {
    public Station(String name, double length, RailwayNetwork railwayNetwork) {
        super(name, length, railwayNetwork);
        capacity = new Random().nextInt(4) + 1;
    }
    public double traversalDurationForTrainOfSpeed(double speed){
        return 5 + (length / speed);
    }
}
