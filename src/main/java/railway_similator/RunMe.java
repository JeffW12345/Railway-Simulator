package railway_similator;

import railway_similator.place.Station;
import railway_similator.place.TrackSegment;
import railway_similator.print.StatusPrinter;
import railway_similator.train.TrainFactory;

public class RunMe {
    public static void main(String[] args) {

        RailwayNetwork railwayNetwork = new RailwayNetwork();
        railwayNetwork.addRailwayPlace(new Station("Glasgow", 0.5, railwayNetwork));
        railwayNetwork.addRailwayPlace(new TrackSegment( 1, railwayNetwork));
        railwayNetwork.addRailwayPlace(new Station("Hamilton", 0.5, railwayNetwork));
        railwayNetwork.addRailwayPlace(new TrackSegment( 1.5, railwayNetwork));
        railwayNetwork.addRailwayPlace(new Station("Airdrie", 0.5, railwayNetwork));
        railwayNetwork.addRailwayPlace(new TrackSegment( 1.9, railwayNetwork));
        railwayNetwork.addRailwayPlace(new Station("Motherwell", 0.5, railwayNetwork));

        new Thread(() -> new TrainFactory()
                .createTrains(railwayNetwork, 15))
                .start();

        new Thread(() -> new StatusPrinter(railwayNetwork)
                .printStatus())
                .start();
    }
}