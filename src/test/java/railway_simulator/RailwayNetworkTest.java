package railway_simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import railway_similator.RailwayNetwork;
import railway_similator.place.RailwayPlace;
import railway_similator.train.Train;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RailwayNetworkTest {

    private RailwayNetwork railwayNetwork;

    @BeforeEach
    void setUp() {
        railwayNetwork = new RailwayNetwork();
    }

    @Test
    void addRailwayPlace_ShouldAddPlace() {
        RailwayPlace railwayPlace = mock(RailwayPlace.class);
        railwayNetwork.addRailwayPlace(railwayPlace);
        assertTrue(railwayNetwork.containsRailwayPlace(railwayPlace));
    }

    @Test
    void getNextFreeRailwayPlace_ShouldReturnFreePlace() {
        RailwayPlace freePlace = mock(RailwayPlace.class);
        when(freePlace.canAcceptNewTrain()).thenReturn(true);
        RailwayPlace occupiedPlace = mock(RailwayPlace.class);
        when(occupiedPlace.canAcceptNewTrain()).thenReturn(false);

        railwayNetwork.addRailwayPlace(freePlace);
        railwayNetwork.addRailwayPlace(occupiedPlace);

        assertEquals(freePlace, railwayNetwork.getNextFreeRailwayPlace());
    }

    @Test
    void checkIfPlaceAtEndOfNetwork_WhenAtEnd_ShouldReturnTrue() {
        RailwayPlace lastPlace = mock(RailwayPlace.class);
        railwayNetwork.addRailwayPlace(mock(RailwayPlace.class));
        railwayNetwork.addRailwayPlace(lastPlace);

        assertTrue(railwayNetwork.checkIfPlaceAtEndOfNetwork(lastPlace));
    }

    @Test
    void checkIfPlaceAtEndOfNetwork_WhenNotAtEnd_ShouldReturnFalse() {
        RailwayPlace notLastPlace = mock(RailwayPlace.class);
        railwayNetwork.addRailwayPlace(notLastPlace);
        railwayNetwork.addRailwayPlace(mock(RailwayPlace.class));

        assertFalse(railwayNetwork.checkIfPlaceAtEndOfNetwork(notLastPlace));
    }

    @Test
    void getRightNeighbourOfRailwayPlace_WhenNeighbourExists_ShouldReturnOptionalOfNeighbour() {
        RailwayPlace place = mock(RailwayPlace.class);
        RailwayPlace neighbour = mock(RailwayPlace.class);
        railwayNetwork.addRailwayPlace(place);
        railwayNetwork.addRailwayPlace(neighbour);

        Optional<RailwayPlace> result = railwayNetwork.getRightNeighbourOfRailwayPlace(place);
        assertTrue(result.isPresent());
        assertEquals(neighbour, result.get());
    }

    @Test
    void getRightNeighbourOfRailwayPlace_WhenNoNeighbourExists_ShouldReturnEmptyOptional() {
        RailwayPlace place = mock(RailwayPlace.class);
        railwayNetwork.addRailwayPlace(place);

        Optional<RailwayPlace> result = railwayNetwork.getRightNeighbourOfRailwayPlace(place);
        assertTrue(result.isEmpty());
    }
}
