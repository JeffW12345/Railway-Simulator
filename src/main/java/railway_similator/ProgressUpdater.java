package railway_similator;

import railway_similator.place.RailwayPlace;
import railway_similator.train.Train;
import java.util.LinkedList;

public class ProgressUpdater {

    @SuppressWarnings("Infinite loop")
    LinkedList<RailwayPlace> railwayPlaces = RailwayNetwork.getRailwayPlaces();
    public void updateNetwork() {
        while(true){
            for(RailwayPlace railwayPlace : railwayPlaces){
                for(Train train : railwayPlace.getTrainsHosted()){
                    if(train.atEndOfCurrentPlace()){
                        moveTrainToNextPlace(railwayPlace, train);
                    }
                }
            }
        }
    }

    private void moveTrainToNextPlace(RailwayPlace railwayPlace, Train train) {
        if(RailwayNetwork.getRightNeighbourOfRailwayPlace(railwayPlace).isPresent()){
            RailwayPlace nextRailwayPlace = RailwayNetwork.getRightNeighbourOfRailwayPlace(railwayPlace).get();
            if(nextRailwayPlace.canAcceptNewTrain()){
                nextRailwayPlace.addTrain(train);
                train.setTimeArrivedAtCurrentPlace();
            }
        }
        boolean endOfLine = RailwayNetwork.getRightNeighbourOfRailwayPlace(railwayPlace).isEmpty();
        if(endOfLine){
            railwayPlace.removeTrain(train);
        }
    }
}
