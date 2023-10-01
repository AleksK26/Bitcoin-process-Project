import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException,
            InvalidKeySpecException, SignatureException, InvalidKeyException {
        // Create network, agents, and blockchain
        Network network = new Network();
        Agent agent1 = new Agent("User 1", 100.0);
        Agent agent2 = new Agent("User 2", 50.0);
        Agent agent3 = new Agent("User 3", 200.0);
        Blockchain blockchain = new Blockchain();

        // Add agents to the network
        network.addAgent(agent1);
        network.addAgent(agent2);
        network.addAgent(agent3);

        // Create miners with the blockchain instance
        Miner miner1 = new Miner("Miner 1", blockchain);
        Miner miner2 = new Miner("Miner 2", blockchain);
        Miner miner3 = new Miner("Miner 3", blockchain);

        // Add miners to the network
        network.addMiner(miner1);
        network.addMiner(miner2);
        network.addMiner(miner3);

        // The public key
        Miner miner = new Miner("Miner1", blockchain);
        miner.printPublicKey();
        System.out.println();

        // Simulate transactions
        Transaction transaction1 = new Transaction(agent1, agent2, 5.0);
        agent1.addTransactionToHistory(transaction1);
        network.simulateTransaction(agent1, agent2, 5.0);

        Transaction transaction2 = new Transaction(agent2, agent3, 3.0);
        agent2.addTransactionToHistory(transaction2);
        network.simulateTransaction(agent2, agent3, 3.0);

        Transaction transaction3 = new Transaction(agent3, agent1, 2.0);
        agent3.addTransactionToHistory(transaction3);
        network.simulateTransaction(agent3, agent1, 2.0);

        //example where agent 3 makes a new transaction,but when he makes it the previous one becomes history,
        //and the new one stays in the blockchain because it's from the same agent
        Transaction transaction4 = new Transaction(agent3, agent2, 10.0);
        agent3.addTransactionToHistory(transaction4);
        network.simulateTransaction(agent3, agent2, 10.0);

        // Check if transaction history is empty before mining
        if (agent1.getTransactionHistory().isEmpty() || agent2.getTransactionHistory().isEmpty()) {
            System.out.println("Transaction history is empty. Please add transactions before mining.");
            return;
        }

        // Simulate mining
        Block minedBlock1 = miner1.mine(agent1.getTransactionHistory().get(agent1.getTransactionHistory().size() - 1));
        Block minedBlock2 = miner2.mine(agent2.getTransactionHistory().get(agent2.getTransactionHistory().size() - 1));
        Block minedBlock3 = miner3.mine(agent3.getTransactionHistory().get(agent3.getTransactionHistory().size() - 1));

        // Print mined blocks
        System.out.println("Mined Blocks:");
        System.out.println("Block 1:");
        System.out.println("Transaction: " + minedBlock1.getTransaction());
        System.out.println("Block Hash: " + minedBlock1.getHash());
        System.out.println();

        System.out.println("Block 2:");
        System.out.println("Transaction: " + minedBlock2.getTransaction());
        System.out.println("Block Hash: " + minedBlock2.getHash());
        System.out.println();

        System.out.println("Block 3:");
        System.out.println("Transaction: " + minedBlock3.getTransaction());
        System.out.println("Block Hash: " + minedBlock3.getHash());
        System.out.println();

        // Print agent transactions
        System.out.println("Users Transactions:");
        network.printBalances();
        System.out.println();

        // Print blockchain
        System.out.println("Blockchain:");
        blockchain.printBlockchain();
    }
}
