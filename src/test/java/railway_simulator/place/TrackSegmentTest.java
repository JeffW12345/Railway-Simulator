package railway_simulator.place;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import railway_similator.place.RailwayPlace;
import railway_similator.place.TrackSegment;
import railway_similator.train.ExpressTrain;
import railway_similator.train.Train;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TrackSegmentTest {
    private TrackSegment trackSegment;

    @BeforeEach
    void setUp() {
        trackSegment = new TrackSegment(1000.0, null);
    }

    @Test
    void addTrain_WhenNotAtCapacity_ShouldAddTrain() {
        Train train = new Train(1, null);
        trackSegment.addTrain(train);
        assertTrue(trackSegment.numberOfTrainsEqualTo(1));
    }
    @Test
    void addTrain_WhenAtCapacity_ShouldNotAddTrain() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        Field capacityField = RailwayPlace.class.getDeclaredField("capacity");
        capacityField.setAccessible(true);
        capacityField.set(trackSegment, 0);

        Thread thread = new Thread(() -> {
            Train train = new Train(1, null);
            trackSegment.addTrain(train);
        });
        thread.start();
        Thread.sleep(100);

        assertTrue(trackSegment.numberOfTrainsEqualTo(0));
    }
    @Test
    void removeTrain_ShouldRemoveTrain() {
        Train train = new Train(1, null);
        trackSegment.addTrain(train);
        trackSegment.removeTrain(train);
        assertTrue(trackSegment.numberOfTrainsEqualTo(0));
    }

    @Test
    void traversalTimeInSeconds_ShouldReturnCorrectTime() {
        Train train = new ExpressTrain(1, null);
        double expectedTime = ((double)1000 / 500);
        assertEquals(expectedTime, trackSegment.traversalTimeInSeconds(train));
    }
}
