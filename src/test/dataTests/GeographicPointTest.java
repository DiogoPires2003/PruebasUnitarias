package test.dataTests;
import data.GeographicPoint;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class GeographicPointTest {

    @Test
    void testConstructorValidValues() {
        GeographicPoint point = new GeographicPoint(40.7128f, -74.0060f);
        assertEquals(40.7128f, point.getLatitude());
        assertEquals(-74.0060f, point.getLongitude());
    }

    @Test
    void testEqualsAndHashCode() {
        GeographicPoint point1 = new GeographicPoint(40.7128f, -74.0060f);
        GeographicPoint point2 = new GeographicPoint(40.7128f, -74.0060f);
        assertEquals(point1, point2);
        assertEquals(point1.hashCode(), point2.hashCode());
    }

    @Test
    void testToString() {
        GeographicPoint point = new GeographicPoint(40.7128f, -74.0060f);
        assertEquals("Geographic point {latitude=40.7128, longitude=-74.006}", point.toString());
    }

}
