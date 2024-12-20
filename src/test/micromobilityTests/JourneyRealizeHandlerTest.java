package test.micromobilityTests;

import data.*;
import micromobility.JourneyRealizeHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.*;
import services.smartfeatures.*;

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
    void setup() {
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


}
