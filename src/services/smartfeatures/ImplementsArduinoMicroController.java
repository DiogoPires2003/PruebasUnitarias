package services.smartfeatures;

import exceptions.*;

public class ImplementsArduinoMicroController implements ArduinoMicroController {

    private boolean isConnected = false;
    private boolean isDriving = false;

    @Override
    public void setBTconnection() throws ConnectException {
        if (isConnected) {
            throw new ConnectException("Bluetooth connection is already established.");
        }
        try {
            isConnected = true;
            System.out.println("Bluetooth connection established.");
        } catch (Exception e) {
            throw new ConnectException("Failed to establish Bluetooth connection.", e);
        }
    }

    @Override
    public void startDriving() throws PMVPhisicalException, ConnectException, ProceduralException {
        if (!isConnected) {
            throw new ConnectException("Cannot start driving: Bluetooth connection is not established.");
        }
        if (isDriving) {
            throw new ProceduralException("Driving has already started.");
        }
        try {
            isDriving = true;
            System.out.println("Driving started.");
        } catch (Exception e) {
            throw new PMVPhisicalException("Physical error occurred while starting driving.", e);
        }
    }

    @Override
    public void stopDriving() throws PMVPhisicalException, ConnectException, ProceduralException {
        if (!isConnected) {
            throw new ConnectException("Cannot stop driving: Bluetooth connection is not established.");
        }
        if (!isDriving) {
            throw new ProceduralException("Cannot stop driving: Driving has not started.");
        }
        try {
            isDriving = false;
            System.out.println("Driving stopped.");
        } catch (Exception e) {
            throw new PMVPhisicalException("Physical error occurred while stopping driving.", e);
        }
    }

    @Override
    public void undoBTconnection() {
        if (!isConnected) {
            System.out.println("No Bluetooth connection to undo.");
            return;
        }
        isConnected = false;
        isDriving = false;
        System.out.println("Bluetooth connection undone.");
    }
}
