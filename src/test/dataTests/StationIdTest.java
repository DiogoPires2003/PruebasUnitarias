package test.dataTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import data.StationID;

public class StationIdTest {

    public StationID st;

    @BeforeEach
    void setup(){
        this.st = new StationID("AE123");
    }

    @Test
    void ConstructorNullTest1(){
        assertThrows(IllegalArgumentException.class, () -> new StationID(null) );
    }

    @Test
    void ConstructorNullTest2(){
        assertThrows(IllegalArgumentException.class, () -> new StationID("") );
    }

    @Test
    void InvalidIdTest(){
        assertThrows(IllegalArgumentException.class, () -> new StationID("ASDF123412365") );
        assertThrows(IllegalArgumentException.class, () -> new StationID("AS") );
        assertThrows(IllegalArgumentException.class, () -> new StationID("asdfghj") );
    }

    @Test
    void GetTest(){
        assertEquals("AE123", st.getId());
    }

    @Test
    void toStringTest(){
        assertEquals("StationID{" + "id='" + st.getId() + '\'' + '}', st.toString());
    }

}
