package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class ToDoList implements ActionListener {
	public static void main(String[] args) {
		ToDoList td = new ToDoList();
		td.start();
	}
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save
	 * list, and load list.
	 *
	 * When add task is clicked: Create a JOptionPane to ask the user for a task and
	 * add it to an ArrayList
	 * 
	 * When the view tasks button is clicked: show all the tasks in the list
	 * 
	 * When the remove task button is clicked: Create a JOptionPane to prompt the
	 * user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked: Save the list to a file
	 * 
	 * When the load list button is clicked: Create a JOptionPane to Prompt the user
	 * for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file
	 * into the list.
	 */
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton addTask = new JButton();
	JButton viewTasks = new JButton();
	JButton removeTask = new JButton();
	JButton saveList = new JButton();
	JButton loadList = new JButton();
	ArrayList<String> tasks = new ArrayList<String>();
	String[] options = { "By Number", "By Task" };
	String message = "";

	void start() {
		addTask.addActionListener(this);
		viewTasks.addActionListener(this);
		removeTask.addActionListener(this);
		saveList.addActionListener(this);
		loadList.addActionListener(this);
		addTask.setText("Add Task");
		viewTasks.setText("View Tasks");
		removeTask.setText("Remove Task");
		saveList.setText("Save List");
		loadList.setText("Load List");
		panel.add(addTask);
		panel.add(viewTasks);
		panel.add(removeTask);
		panel.add(saveList);
		panel.add(loadList);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	void update() {
		message="";
		for (int i = 0; i < tasks.size(); i++) {
			if (i == tasks.size() - 1) {
				message += tasks.get(i);
			} else {
				message += tasks.get(i) + "\n";
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == addTask) {
			String task = JOptionPane.showInputDialog("Add a task.");
			tasks.add(task);
		}
		if (e.getSource() == viewTasks) {
			update();
			JOptionPane.showMessageDialog(null, message);
		}
		if (e.getSource() == removeTask) {
			int choice = JOptionPane.showOptionDialog(null, "How would you like to remove the task?", "Task Removal",
					JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
			if (choice == 0) {
				String numForRemoval = JOptionPane.showInputDialog("Input which number task you would like to remove.");
				int num = Integer.parseInt(numForRemoval);
				tasks.remove(num-1);
			} else {
				String taskForRemoval = JOptionPane.showInputDialog("Input which task you would like to remove.");
				int size = tasks.size();
				for (int i = 0; i < tasks.size(); i++) {
					if (tasks.get(i).equals(taskForRemoval)) {
						tasks.remove(i);
						break;
					}
				}
				if(tasks.size()==size) {
					JOptionPane.showMessageDialog(null, "Error: could not find that task!" );
				}
				
			}
		}
		if (e.getSource() == saveList) {
			update();
			String whereToSave = JOptionPane.showInputDialog("Where would you like to save the file (specify package)? ");
			try {
				FileWriter fw = new FileWriter("src/"+whereToSave+"/ToDoList.txt");
				fw.write(message);
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==loadList) {
			update();
			JOptionPane.showMessageDialog(null, message);
		}
	}
}
