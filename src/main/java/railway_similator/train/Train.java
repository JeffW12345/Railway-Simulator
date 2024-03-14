package railway_similator.train;

import railway_similator.RailwayNetwork;
import railway_similator.place.RailwayPlace;

public class Train implements Runnable {
    protected double speed;
    protected final int trainNumber;
    protected long timeArrivedAtCurrentPlace;
    protected RailwayPlace currentRailwayPlace;
    private boolean trainAddedToInitialRailwayPlace;
    private final RailwayNetwork railwayNetwork;

    public Train(int trainNumber, RailwayNetwork railwayNetwork) {
        this.trainNumber = trainNumber;
        this.railwayNetwork = railwayNetwork;
    }
    public double getSpeed(){
        return speed;
    }

    public boolean isCurrentRailwayPlace(RailwayPlace railwayPlace){
        return railwayPlace == currentRailwayPlace;
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

    public void addTrainToInitialRailwayPlace() {
        while (!trainAddedToInitialRailwayPlace){
            if (railwayNetwork.getNextFreeRailwayPlace() != null) {
                currentRailwayPlace = railwayNetwork.getNextFreeRailwayPlace();
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
