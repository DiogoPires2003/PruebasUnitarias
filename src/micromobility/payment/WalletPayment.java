package micromobility.payment;
//TODO PAYMENT

import data.*;
import exceptions.*;
import services.*;
import services.smartfeatures.*;
import micromobility.*;

import java.math.BigDecimal;


public class WalletPayment {
    public WalletPayment() {

    }
    public void payService(ServiceID serviceID, UserAccount user, BigDecimal amount, char paymentMethod) {
        System.out.println("Paying service: ServiceID=" + serviceID + ", User=" + user + ", Amount=" + amount + ", PaymentMethod=" + paymentMethod);
    }
}
