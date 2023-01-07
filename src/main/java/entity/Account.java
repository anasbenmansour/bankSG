package entity;


import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * the bank account entity
 */
public class Account {

    // user name
    @Getter
    private String name;
    @Getter
    private BigDecimal balance;
    //  user's operations list
    private List<Operation> operations;

    public Account(String name) {
        this.name = name;
        this.operations = new ArrayList<>();
        this.balance = BigDecimal.ZERO;
    }

    public List<Operation> getOperations() {
        return new ArrayList<>(operations);
    }

    public void addOperationAndSetBalance(Operation operation, BigDecimal balance) {
        this.operations.add(operation);
        this.balance = balance;
    }
}
