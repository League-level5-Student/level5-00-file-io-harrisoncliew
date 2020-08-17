package _02_File_Encrypt_Decrypt;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileEncryptor {
	/*
	 * Encryption is the process of encoding a message or information
	 * in such a way that only authorized parties can access it and
	 * those who are not authorized cannot.
	 *
	 * A simple shift cipher works by shifting the letters of a message
	 * down based on a key. If our key is 4 then:
	 * 
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 * 
	 * becomes:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 * 
	 * "Hello World" changes to "Lipps Asvph"
	 *
	 * Create a program that takes a message and a key from the user.
	 * Use the key to shift each letter in the users input and save the final result to a file.
	 */
	static int key = 0;
	public static void main(String[] args) {
		String encrypted = "";
		String keyInString = JOptionPane.showInputDialog("Enter key to encrypt.");
		String message = JOptionPane.showInputDialog("Enter your message.");
		key = Integer.parseInt(keyInString);
		for (int i = 0; i < message.length(); i++) {
			int asciicodekey = message.charAt(i)+key;
			if(Character.isLetter(message.charAt(i))) {
				if(Character.isLowerCase(message.charAt(i))) {
					if(asciicodekey > 122) {
						asciicodekey = asciicodekey-'z'-1+'a';
					}
				} else {
					if(asciicodekey > 90) {
						asciicodekey = asciicodekey - 'Z'-1+'A';
					}
				}
				encrypted += (char) (asciicodekey);
			} else {
				encrypted += message.charAt(i);
			}
		}
		
		try {
			FileWriter fw = new FileWriter("src/_02_File_Encrypt_Decrypt/EncryptedMessage.txt");
			fw.write(encrypted);
			fw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
//Copyright © 2020 Harrison Liew