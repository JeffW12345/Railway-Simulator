package railway_similator;

import railway_similator.place.RailwayPlace;
import railway_similator.train.ExpressTrain;
import railway_similator.train.LocalTrain;
import railway_similator.train.Train;

import java.util.ArrayList;


public class TrainFactory {
    private int lastNumberAssigned = 0;

    public Train train(TrainType type, Thread thread, ArrayList<RailwayPlace> places) {
        if(type == TrainType.LOCAL){
            return new LocalTrain(++lastNumberAssigned);
        }
        return new ExpressTrain(++lastNumberAssigned);
    }

}
