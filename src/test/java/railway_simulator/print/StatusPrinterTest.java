package railway_simulator.print;

import org.junit.jupiter.api.*;
import org.mockito.*;
import railway_similator.RailwayNetwork;
import railway_similator.print.StatusPrinter;

import java.io.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class StatusPrinterTest {
    @Mock
    private RailwayNetwork mockRailwayNetwork;
    private StatusPrinter statusPrinter;
    private ByteArrayOutputStream outputStream;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = openMocks(this);
        statusPrinter = new StatusPrinter(mockRailwayNetwork);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() throws Exception {
        System.setOut(System.out);
        closeable.close();
    }

    @Test
    void printStatus_PrintsRailwayNetworkStatus() throws InterruptedException {
        when(mockRailwayNetwork.toString()).thenReturn("Mock Railway Network Status");

        new Thread(() -> statusPrinter.printStatus()).start();

        Thread.sleep(1100);

        String printedContent = outputStream.toString().trim();
        assertTrue(printedContent.contains("Mock Railway Network Status"));
    }
}
