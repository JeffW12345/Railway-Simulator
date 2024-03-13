package railway_similator.place;

import railway_similator.RailwayNetwork;
import railway_similator.train.Train;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class RailwayPlace {
    protected int capacity;
    protected String name;
    protected double length;
    protected RailwayNetwork railwayNetwork;

    private final Lock lock = new ReentrantLock();

    private final Condition atCapacity = lock.newCondition();

    private final ArrayList<Train> trainsHosted = new ArrayList<>();

    public RailwayPlace(double length, RailwayNetwork railwayNetwork) {
        this.length = length;
        this.railwayNetwork = railwayNetwork;
    }

    public RailwayPlace(String name, double length, RailwayNetwork railwayNetwork) {
        this.name = name;
        this.length = length;
        this.railwayNetwork = railwayNetwork;
    }
    public abstract double traversalDurationForTrainOfSpeed(double speed);

    public void addTrain(Train train) {
        lock.lock();
        while(trainsHosted.size() == capacity){
            try{
                atCapacity.await();
            } catch (InterruptedException e) {
                //TODO - Add logging
                System.out.println(e.getMessage());
            }
        }
        try {
            trainsHosted.add(train);
            }
            finally {
            atCapacity.signal();
            lock.unlock();
        }
    }

    public void removeTrain(Train train){
        lock.lock();
        try {
            trainsHosted.remove(train);
        }
        finally {
            lock.unlock();
        }
    }

    public boolean isLastPlaceOnNetwork(){
        return railwayNetwork.checkIfPlaceAtEndOfNetwork(this);
    }
    public double traversalTimeInSeconds(Train train){
        return traversalDurationForTrainOfSpeed(train.getSpeed());
    }
    public boolean canAcceptNewTrain() {
        return trainsHosted.size() < capacity;
    }

    @Override
    public String toString(){
        String trainsHostedAsString = trainsHosted
                .toString()
                .replace("[", "")
                .replace("]", "");

        String nameFollowedByTrains = name + "--" + trainsHostedAsString;

        int numberOfDashes = 24 - nameFollowedByTrains.length();
        String dashes = getDashes(numberOfDashes / 2);
        return "|" + dashes + nameFollowedByTrains + dashes + "|";
    }

    private String getDashes(int numberOfDashes){
        return "-" + "-".repeat(numberOfDashes);
    }
}
