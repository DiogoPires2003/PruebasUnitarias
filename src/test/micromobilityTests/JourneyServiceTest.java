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
    private GeographicPoint endlocation;
    private PMVehicle vehicle;
    private UserAccount user;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private JourneyService journey;
    private JourneyService finishedJourney;
    @BeforeEach
    void setUp() {
        this.startlocation = new GeographicPoint(0, 0);
        this.id = new VehicleID("AB345");
        this.location = new GeographicPoint(200,300);
        this.vehicle= new PMVehicle(id,location);
        this.user = new UserAccount("JourneyServiceTest");
        this.startTime = LocalDateTime.now();
        this.journey= new JourneyService(user,vehicle,startlocation,startTime);
        this.endTime = LocalDateTime.now();
        this.endlocation = new GeographicPoint(200,300);
        this.finishedJourney = new JourneyService(user,vehicle,startlocation,startTime);
        finishedJourney.setServiceFinish(endlocation,endTime);
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
        assertEquals(this.user, this.journey.getUser());
        assertEquals(this.vehicle, this.journey.getVehicle());
        assertEquals(this.startlocation, this.journey.getStartLocation());
        assertEquals(this.startTime, this.journey.getStartTime());
    }


    @Test
    @DisplayName("getUserTest")
    void test7(){
        assertEquals(user, journey.getUser());
    }
    @Test
    @DisplayName("getVehicleTest")
    void test8(){
        assertEquals(vehicle, journey.getVehicle());
    }
    @Test
    @DisplayName("getStartLocationTest")
    void test9(){
        assertEquals(startlocation, journey.getStartLocation());
    }
    @Test
    @DisplayName("getEndLocationTest")
    void test10(){
        assertEquals(endlocation, finishedJourney.getEndLocation());
    }
    @Test
    @DisplayName("getStartTimeTest")
    void test11(){
        assertEquals(startTime, journey.getStartTime());
    }
    @Test
    @DisplayName("getEndTimeTest")
    void test12(){
        assertEquals(endTime, finishedJourney.getEndTime());
    }
    @Test
    @DisplayName("setServiceInitTest")
    void test13(){
        assertEquals(PMVState.Available,vehicle.getState());
        journey.setServiceInit();
        assertEquals(PMVState.UnderWay,vehicle.getState());
    }

    @Test
    @DisplayName("setServiceFinishEndLocationNullTest")
    void test14(){
        assertThrows(IllegalArgumentException.class, () -> {
            journey.setServiceFinish(null,endTime);
        });
    }
    @Test
    @DisplayName("setServiceFinishEndTimeNullTest")
    void test15(){
        assertThrows(IllegalArgumentException.class, () -> {
            journey.setServiceFinish(endlocation,null);
        });
    }
    @Test
    @DisplayName("setServiceFinishAllNullTest")
    void test16(){
        assertThrows(IllegalArgumentException.class, () -> {
            journey.setServiceFinish(null,null);
        });
    }
    @Test
    @DisplayName("setServiceFinishSetsVehicleAvailableTest")
    void test17(){
        journey.setServiceFinish(endlocation,endTime);
        assertEquals(PMVState.Available, journey.getVehicle().getState());
    }

}
