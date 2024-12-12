package data;

public final class StationId {

    private final String id;

    public StationId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("StationID cannot be null or empty.");
        }
        if (!id.matches("[A-Z0-9]{4,10}")) {
            throw new IllegalArgumentException("Invalid StationID format. Must be 4-10 alphanumeric characters.");
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
        StationId stationID = (StationId) o;
        return id.equals(stationID.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "StationID{" + "id='" + id + '\'' + '}';
    }
}
