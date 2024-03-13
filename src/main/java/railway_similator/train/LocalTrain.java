package railway_similator.train;

import railway_similator.RailwayNetwork;

public class LocalTrain extends Train {

    public LocalTrain(int trainNumber, RailwayNetwork railwayNetwork) {
        super(trainNumber, railwayNetwork);
        speed = 10;
    }
}
