package test.dataTests;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import data.VehicleID;
public class VehicleIDTest {
    //Aqui començent els testos del constructor
    @Test
    void VehicleIDConstructorTest(){
        assertThrows(IllegalArgumentException.class, () -> new VehicleID(""));
    }

}
