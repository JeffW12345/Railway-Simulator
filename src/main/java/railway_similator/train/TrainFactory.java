package railway_similator.train;

import railway_similator.RailwayNetwork;
import java.util.Random;
import static java.lang.Thread.sleep;

public class TrainFactory {
    private static int lastNumberAssigned = 0;

    @SuppressWarnings("InfiniteLoopStatement")
    public void createTrainsAndActivate(RailwayNetwork railwayNetwork, int maximumIntervalBetweenLoopsInSeconds) {
        boolean firstTrainAdded = false;
        while(true){
            long intervalBetweenTrainCreation = firstTrainAdded
                    ? new Random().nextInt(maximumIntervalBetweenLoopsInSeconds + 1) * 1000L
                    : 0;
            try {
                sleep(intervalBetweenTrainCreation);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TrainType trainType = new Random().nextInt(2) == 0 ? TrainType.EXPRESS : TrainType.LOCAL;
            if(trainType == TrainType.LOCAL){
               new LocalTrain(++lastNumberAssigned, railwayNetwork);
            }
            new ExpressTrain(++lastNumberAssigned, railwayNetwork);
            firstTrainAdded = true;
        }
    }
}
