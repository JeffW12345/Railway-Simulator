package railway_similator.train;

import railway_similator.RailwayNetwork;
import railway_similator.place.RailwayPlace;

public class Train implements Runnable {
    protected double speedInMetersPerSecond;
    protected final int trainNumber;
    protected long timeArrivedAtCurrentPlace;
    protected RailwayPlace currentRailwayPlace;
    private boolean trainAddedToInitialRailwayPlace;
    private final RailwayNetwork railwayNetwork;

    public Train(int trainNumber, RailwayNetwork railwayNetwork) {
        this.trainNumber = trainNumber;
        this.railwayNetwork = railwayNetwork;
        new Thread(this).start();
    }
    public double getSpeedInMetersPerSecond(){
        return speedInMetersPerSecond;
    }

    public boolean isCurrentRailwayPlace(RailwayPlace railwayPlace){
        return railwayPlace == currentRailwayPlace;
    }
    public void setTimeArrivedAtCurrentPlace() {
        this.timeArrivedAtCurrentPlace = System.currentTimeMillis();
    }
    public boolean atEndOfCurrentPlace(){
        if(currentRailwayPlace == null) return false;
        double traversalTime = currentRailwayPlace.traversalTimeInSeconds(this);
        long secondsElapsedAtCurrentPlace = (System.currentTimeMillis() - timeArrivedAtCurrentPlace) / 1000;
        return secondsElapsedAtCurrentPlace >= traversalTime;
    }

    public void addTrainToInitialRailwayPlace() {
        while (!trainAddedToInitialRailwayPlace){
            RailwayPlace nextFreeRailwayPlace = railwayNetwork.getNextFreeRailwayPlace();
            if (nextFreeRailwayPlace != null) {
                currentRailwayPlace = nextFreeRailwayPlace;
                currentRailwayPlace.addTrain(this);
                this.setTimeArrivedAtCurrentPlace();
                trainAddedToInitialRailwayPlace = true;
            }
        }
    }

    public void setNewCurrentRailwayPlace(RailwayPlace place) {
        this.currentRailwayPlace = place;
    }

    @Override
    public String toString(){
        return String.valueOf(trainNumber);
    }

    @Override
    public void run() {
        addTrainToInitialRailwayPlace();
        while (trainAddedToInitialRailwayPlace) {
            if(atEndOfCurrentPlace() && !currentRailwayPlace.isLastPlaceOnNetwork()){
                try {
                    railwayNetwork.moveTrainToNextPlace(currentRailwayPlace, this);
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
}
