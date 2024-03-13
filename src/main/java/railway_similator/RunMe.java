package railway_similator;

import railway_similator.place.Station;
import railway_similator.place.TrackSegment;
import railway_similator.train.TrainFactory;

import static railway_similator.train.TrainFactory.addTrainsAndActivate;

public class RunMe {
    public static void main(String[] args) {
        RailwayNetwork.addRailwayPlace(new Station("Glasgow", 0.5));
        RailwayNetwork.addRailwayPlace(new TrackSegment( 1));
        RailwayNetwork.addRailwayPlace(new Station("Hamilton", 0.5));
        RailwayNetwork.addRailwayPlace(new TrackSegment( 1.5));
        RailwayNetwork.addRailwayPlace(new Station("Airdrie", 0.5));
        RailwayNetwork.addRailwayPlace(new TrackSegment( 1.9));
        RailwayNetwork.addRailwayPlace(new Station("Motherwell", 0.5));

        new Thread(TrainFactory::addTrainsAndActivate);

    }
}