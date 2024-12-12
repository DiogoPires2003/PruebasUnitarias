package micromobility;

import services.*;
import services.smartfeatures.*;
import data.*;
import exceptions.*;
import java.time.LocalDateTime;
import java.net.ConnectException;

public class JourneyRealizeHandler {

    private final QRDecoder qrDecoder;
    private final Server server;
    private JourneyService currentJourney;

    // Constructor donde se inyectan dependencias
    public JourneyRealizeHandler(QRDecoder qrDecoder, Server server) {
        this.qrDecoder = qrDecoder;
        this.server = server;
        this.currentJourney = null; // No hay un viaje activo inicialmente
    }

    public void scanQR() throws ConnectException, InvalidPairingException,
            CorruptedImgException, PMVNotAvailException, ProceduralException {
        try {
            String qrData = qrDecoder.decodeQR();
            if (!server.checkVehicleAvailability(qrData)) {
                throw new PMVNotAvailException("Vehicle is not available.");
            }
            server.prepareJourney(qrData);

            // Crear un nuevo JourneyService para el estado del viaje
            this.currentJourney = new JourneyService(qrData, "currentUserId"); // Usar ID de usuario actual
        } catch (ConnectException | InvalidPairingException | CorruptedImgException e) {
            // Catching specific exceptions here just to re-throw them
            throw e;
        }
    }

    public void unPairVehicle() throws ConnectException, InvalidPairingException,
            PairingNotFoundException, ProceduralException {
        if (currentJourney == null || !currentJourney.isServiceActive()) {
            throw new ProceduralException("No active journey to unpair.");
        }
        try {
            server.finishJourney();
            currentJourney.setServiceFinish();
        } catch (ConnectException | InvalidPairingException | PairingNotFoundException e) {
            // Pass exception up
            throw e;
        }
    }

    public void broadcastStationID(StationID stID) throws ConnectException {
        try {
            server.broadcastStation(stID); // Assuming there is a method server.broadcastStation()
        } catch (ConnectException e) {
            throw e; // Re-throwing exception after catching it
        }
    }

    public void startDriving() throws ConnectException, ProceduralException {
        if (currentJourney == null || !currentJourney.isServiceActive()) {
            throw new ProceduralException("No active journey to start driving.");
        }
        try {
            server.startDriving();
        } catch (ConnectException | ProceduralException e) {
            // Re-throwing exception
            throw e;
        }
    }

    public void stopDriving() throws ConnectException, ProceduralException {
        if (currentJourney == null || !currentJourney.isServiceActive()) {
            throw new ProceduralException("No active journey to stop.");
        }
        try {
            server.stopDriving();
        } catch (ConnectException | ProceduralException e) {
            throw e;
        }
    }

    // Métodos internos para cálculos relacionados con el servicio
    private void calculateValues(GeographicPoint gP, LocalDateTime date) {
        double distance = server.calculateDistance(gP);
        int duration = server.calculateDuration(date);
        double averageSpeed = server.calculateAverageSpeed(distance, duration);

        if (currentJourney != null) {
            currentJourney.setDistance(distance);
        }

        System.out.println("Distance: " + distance);
        System.out.println("Duration: " + duration);
        System.out.println("Average Speed: " + averageSpeed);
    }

    private void calculateImport(float dis, int dur, float avSp, LocalDateTime date) {
        double cost = server.calculateCost(dis, dur, avSp, date);
        if (currentJourney != null) {
            currentJourney.setCost(cost);
        }
        System.out.println("Cost of the journey: " + cost);
    }
}