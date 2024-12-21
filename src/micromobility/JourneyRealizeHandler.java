package micromobility;

import data.*;
import services.*;
import services.smartfeatures.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import exceptions.*;

/**
 * Handles the realization of journeys, managing events and dependencies.
 */
public class JourneyRealizeHandler {

    private Server server;
    private QRDecoder qrDecoder;
    private ArduinoMicroController arduinoMicroController;
    private UnbondedBTSignal btSignal;

    public JourneyRealizeHandler(Server server, QRDecoder qrDecoder, ArduinoMicroController arduinoMicroController, UnbondedBTSignal btSignal) {
        if (server == null || qrDecoder == null || arduinoMicroController == null || btSignal == null) {
            throw new IllegalArgumentException("Dependencies cannot be null.");
        }
        this.server = server;
        this.qrDecoder = qrDecoder;
        this.arduinoMicroController = arduinoMicroController;
        this.btSignal = btSignal;
    }

    public void scanQR(BufferedImage qrImage, UserAccount user, StationID stationID, GeographicPoint location, LocalDateTime date)
            throws ConnectException, InvalidPairingArgsException, CorruptedImgException, PMVNotAvailException, ProceduralException {
        VehicleID vehicleID = qrDecoder.getVehicleID(qrImage);
        server.checkPMVAvail(vehicleID);

        PMVehicle vehicle = new PMVehicle(vehicleID, location);
        JourneyService journeyService = new JourneyService(user, vehicle, location, date);

        server.registerPairing(user, vehicleID, stationID, location, date);
        vehicle.setNotAvailb();
        journeyService.setServiceInit();
    }

    public void unPairVehicle(UserAccount user, PMVehicle vehicle, StationID stationID, GeographicPoint location, LocalDateTime date, float averageSpeed, float distance, int duration, BigDecimal amount)
            throws ConnectException, InvalidPairingArgsException, PairingNotFoundException, ProceduralException {
        if (user == null || vehicle == null || stationID == null || location == null || date == null || amount == null) {
            throw new InvalidPairingArgsException("Dependencies cannot be null.");
        }
        server.stopPairing(user, vehicle.getId(), stationID, location, date, averageSpeed, distance, duration, amount);
        vehicle.setAvailb();
        vehicle.setLocation(location);
    }

    public void broadcastStationID(StationID stationID) throws ConnectException {
        btSignal.BTbroadcast();
    }

    public void startDriving() throws ConnectException, ProceduralException, PMVPhisicalException {
        arduinoMicroController.startDriving();
    }

    public void stopDriving() throws ConnectException, ProceduralException, PMVNotAvailException, PMVPhisicalException, ConnectException {
        arduinoMicroController.stopDriving();
    }

    private void calculateValues(GeographicPoint startLocation, GeographicPoint endLocation, LocalDateTime startTime, LocalDateTime endTime)
            throws ProceduralException {
        if (startLocation == null || endLocation == null || startTime == null || endTime == null) {
            throw new ProceduralException("Invalid inputs for calculating values.");
        }
        float distance = calculateDistance(startLocation, endLocation);
        int duration = calculateDuration(startTime, endTime);
        float averageSpeed = calculateAverageSpeed(distance, duration);
    }

    private float calculateDistance(GeographicPoint start, GeographicPoint end) {
        return Math.abs(start.getLatitude() - end.getLatitude()) + Math.abs(start.getLongitude() - end.getLongitude());
    }

    private int calculateDuration(LocalDateTime start, LocalDateTime end) {
        return (int) java.time.Duration.between(start, end).toMinutes();
    }

    private float calculateAverageSpeed(float distance, int duration) {
        return duration > 0 ? distance / duration : 0;
    }
}