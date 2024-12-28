package micromobility.payment;
//TODO PAYMENT
import data.*;
import exceptions.*;
import services.*;
import services.smartfeatures.*;
import micromobility.*;

import java.math.BigDecimal;
import java.util.GregorianCalendar;


public class WalletPayment {
    private Server server;
    public void payService(ServiceID serviceID, UserAccount user, BigDecimal amount, char paymentMethod) throws ConnectException {
        if (serviceID == null || user == null || amount == null) {
            throw new IllegalArgumentException("Service ID, User Account or Amount cannot be null.");
        }
       server.registerPayment(serviceID, user, amount, paymentMethod);
    }
}
