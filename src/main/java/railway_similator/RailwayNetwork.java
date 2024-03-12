package railway_similator;

import railway_similator.place.RailwayPlace;
import railway_similator.train.Train;

import java.util.LinkedList;
import java.util.Optional;

public class RailwayNetwork {
    private final LinkedList<RailwayPlace> railwayPlaces = new LinkedList<>();

    public void addRailwayPlace(RailwayPlace railwayPlace) {
        railwayPlaces.add(railwayPlace);
    }

    public void removeRailwayPlace(RailwayPlace railwayPlace) {
        railwayPlaces.remove(railwayPlace);
    }

    public Optional<RailwayPlace> getNextFreeRailwayPlace() {
        for (RailwayPlace railwayPlace : railwayPlaces) {
            if (railwayPlace.canAcceptNewTrain()) {
                return Optional.of(railwayPlace);
            }
        }
        return Optional.empty();
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

    public Optional<RailwayPlace> getRightNeighbourOfRailwayPlace(RailwayPlace railwayPlaceNeighbourWanted) {
        boolean rightNeighbourFound = false;
        for (RailwayPlace railwayPlace : railwayPlaces) {
            if (rightNeighbourFound) {
                return Optional.of(railwayPlace);
            }
            if (railwayPlace == railwayPlaceNeighbourWanted) {
                rightNeighbourFound = true;
            }
        }
        return Optional.empty();
    }

    public void moveTrainToNextDestination(Train train) {
        if (canNextDestinationAcceptATrain(train)) {
            RailwayPlace currentRailwayPlace = getRailwayPlaceForTrain(train);
            currentRailwayPlace.removeTrain(train);
            RailwayPlace newRailwayPlace = getRightNeighbourOfRailwayPlace(currentRailwayPlace).isPresent()
                    ? getRightNeighbourOfRailwayPlace(currentRailwayPlace).get()
                    : null;
            boolean isTrainAtEndOfTrack = newRailwayPlace == null;
            if(!isTrainAtEndOfTrack){
                newRailwayPlace.addTrain(train);
            }
        }
    }

    public double timeInSecondsRequiredToTravel(RailwayPlace start, RailwayPlace end, Train train){
        return 0;
    }

    private boolean canNextDestinationAcceptATrain (Train train){
        return false;
        }


    private RailwayPlace getRailwayPlaceForTrain(Train train) {
        return railwayPlaces.stream().filter(railwayplace -> railwayplace.hasTrain(train)).toList().get(0);

    }
}