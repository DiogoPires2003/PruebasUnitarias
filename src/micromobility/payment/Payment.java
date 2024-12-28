package micromobility.payment;
import data.*;
import exceptions.*;
import services.*;
import services.smartfeatures.*;
import micromobility.*;

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
        server.registerPayment(serviceID, user, amount, paymentMethod);
        walletPayment.payService(serviceID, user, amount, paymentMethod);
    }
}
