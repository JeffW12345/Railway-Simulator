package railway_similator.place;

import railway_similator.train.Train;

public class TrackSegment extends RailwayPlace {
    private Train trainVisitingThisStation;
    public TrackSegment(int capacity, String name, int lengthInMeters) {
        super(name, lengthInMeters);
        capacity = 1;
    }

    public double traversalDurationForTrainOfSpeed(double speed){
        return (length / speed);
    }
}
