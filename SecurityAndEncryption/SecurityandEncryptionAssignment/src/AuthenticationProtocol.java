import javax.crypto.Cipher;
import java.security.*;

public class AuthenticationProtocol
{
    public static void main(String[] args) throws Exception
    {
        // Create a KeyPairGenerator object
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

        // Initialize the KeyPairGenerator
        keyPairGen.initialize(1024);

        // Generate a pair of keys for Alice
        KeyPair keyPairA = keyPairGen.generateKeyPair();

        // Generate a pair of keys for Bob
        KeyPair keyPairB = keyPairGen.generateKeyPair();

        // Generate a pair of keys for Server
        KeyPair keyPairS = keyPairGen.generateKeyPair();

        // Generate a nonce for Alice
        SecureRandom randomA = new SecureRandom();
        byte[] nonceA = new byte[16];
        randomA.nextBytes(nonceA);

        // Generate a nonce for Bob
        SecureRandom randomB = new SecureRandom();
        byte[] nonceB = new byte[16];
        randomB.nextBytes(nonceB);

        // Step 1: Alice sends her request to Server
        System.out.println("Step 1: Alice sends her request to Server");
        System.out.println("Alice: Dear S, This is A and I would like to get B's public key. Yours sincerely, A.");

        // Step 2: Server sends Bob's public key to Alice
        System.out.println("\nStep 2: Server sends Bob's public key to Alice");
        System.out.println("Server: Dear A, Here is B's public key signed by me. Yours sincerely, S.");

        // Step 3: Alice sends her nonce to Bob
        System.out.println("\nStep 3: Alice sends her nonce to Bob");
        System.out.println("Alice: Dear B, This is A and I have sent you a nonce only you can read. Yours sincerely, A.");

        // Step 4: Bob sends his request to Server
        System.out.println("\nStep 4: Bob sends his request to Server");
        System.out.println("Bob: Dear S, This is B and I would like to get A's public key. Yours sincerely, B.");

        // Step 5: Server sends Alice's public key to Bob
        System.out.println("\nStep 5: Server sends Alice's public key to Bob");
        System.out.println("Server: Dear B, Here is A's public key signed by me. Yours sincerely, S.");

        // Step 6: Bob sends his and Alice's nonce to Alice
        System.out.println("\nStep 6: Bob sends his and Alice's nonce to Alice");
        System.out.println("Bob: Dear A, Here is my nonce and yours, proving I decrypted it. Yours sincerely, B.");

        // Step 7: Alice sends her nonce to Bob
        System.out.println("\nStep 7: Alice sends her nonce to Bob");
        System.out.println("Alice: Dear B, Here is your nonce proving I decrypted it. Yours sincerely, A.");

        // Get Alice's public key
        PublicKey KA = keyPairA.getPublic();

        // Get Bob's public key
        PublicKey KB = keyPairB.getPublic();

        // Get Server's public key
        PublicKey kS = keyPairS.getPublic();

        // Sign Bob's public key with server's private key
        Signature s = Signature.getInstance("SHA256withRSA");
        s.initSign(keyPairS.getPrivate());
        s.update(KB.getEncoded());
        byte[] signedKB = s.sign();

        // Sign Alice's public key with server's private key
        s.initSign(keyPairS.getPrivate());
        s.update(KA.getEncoded());
        byte[] signedKA = s.sign();

        // Encrypt Alice's nonce with Bob's public key
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, KB);
        byte[] encryptedNonceA = cipher.doFinal(nonceA);

        // Encrypt Bob's nonce with Alice's public key
        cipher.init(Cipher.ENCRYPT_MODE, KA);
        byte[] encryptedNonceB = cipher.doFinal(nonceB);

        // Decrypt Alice's nonce with Bob's private key
        cipher.init(Cipher.DECRYPT_MODE, keyPairB.getPrivate());
        byte[] decryptedNonceA = cipher.doFinal(encryptedNonceA);

        // Decrypt Bob's nonce with Alice's private key
        cipher.init(Cipher.DECRYPT_MODE, keyPairA.getPrivate());
        byte[] decryptedNonceB = cipher.doFinal(encryptedNonceB);

        // Verify Alice's nonce
        if (decryptedNonceA[0] == nonceA[0] && decryptedNonceA[1] == nonceA[1]
                && decryptedNonceA[2] == nonceA[2] && decryptedNonceA[3] == nonceA[3])
        {
            System.out.println("\nAlice's Nonce verified successfully");
        }
        else
        {
            System.out.println("\nAlice's Nonce verification failed");
        }

        // Verify Bob's nonce
        if (decryptedNonceB[0] == nonceB[0] && decryptedNonceB[1] == nonceB[1]
                && decryptedNonceB[2] == nonceB[2] && decryptedNonceB[3] == nonceB[3])
        {
            System.out.println("\nBob's Nonce verified successfully");
        }
        else
        {
            System.out.println("\nBob's Nonce verification failed");
        }
    }
}

// Error and Subtle Vulnerability

//Error: The protocol does not provide protection against Man-in-the-Middle attacks.
//Subtle Vulnerability: If Alice uses this protocol to authenticate with a third-party Z, then Z could get access to Alice's nonce and use it to impersonate Alice.