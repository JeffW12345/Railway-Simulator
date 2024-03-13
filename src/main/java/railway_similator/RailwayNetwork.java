package railway_similator;

import railway_similator.place.RailwayPlace;
import railway_similator.train.Train;

import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RailwayNetwork {
    private final LinkedList<RailwayPlace> railwayPlaces = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition railwayPlaceCanAcceptNewTrain = lock.newCondition();

    public void addRailwayPlace(RailwayPlace railwayPlace) {
        railwayPlaces.add(railwayPlace);
    }

    public RailwayPlace getNextFreeRailwayPlace() {
        lock.lock();
        try{
            for (RailwayPlace railwayPlace : railwayPlaces) {
                if (railwayPlace.canAcceptNewTrain()) {
                    return railwayPlace;
                }
            }
            return null;
        }
        finally{
            lock.unlock();
        }
    }

    public synchronized boolean checkIfPlaceAtEndOfNetwork(RailwayPlace railwayPlace) {
        return railwayPlaces.getLast() == railwayPlace;
    }
    public synchronized Optional<RailwayPlace> getRightNeighbourOfRailwayPlace(RailwayPlace neighbourWanted) {
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

    public void moveTrainToNextPlace(RailwayPlace railwayPlace, Train train) throws InterruptedException {
        lock.lock();
        boolean notEndRailwayPlace = getRightNeighbourOfRailwayPlace(railwayPlace).isPresent();
        try {
            if(notEndRailwayPlace) {
                RailwayPlace nextRailwayPlace = getRightNeighbourOfRailwayPlace(railwayPlace).get();
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
        train.setNewCurrentRailwayPlace(nextRailwayPlace);
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