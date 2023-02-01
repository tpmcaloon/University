import java.util.Scanner;

public class RSAAlgorithm {

    public static void main(String[] args) {

        // Prompt user for two prime numbers
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter two prime numbers: ");
        int p = scanner.nextInt();
        int q = scanner.nextInt();
        int n = p * q;

        // Calculate r
        int r = (p - 1) * (q - 1);

        // Calculate e
        int e = 1;
        while (e < r && GCD(e, r) != 1) {
            e++;
        }

        // Calculate d
        int d = 1;
        while ((d * e) % r != 1) {
            d++;
        }

        // Generate keys
        int[] publicKey = new int[2];
        publicKey[0] = e;
        publicKey[1] = n;
        int privateKey = d;

        // Prompt user for message
        System.out.println("Please enter a message: ");
        String message = scanner.next();

        // Encrypt message
        int[] encryptedMessage = encryptMessage(message, publicKey);

        // Print out encrypted message
        System.out.println("Encrypted Message:");
        for (int i = 0; i < encryptedMessage.length; i++) {
            System.out.print(encryptedMessage[i] + " ");
        }

        // Decrypt message
        String decryptedMessage = decryptMessage(encryptedMessage, privateKey, n);

        // Print out decrypted message
        System.out.println("\nDecrypted Message: ");
        System.out.println(decryptedMessage);

        // Special case
        System.out.println("\nSpecial case: ");
        System.out.println("If the message is too long, the encrypted message will be too long and will not be secure.");

        scanner.close();
    }

    // Calculate GCD
    public static int GCD(int a, int b) {
        if (a == 0) {
            return b;
        }
        return GCD(b % a, a);
    }

    // Encrypt message
    public static int[] encryptMessage(String message, int[] publicKey) {
        int[] encryptedMessage = new int[message.length()];
        for (int i = 0; i < message.length(); i++) {
            int temp = (int)message.charAt(i);
            encryptedMessage[i] = (int)Math.pow(temp, publicKey[0]) % publicKey[1];
        }
        return encryptedMessage;
    }

    // Decrypt message
    public static String decryptMessage(int[] encryptedMessage, int privateKey, int n) {
        String decryptedMessage = "";
        for (int i = 0; i < encryptedMessage.length; i++) {
            int temp = (int)Math.pow(encryptedMessage[i], privateKey) % n;
            decryptedMessage += (char)temp;
        }
        return decryptedMessage;
    }
}

