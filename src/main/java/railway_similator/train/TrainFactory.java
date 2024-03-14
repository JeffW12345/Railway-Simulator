package railway_similator.train;

import railway_similator.RailwayNetwork;
import java.util.Random;
import static java.lang.Thread.sleep;

public class TrainFactory {
    private static int lastNumberAssigned = 0;
    private static Train createTrain(TrainType type, RailwayNetwork railwayNetwork) {
        if(type == TrainType.LOCAL){
            return new LocalTrain(++lastNumberAssigned, railwayNetwork);
        }
        return new ExpressTrain(++lastNumberAssigned, railwayNetwork);
    }
    private static void createTrainAndActivateThread(TrainType type, RailwayNetwork railwayNetwork) {
        Train train = createTrain(type, railwayNetwork);
        train.getThread().start();
    }
    @SuppressWarnings("InfiniteLoopStatement")
    public static void createTrainsAndActivate(RailwayNetwork railwayNetwork, int maximumIntervalBetweenLoops) {
        while(true){
            try {
                sleep(new Random().nextInt(maximumIntervalBetweenLoops + 1) * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TrainType trainType = new Random().nextInt(2) == 0 ? TrainType.EXPRESS : TrainType.LOCAL;
            createTrainAndActivateThread(trainType, railwayNetwork);
        }
    }
}
