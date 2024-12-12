package micromobility;

import java.time.LocalDateTime;

public class JourneyService {

    private String vehicleId;
    private String userId;
    private LocalDateTime serviceStart;
    private LocalDateTime serviceEnd;
    private double distance;
    private double cost;

    public JourneyService(String vehicleId, String userId) {
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.serviceStart = null;
        this.serviceEnd = null;
        this.distance = 0.0;
        this.cost = 0.0;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getServiceStart() {
        return serviceStart;
    }

    public LocalDateTime getServiceEnd() {
        return serviceEnd;
    }

    public double getDistance() {
        return distance;
    }

    public double getCost() {
        return cost;
    }

    public void setServiceInit() {
        this.serviceStart = LocalDateTime.now();
    }

    public void setServiceFinish() {
        this.serviceEnd = LocalDateTime.now();
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isServiceActive() {
        return serviceStart != null && serviceEnd == null;
    }
}