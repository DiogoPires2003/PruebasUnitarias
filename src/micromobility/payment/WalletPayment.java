package micromobility.payment;

import data.ServiceID;
import data.UserAccount;
import exceptions.ConnectException;
import exceptions.NotEnoughWalletException;
import services.Server;

import java.math.BigDecimal;


public class WalletPayment {
    private Server server;

    public WalletPayment (Server server){
        this.server = server;
    }

    public void payService(ServiceID serviceID, UserAccount user, BigDecimal amount, char paymentMethod) throws ConnectException {
        if (serviceID == null || user == null || amount == null) {
            throw new IllegalArgumentException("Service ID, User Account or Amount cannot be null.");
        }
        if (user.getWallet().getBalance() <= 0 || user.getWallet().getBalance() < amount.floatValue()) {
            throw new NotEnoughWalletException("Not enough balance");
        }
        server.registerPayment(serviceID, user, amount, paymentMethod);
    }
}
