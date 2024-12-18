package test.micromobilityTests;

import micromobility.*;
import data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PMVehicleTest {
    private GeographicPoint location;
    private VehicleID id;
    private PMVehicle vehicle;
    @BeforeEach
    void setUp() {
        this.location = new GeographicPoint(200, 200);
        this.id = new VehicleID("AA123");
        this.vehicle = new PMVehicle(id, location);
    }

    @Test
    @DisplayName("PMVehicleConstructorTestWithIdAndLocationNull")
    public void Test1() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PMVehicle(null, null);
        });
    }

    @Test
    @DisplayName("PMVehicleConstructorTestWithNullId")
    public void Test2() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PMVehicle(null, location);

        });
    }

    @Test
    @DisplayName("PMVehicleConstructorTestWithNullLocation")
    public void Test3() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PMVehicle(id, null);
        });
    }

    @Test
    @DisplayName("getIdTest")
    public void Test4() {
        assertEquals(id, vehicle.getId());
    }

    @Test
    @DisplayName("getLocationTest")
    public void Test5() {
        assertEquals(location, vehicle.getLocation());
    }

    @Test
    @DisplayName("getStateTest")
    public void Test6() {
        assertEquals(PMVState.Available, vehicle.getState());
    }
    @Test
    @DisplayName("setNotAvailbTest")
    public void Test7() {
        vehicle.setNotAvailb();
        assertEquals(PMVState.NotAvailable, vehicle.getState());
    }
    @Test
    @DisplayName("setUnderWayTest")
    public void Test8() {
        vehicle.setUnderWay();
        assertEquals(PMVState.UnderWay, vehicle.getState());
    }
    @Test
    @DisplayName("setAvailbTest")
    public void Test9() {
        vehicle.setAvailb();
        assertEquals(PMVState.Available, vehicle.getState());
    }
    @Test
    @DisplayName("setLocationNULLExcetionTest")
    public void Test10() {
        assertThrows(IllegalArgumentException.class, () -> {vehicle.setLocation(null);});
    }
    @Test
    @DisplayName("setLocationTest")
    public void Test11() {
        GeographicPoint location2 =new GeographicPoint(3000,200);
        vehicle.setLocation(location2);
        assertEquals(location2,vehicle.getLocation());
    }
}
