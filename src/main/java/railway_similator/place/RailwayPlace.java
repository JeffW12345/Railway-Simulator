package railway_similator.place;

import railway_similator.train.Train;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RailwayPlace {
    protected int capacity;
    protected String name;
    protected double length;

    private final ArrayList<Train> trainsHosted = new ArrayList<>();

    public RailwayPlace(int capacity, String name, double length) {
        this.capacity = capacity;
        this.name = name;
        this.length = length;
    }

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

    public boolean fullyBooked(){
        return trainsHosted.size() == capacity;
    }

    public boolean amIHere(Train train) {
        return trainsHosted.contains(train);
    }

    public double traversalTime(double speed){
        return this instanceof Station ? 5 + (length / speed) : (length / speed);
    }

    public boolean canAcceptNewTrain() {
        return false;
    }
    public boolean hasTrain(Train train) {
        return false;
    }
}
