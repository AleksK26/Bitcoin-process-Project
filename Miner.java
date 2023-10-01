import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class Miner {
    private String identifier;
    private Blockchain blockchain;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public Miner(String identifier, Blockchain blockchain) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.identifier = identifier;
        this.blockchain = blockchain;
        generateKeyPair();
    }

    public String getIdentifier() {
        return identifier;
    }

    public Blockchain getBlockchain() {
        return blockchain;
    }

    public Block mine(Transaction transaction) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        // Simulated mining process
        int nonce = 0;
        String previousBlockHash = blockchain.getLatestBlockHash();

        // Solve the proof-of-work puzzle
        String newBlockHash = calculateBlockHash(nonce, previousBlockHash, transaction);
        while (!isValidBlockHash(newBlockHash)) {
            nonce++;
            newBlockHash = calculateBlockHash(nonce, previousBlockHash, transaction);
        }

        // Once a valid block hash is found, construct a new block and add it to the blockchain
        Block newBlock = new Block(previousBlockHash, newBlockHash, transaction);

        // Print the block hash and reward
        System.out.println("Block Hash: " + newBlock.getHash());
        System.out.println("Miner Reward: " +  "5.0 additional reward coins");


        blockchain.addBlock(newBlock);

        // Update the latest block hash in the blockchain
        blockchain.setLatestBlockHash(newBlock.getHash());

        return newBlock;
    }

    private void generateKeyPair() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // You can adjust the key size as needed
        KeyPair keyPair = keyGen.generateKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
    }

    private String calculateBlockHash(int nonce, String previousBlockHash, Transaction transaction) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        // Simulated block hash calculation
        String data = nonce + previousBlockHash + transaction.toString();
        return calculateSHA256Hash(data);
    }

    private String calculateSHA256Hash(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(data.getBytes());

        // Convert the byte array to a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private boolean isValidBlockHash(String blockHash) {
        // Simulated validation of the block hash
        return blockHash.startsWith("0000"); // Simple proof-of-work condition
    }

    private String signSHA256RSA(String input) throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(input.getBytes());
        byte[] signature = privateSignature.sign();
        return Base64.getEncoder().encodeToString(signature);
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void printPublicKey() {
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("Public Key: " + publicKeyString);
    }
}
