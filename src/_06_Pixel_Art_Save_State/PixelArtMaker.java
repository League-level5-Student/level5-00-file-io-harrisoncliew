package _06_Pixel_Art_Save_State;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

import _05_Serialization.SaveData;

public class PixelArtMaker implements MouseListener {
	private static final String DATA_FILE = "src/_06_Pixel_Art_Save_State/SavedState.dat";
	private JFrame window;
	private JButton save;
	private JButton load;
	private GridInputPanel gip;
	private GridPanel gp;
	ColorSelectionPanel csp;
	
	public void start() {
		gip = new GridInputPanel(this);
		load = new JButton("Load");
		load.addMouseListener(this);
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		
		window.add(gip);
		window.add(load);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		gp = new GridPanel(w, h, r, c);
		csp = new ColorSelectionPanel();
		save = new JButton();
		save.setText("Save");
		save.addMouseListener(this);
		window.remove(gip);
		window.remove(load);
		window.add(gp);
		window.add(csp);
		window.add(save);
		gp.repaint();
		gp.addMouseListener(this);
		window.pack();
	}
	
	private static void save(GridPanel gp) {
		try (FileOutputStream fos = new FileOutputStream(new File(DATA_FILE)); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(gp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static GridPanel load() {
		try (FileInputStream fis = new FileInputStream(new File(DATA_FILE)); ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (GridPanel) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// This can occur if the object we read from the file is not
			// an instance of any recognized class
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==save) {
			save(gp);
		}else if(e.getSource()==load) {
			GridPanel temp = load();
			gp = temp;
			csp = new ColorSelectionPanel();
			save = new JButton();
			save.setText("Save");
			save.addMouseListener(this);
			window.remove(gip);
			window.remove(load);
			window.add(gp);
			window.add(csp);
			window.add(save);
			gp.repaint();
			gp.addMouseListener(this);
			window.pack();
		} else {
			gp.setColor(csp.getSelectedColor());
			System.out.println(csp.getSelectedColor());
			gp.clickPixel(e.getX(), e.getY());
			gp.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
