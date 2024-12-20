package test.micromobilityTests;

import data.*;
import micromobility.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.*;
import services.smartfeatures.*;
import java.math.BigDecimal;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JourneyRealizeHandlerTest {

    JourneyRealizeHandler journeyRH;
    Server server;
    QRDecoder qrDecoder;
    ArduinoMicroController arduinoMicroController;
    UnbondedBTSignal btSignal;
    BufferedImage img;
    UserAccount user;
    StationID station;
    GeographicPoint point;
    LocalDateTime date;


    @BeforeEach
    void setup(){
        this.server = new ImplementsServer();
        this.qrDecoder = new ImplementsQRDecoder();
        this.arduinoMicroController = new ImplementsArduinoMicroController();
        this.btSignal = new ImplementsUnbondedBTSignal();
        this.journeyRH = new JourneyRealizeHandler(server, qrDecoder, arduinoMicroController, btSignal);
        VehicleID vehicleID = new VehicleID("AA312");
        this.img = new BufferedImage(1,1,1);
        this.user = new UserAccount("usuario");
        this.station = new StationID("ACE12");
        this.point = new GeographicPoint(2500, 2500);
        this.date = LocalDateTime.now();
    }

    //Comprovem que el constructor doni excepció quan una variable es null
    @Test
    void JourneyRealizeHandlerConstructorNullTest() {
        assertThrows(IllegalArgumentException.class, () -> new JourneyRealizeHandler(server, qrDecoder, arduinoMicroController, null));
        assertThrows(IllegalArgumentException.class, () -> new JourneyRealizeHandler(server, qrDecoder, null, btSignal));
        assertThrows(IllegalArgumentException.class, () -> new JourneyRealizeHandler(null, qrDecoder, arduinoMicroController, btSignal));
        assertThrows(IllegalArgumentException.class, () -> new JourneyRealizeHandler(server, null, arduinoMicroController, btSignal));
    }

    @Test
    void ScanQRTest() {
        assertDoesNotThrow(() -> journeyRH.scanQR(img, user, station, point, date));
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


}
