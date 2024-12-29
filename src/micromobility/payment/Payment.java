package micromobility.payment;

import data.ServiceID;
import data.UserAccount;
import exceptions.ConnectException;
import exceptions.InvalidPairingArgsException;
import micromobility.JourneyRealizeHandler;
import services.Server;

import java.math.BigDecimal;

public class Payment {
    private final Server server;
    private JourneyRealizeHandler journeyRealizeHandler;
    private final WalletPayment walletPayment;
    private ServiceID serviceID;

    public Payment(Server server, WalletPayment walletPayment) {
        if (server == null || walletPayment == null) {
            throw new IllegalArgumentException("Dependencies cannot be null.");
        }
        this.server = server;
        this.walletPayment = walletPayment;
    }

    public void payService(ServiceID serviceID, UserAccount user, BigDecimal amount, char paymentMethod)
            throws ConnectException, InvalidPairingArgsException {
        if (serviceID == null || user == null || amount == null) {
            throw new InvalidPairingArgsException("Dependencies cannot be null.");
        }
        walletPayment.payService(serviceID, user, amount, paymentMethod);
    }
}
