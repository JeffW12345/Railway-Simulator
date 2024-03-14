package railway_simulator.place;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import railway_similator.RailwayNetwork;
import railway_similator.place.RailwayPlace;
import railway_similator.place.Station;
import railway_similator.train.ExpressTrain;
import railway_similator.train.Train;
import java.lang.reflect.Field;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
        assertEquals(1, station.numberOfTrainsHosted());
    }
    @Test
    void addTrain_WhenAtCapacity_ShouldNotAddTrain() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        Field capacityField = RailwayPlace.class.getDeclaredField("capacity");
        capacityField.setAccessible(true);
        capacityField.set(station, 0);

        Thread thread = new Thread(() -> {
            Train train = new Train(1, null);
            station.addTrain(train);
        });
        thread.start();
        Thread.sleep(100);

        assertEquals(0, station.numberOfTrainsHosted());
    }
    @Test
    void removeTrain_ShouldRemoveTrain() {
        Train train = new Train(1, null);
        station.addTrain(train);
        station.removeTrain(train);
        assertEquals(0, station.numberOfTrainsHosted());
    }

    @Test
    void traversalTimeInSeconds_ShouldReturnCorrectTime() {
        Train train = new ExpressTrain(1, null);
        double expectedTime = ((double)100 / 500) + 5;
        assertEquals(expectedTime, station.traversalTimeInSeconds(train));
    }
}
