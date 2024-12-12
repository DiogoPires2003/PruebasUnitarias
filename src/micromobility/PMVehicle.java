package micromobility;
import data.GeographicPoint;

public class PMVehicle {

    private PMVState state;
    private GeographicPoint location;

    public PMVehicle(PMVState state, GeographicPoint initialLocation) {
        this.state = state;
        this.location = initialLocation;
    }

    public PMVState getState() {
        return state;
    }

    public GeographicPoint getLocation() {
        return location;
    }

    public void setNotAvailb() {
        this.state = PMVState.NotAvailable;
    }

    public void setUnderWay() {
        this.state = PMVState.UnderWay;
    }

    public void setAvailb() {
        this.state = PMVState.Availbale;
    }

    public void setLocation(GeographicPoint gP) {
        this.location = gP;
    }
}



