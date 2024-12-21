package test.micromobilityTests;

import data.*;
import exceptions.InvalidPairingArgsException;
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
    VehicleID vehicleId;
    PMVehicle vehicle;


    @BeforeEach
    void setup(){
        this.server = new ImplementsServer();
        this.qrDecoder = new ImplementsQRDecoder();
        this.arduinoMicroController = new ImplementsArduinoMicroController();
        this.btSignal = new ImplementsUnbondedBTSignal();
        this.journeyRH = new JourneyRealizeHandler(server, qrDecoder, arduinoMicroController, btSignal);
        this.vehicleId = new VehicleID("AA312");
        this.img = new BufferedImage(1,1,1);
        this.user = new UserAccount("usuario");
        this.station = new StationID("ACE12");
        this.point = new GeographicPoint(2500, 2500);
        this.date = LocalDateTime.now();
        this.vehicle=new PMVehicle(vehicleId,point);
    }

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
    //TODO tira la excepció equivocada

    @Test
    void ScanQRUnseccessfulTest() {
        assertThrows(InvalidPairingArgsException.class,() -> {
            journeyRH.scanQR(img, user, station, null, date);
        });
    }
    @Test
    void testUnPairVehicle_Successful(){
        float averageSpeed = 20.0f;
        float distance = 5.0f;
        int duration = 10;
        BigDecimal amount = BigDecimal.valueOf(100.0);

        assertDoesNotThrow(() -> journeyRH.unPairVehicle(user, vehicle, station, point, date, averageSpeed, distance, duration, amount));
    }

    @Test
    void testUnPairVehicle_NullVehicle_ShouldThrowException() {
        float averageSpeed = 20.0f;
        float distance = 5.0f;
        int duration = 10;
        BigDecimal amount = BigDecimal.valueOf(100.0);
        assertThrows(InvalidPairingArgsException.class, () -> journeyRH.unPairVehicle(user,null, station, point, date, averageSpeed, distance, duration, amount));
    }

    //TODO:PER ALGUNA RAÓ TIRA CONNECTION EXCEPTION
    @Test
    void testBroadcastStationID_Successful() {
        assertDoesNotThrow(() -> journeyRH.broadcastStationID(station));
    }
    //TODO:PER ALGUNA RAÓ TIRA CONNECTION EXCEPTION
    @Test
    void testStartDriving_Successful() {
        assertDoesNotThrow(() -> journeyRH.startDriving());
    }

    //TODO:PER ALGUNA RAÓ TIRA CONNECTION EXCEPTION
    @Test
    void testStopDriving_Successful() {
        assertDoesNotThrow(() -> journeyRH.stopDriving());
    }

}