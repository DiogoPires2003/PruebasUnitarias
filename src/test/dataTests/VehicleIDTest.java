package test.dataTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import data.VehicleID;
public class VehicleIDTest {

    VehicleID vh;
    @BeforeEach
    void setup(){
        vh = new VehicleID("AZ059");
    }

    //Tests del constructor
    @Test
    void VehicleIDConstructorTest(){
        assertThrows(IllegalArgumentException.class, () -> new VehicleID(""));
        assertThrows(IllegalArgumentException.class, () -> new VehicleID("AZE01"));
        assertThrows(IllegalArgumentException.class, () -> new VehicleID("as234"));
        assertThrows(IllegalArgumentException.class, () -> new VehicleID(null));
    }

    //Test del get
    @Test
    void VehicleIDGetTest(){
        assertEquals("AZ059", vh.getId());
    }

    //Tests del equals
    @Test
    void VehicleIDEqualsTest(){
        VehicleID vh2 = new VehicleID("AZ059");
        VehicleID vh3 = new VehicleID("AZ079");
        assertTrue(vh.equals(vh2));
        assertFalse(vh.equals(vh3));
    }

    @Test
    void VehicleIDToStringTest(){
        assertEquals(vh.toString(), "VehicleID{" + "id='" + vh.getId() + '\'' + '}');
    }

}
