package micromobility;

import java.time.LocalDateTime;
import data.*;

public class JourneyService {

    private UserAccount user;
    private PMVehicle vehicle;
    private GeographicPoint startLocation;
    private GeographicPoint endLocation;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public JourneyService(UserAccount user, PMVehicle vehicle, GeographicPoint startLocation, LocalDateTime startTime) {
        if (user == null || vehicle == null || startLocation == null || startTime == null) {
            throw new IllegalArgumentException("None of the arguments can be null.");
        }
        this.user = user;
        this.vehicle = vehicle;
        this.startLocation = startLocation;
        this.startTime = startTime;
    }

    public UserAccount getUser() {
        return user;
    }

    public PMVehicle getVehicle() {
        return vehicle;
    }

    public GeographicPoint getStartLocation() {
        return startLocation;
    }

    public GeographicPoint getEndLocation() {
        return endLocation;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setServiceInit() {
        vehicle.setUnderWay();
    }

    public void setServiceFinish(GeographicPoint endLocation, LocalDateTime endTime) {
        if (endLocation == null || endTime == null) {
            throw new IllegalArgumentException("End location and end time cannot be null.");
        }
        this.endLocation = endLocation;
        this.endTime = endTime;
        vehicle.setAvailb();
    }
}