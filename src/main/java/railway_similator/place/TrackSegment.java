package railway_similator.place;

import railway_similator.RailwayNetwork;

public class TrackSegment extends RailwayPlace {
    public TrackSegment(double lengthInMeters, RailwayNetwork railwayNetwork) {
        super(lengthInMeters, railwayNetwork);
        name = "Track";
        capacity = 1;
    }
    public double traversalDurationForTrainOfSpeed(double speedInMetersPerSecond){
        return (length / speedInMetersPerSecond);
    }
}
