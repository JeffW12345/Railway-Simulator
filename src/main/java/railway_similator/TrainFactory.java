package railway_similator;

import railway_similator.train.ExpressTrain;
import railway_similator.train.LocalTrain;
import railway_similator.train.Train;


public class TrainFactory {
    private int lastNumberAssigned = 0;

    public Train train(TrainType type) {
        if(type == TrainType.LOCAL){
            return new LocalTrain(++lastNumberAssigned);
        }
        return new ExpressTrain(++lastNumberAssigned);
    }
}
