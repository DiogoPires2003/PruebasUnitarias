package micromobility;
import data.*;

public class PMVehicle {

    private VehicleID id;
    private PMVState state;
    private GeographicPoint location;

    public PMVehicle(VehicleID id, GeographicPoint location) {
        if (id == null || location == null) {
            throw new IllegalArgumentException("VehicleID and location cannot be null.");
        }
        this.id = id;
        this.location = location;
        this.state = PMVState.Available;
    }

    public VehicleID getId() {
        return id;
    }
    public GeographicPoint getLocation() {
        return location;
    }

    public PMVState getState() {
        return state;
    }
    public void setNotAvailb() {
        this.state = PMVState.NotAvailable;
    }

    public void setUnderWay() {
        this.state = PMVState.UnderWay;
    }

    public void setAvailb() {
        this.state = PMVState.Available;
    }

    public void setLocation(GeographicPoint location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null.");
        }
        this.location = location;
    }
}



