package railway_similator.train;

import java.util.ArrayList;

public class Trains {
    protected ArrayList<Train> trains = new ArrayList<>();

    public void add(Train train){
        trains.add(train);
    }
    public void remove(Train train){
        trains.remove(train);
    }
}
