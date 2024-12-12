package services.smartfeatures;

import java.net.ConnectException;
import exceptions.*;

public interface ArduinoMicroController {
    void setBTconnection() throws ConnectException;

    void startDriving() throws PMVPhisicalException, ConnectException, ProceduralException;

    void stopDriving() throws PMVPhisicalException, ConnectException, ProceduralException;

    void undoBTconnection();
}
