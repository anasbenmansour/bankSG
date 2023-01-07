package service;

import entity.OperationType;
import exception.BalanceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class BankServiceTest {

    private BankService myBank;

    @Before
    public void setUp() {
        myBank = new BankService("My bank account");
    }

    @Test
    public void testDeposit_GIVEN_DepositValidInput_SHOULD_AddNewOperationHistory() {
        // GIVEN
        myBank.deposit(new BigDecimal(5));
        //THEN
        Assert.assertEquals(1, myBank.getAccount().getOperations().size());
        Assert.assertEquals(OperationType.DEPOSIT, myBank.getAccount().getOperations().get(0).getType());
        Assert.assertEquals(new BigDecimal(5), myBank.getAccount().getBalance());
        // GIVEN
        myBank.deposit(new BigDecimal(15));
        //THEN
        Assert.assertEquals(2, myBank.getAccount().getOperations().size());
        Assert.assertEquals(OperationType.DEPOSIT, myBank.getAccount().getOperations().get(1).getType());
        Assert.assertEquals(new BigDecimal(20), myBank.getAccount().getBalance());
    }

    @Test
    public void testWithdraw_GIVEN_DepositAndWithdrawValidInput_SHOULD_ADD_AddNewOperationHistory() {
        // GIVEN
        myBank.deposit(new BigDecimal(5));
        myBank.withdraw(new BigDecimal(3));
        //THEN
        Assert.assertEquals(2, myBank.getAccount().getOperations().size());
        Assert.assertEquals(OperationType.DEPOSIT, myBank.getAccount().getOperations().get(0).getType());
        Assert.assertEquals(OperationType.WITHDRAWAL, myBank.getAccount().getOperations().get(1).getType());
        Assert.assertEquals(new BigDecimal(2), myBank.getAccount().getBalance());
    }

    @Test
    public void testWithdraw_GIVEN_DepositAndWithdrawValidInput_WHEN_InsufficientBalance_SHOULD_AddNewOperationHistory() {
        try {
            // GIVEN
            myBank.deposit(new BigDecimal(5));
            myBank.withdraw(new BigDecimal(3));
            myBank.withdraw(new BigDecimal(3));
        } catch (BalanceException e) {
            Assert.assertEquals("You do not have balance for this withdrawal 3", e.getMessage());
        }
    }

    @Test
    public void testDeposit_GIVEN_DepositInvalidInput_WHEN_SufficientBalance_SHOULD_AddNewOperationHistory() {
        try {
            // GIVEN
            myBank.deposit(new BigDecimal(-2));
        } catch (RuntimeException e) {
            Assert.assertEquals("The amount of deposit must be positive and >0", e.getMessage());
        }
    }

    @Test
    public void testWithdraw_GIVEN_WithdrawInvalidInput_WHEN_SufficientBalance_SHOULD_AddNewOperationHistory() {
        try {
            // GIVEN
            myBank.withdraw(new BigDecimal(-25));
        } catch (RuntimeException e) {
            Assert.assertEquals("The amount of withdrawal must be positive and >0", e.getMessage());
        }
    }

    @Test
    public void testPrintHistoryGIVEN_DepositAndWithdrawValidInput_SHOULD_ReturnHistoryList() {
        // GIVEN
        myBank.deposit(new BigDecimal(20));
        myBank.withdraw(new BigDecimal(3));
        myBank.withdraw(new BigDecimal(6));
        myBank.deposit(new BigDecimal(2));
        //THEN
        myBank.printAccountHistory();
        Assert.assertEquals(4, myBank.getAccount().getOperations().size());
        Assert.assertEquals(new BigDecimal(13), myBank.getAccount().getOperations().get(myBank.getAccount().getOperations().size() - 1).getBalance());
    }
}
