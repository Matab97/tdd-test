package banking;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Bank implementation.
 */
public class Bank implements BankInterface {
    private LinkedHashMap<Long, Account> accounts;

    private static long accountCounter = 0;

    public Bank() {
        accounts = new LinkedHashMap<>();
    }

    private Account getAccount(Long accountNumber) {
        return this.accounts.getOrDefault(accountNumber, null);
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        long id = accountCounter++;
        Account account = new CommercialAccount(company, id, pin, startingDeposit);
        accounts.put(id, account);
        return id;
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        long id = accountCounter++;
        Account account = new ConsumerAccount(person, id, pin, startingDeposit);
        accounts.put(id, account);
        return id;
    }

    public double getBalance(Long accountNumber) {
        if (!accounts.containsKey(accountNumber)) return -1;
        // TODO: complete the method
        return accounts.get(accountNumber).getBalance();
    }

    public void credit(Long accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        account.creditAccount(amount);
        accounts.put(accountNumber, account);
    }

    public boolean debit(Long accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        boolean debitted = account.debitAccount(amount);
        if (debitted) accounts.put(accountNumber, account);
        ;
        return debitted;
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        Account account = accounts.get(accountNumber);
        return account.validatePin(pin);
    }

    public void addAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        Account account = accounts.get(accountNumber);
        if (account instanceof CommercialAccount) {
            ((CommercialAccount) account).addAuthorizedUser(authorizedPerson);
        }
    }

    public boolean checkAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        Account account = accounts.get(accountNumber);
        if (account instanceof CommercialAccount) {
            return ((CommercialAccount) account).isAuthorizedUser(authorizedPerson);
        }
        return false;
    }

    public Map<String, Double> getAverageBalanceReport() {
        Map<String, Double> result = new HashMap<>();
        result.put("ConsumerAccount", 0.0);
        result.put("CommercialAccount", 0.0);
        AtomicInteger commercialCounter = new AtomicInteger();
        AtomicInteger consumerCounter = new AtomicInteger();
        accounts.values().forEach(account -> {
                    if (account instanceof CommercialAccount){
                        commercialCounter.getAndIncrement();
                        result.put("CommercialAccount", result.get("CommercialAccount") + account.getBalance());
                    }
                    else {
                        consumerCounter.getAndIncrement();
                        result.put("ConsumerAccount", result.get("ConsumerAccount") + account.getBalance());
                    }
                }
        );
        result.put("CommercialAccount", result.get("CommercialAccount") / commercialCounter.get());
        result.put("ConsumerAccount", result.get("ConsumerAccount") / consumerCounter.get());
        return result;
    }

}
