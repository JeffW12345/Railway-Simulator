package railway_similator;

import railway_similator.place.Station;
import railway_similator.place.TrackSegment;

public class RunMe {
    public static void main(String[] args) {
        RailwayNetwork.addRailwayPlace(new Station("Glasgow", 0.5));
        RailwayNetwork.addRailwayPlace(new TrackSegment( 1));
        RailwayNetwork.addRailwayPlace(new Station("Hamilton", 0.5));
        RailwayNetwork.addRailwayPlace(new TrackSegment( 1.5));
        RailwayNetwork.addRailwayPlace(new Station("Airdrie", 0.5));
        RailwayNetwork.addRailwayPlace(new TrackSegment( 1.9));
        RailwayNetwork.addRailwayPlace(new Station("Motherwell", 0.5));

        Thread railNetworkRefreshThread = new Thread(ProgressUpdater::updateNetwork);
        railNetworkRefreshThread.start();


    }
}