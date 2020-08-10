package _02_File_Encrypt_Decrypt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileDecryptor {
	/*
	 * Decryption is the process of taking encoded or encrypted text or other data
	 * and converting it back into text that you or the computer can read and understand.
	 *
	 * The shift cipher is decrypted by using using the key and shifting back up,
	 * at the end of our encryption example we had our alphabet as:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 *
	 * If we shift it back up by 4 based on the key we used the alphabet is decrypted:
	 *
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 *
	 * "Lipps Asvph" returns to "Hello World"
	 * 
	 * Create a program that opens the file created by FileEncryptor and decrypts
	 * the message, then display it to the user in a JOptionPane.
	 */
	public static void main(String[] args) {
		int asciicodekey;
		String em = "";
		String message = "";
		String keyInString = JOptionPane.showInputDialog("What is the key to decrypt the message?");
		int key = Integer.parseInt(keyInString);
		try {
			FileReader fr = new FileReader("src/_02_File_Encrypt_Decrypt/EncryptedMessage.txt");
			int c = fr.read();
			while(c!=-1) {
				em += (char) c;
				c= fr.read();
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		for (int i = 0; i < em.length(); i++) {
			asciicodekey = em.charAt(i)-key;
			if(Character.isLetter(em.charAt(i))) {
				if(Character.isLowerCase(em.charAt(i))) {
					if(asciicodekey<97) {
						asciicodekey = 'z' - ('a' - 1 - asciicodekey);
					}
				} else {
					if(asciicodekey<65) {
						asciicodekey = 'Z' - ('A' - 1 - asciicodekey);
					}
				}
				message += (char) asciicodekey;
			} else {
				message+=em.charAt(i);
			}
		}
		
		JOptionPane.showMessageDialog(null, "The encrypted message is: \n"+message);
		
	}
	
	
}
