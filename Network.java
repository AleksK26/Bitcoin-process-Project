import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<Agent> agents;
    private List<Miner> miners;

    public Network() {
        this.agents = new ArrayList<>();
        this.miners = new ArrayList<>();
    }

    public void addAgent(Agent agent) {
        agents.add(agent);
    }

    public void addMiner(Miner miner) {
        miners.add(miner);
    }

    public void simulateTransaction(Agent sender, Agent recipient, double amount) {
        boolean successful = sender.sendTransaction(recipient, amount);
        if (successful) {
            System.out.println("Transaction: " + sender.getIdentifier() + " sent " + amount + " BellaCoins to " + recipient.getIdentifier());
            Transaction transaction = new Transaction(sender, recipient, amount);
            sender.addTransactionToHistory(transaction);
        } else {
            System.out.println("Transaction failed: " + sender.getIdentifier() + " does not have sufficient balance.");
        }
        printBalances();
    }


    public void simulateMining(Miner miner, Transaction transaction) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        miner.mine(transaction);
        System.out.println("Miner: " + miner.getIdentifier() + " mined a new block with transaction: " + transaction);
        printBlockchain();
    }

    void printBalances() {
        System.out.println("Users Balances:");
        for (Agent agent : agents) {
            System.out.println(agent.getIdentifier() + ": " + agent.getBalance() + " BellaCoins");
        }
        System.out.println();
    }


    public void printBlockchain() {
        System.out.println("Blockchain:");

        Blockchain blockchain = miners.get(0).getBlockchain(); // Assuming all miners have the same blockchain

        for (int i = 0; i < blockchain.getSize(); i++) {
            Block block = blockchain.getBlock(i);

            System.out.println("Block " + i + ":");
            System.out.println("Hash: " + block.getHash());
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("Transaction: " + block.getTransaction());
            System.out.println();
        }
    }

}

