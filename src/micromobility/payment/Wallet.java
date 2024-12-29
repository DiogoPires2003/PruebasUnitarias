package micromobility.payment;
public class Wallet {
    private float balance;
    public Wallet(float balance){
        if (balance < 0){
            throw new IllegalArgumentException("Balance can't be negative.");
        }
        this.balance = balance;
    }
    public float getBalance(){
        return balance;
    }
    public void setBalance(float balance){
        this.balance = balance;
    }
    public void addBalance(float amount){
        balance += amount;
    }
    public void substractBalance(float amount){
        balance -= amount;
    }
    public boolean hasEnoughBalance(float amount){
        return balance >= amount;
    }
    public void pay(float amount){
        if(hasEnoughBalance(amount)){
            substractBalance(amount);
        }else{
            throw new IllegalArgumentException("Not enough balance");
        }
    }
}
