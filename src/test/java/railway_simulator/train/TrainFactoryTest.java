package railway_simulator.train;

import org.junit.jupiter.api.Test;
import railway_similator.RailwayNetwork;
import railway_similator.train.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
public class TrainFactoryTest {
    @Test
    void TrainFactory_ShouldResultInNewThreads() throws InterruptedException {
        ThreadMXBean threadBeanBefore = ManagementFactory.getThreadMXBean();
        int threadCountBefore = threadBeanBefore.getThreadCount();

        Thread thread = new Thread(() -> {
            RailwayNetwork railwayNetwork = mock(RailwayNetwork.class);
            new TrainFactory().createTrains(railwayNetwork, 0);
        });

        thread.start();

        Thread.sleep(2000);
        ThreadMXBean threadBeanAfter = ManagementFactory.getThreadMXBean();
        int threadCountAfter = threadBeanAfter.getThreadCount();

        assertTrue(threadCountAfter - threadCountBefore >= 2);
    }
}
