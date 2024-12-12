package data;

public class VehicleId {
    private final String id;

    public VehicleId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("VehicleID cannot be null or empty.");
        }
        if (!id.matches("[A-Z]{2}[0-9]{3}")) {
            throw new IllegalArgumentException("Invalid VehicleID format. Must be 2 letters followed by 3 digits.");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleId vehicleID = (VehicleId) o;
        return id.equals(vehicleID.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "VehicleID{" + "id='" + id + '\'' + '}';
    }
}
