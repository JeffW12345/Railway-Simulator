package railway_similator;

import railway_similator.place.RailwayPlace;
import railway_similator.train.Train;

import java.util.LinkedList;
import java.util.Optional;

public class RailwayNetwork {
    private static final LinkedList<RailwayPlace> railwayPlaces = new LinkedList<>();

    public void addRailwayPlace(RailwayPlace railwayPlace) {
        railwayPlaces.add(railwayPlace);
    }

    public void removeRailwayPlace(RailwayPlace railwayPlace) {
        railwayPlaces.remove(railwayPlace);
    }

    public static Optional<RailwayPlace> getNextFreeRailwayPlace() {
        for (RailwayPlace railwayPlace : railwayPlaces) {
            if (railwayPlace.canAcceptNewTrain()) {
                return Optional.of(railwayPlace);
            }
        }
        return Optional.empty();
    }

    public static LinkedList<RailwayPlace> getRailwayPlaces(){
        return railwayPlaces;
    }

    public static Optional<RailwayPlace> getFirstSegment(){
        return railwayPlaces.isEmpty() ? Optional.empty() : Optional.of(railwayPlaces.getFirst());
    }

    public Optional<RailwayPlace> getNextFreeRailwayPlaceAfter(RailwayPlace currentRailwayPlace) {
        boolean returnNext = false;
        for (RailwayPlace railwayPlace : railwayPlaces) {
            if (returnNext) {
                return Optional.of(railwayPlace);
            }
            if (railwayPlace == currentRailwayPlace) {
                returnNext = true;
            }
        }
        return Optional.empty();
    }

    public static Optional<RailwayPlace> getRightNeighbourOfRailwayPlace(RailwayPlace neighbourWanted) {
        boolean elementSeekingNeighbourFound = false;
        for (RailwayPlace railwayPlace : railwayPlaces) {
            if (elementSeekingNeighbourFound) {
                return Optional.of(railwayPlace);
            }
            if (railwayPlace == neighbourWanted) {
                elementSeekingNeighbourFound = true;
            }
        }
        return Optional.empty();
    }
}