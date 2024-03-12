package railway_similator.train;

import railway_similator.RailwayNetwork;
import railway_similator.place.RailwayPlace;

import java.util.Random;


public class Train implements Runnable {
    protected double speed;
    protected final int trainNumber;
    protected long timeArrivedAtCurrentPlace;
    protected RailwayPlace currentRailwayPlace;

    public Train(int trainNumber) {
        this.trainNumber = trainNumber;
        this.currentRailwayPlace = RailwayNetwork.getFirstSegment().isPresent()
                ? RailwayNetwork.getFirstSegment().get()
                : null;
    }
    public double getSpeed(){
        return speed;
    }
    public void setTimeArrivedAtCurrentPlace() {
        this.timeArrivedAtCurrentPlace = System.currentTimeMillis();
    }

    public Thread getThread(){
        return new Thread(this);
    }
    public boolean atEndOfCurrentPlace(){
        if(currentRailwayPlace == null) return false;
        double traversalTime = currentRailwayPlace.traversalTimeInSeconds(this);
        long secondsElapsedAtCurrentPlace = (System.currentTimeMillis() - timeArrivedAtCurrentPlace) / 1000;
        return secondsElapsedAtCurrentPlace >= traversalTime;
    }

    @Override
    public String toString(){
        return String.valueOf(trainNumber);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        boolean trainAddedToInitialRailwayPlace = false;
        if (RailwayNetwork.getNextFreeRailwayPlace().isPresent()) {
            RailwayNetwork.getNextFreeRailwayPlace().get().addTrain(this);
            this.setTimeArrivedAtCurrentPlace();
            trainAddedToInitialRailwayPlace = true;
        }
        long addNextTrainOnOrAfter = System.currentTimeMillis();
        while (trainAddedToInitialRailwayPlace) {
            if(atEndOfCurrentPlace()){
                try {
                    RailwayNetwork.moveTrainToNextPlace(currentRailwayPlace, this);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
