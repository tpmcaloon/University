import java.math.BigInteger;

public class DSSAlgorithm {
    public static void main(String[] args) {
        //Alice's public key
        BigInteger p = new BigInteger("19");
        BigInteger g = new BigInteger("5");
        BigInteger y = new BigInteger("8");
        //Alice's message and signature
        BigInteger m = new BigInteger("9");
        BigInteger r = new BigInteger("7");
        BigInteger s = new BigInteger("4");
        //Bob computes v1 and v2
        BigInteger v1 = y.modPow(r.multiply(s), p);
        BigInteger v2 = g.modPow(m, p);
        //Bob verifies the message and signature
        if (v1.compareTo(v2) == 0) {
            System.out.println("Message and signature are genuine!");
        } else {
            System.out.println("Message and signature are not genuine!");
        }
    }
}
