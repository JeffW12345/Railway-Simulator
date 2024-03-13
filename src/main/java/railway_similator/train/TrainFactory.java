package railway_similator.train;

import java.util.Random;

public class TrainFactory {
    private static int lastNumberAssigned = 0;
    private static Train train(TrainType type) {
        if(type == TrainType.LOCAL){
            return new LocalTrain(++lastNumberAssigned);
        }
        return new ExpressTrain(++lastNumberAssigned);
    }
    private static void createTrainAndActivateThread(TrainType type) {
        Train train = train(type);
        train.getThread().start();
    }
    @SuppressWarnings("InfiniteLoopStatement")
    public static void addTrainsAndActivate() {
        long addNextTrainOnOrAfter = System.currentTimeMillis();
        while(true){
            if(System.currentTimeMillis() < addNextTrainOnOrAfter){
                continue;
            }
            TrainType trainType = new Random().nextInt(2) == 0 ? TrainType.EXPRESS : TrainType.LOCAL;
            createTrainAndActivateThread(trainType);
            addNextTrainOnOrAfter += (new Random().nextInt(61) * 1000) + addNextTrainOnOrAfter;
        }
    }
}
