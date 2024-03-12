package railway_similator.place;

import railway_similator.train.Train;

public class TrackSegment extends RailwayPlace {
    public TrackSegment(double lengthInMeters) {
        super(lengthInMeters);
        name = "Track";
        capacity = 1;
    }

    public double traversalDurationForTrainOfSpeed(double speed){
        return (length / speed);
    }
}
