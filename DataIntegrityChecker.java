import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class DataIntegrityChecker 
{
        public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the data to check integrity: ");
        String Data = sc.nextLine();
        System.out.println("Data : " +Data+"\n");

        // Generate and printing MD5 Hash
        String md5Hash = generateHash(Data,"MD5");
        System.out.println("MD5 Hash:    " + md5Hash);

        // Generate and printing SHA-256 Hash
        String sha256Hash = generateHash(Data, "SHA-256");
        System.out.println("SHA-256 Hash: " + sha256Hash);
    }

    public static String generateHash(String s, String algorithm) {
        try {
            // 1. Initialize the MessageDigest with the specified algorithm
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            
            // 2. Pass the input string's bytes to the digest method to compute the hash
            byte[] hashBytes = digest.digest(s.getBytes());
            
            // 3. Convert the resulting byte array into a hexadecimal string representation
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hashBytes.length; i++) {
                byte b = hashBytes[i];                                                                              
                // Convert each byte to a 2-character hex representation
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // Add leading zero if necessary
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found: " + algorithm, e);
        }
    }
}