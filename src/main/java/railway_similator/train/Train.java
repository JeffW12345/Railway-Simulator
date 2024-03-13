package railway_similator.train;

import railway_similator.RailwayNetwork;
import railway_similator.place.RailwayPlace;

import java.util.Random;


public class Train implements Runnable {
    protected double speed;
    protected final int trainNumber;
    protected long timeArrivedAtCurrentPlace;
    protected RailwayPlace currentRailwayPlace;
    private boolean trainAddedToInitialRailwayPlace;

    public Train(int trainNumber) {
        this.trainNumber = trainNumber;
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
        addTrainToInitialRailwayPlace();
        while (trainAddedToInitialRailwayPlace) {
            if(atEndOfCurrentPlace() && !currentRailwayPlace.isLastPlaceOnNetwork()){
                try {
                    RailwayNetwork.moveTrainToNextPlace(currentRailwayPlace, this);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if(atEndOfCurrentPlace() && currentRailwayPlace.isLastPlaceOnNetwork()){
                currentRailwayPlace.removeTrain(this);
                break;
            }
        }
    }

    private void addTrainToInitialRailwayPlace() {
        while (!trainAddedToInitialRailwayPlace){
            if (RailwayNetwork.getNextFreeRailwayPlace().isPresent()) {
                currentRailwayPlace = RailwayNetwork.getNextFreeRailwayPlace().get();
                currentRailwayPlace.addTrain(this);
                this.setTimeArrivedAtCurrentPlace();
                trainAddedToInitialRailwayPlace = true;
            }
        }
    }
}
