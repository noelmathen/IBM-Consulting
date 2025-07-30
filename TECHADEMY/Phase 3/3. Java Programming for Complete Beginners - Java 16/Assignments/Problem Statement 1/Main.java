import java.io.File;
import java.util.Arrays;
import javax.crypto.SecretKey;

public class Main {
    public static void main(String[] args) {
        try {
            // Generate AES key
            SecretKey secretKey = FileEncryption.generateKey();
            System.out.println("AES key generated.");

            // Define files
            File inputFile = new File("input.txt");
            File encryptedFile = new File("encrypted.dat");
            File decryptedFile = new File("decrypted.txt");

            // Encrypt file
            FileEncryption.encryptFile(secretKey, inputFile, encryptedFile);
            System.out.println("File encrypted to " + encryptedFile.getName());

            // Decrypt file
            FileEncryption.decryptFile(secretKey, encryptedFile, decryptedFile);
            System.out.println("File decrypted to " + decryptedFile.getName());

            // Verify integrity by comparing input and decrypted files
            byte[] original = java.nio.file.Files.readAllBytes(inputFile.toPath());
            byte[] decrypted = java.nio.file.Files.readAllBytes(decryptedFile.toPath());

            if (Arrays.equals(original, decrypted)) {
                System.out.println("Decryption verified: files match exactly.");
            } else {
                System.out.println("Decryption verification failed: files differ.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
