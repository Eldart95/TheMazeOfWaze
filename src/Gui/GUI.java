package Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JFrame;

import utils.Point3D;

/**
 * This class makes a gui window to represent a graph and
 * use the Algorithms from class Graph_Algo on live.
 * (use the methods and represent it on the gui window while it is still up).
 * @author YosefTwito and EldarTakach
 */

public class GUI extends JFrame implements ActionListener, MouseListener{
	
	LinkedList<Point3D> l = new LinkedList<Point3D>();
	
	public GUI(){
		initGUI();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.DARK_GRAY);
		g.fillOval(l.get(0).ix(), l.get(0).iy() , 8, 8);
		Point3D prev = new Point3D(l.get(0));
		
		for (int i=1; i<l.size(); i++) {
			g.setColor(Color.DARK_GRAY);
			g.fillOval(l.get(i).ix(), l.get(i).iy() , 8, 8);
			
			g.setColor(Color.yellow);
			g.drawLine(l.get(i).ix()+4, l.get(i).iy()+4, prev.ix()+4, prev.iy()+4);
			prev = new Point3D(l.get(i));
		}
			
	}
	
	private void initGUI() {
		this.setSize(1280, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		
		Menu file = new Menu("File ");
		menuBar.add(file);
		
		Menu alg  = new Menu("Algorithms ");
		menuBar.add(alg);
		
		MenuItem item1 = new MenuItem("Init Graph ");
		item1.addActionListener(this);
		file.add(item1);
		
		MenuItem item2 = new MenuItem("Init From textFile ");
		item2.addActionListener(this);
		file.add(item2);
		
		MenuItem item3 = new MenuItem("Save as textFile ");
		item3.addActionListener(this);
		file.add(item3);
		
		MenuItem item4 = new MenuItem("Save as png ");
		item4.addActionListener(this);
		file.add(item4);
		
		MenuItem item5 = new MenuItem("Show Shortest Path ");
		item5.addActionListener(this);
		alg.add(item5);
		
		MenuItem item6 = new MenuItem("$$ TSP $$ ");
		item6.addActionListener(this);
		alg.add(item6);
		
		MenuItem item7 = new MenuItem("Is Conncected ");
		item7.addActionListener(this);
		alg.add(item7);
		
		this.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String str = e.getActionCommand();
		
		if(str.equals("Init Graph ")) {
			System.out.println("Init Graph ");
			Point3D p1 = new Point3D(100,100);
			Point3D p2 = new Point3D(200,200);
			Point3D p3 = new Point3D(300,400);
			l.add(p1);
			l.add(p2);
			l.add(p3);
			
			this.repaint();
		}
		
		if(str.equals("Init From textFile ")) {
			System.out.println("Init From textFile ");
		}
		
		if(str.equals("Save as textFile ")) {
			System.out.println("Save as textFile ");
		}
		
		if(str.equals("Save as png ")) {
			System.out.println("Save as png ");
		}
		
		if(str.equals("Show Shortest Path ")) {
			System.out.println("Show Shortest Path ");
		}
		
		if(str.equals("$$ TSP $$ ")) {
			System.out.println("$$ TSP $$ ");
		}
		
		if(str.equals("Is Conncected ")) {
			System.out.println("Is Conncected ");
		}

	}

	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed");
		System.out.println(e.getX()+" , "+e.getY());
		Point3D r = new Point3D(e.getX(), e.getY(), 0);
		
		//g.setColor(Color.BLUE);
		//g.fillOval((int)p.ix(), (int)p.iy(), 20, 20);
		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited");
	}
}
