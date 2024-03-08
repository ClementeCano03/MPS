package gestor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bank.BankAccount;

public class BankTest {
    BankAccount banco;


    @Test
    @DisplayName("Testing to withdraw an amount less than the initial balance")
    void TestWithdrawAmountLessBalance(){
        BankAccount banco = new BankAccount(1000);
        //retiramos 500€ de la cuenta
        banco.withdraw(500);
        assert(banco.getBalance() == 500);

    }

    @Test
    @DisplayName("Testing to withdraw an amount more than the initial balance")
    void TestWithdrawAmountMoreBalance(){
        BankAccount banco = new BankAccount(1000);
        //retiramos 1500 teniendo 1000
        int retiro = 1500;

        assertThrows(IllegalArgumentException.class, () -> banco.withdraw(retiro));

    }

    @Test
    @DisplayName("Testing to withdraw an negative amount")
    void TestWithdrawNegativeAmount(){
        BankAccount banco = new BankAccount(1000);
        //retiramos cantidad negativa
        int retiro = -500;
        assertThrows(IllegalArgumentException.class, () -> banco.withdraw(retiro));
    }

    @Test
    @DisplayName("Testing to deposit a positive amount")
    void TestDepositPositiveAmount(){
        BankAccount banco = new BankAccount(1000);
        //depositicamos cantidad positiva
        int deposito = 500;
        banco.deposit(deposito);
        assert(banco.getBalance() == 1500);
    }

    @Test
    @DisplayName("Testing to deposit a negative amount")
    void TestDepositNegativeAmount(){
        BankAccount banco = new BankAccount(1000);
        //depositicamos cantidad negativa
        int deposito = -500;
        assertThrows(IllegalArgumentException.class, () -> banco.deposit(deposito));
    }
    @Test
    @DisplayName("Testing return balance")
    void TestBalance(){
        BankAccount banco = new BankAccount(1000);
        //comprobamos q lo devuelva sea igual al balance inicial
        assert(banco.getBalance() == 1000);
    }

    @Test
    @DisplayName("Testing to pay with all the positive values")
    void TestPaymentPositiveAmountPositiveInterestPositivePayment(){
        BankAccount banco = new BankAccount(1000);
        //calculamos la cuota mensual del prestamo
        double amount = 100, interes = 0.15;
        int  npayments = 24;
        double expectedValue = banco.payment(amount,interes,npayments);
        assert(expectedValue == amount*(interes*Math.pow((1+interes), npayments)/(Math.pow((1+interes), npayments)-1)));
    }

    @Test
    @DisplayName("Testing to pay with amount negative")
    void TestPaymentNegativeAmountPositiveInterestPositivePayment(){
        BankAccount banco = new BankAccount(1000);
        //calculamos la cuota mensual del prestamo
        double amount = -100, interes = 0.15;
        int  npayments = 24;
        assertThrows(IllegalArgumentException.class, () -> banco.payment(amount,interes,npayments));
    }

    @Test
    @DisplayName("Testing to pay with interest negative")
    void TestPaymentPositiveAmountNegativeInterestPositivePayment(){
        BankAccount banco = new BankAccount(1000);
        //interes negativo
        double amount = 100, interes = -0.15;
        int  npayments = 24;
        assertThrows(IllegalArgumentException.class, () -> banco.payment(amount,interes,npayments));
    }

    @Test
    @DisplayName("Testing to pay with npayment negative")
    void TestPaymentPositiveAmountPositiveInterestNegativePayment(){
        BankAccount banco = new BankAccount(1000);
        //dia pagos negativo
        double amount = 100, interes = 0.15;
        int  npayments = -24;
        assertThrows(IllegalArgumentException.class, () -> banco.payment(amount,interes,npayments));
    }
    //npayment=0

    @Test
    @DisplayName("Testing to calculate the pending amount for a loan in a month")
    void TestingPendingPositiveValues(){
        BankAccount banco = new BankAccount(1000);
        double amount = 1000, interest = 0.001;
        int npayments = 12, month = 2;
        double pendingAmount = banco.pending(amount, interest, npayments, month);
        double expectedAmount = 1000 * Math.pow(1 + interest, month) - banco.payment(amount, interest, npayments) * ((Math.pow(1 + interest, month) - 1) / interest);
        double margin = 0.001;
        assertEquals(expectedAmount, pendingAmount, margin);
    }

    @Test
    @DisplayName("Testing to calculate the pending amount for a loan in a month with negative parameter")
    void TestingPendingZeroMonths(){
        BankAccount banco = new BankAccount(1000);
        double amount = 1000, interes = 0.001;
        int npayments = 12, month = 0;
        double pendingAmount = banco.pending(amount, interes, npayments, month);
        assertEquals(amount, pendingAmount);
    }

    @Test
    @DisplayName("Testing to calculate the pending amount for a loan in a month with negative parameter")
    void TestingPendingNegativeAmountPositiveInteresPositiveNpaymentsPositiveMonths(){
        BankAccount banco = new BankAccount(1000);
        double amount = -1000, interes = 0.001;
        int npayments = 12, month = 2;
        assertThrows(IllegalArgumentException.class, () -> banco.pending(amount, interes, npayments, month));
    }

    @Test
    @DisplayName("Testing to calculate the pending amount for a loan in a month with negative parameter")
    void TestingPendingPositiveAmountNegativeInteresPositiveNpaymentsPositiveMonths(){
        BankAccount banco = new BankAccount(1000);
        double amount = 1000, interes = -0.001;
        int npayments = 12, month = 2;
        assertThrows(IllegalArgumentException.class, () -> banco.pending(amount, interes, npayments, month));
    }

    @Test
    @DisplayName("Testing to calculate the pending amount for a loan in a month with negative parameter")
    void TestingPendingPositiveAmountPositiveInteresNegativeNpaymentsPositiveMonths(){
        BankAccount banco = new BankAccount(1000);
        double amount = 1000, interes = 0.001;
        int npayments = -12, month = 2;
        assertThrows(IllegalArgumentException.class, () -> banco.pending(amount, interes, npayments, month));
    }

    @Test
    @DisplayName("Testing to calculate the pending amount for a loan in a month with negative parameter")
    void TestingPendingPositiveAmountPositiveInteresPositiveNpaymentsNegativeMonths(){
        BankAccount banco = new BankAccount(1000);
        double amount = 1000, interes = 0.001;
        int npayments = 12, month = -2;
        assertThrows(IllegalArgumentException.class, () -> banco.pending(amount, interes, npayments, month));
    }


}
