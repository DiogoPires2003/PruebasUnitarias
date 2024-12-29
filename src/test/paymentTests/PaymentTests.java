package test.paymentTests;

import data.*;
import exceptions.InvalidPairingArgsException;
import micromobility.payment.*;
import org.junit.jupiter.api.*;
import services.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTests {
    Payment payment;
    Server server;
    WalletPayment walletPayment;
    ServiceID serviceID;
    UserAccount user;
    BigDecimal amount;
    char method;

    @BeforeEach
    public void setup(){
        this.server = new ImplementsServer();
        this.walletPayment = new WalletPayment(server);
        this.payment = new Payment(server,walletPayment);
        this.serviceID = new ServiceID("TEST12");
        this.user = new UserAccount("tests");
        this.amount = new BigDecimal(10);
        this.method = 'W';
        this.user.getWallet().addBalance(50);
    }

    @Test
    public void PaymentConstructorTest() {
        assertThrows(IllegalArgumentException.class, () -> new Payment(null, walletPayment));
        assertThrows(IllegalArgumentException.class, () -> new Payment(server, null));
    }

    @Test
    public void payServiceTestNullCase(){
        assertThrows(InvalidPairingArgsException.class, () -> payment.payService(null, user, amount, method));
        assertThrows(InvalidPairingArgsException.class, () -> payment.payService(serviceID, null, amount, method));
        assertThrows(InvalidPairingArgsException.class, () -> payment.payService(serviceID, user, null, method));
    }

    @Test
    public void payServiceTestSuccessfullyCase(){
        assertDoesNotThrow(() -> payment.payService(serviceID,user,amount,method));
    }

}
