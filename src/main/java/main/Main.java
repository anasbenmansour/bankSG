package main;

import exception.BalanceException;
import service.BankService;
import util.Util;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final Scanner s = new Scanner(System.in);
        // init bank service
        final BankService myBank = new BankService("My account name");
        // Menu
        printMainMenu(s, myBank);
    }

    /**
     *  print the user menu
     * @param s : scanner
     * @param myBank: the bank service object
     */
    private static void printMainMenu(Scanner s, BankService myBank) {
        String userChoice = "4";
        do {
            // display menu to user
            System.out.println();
            System.out.println("1) Deposit to a bank account");
            System.out.println("2) Withdraw to bank account");
            System.out.println("3) Print account history");
            System.out.println("4) Quit");
            System.out.println();
            System.out.print("Enter choice [1-4]: ");
            try {
                userChoice = s.next();
                switch (Integer.parseInt(userChoice)) {
                    case 1:
                        System.out.println("Enter a deposit amount");
                        final String depositAmount = s.next();
                        myBank.deposit(new BigDecimal(depositAmount));
                        break;
                    case 2:
                        System.out.println("Enter a withdraw amount");
                        final String withdrawAmount = s.next();
                        myBank.withdraw(new BigDecimal(withdrawAmount));
                        break;
                    case 3:
                        myBank.printAccountHistory();
                        break;
                    case 4:
                        System.out.println("See you later");
                        System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid input format");
                Util.pressAnyKeyToContinue();
            } catch (IllegalArgumentException | BalanceException e) {
                System.out.println(e.getMessage());
            }
        } while (userChoice != "4");
    }
}
