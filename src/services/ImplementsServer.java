package services;

import data.*;
import micromobility.*;
import exceptions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ImplementsServer implements Server {

    @Override
    public void checkPMVAvail(VehicleID vhID) throws PMVNotAvailException, ConnectException {
        // TODO: Implement logic to check if a PMV (Personal Mobility Vehicle) is available
        if (vhID == null) {
            throw new PMVNotAvailException("Vehicle ID is null");
        }
        System.out.println("Checking PMV availability for vehicle: " + vhID);
    }

    @Override
    public void registerPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date) throws InvalidPairingArgsException, ConnectException {
        // TODO: Implement logic to register a pairing
        if (user == null || veh == null || st == null || loc == null || date == null) {
            throw new InvalidPairingArgsException("Invalid arguments for registering pairing");
        }
        System.out.println("Registering pairing: User=" + user + ", Vehicle=" + veh + ", Station=" + st + ", Location=" + loc + ", Date=" + date);
    }

    @Override
    public void stopPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date, float avSp, float dist, int dur, BigDecimal imp) throws InvalidPairingArgsException, ConnectException {
        // TODO: Implement logic to stop a pairing
        if (user == null || veh == null || st == null || loc == null || date == null || imp == null) {
            throw new InvalidPairingArgsException("Invalid arguments for stopping pairing");
        }
        System.out.println("Stopping pairing: User=" + user + ", Vehicle=" + veh + ", Station=" + st + ", Location=" + loc + ", Date=" + date + ", AvgSpeed=" + avSp + ", Distance=" + dist + ", Duration=" + dur + ", Impact=" + imp);
    }

    @Override
    public void setPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date) {
        // TODO: Implement logic to set a pairing
        System.out.println("Setting pairing: User=" + user + ", Vehicle=" + veh + ", Station=" + st + ", Location=" + loc + ", Date=" + date);
    }

    @Override
    public void unPairRegisterService(JourneyService s) throws PairingNotFoundException {
        // TODO: Implement logic to unpair and register a service
        if (s == null) {
            throw new PairingNotFoundException("Journey service not found");
        }
        System.out.println("Unpairing and registering service: " + s);
    }

    @Override
    public void registerLocation(VehicleID veh, StationID st) {
        // TODO: Implement logic to register a vehicle location
        if (veh == null || st == null) {
            throw new IllegalArgumentException("Vehicle ID or Station ID is null");
        }
        System.out.println("Registering location: Vehicle=" + veh + ", Station=" + st);
    }
}
