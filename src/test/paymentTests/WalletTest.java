package test.paymentTests;

import micromobility.payment.Wallet;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class WalletTest {

    private Wallet w;

    @BeforeEach
    public void setup() {
        this.w = new Wallet(0);
    }

    @Test
    public void walletConstructorTest() {
        assertThrows(IllegalArgumentException.class, () -> new Wallet(-12));
    }

    @Test
    public void walletGetSetTests() {
        assertEquals(0, w.getBalance());
        w.setBalance(20);
        assertEquals(20, w.getBalance());
        w.addBalance(20);
        assertEquals(40, w.getBalance());
        w.substractBalance(20);
        assertEquals(20, w.getBalance());
        assertTrue(w.hasEnoughBalance(20));
    }

    @Test
    public void walletPayTest() {
        w.addBalance(500);
        assertThrows(IllegalArgumentException.class, () -> w.pay(501));
        w.pay(200);
        assertEquals(300, w.getBalance());
    }


}
