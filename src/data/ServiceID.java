package data;

public final class ServiceID {
    private final String id;

    public ServiceID(String id) {
        if(id==null||id.isEmpty()){
            throw new IllegalArgumentException("ServiceId cannot be null or empty");
        }
        if (!id.matches("[A-Z0-9]{4,10}")){
            throw new IllegalArgumentException("Invalid ServiceId format. Must be 4-10 alphanumeric characters.");
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
        ServiceID serviceId = (ServiceID) o;
        return id.equals(serviceId.id);
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
