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
        // Simulate the process of establishing a Bluetooth connection.
        try {
            // Add logic for actual BT connection here
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
        // Simulate the process of starting to drive.
        try {
            // Add logic for actual start driving here
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
        // Simulate the process of stopping driving.
        try {
            // Add logic for actual stop driving here
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
        // Simulate the process of disconnecting Bluetooth.
        isConnected = false;
        isDriving = false; // Ensure driving stops if connection is undone.
        System.out.println("Bluetooth connection undone.");
    }
}
