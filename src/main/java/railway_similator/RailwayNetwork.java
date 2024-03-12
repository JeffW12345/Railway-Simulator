package railway_similator;

import railway_similator.place.RailwayPlace;
import railway_similator.train.Train;

import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RailwayNetwork {
    private static final LinkedList<RailwayPlace> railwayPlaces = new LinkedList<>();
    private static final Lock lock = new ReentrantLock();
    private static final Condition railwayPlaceCanAcceptNewTrain = lock.newCondition();


    public static void addRailwayPlace(RailwayPlace railwayPlace) {
        railwayPlaces.add(railwayPlace);
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

    public static void moveTrainToNextPlace(RailwayPlace railwayPlace, Train train) throws InterruptedException {
        lock.lock();
        boolean notEndRailwayPlace = RailwayNetwork.getRightNeighbourOfRailwayPlace(railwayPlace).isPresent();
        try {
            if(notEndRailwayPlace) {
                RailwayPlace nextRailwayPlace = RailwayNetwork.getRightNeighbourOfRailwayPlace(railwayPlace).get();
                while (!nextRailwayPlace.canAcceptNewTrain()) {
                    railwayPlaceCanAcceptNewTrain.await();
                }
                trainNewRailwayPlaceActions(train, nextRailwayPlace);
                railwayPlaceCanAcceptNewTrain.signal();
            } else {
                railwayPlace.removeTrain(train);
            }
        } finally {
            lock.unlock();
        }
    }

    private static void trainNewRailwayPlaceActions(Train train, RailwayPlace nextRailwayPlace) {
        nextRailwayPlace.addTrain(train);
        train.setTimeArrivedAtCurrentPlace();
    }

    @Override
    public String toString(){
        StringBuilder toReturn = new StringBuilder();
        for(RailwayPlace railwayPlace : railwayPlaces){
            toReturn.append(railwayPlace);
        }
        return toReturn.toString();
    }
}