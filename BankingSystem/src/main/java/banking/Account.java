package banking;

/**
 * Abstract bank account.
 */
public abstract class Account implements AccountInterface {
    private AccountHolder accountHolder;
    private Long accountNumber;
    private int pin;
    private double balance;

    protected Account(AccountHolder accountHolder, Long accountNumber, int pin, double startingDeposit) {
       this.accountHolder = accountHolder;
       this.accountNumber = accountNumber;
       this.pin = pin;
       this.balance = startingDeposit;
    }

    public AccountHolder getAccountHolder() {
        return this.accountHolder;
    }

    public boolean validatePin(int attemptedPin) {
        return (pin == attemptedPin);
    }

    public double getBalance() {
        return this.balance;
    }

    public Long getAccountNumber() {
        return this.accountNumber;
    }

    public void creditAccount(double amount) {
        this.balance = this.balance + amount;
    }

    public boolean debitAccount(double amount) {
        if( amount > this.balance ) return false;
        else this.balance -= amount;
        return true;
    }
}
