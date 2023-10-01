import java.util.ArrayList;
import java.util.List;

public class Agent {
    private String identifier;
    private double balance;
    private List<Transaction> transactionHistory;

    public Agent(String identifier, double initialBalance) {
        this.identifier = identifier;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransactionToHistory(Transaction transaction) {
        transactionHistory.add(transaction);
    }
    //return some element for the error

    public boolean sendTransaction(Agent recipient, double amount) {
        if (balance >= amount) {
            // Check if there's a previous transaction between the same sender and recipient
            Transaction previousTransaction = getPreviousTransaction(recipient);
            if (previousTransaction != null) {
                // Remove the previous transaction from the transaction history
                transactionHistory.remove(previousTransaction);
            }

            // Perform the transaction
            balance -= amount;
            recipient.receiveTransaction(amount);

            // Create a new transaction and add it to the transaction history
            Transaction newTransaction = new Transaction(this, recipient, amount);
            transactionHistory.add(newTransaction);

            return true;
        } else {
            return false;
        }
    }

    private Transaction getPreviousTransaction(Agent recipient) {
        // Find the most recent transaction with the given recipient in the transaction history
        for (int i = transactionHistory.size() - 1; i >= 0; i--) {
            Transaction transaction = transactionHistory.get(i);
            if (transaction.getRecipient() == recipient) {
                return transaction;
            }
        }
        return null;
    }

    public void receiveTransaction(double amount) {
        balance += amount;
    }

}

