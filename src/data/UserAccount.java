package data;

import micromobility.payment.Wallet;

public final class UserAccount {
    private final String username;
    private Wallet wallet;

    public UserAccount(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("UserAccount cannot be null or empty.");
        }
        if (!username.matches("[a-zA-Z0-9_.]{5,20}")) {
            throw new IllegalArgumentException("Invalid UserAccount format. Must be 5-20 characters long and can include letters, numbers, underscores, and dots.");
        }
        this.username = username;
        this.wallet = new Wallet(0);
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "UserAccount{" + "username='" + username + '\'' + '}';
    }

    public Wallet getWallet() {
        return this.wallet;
    }
}
