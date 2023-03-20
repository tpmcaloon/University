import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {

    private static final int KEY_SIZE = 512;

    // method to generate prime numbers
    public static BigInteger generatePrime(int numBits) {
        Random random = new Random();
        return BigInteger.probablePrime(numBits, random);
    }

    // method to generate random number 'e' where gcd(e,r)=1
    public static BigInteger generateRandomNumber(BigInteger r) {
        Random random = new Random();
        BigInteger e = BigInteger.ZERO;
        while (e.gcd(r).intValue() != 1) {
            e = new BigInteger(r.bitLength(), random);
        }
        return e;
    }

    // method to generate public and private keys
    public static BigInteger[] generateKeys() {
        // generate two prime numbers
        BigInteger p = generatePrime(KEY_SIZE);
        BigInteger q = generatePrime(KEY_SIZE);
        // calculate n and r
        BigInteger n = p.multiply(q);
        BigInteger r = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        // generate random number e such that gcd(e,r) = 1
        BigInteger e = generateRandomNumber(r);
        // calculate private key d
        BigInteger d = e.modInverse(r);
        // return public and private keys
        return new BigInteger[]{e, d, n};
    }

    // method to encrypt message using public key
    public static BigInteger encrypt(BigInteger m, BigInteger e, BigInteger n) {
        return m.modPow(e, n);
    }

    // method to decrypt message using private key
    public static BigInteger decrypt(BigInteger c, BigInteger d, BigInteger n) {
        return c.modPow(d, n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("RSA Prototype");

        // generate public and private keys
        BigInteger[] keys = generateKeys();
        BigInteger e = keys[0];
        BigInteger d = keys[1];
        BigInteger n = keys[2];

        System.out.println("\nPublic Key: (" + e + ", " + n + ")");
        System.out.println("Private Key: (" + d + ", " + n + ")");

        // prompt user to enter message and recipient
        System.out.print("\nEnter message to encrypt: ");
        String message = "";
        while (message.isEmpty()) {
            message = sc.nextLine().trim();
            if (message.isEmpty()) {
                System.out.print("Invalid input. Please enter a non-empty message: ");
            }
        }

        // convert message to BigInteger
        BigInteger m = new BigInteger(message.getBytes());

        // encrypt message using recipient's public key
        BigInteger c = encrypt(m, e, n);

        System.out.println("\nEncrypted message: " + c);

        // simulate an interception by Charlie
        System.out.println("\nCharlie intercepted the message: " + c);

        // prompt user to choose whether to decrypt the message
        String response = "";
        while (!response.equalsIgnoreCase("Y") && !response.equalsIgnoreCase("N")) {
            System.out.print("\nDo you want to decrypt the message? (Y/N): ");
            response = sc.nextLine().trim();
            if (!response.equalsIgnoreCase("Y") && !response.equalsIgnoreCase("N")) {
                System.out.print("Invalid input. Please enter 'Y' or 'N': ");
            }
        }

        // decrypt message using private key
        if (response.equalsIgnoreCase("Y")) {
            BigInteger decryptedMessage;
            try {
                decryptedMessage = decrypt(c, d, n);
                // convert decrypted message back to string
                String originalMessage = new String(decryptedMessage.toByteArray());
                System.out.println("\nDecrypted message: " + originalMessage);
            } catch (ArithmeticException ex) {
                System.out.println("\nError: " + ex.getMessage());
                System.out.println("Exiting program...");
            }
        }

        // demonstrate a problematic case
        System.out.println("\nDemonstrating a problematic case...");
        System.out.println("\nDemonstration of a problematic case where the prime factors of n are small and are not kept secret, making it easy for an attacker to factor n and break the encryption. \nThe p and q values used to generate n in this problematic case are small primes, p=61 and q=53, meaning the attacker can easily factor n to recover p and q.");

        // generate two

        System.out.println("\nPublic Key: (" + e + ", " + n + ")");
        System.out.println("Private Key: (" + d + ", " + n + ")");

        // prompt user to enter message and recipient
        System.out.print("\nEnter message to encrypt: ");
        String message2 = "";
        while (message2.isEmpty()) {
            message2 = sc.nextLine().trim();
            if (message2.isEmpty()) {
                System.out.print("Invalid input. Please enter a non-empty message: ");
            }
        }

        // convert message to BigInteger
        BigInteger m2 = new BigInteger(message2.getBytes());

        // encrypt message using recipient's public key
        BigInteger c2 = encrypt(m2, e, n);

        System.out.println("\nEncrypted message: " + c2);

        // prompt user to choose whether to decrypt the message
        String response2 = "";
        while (!response2.equalsIgnoreCase("Y") && !response2.equalsIgnoreCase("N")) {
            System.out.print("\nDo you want to decrypt the message? (Y/N): ");
            response2 = sc.nextLine().trim();
            if (!response2.equalsIgnoreCase("Y") && !response2.equalsIgnoreCase("N")) {
                System.out.print("Invalid input. Please enter 'Y' or 'N': ");
            }
        }

        // decrypt message using private key
        if (response2.equalsIgnoreCase("Y")) {
            BigInteger decryptedMessage2 = decrypt(c2, d, n);
            // convert decrypted message back to string
            String originalMessage2 = new String(decryptedMessage2.toByteArray());
            System.out.println("\nDecrypted message: " + originalMessage2);
        } else {
            System.out.println("\nExiting program...");
        }
    }
}