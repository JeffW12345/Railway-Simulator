package railway_similator.train;

import railway_similator.RailwayNetwork;

import java.util.Random;

public class TrainFactory {
    private int lastNumberAssigned = 0;

    public Train train(TrainType type) {
        if(type == TrainType.LOCAL){
            return new LocalTrain(++lastNumberAssigned);
        }
        return new ExpressTrain(++lastNumberAssigned);
    }

    public void createTrainAndPutOnTrack(TrainType type) {
        Train train = train(type);
        if(RailwayNetwork.getNextFreeRailwayPlace().isPresent()){
            RailwayNetwork.getNextFreeRailwayPlace().get().addTrain(train);
            train.setTimeArrivedAtCurrentPlace();
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void addTrains() {
        long addNextTrainOnOrAfter = System.currentTimeMillis();
        while(true){
            if(System.currentTimeMillis() < addNextTrainOnOrAfter){
                continue;
            }
            TrainType trainType = new Random().nextInt(2) == 0 ? TrainType.EXPRESS : TrainType.LOCAL;
            createTrainAndPutOnTrack(trainType);
            addNextTrainOnOrAfter += (new Random().nextInt(61) * 1000) + addNextTrainOnOrAfter;
        }
    }
}
