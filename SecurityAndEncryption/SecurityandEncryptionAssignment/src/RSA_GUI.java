import java.awt.BorderLayout;
import java.io.Serial;
import java.math.BigInteger;
import java.util.Random;

import javax.swing.*;

public class RSA_GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final int KEY_SIZE = 1024;
    private final JPanel panel;

    private final JTextArea messageArea;
    private final JTextField publicKeyField;
    private final JTextField privateKeyField;
    private final JTextField encryptedMessageField;
    private final JTextArea decryptedMessageField;
    private final JTextField interceptedMessageField;

    public RSA_GUI() {
        setTitle("RSA Encryption");

        panel = new JPanel();
        panel.setLayout(null);

        JLabel publicKeyLabel = new JLabel("Public Key:");
        publicKeyLabel.setBounds(10, 10, 80, 25);
        panel.add(publicKeyLabel);

        publicKeyField = new JTextField();
        publicKeyField.setBounds(100, 10, 600, 25);
        publicKeyField.setEditable(false);
        panel.add(publicKeyField);

        JLabel privateKeyLabel = new JLabel("Private Key:");
        privateKeyLabel.setBounds(10, 40, 80, 25);
        panel.add(privateKeyLabel);

        privateKeyField = new JTextField();
        privateKeyField.setBounds(100, 40, 600, 25);
        privateKeyField.setEditable(false);
        panel.add(privateKeyField);

        JButton generateKeysButton = new JButton("Generate Keys");
        generateKeysButton.setBounds(100, 70, 600, 25);
        generateKeysButton.addActionListener(e -> {
            BigInteger[] keys = generateKeys();
            publicKeyField.setText(keys[0] + " " + keys[2]);
            privateKeyField.setText(keys[1] + " " + keys[2]);
        });
        panel.add(generateKeysButton);

        JLabel messageLabel = new JLabel("Message (Alice):");
        messageLabel.setBounds(10, 125, 100, 25);
        panel.add(messageLabel);

        messageArea = new JTextArea();
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);
        messageScrollPane.setBounds(175, 125, 530, 70);
        panel.add(messageScrollPane);

        JLabel encryptedMessageLabel = new JLabel("Encrypted Message:");
        encryptedMessageLabel.setBounds(10, 200, 150, 25);
        panel.add(encryptedMessageLabel);

        encryptedMessageField = new JTextField();
        encryptedMessageField.setBounds(175, 200, 530, 25);
        encryptedMessageField.setEditable(false);
        panel.add(encryptedMessageField);

        JButton encryptButton = new JButton("Encrypt (Send to Bob)");
        encryptButton.setBounds(175, 230, 530, 25);
        encryptButton.addActionListener(e -> {
            try {
                String message = messageArea.getText().trim();
                if (message.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please enter a message.");
                    return;
                }
                BigInteger m = new BigInteger(message.getBytes());
                BigInteger[] keys = parseKeys(publicKeyField.getText().trim());
                if (keys == null) {
                    JOptionPane.showMessageDialog(panel, "Please generate keys first.");
                    return;
                }
                BigInteger c = encrypt(m, keys[0], keys[1]);
                encryptedMessageField.setText(c.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "An error occurred: " + ex.getMessage());
            }
        });
        panel.add(encryptButton);

        JLabel decryptedMessageLabel = new JLabel("Decrypted Message (Bob):");
        decryptedMessageLabel.setBounds(10, 290, 150, 25);
        panel.add(decryptedMessageLabel);

        decryptedMessageField = new JTextArea();
        decryptedMessageField.setLineWrap(true);
        decryptedMessageField.setWrapStyleWord(true);
        decryptedMessageField.setEditable(false);
        JScrollPane decryptedMessageScrollPane = new JScrollPane(decryptedMessageField);
        decryptedMessageScrollPane.setBounds(175, 290, 530, 70);
        panel.add(decryptedMessageScrollPane);

        JLabel interceptedMessageLabel = new JLabel("Intercepted Message (Charlie):");
        interceptedMessageLabel.setBounds(10, 420, 200, 25);
        panel.add(interceptedMessageLabel);

        interceptedMessageField = new JTextField();
        interceptedMessageField.setBounds(200, 420, 505, 25);
        interceptedMessageField.setEditable(false);
        panel.add(interceptedMessageField);

        JButton decryptButton = new JButton("Decrypt (Bob Receives the Message)");
        decryptButton.setBounds(175, 365, 530, 25);
        decryptButton.addActionListener(e -> {
            try {
                String encryptedMessage = encryptedMessageField.getText().trim();
                if (encryptedMessage.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please enter an encrypted message.");
                    return;
                }
                BigInteger c = new BigInteger(encryptedMessage);
                BigInteger[] keys = parseKeys(privateKeyField.getText().trim());
                if (keys == null) {
                    JOptionPane.showMessageDialog(panel, "Please generate keys first.");
                    return;
                }
                BigInteger m = decrypt(c, keys[0], keys[1]);
                interceptedMessageField.setText(c.toString());
                decryptedMessageField.setText(new String(m.toByteArray()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "An error occurred: " + ex.getMessage());
            }
        });
        panel.add(decryptButton);

        add(panel, BorderLayout.CENTER);

        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private BigInteger[] parseKeys(String keys) {
        String[] parts = keys.split(" ");
        if (parts.length != 2) {
            return null;
        }
        try {
            BigInteger e = new BigInteger(parts[0]);
            BigInteger n = new BigInteger(parts[1]);
            return new BigInteger[] { e, n };
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        RSA_GUI gui = new RSA_GUI();
        gui.setVisible(true);
    }

    public static BigInteger generatePrime(int numBits) {
        Random random = new Random();
        return BigInteger.probablePrime(numBits, random);
    }

    public static BigInteger generateRandomNumber(BigInteger r) {
        Random random = new Random();
        BigInteger e = BigInteger.ZERO;
        while (e.gcd(r).intValue() != 1) {
            e = new BigInteger(r.bitLength(), random);
        }
        return e;
    }

    public static BigInteger[] generateKeys() {
        BigInteger p = generatePrime(KEY_SIZE);
        BigInteger q = generatePrime(KEY_SIZE);
        BigInteger n = p.multiply(q);
        BigInteger r = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = generateRandomNumber(r);
        BigInteger d = e.modInverse(r);
        return new BigInteger[] { e, d, n };
    }

    public static BigInteger encrypt(BigInteger m, BigInteger e, BigInteger n) {
        return m.modPow(e, n);
    }

    public static BigInteger decrypt(BigInteger c, BigInteger d, BigInteger n) {
        return c.modPow(d, n);
    }
}