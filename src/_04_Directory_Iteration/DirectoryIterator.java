package _04_Directory_Iteration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
		 * Your task is to write a program that iterates through the src folder of this
		 * current Java Project. For every .java file it finds, the program will add a
		 * (non-legally binding) copyright statement at the bottom. Be aware of possible
		 * directories inside of directories. (e.g //Copyright © 2019 FirstName
		 * LastName)
		 */
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File directory = jfc.getSelectedFile();
			File[] file = directory.listFiles();

			if (file != null) {
				for (File f : file) {
					File[] file2 = f.listFiles();
					if (file2 != null) {
						for (File f2 : file2) {
							String fileName = f2.getAbsolutePath();
							if (fileName.substring(fileName.length() - 4, fileName.length()).equals("java")) {
								System.out.println(fileName);
								try {
									FileWriter fw = new FileWriter(fileName, true);
									String message = "//Copyright © 2020 Harrison Liew";
									fw.write(message);
									fw.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}

						}
					}

				}
			}
		}
	}
}
//Copyright © 2020 Harrison Liew