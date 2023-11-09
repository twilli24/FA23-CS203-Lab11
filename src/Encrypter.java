import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class Encrypter {

	private int shift;
	private String encrypted;

	/**
	 * Default Constructor
	 */
	public Encrypter() {
		this.shift = 1;
		this.encrypted = "";
	}

	/**
	 * Non-default Constructor
	 * 
	 * @param s - custom shift amount
	 */
	public Encrypter(int s) {
		this.shift = s;
		this.encrypted = "";
	}

	/**
	 * Encrypts the content of a file and writes the result to another file.
	 *
	 * @param inputFilePath     the path to the file containing the text to be
	 *                          encrypted
	 * @param encryptedFilePath the path to the file where the encrypted text will
	 *                          be written
	 * @throws Exception if an error occurs while reading or writing the files
	 */
	public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
		// TODO: Call the read method, encrypt the file contents, and then write to new
		// file
		String alphabet = "abcdefghijklmnopqrstuvwxyz";

		readFile(inputFilePath);
		shift = 3;
		for (int i = 0; i < inputFilePath.length(); i++) {
			int pos = alphabet.indexOf(inputFilePath.charAt(i));
			int encryptPos = (shift + pos) % 26;
			char encryptChar = alphabet.charAt(encryptPos);
			encrypted += encryptChar;
		}
		writeFile(encrypted, encryptedFilePath);
	}

	/**
	 * Decrypts the content of an encrypted file and writes the result to another
	 * file.
	 *
	 * @param messageFilePath   the path to the file containing the encrypted text
	 * @param decryptedFilePath the path to the file where the decrypted text will
	 *                          be written
	 * @throws Exception if an error occurs while reading or writing the files
	 */
	public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
		// TODO: Call the read method, decrypt the file contents, and then write to new
		// file
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String decryptStr = "";
		readFile(messageFilePath);
		shift = 3;
		for (int i = 0; i < messageFilePath.length(); i++) {
			int pos = alphabet.indexOf(messageFilePath.charAt(i));
			int decryptPos = (pos - shift) % 26;
			if (decryptPos < 0) {
				decryptPos = alphabet.length() + decryptPos;
			}
			char decryptChar = alphabet.charAt(decryptPos);
			decryptStr += decryptChar;
		}
		writeFile(decryptStr, decryptedFilePath);
	}

	/**
	 * Reads the content of a file and returns it as a string.
	 *
	 * @param filePath the path to the file to be read
	 * @return the content of the file as a string
	 * @throws Exception if an error occurs while reading the file
	 */
	private static String readFile(String filePath) throws Exception {
		String message = "";
		// TODO: Read file from filePath
		try (Scanner filescanner = new Scanner(filePath)) {
			while (filescanner.hasNextLine()) {
				message = filescanner.nextLine();
			}
			filescanner.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return message;
	}

	/**
	 * Writes data to a file.
	 *
	 * @param data     the data to be written to the file
	 * @param filePath the path to the file where the data will be written
	 */
	private static void writeFile(String data, String filePath) {
		// TODO: Write to filePath
		try (PrintWriter output = new PrintWriter(filePath)) {
			
			output.print(data);
			output.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}

	/**
	 * Returns a string representation of the encrypted text.
	 *
	 * @return the encrypted text
	 */
	@Override
	public String toString() {
		return encrypted;
	}
}
