import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private String hash;
    private String previousHash;
    private Transaction transaction;

    public Block(String previousHash, String newBlockHash, Transaction transaction) {
        this.previousHash = previousHash;
        this.hash = newBlockHash;
        this.transaction = transaction;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getBlockHash() {
        return hash;
    }

    private String calculateBlockHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            String data = previousHash + transaction.toString();
            byte[] hashBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));

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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
