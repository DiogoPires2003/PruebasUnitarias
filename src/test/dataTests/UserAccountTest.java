package test.dataTests;
import data.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class UserAccountTest {
    private UserAccount user;
    @BeforeEach
    void setUp() {
        this.user=new UserAccount("user123");
    }
    @Test
    @DisplayName("UserAccountConstructorNullUsernameTest")
    void test1(){
        assertThrows(IllegalArgumentException.class, ()->new UserAccount(null));
    }
    @Test
    @DisplayName("UserAccountConstructorInvalidShortUsernameTest")
    void test2(){
        assertThrows(IllegalArgumentException.class, ()->new UserAccount("1234"));
    }
    @Test
    @DisplayName("UserAccountConstructorInvalidLongUsernameTest")
    void test3(){
        assertThrows(IllegalArgumentException.class, ()->new UserAccount("123456789abcdefghijkl"));
    }
    @Test
    @DisplayName("getUsernameTest")
    void test4(){
        assertEquals("user123", user.getUsername());
    }
    @Test
    @DisplayName("equalsItselfTest")
    void test5(){
        assertTrue(user.equals(user));
    }
    @Test
    @DisplayName("equalsShouldReturnFalseWhenDifferentUser")
    void test6(){
        assertFalse(user.equals(new UserAccount("user132")));
    }
    @Test
    @DisplayName("hashCodeTest")
    void test7(){
        assertEquals(user.hashCode(), user.hashCode());
    }
    @Test
    @DisplayName("toStringTest")
    void test8(){
        assertEquals("UserAccount{username='user123'}", user.toString());
    }


}
