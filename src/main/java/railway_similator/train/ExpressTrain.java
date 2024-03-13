package railway_similator.train;

import railway_similator.RailwayNetwork;

public class ExpressTrain extends Train {
    public ExpressTrain(int trainNumber, RailwayNetwork railwayNetwork) {
        super(trainNumber, railwayNetwork);
        speed = 500;
    }
}
