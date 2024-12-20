package test.micromobilityTests;
import data.GeographicPoint;
import data.StationID;
import data.UserAccount;
import data.VehicleID;
import micromobility.JourneyRealizeHandler;
import micromobility.PMVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ImplementsServer;
import services.Server;
import services.smartfeatures.*;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class JourneyRealizeHandlerTest {

    JourneyRealizeHandler journeyRH;
    Server server;
    QRDecoder qrDecoder;
    ArduinoMicroController arduinoMicroController;
    UnbondedBTSignal btSignal;

    @BeforeEach
    void setup(){
        this.server = new ImplementsServer();
        this.qrDecoder = new ImplementsQRDecoder();
        this.arduinoMicroController = new ImplementsArduinoMicroController();
        this.btSignal = new ImplementsUnbondedBTSignal();
        this.journeyRH = new JourneyRealizeHandler(server,qrDecoder,arduinoMicroController,btSignal);
    }

    @Test
    void testUnPairVehicle_Successful() throws Exception {
        UserAccount user = new UserAccount("test_user");
        PMVehicle vehicle = new PMVehicle(new VehicleID("vehicle_123"), new GeographicPoint(10.0F, 20.0F));
        StationID stationID = new StationID("station_123");
        GeographicPoint location = new GeographicPoint(15.0F, 25.0F);
        LocalDateTime date = LocalDateTime.now();
        float averageSpeed = 20.0f;
        float distance = 5.0f;
        int duration = 10;
        BigDecimal amount = BigDecimal.valueOf(100.0);

        assertDoesNotThrow(() -> journeyRH.unPairVehicle(user, vehicle, stationID, location, date, averageSpeed, distance, duration, amount));
    }

    @Test
    void testUnPairVehicle_NullVehicle_ShouldThrowException() {
        UserAccount user = new UserAccount("test_user");
        StationID stationID = new StationID("station_123");
        GeographicPoint location = new GeographicPoint(15.0F, 25.0F);
        LocalDateTime date = LocalDateTime.now();
        float averageSpeed = 20.0f;
        float distance = 5.0f;
        int duration = 10;
        BigDecimal amount = BigDecimal.valueOf(100.0);

        assertThrows(IllegalArgumentException.class, () -> journeyRH.unPairVehicle(user, null, stationID, location, date, averageSpeed, distance, duration, amount));
    }

    @Test
    void testBroadcastStationID_Successful() {
        StationID stationID = new StationID("station_123");
        assertDoesNotThrow(() -> journeyRH.broadcastStationID(stationID));
    }

    @Test
    void testStartDriving_Successful() {
        assertDoesNotThrow(() -> journeyRH.startDriving());
    }

    @Test
    void testStopDriving_Successful() {
        assertDoesNotThrow(() -> journeyRH.stopDriving());
    }

    @Test
    void testCalculateValues_Successful() throws Exception {
        GeographicPoint startLocation = new GeographicPoint(10.0F, 20.0F);
        GeographicPoint endLocation = new GeographicPoint(15.0F, 25.0F);
        LocalDateTime startTime = LocalDateTime.of(2023, 12, 1, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 12, 1, 10, 30);

        Method method = JourneyRealizeHandler.class.getDeclaredMethod("calculateValues", GeographicPoint.class, GeographicPoint.class, LocalDateTime.class, LocalDateTime.class);
        method.setAccessible(true);

        assertDoesNotThrow(() -> method.invoke(journeyRH, startLocation, endLocation, startTime, endTime));
    }


    @Test
    void testCalculateDistance_Successful() throws Exception {
        GeographicPoint start = new GeographicPoint(10.0F, 20.0F);
        GeographicPoint end = new GeographicPoint(15.0F, 25.0F);

        Method method = JourneyRealizeHandler.class.getDeclaredMethod("calculateDistance", GeographicPoint.class, GeographicPoint.class);
        method.setAccessible(true);

        float distance = (float) method.invoke(journeyRH, start, end);

        assertEquals(10.0, distance, 0.01);
    }

    @Test
    void testCalculateDuration_Successful() throws Exception {
        LocalDateTime start = LocalDateTime.of(2023, 12, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2023, 12, 1, 10, 30);

        Method method = JourneyRealizeHandler.class.getDeclaredMethod("calculateDuration", LocalDateTime.class, LocalDateTime.class);
        method.setAccessible(true);

        int duration = (int) method.invoke(journeyRH, start, end);

        assertEquals(30, duration);
    }

    @Test
    void testCalculateAverageSpeed_Successful() throws Exception {
        float distance = 10.0f; // Km
        int duration = 30; // Min

        Method method = JourneyRealizeHandler.class.getDeclaredMethod("calculateAverageSpeed", float.class, int.class);
        method.setAccessible(true);

        float speed = (float) method.invoke(journeyRH, distance, duration);
        assertEquals(0.333, speed, 0.001);
    }
}
