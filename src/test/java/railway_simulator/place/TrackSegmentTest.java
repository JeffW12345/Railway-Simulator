package railway_simulator.place;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import railway_similator.place.TrackSegment;
import railway_similator.train.ExpressTrain;
import railway_similator.train.Train;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
        assertEquals(1, trackSegment.numberOfTrainsHosted());
    }
    @Test
    void addTrain_WhenAtCapacity_ShouldNotAddTrain() throws InterruptedException {
        trackSegment.setCapacity(0);

        Thread thread = new Thread(() -> {
            Train train = new Train(1, null);
            trackSegment.addTrain(train);
        });
        thread.start();
        Thread.sleep(100);

        assertEquals(0, trackSegment.numberOfTrainsHosted());
    }
    @Test
    void removeTrain_ShouldRemoveTrain() {
        Train train = new Train(1, null);
        trackSegment.addTrain(train);
        trackSegment.removeTrain(train);
        assertEquals(0, trackSegment.numberOfTrainsHosted());
    }

    @Test
    void traversalTimeInSeconds_ShouldReturnCorrectTime() {
        Train train = new ExpressTrain(1, null);
        double expectedTime = ((double)1000 / 500);
        assertEquals(expectedTime, trackSegment.traversalTimeInSeconds(train));
    }
}
