package test.micromobilityTests;
import micromobility.*;
import data.*;
import org.junit.jupiter.api.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
public class JourneyServiceTest {
    private VehicleID id;
    private GeographicPoint location;
    private GeographicPoint startlocation;
    private PMVehicle vehicle;
    private UserAccount user;
    private LocalDateTime startTime;
    private JourneyService journey;
    @BeforeEach
    void setUp() {
        VehicleID id = new VehicleID("AB345");
        GeographicPoint location = new GeographicPoint(200,300);
        GeographicPoint startlocation = new GeographicPoint(200,400);
        PMVehicle vehicle= new PMVehicle(id,location);
        UserAccount user = new UserAccount("JourneyServiceTest");
    }

    @Test
    @DisplayName("JourneyServiceConstructorNullUserTest")
    void test1(){
        assertThrows(IllegalArgumentException.class, () -> {
            new JourneyService(null, vehicle,startlocation,startTime);
        });
    }
    @Test
    @DisplayName("JourneyServiceConstructorNullVehicleTest")
    void test2(){
        assertThrows(IllegalArgumentException.class, () -> {
            new JourneyService(user, null,startlocation,startTime);
        });
    }
    @Test
    @DisplayName("JourneyServiceConstructorNullstartlocationTest")
    void test3(){
        assertThrows(IllegalArgumentException.class, () -> {
            new JourneyService(user, vehicle,null,startTime);
        });
    }
    @Test
    @DisplayName("JourneyServiceConstructorNullStartTimeTest")
    void test4(){
        assertThrows(IllegalArgumentException.class, () -> {
            new JourneyService(user, vehicle,startlocation,null);
        });
    }
    @Test
    @DisplayName("JourneyServiceConstructorAllNullTest")
    void test5(){
        assertThrows(IllegalArgumentException.class, () -> {
            new JourneyService(null, null,null,null);
        });
    }

    @Test
    @DisplayName("JourneyServiceConstructorNothingNullTest")
    void test6(){
        new JourneyService(user,vehicle,startlocation,startTime);
    }

}
