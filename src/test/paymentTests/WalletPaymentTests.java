package test.paymentTests;

import data.*;
import exceptions.*;
import micromobility.payment.WalletPayment;
import org.junit.jupiter.api.*;
import services.ImplementsServer;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class WalletPaymentTests {

    private WalletPayment walletPayment;
    private UserAccount user;
    private char payMethod;
    private BigDecimal amount;
    private ServiceID serviceID;

    @BeforeEach
    public void setup() {
        this.walletPayment = new WalletPayment(new ImplementsServer());
        this.user = new UserAccount("tests");
        this.payMethod = 'W';
        this.amount = new BigDecimal(50);
        this.serviceID = new ServiceID("TEST12");
        user.getWallet().addBalance(10);
    }

    @Test
    public void payServiceTestNullCase() {
        assertThrows(IllegalArgumentException.class, () -> walletPayment.payService(null, user, amount, payMethod));
        assertThrows(IllegalArgumentException.class, () -> walletPayment.payService(serviceID, null, amount, payMethod));
        assertThrows(IllegalArgumentException.class, () -> walletPayment.payService(serviceID, user, null, payMethod));
    }

    @Test
    public void payServiceTestNotEnoughCase() {
        assertThrows(NotEnoughWalletException.class, () -> walletPayment.payService(serviceID, user, amount, payMethod));
    }

    @Test
    public void payServiceTestSuccessfullyCase() throws ConnectException {
        user.getWallet().setBalance(500);
        assertDoesNotThrow(() -> walletPayment.payService(serviceID, user, amount, payMethod));
        assertEquals(450, user.getWallet().getBalance());
    }

}
