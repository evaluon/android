package co.gov.inci.evaluon.backend.services.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Pablo Dorado <pandres95@boolinc.co>
 */
public class StringHasher {

    private static MessageDigest buildMsgDigest(String algorithm) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(algorithm);
    }

    private static String bytesToHex(byte[] b) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder buf = new StringBuilder();
        for (byte j : b) {
            buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
            buf.append(hexDigit[b[j] & 0x0f]);
        }
        return buf.toString();
    }

    private static String digestString(String algorithm, String message) throws NoSuchAlgorithmException {

        MessageDigest md = buildMsgDigest(algorithm);
        md.update(message.getBytes());
        byte[] output = md.digest();
        return bytesToHex(output);

    }

    public static String SHA1(String message) throws NoSuchAlgorithmException {
        return digestString("SHA1", message);
    }

    public static String MD5(String message) throws NoSuchAlgorithmException {
        return digestString("MD5", message);
    }

}
