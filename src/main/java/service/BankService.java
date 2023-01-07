package service;

import entity.Account;
import entity.Operation;
import entity.OperationType;
import exception.BalanceException;

import java.math.BigDecimal;

/**
 * Bank service in which we implement the bank methods as deposit, withdraw and
 * prints history
 */

public class BankService {

    private final Account account;

    public BankService(String name) {
        this.account = new Account(name);
    }

    /**
     * Withdraw the given amount from the account thread safe method
     */
    public void withdraw(BigDecimal amount) {
        //Amount must be positive
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            synchronized (account) {
                //check if the client has enough balance for the amount
                if (account.getBalance().compareTo(amount) > 0) {
                    final BigDecimal newBalance = account.getBalance().subtract(amount);
                    final Operation operation = new Operation(OperationType.WITHDRAWAL, amount, newBalance);
                    account.addOperationAndSetBalance(operation, newBalance);
                } else {
                    throw new BalanceException("You do not have balance for this withdrawal " + amount);
                }
            }
        } else {
            throw new IllegalArgumentException("The amount of withdrawal must be positive and >0");
        }
    }

    /**
     * Deposit the given amount into the account thread safe method
     */
    public void deposit(BigDecimal amount) {
        //Amount must be positive
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            synchronized (account) {
                final BigDecimal newBalance = account.getBalance().add(amount);
                final Operation operation = new Operation(OperationType.DEPOSIT, amount, newBalance);
                account.addOperationAndSetBalance(operation, newBalance);
            }
        } else {
            throw new IllegalArgumentException("The amount of deposit must be positive and >0");
        }
    }

    /**
     * Prints the account history by status
     */
    public void printAccountHistory() {
        //display account name and number
        System.out.println("Name :" + account.getName() + "\n Operations : ");
        if (account.getOperations().size() == 0) {
            System.out.println("No Saved  operations");
        } else {
            // print history
            account.getOperations().forEach(System.out::println);
        }
    }

    // protected modifier for test purpose
    protected Account getAccount() {
        return account;
    }
}
