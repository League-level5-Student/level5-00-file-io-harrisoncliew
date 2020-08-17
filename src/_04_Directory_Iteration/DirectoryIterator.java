 package _04_Directory_Iteration;

import java.io.File;

import javax.swing.JFileChooser;

public class DirectoryIterator {
	public static void main(String[] args) {
		System.out.println("a");
		/* 
		 * The following is an example of how to list all of the files in a directory.
		 * Once the program is running, the directory is chosen using the JFileChooser.
		 */
//		JFileChooser jfc2 = new JFileChooser();
//		jfc2.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//		int returnVal2 = jfc2.showOpenDialog(null);
//		if (returnVal2 == JFileChooser.APPROVE_OPTION) {
//			File directory2 = jfc2.getSelectedFile();
//			File[] files = directory2.listFiles();
//			if(files != null) {
//				for(File f : files) {
//				  System.out.println(f.getAbsolutePath());
//				}
//			}
//		}
		
		/*
		 * Your task is to write a program that iterates through the src folder of this current Java Project. 
		 * For every .java file it finds, the program will add a (non-legally binding) copyright statement at the bottom.
		 * Be aware of possible directories inside of directories.
		 * (e.g //Copyright © 2019 FirstName LastName)
		 */
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = jfc.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File directory = jfc.getSelectedFile();
			File[] file = directory.listFiles();
			if(file != null) {
				for(File f: file) {
					System.out.println(f.getAbsolutePath());
				}
			}
		}
	}
}
