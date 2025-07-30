import java.io.*;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class FileEncryption {

    private static final String ALGORITHM = "AES";

    // Generate AES key of specified size (128 bits here)
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(128, new SecureRandom());
        return keyGen.generateKey();
    }

    // Encrypt input file and write encrypted bytes to output file
    public static void encryptFile(SecretKey key, File inputFile, File outputFile) throws Exception {
        byte[] inputBytes = readFile(inputFile);
        byte[] outputBytes = doCrypto(Cipher.ENCRYPT_MODE, key, inputBytes);
        writeFile(outputFile, outputBytes);
    }

    // Decrypt encrypted file and write decrypted bytes to output file
    public static void decryptFile(SecretKey key, File encryptedFile, File outputFile) throws Exception {
        byte[] encryptedBytes = readFile(encryptedFile);
        byte[] decryptedBytes = doCrypto(Cipher.DECRYPT_MODE, key, encryptedBytes);
        writeFile(outputFile, decryptedBytes);
    }

    // Helper to perform encryption or decryption
    private static byte[] doCrypto(int cipherMode, SecretKey key, byte[] inputBytes) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(cipherMode, key);
        return cipher.doFinal(inputBytes);
    }

    // Helper to read file bytes
    private static byte[] readFile(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            int read = fis.read(bytes);
            if (read != bytes.length) {
                throw new IOException("Could not read entire file");
            }
            return bytes;
        }
    }

    // Helper to write bytes to file
    private static void writeFile(File file, byte[] bytes) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytes);
        }
    }
}
