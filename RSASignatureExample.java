import java.security.*;

public class RSASignatureExample {
    public static void main(String[] args) throws Exception {
        // Generate a key pair
        KeyPair keyPair = generateKeyPair();

        // Sign a message
        String message = "Hello, World!";
        byte[] signature = signMessage(message, keyPair.getPrivate());

        // Verify the signature
        boolean isValid = verifySignature(message, signature, keyPair.getPublic());

        // Print the result
        System.out.println("Signature is valid: " + isValid);
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // You can adjust the key size as needed
        return keyGen.generateKeyPair();
    }

    private static byte[] signMessage(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        return signature.sign();
    }

    private static boolean verifySignature(String message, byte[] signature, PublicKey publicKey) throws Exception {
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(message.getBytes());
        return verifier.verify(signature);
    }
}
