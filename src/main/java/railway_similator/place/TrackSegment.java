package railway_similator.place;

import railway_similator.train.Train;

public class TrackSegment extends RailwayPlace {
    private Train trainVisitingThisStation;
    public TrackSegment(int capacity, String name, int lengthInMeters) {
        super(capacity, name, lengthInMeters);
        //lengthInKilometers = ThreadLocalRandom.current().nextInt(1, 15);
    }
}
