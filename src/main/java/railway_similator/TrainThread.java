package railway_similator;

import railway_similator.place.RailwayPlace;
import railway_similator.train.Train;

import java.util.ArrayList;

public class TrainThread implements Runnable {
    private final Thread thread;
    private final Train train;

    TrainThread(String name, Train train, TrainType trainType, ArrayList<RailwayPlace> railwayPlaces){
        this.thread = new Thread(this, name);
        this.train = new TrainFactory().train(trainType, thread, railwayPlaces);
        this.thread.start();
    }

    @Override
    public void run() {
        train.go();
    }
}
