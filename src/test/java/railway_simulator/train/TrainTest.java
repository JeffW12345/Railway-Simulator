package railway_simulator.train;
import org.junit.jupiter.api.Test;
import railway_similator.RailwayNetwork;
import railway_similator.place.RailwayPlace;
import railway_similator.train.Train;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainTest {

    @Test
    void addTrainToInitialRailwayPlace_ShouldAddTrainToInitialPlace() {
        RailwayNetwork railwayNetwork = mock(RailwayNetwork.class);
        Train train = new Train(1, railwayNetwork);
        RailwayPlace initialPlace = mock(RailwayPlace.class);

        when(railwayNetwork.getNextFreeRailwayPlace()).thenReturn(initialPlace);

        train.addTrainToInitialRailwayPlace();

        assertTrue(train.isCurrentRailwayPlace(initialPlace));
        verify(initialPlace, times(1)).addTrain(train);
    }

    @Test
    void atEndOfCurrentPlace_ShouldReturnTrueWhenAtEnd() throws NoSuchFieldException, IllegalAccessException {
        Train train = new Train(1, null);
        train.setTimeArrivedAtCurrentPlace();

        RailwayPlace mockRailwayPlace = mock(RailwayPlace.class);
        Field currentRailwayPlace = Train.class.getDeclaredField("currentRailwayPlace");
        currentRailwayPlace.setAccessible(true);
        currentRailwayPlace.set(train, mockRailwayPlace);

        when(mockRailwayPlace.traversalTimeInSeconds(train)).thenReturn(1.0);

        long currentTime = System.currentTimeMillis();

        Field timeArrivedAtCurrentPlace = Train.class.getDeclaredField("timeArrivedAtCurrentPlace");
        timeArrivedAtCurrentPlace.setAccessible(true);
        timeArrivedAtCurrentPlace.set(train, currentTime - 2000);
        assertTrue(train.atEndOfCurrentPlace());
    }
}