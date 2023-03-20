import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.math.BigInteger;
import java.util.Random;
import javax.swing.*;

public class RSA_GUI extends JFrame implements ActionListener {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final int KEY_SIZE = 512;

    private final JLabel labelErrorMessage = new JLabel("");
    private final JTextField textFieldEncryptedMessage = new JTextField(25);
    private final JTextField textFieldDecryptedMessage = new JTextField(25);
    private final JTextField textFieldMessage = new JTextField(25);
    private final JTextField textFieldInterceptedMessage = new JTextField(25);
    private final JButton buttonEncrypt = new JButton("Encrypt");
    private final JButton buttonDecrypt = new JButton("Decrypt");

    private final BigInteger[] keys; // public and private keys

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

    public RSA_GUI() {
        // Set up the frame
        super("RSA Prototype");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new FlowLayout());

        // Generate public and private keys
        keys = generateKeys();
        BigInteger e = keys[0];
        BigInteger d = keys[1];
        BigInteger n = keys[2];

        // Create a panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());

       // Define GridBagConstraints for components
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.insets = new Insets(5, 5, 5, 5);

        // Add public key label and text field
        JLabel labelPublicKey = new JLabel("Public Key:");
        c.gridx = 0;
        c.gridy = 0;
        panel.add(labelPublicKey, c);

        JTextField textFieldPublicKey = new JTextField(25);
        textFieldPublicKey.setText("(" + e + ", " + n + ")");
        textFieldPublicKey.setEditable(false);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(textFieldPublicKey, c);

        // Add private key label and text field
        JLabel labelPrivateKey = new JLabel("Private Key:");
        c.gridx = 0;
        c.gridy = 1;
        panel.add(labelPrivateKey, c);

        JTextField textFieldPrivateKey = new JTextField(25);
        textFieldPrivateKey.setText("(" + d + ", " + n + ")");
        textFieldPrivateKey.setEditable(false);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(textFieldPrivateKey, c);

        // Add message prompt and text field for user input
        JLabel labelPrompt = new JLabel("Enter message to encrypt:");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(labelPrompt, c);

        JTextField textFieldMessage = new JTextField(25);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(textFieldMessage, c);

        // Add encrypt and decrypt buttons
        c.gridx = 0;
        c.gridy = 3;
        panel.add(buttonEncrypt, c);

        c.gridx = 1;
        c.gridy = 3;
        panel.add(buttonDecrypt, c);

        // Add labels and text fields for encrypted and decrypted messages
        JLabel labelEncryptedMessage = new JLabel("Encrypted message:");
        c.gridx = 0;
        c.gridy = 4;
        panel.add(labelEncryptedMessage, c);

        JTextField textFieldEncryptedMessage = new JTextField(25);
        textFieldEncryptedMessage.setEditable(false);
        c.gridx = 1;
        c.gridy = 4;
        panel.add(textFieldEncryptedMessage, c);

        JLabel labelDecryptedMessage = new JLabel("Decrypted message:");
        c.gridx = 0;
        c.gridy = 5;
        panel.add(labelDecryptedMessage, c);

        JTextField textFieldDecryptedMessage = new JTextField(25);
        textFieldDecryptedMessage.setEditable(false);
        c.gridx = 1;
        c.gridy = 5;
        panel.add(textFieldDecryptedMessage, c);

        // Add intercepted message label and text field
        JLabel labelInterceptedMessage = new JLabel("Charlie intercepted the message:");
        c.gridx = 0;
        c.gridy = 6;
        panel.add(labelInterceptedMessage, c);

        JTextField textFieldInterceptedMessage = new JTextField(25);
        textFieldInterceptedMessage.setEditable(false);
        c.gridx = 1;
        c.gridy = 6;
        panel.add(textFieldInterceptedMessage, c);

        // Add error message label
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        panel.add(labelErrorMessage, c);

        // Add panel to the frame
        add(panel);

        // Add action listeners to buttons
        buttonEncrypt.addActionListener(this);
        buttonDecrypt.addActionListener(this);

        // Display the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check which button was pressed
        if (e.getSource() == buttonEncrypt) {
            // Get message to encrypt
            String message = textFieldMessage.getText();
            if (message.equals("")) {
                labelErrorMessage.setText("Error: Please enter a message to encrypt");
                return;
            }
            // Encrypt the message
            BigInteger m = new BigInteger(message.getBytes());
            BigInteger c = encrypt(m, keys[0], keys[2]);
            // Display the encrypted message
            textFieldEncryptedMessage.setText(c.toString());
            labelErrorMessage.setText("");
        } else if (e.getSource() == buttonDecrypt) {
            // Get message to decrypt
            String encryptedMessage = textFieldEncryptedMessage.getText();
            if (encryptedMessage.equals("")) {
                labelErrorMessage.setText("Error: Please encrypt a message first");
                return;
            }
            //Decrypt the message
            BigInteger c = new BigInteger(encryptedMessage);
            BigInteger m = decrypt(c, keys[1], keys[2]);
            // Display the decrypted message
            textFieldDecryptedMessage.setText(new String(m.toByteArray()));
            // Display the intercepted message
            BigInteger intercepted = encrypt(m, generateRandomNumber(keys[2]), keys[2]);
            textFieldInterceptedMessage.setText(intercepted.toString());
            labelErrorMessage.setText("");
        }
    }
    public static void main(String[] args) {
        new RSA_GUI();
    }
}
