package railway_similator.train;

import railway_similator.RailwayNetwork;

import java.util.Random;

import static java.lang.Thread.sleep;

public class TrainFactory {
    private static int lastNumberAssigned = 0;
    private static Train train(TrainType type, RailwayNetwork railwayNetwork) {
        if(type == TrainType.LOCAL){
            return new LocalTrain(++lastNumberAssigned, railwayNetwork);
        }
        return new ExpressTrain(++lastNumberAssigned, railwayNetwork);
    }
    private static void createTrainAndActivateThread(TrainType type, RailwayNetwork railwayNetwork) {
        Train train = train(type, railwayNetwork);
        train.getThread().start();
    }
    @SuppressWarnings("InfiniteLoopStatement")
    public static void addTrainsAndActivate(RailwayNetwork railwayNetwork) {
        while(true){
            try {
                sleep(new Random().nextInt(61) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TrainType trainType = new Random().nextInt(2) == 0 ? TrainType.EXPRESS : TrainType.LOCAL;
            createTrainAndActivateThread(trainType, railwayNetwork);
        }
    }
}
