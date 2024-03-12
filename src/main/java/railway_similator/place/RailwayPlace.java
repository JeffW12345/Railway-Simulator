package railway_similator.place;

import railway_similator.train.Train;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class RailwayPlace {
    protected int capacity;
    protected String name;
    protected double length;

    private final ArrayList<Train> trainsHosted = new ArrayList<>();

    public ArrayList<Train> getTrainsHosted() {
        return trainsHosted;
    }

    public RailwayPlace(String name, double length) {
        this.name = name;
        this.length = length;
    }


    public abstract double traversalDurationForTrainOfSpeed(double speed);

    public void addTrain(Train train) {
        Lock addTrainLock = new ReentrantLock();
        // final Condition cannotMoveTrain  = addTrainLock.newCondition();
        addTrainLock.lock();
        try {
            trainsHosted.add(train);
            }
            finally {
                addTrainLock.unlock();
            }
    }

    public void removeTrain(Train train){
        Lock removeTrainLock = new ReentrantLock();
        if (removeTrainLock.tryLock()) {
            try {
                trainsHosted.remove(train);
            }
            finally {
                removeTrainLock.unlock();
            }
        }
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
        String dashes = "-";
        for(int i = 0; i < numberOfDashes; i++){
            dashes += dashes;
        }
        return dashes;
    }
}
