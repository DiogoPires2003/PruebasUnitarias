package services.smartfeatures;

import data.VehicleID;
import java.awt.image.BufferedImage;

import exceptions.ConnectException;
import exceptions.CorruptedImgException;

public class ImplementsUnbondedBTSignal implements UnbondedBTSignal {

    private boolean isBroadcasting;

    public ImplementsUnbondedBTSignal() {
        this.isBroadcasting = false;
    }

    @Override
    public void BTbroadcast() throws ConnectException {
        startBroadcasting();
        if (!isBroadcasting) {
            throw new ConnectException("Bluetooth connection is not established.");
        }

        new Thread(() -> {
            try {
                while (isBroadcasting) {
                    broadcastStationID();
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                stopBroadcasting();
                System.err.println("Broadcasting interrupted: " + e.getMessage());
            }
        }).start();
    }

    public void startBroadcasting() {
        isBroadcasting = true;
        System.out.println("Broadcasting started.");
    }

    public void stopBroadcasting() {
        isBroadcasting = false;
        System.out.println("Broadcasting stopped.");
    }

    private void broadcastStationID() {
        System.out.println("Broadcasting station ID: ST-1234");
    }
}