package place;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import railway_similator.place.Station;
import railway_similator.train.ExpressTrain;
import railway_similator.train.Train;


class StationTest {
    private Station station;

    @BeforeEach
    void setUp() {
        station = new Station("TestPlace", 100.0, null);
    }

    @Test
    void addTrain_WhenNotAtCapacity_ShouldAddTrain() {
        Train train = new Train(1, null);
        station.addTrain(train);
        Assertions.assertEquals(1, station.numberOfTrainsHosted());
    }
    @Test
    void addTrain_WhenAtCapacity_ShouldNotAddTrain() {
        //TODO - Test does not pass as the thread awaits and is never woken by another thread. Fix.
        station.setCapacity(0);
        Train train = new Train(1, null);
        station.addTrain(train);
        //train.getThread().interrupt(); This attempt to fix the problem didn't work
        Assertions.assertEquals(0, station.numberOfTrainsHosted());
    }

    @Test
    void removeTrain_ShouldRemoveTrain() {
        Train train = new Train(1, null);
        station.addTrain(train);
        station.removeTrain(train);
        Assertions.assertEquals(0, station.numberOfTrainsHosted());
    }

    @Test
    void traversalTimeInSeconds_ShouldReturnCorrectTime() {
        Train train = new ExpressTrain(1, null);
        double expectedTime = ((double)100 / 500) + 5;
        Assertions.assertEquals(expectedTime, station.traversalTimeInSeconds(train));
    }
}
