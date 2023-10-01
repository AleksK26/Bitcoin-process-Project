import java.util.UUID;

public class Transaction {
    private UUID id;
    private Agent sender;
    private Agent recipient;
    private double amount;

    public Transaction(Agent sender, Agent recipient, double amount) {
        this.id = UUID.randomUUID();
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public Agent getSender() {
        return sender;
    }

    public Agent getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction: " + sender.getIdentifier() + " sent " + amount + " BellaCoins to " + recipient.getIdentifier();
    }
}