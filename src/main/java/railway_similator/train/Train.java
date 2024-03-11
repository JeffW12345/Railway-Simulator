package railway_similator.train;

public class Train {
    protected double speed;
    protected final int trainNumber;

    public Train(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    protected double getSpeed(){
        if(this instanceof LocalTrain){
            return 0;
        }
        return 1;
    }
}
