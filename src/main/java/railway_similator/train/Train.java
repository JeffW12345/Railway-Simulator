package railway_similator.train;

import railway_similator.RailwayNetwork;
import railway_similator.place.RailwayPlace;


public class Train {
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
}
