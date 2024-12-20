package test.micromobilityTests;
import micromobility.JourneyRealizeHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ImplementsServer;
import services.Server;
import services.smartfeatures.*;

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



}
