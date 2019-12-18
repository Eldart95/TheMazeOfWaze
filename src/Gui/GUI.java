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
 * 
 * @author YosefTwito and EldarTakach
 *
 */

public class GUI extends JFrame implements ActionListener, MouseListener{
	
	LinkedList<Point3D> l = new LinkedList<Point3D>();
	
	public GUI(){
		initGUI();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		
		for (Point3D p : l) {
			System.out.println(p);
			g.setColor(Color.BLUE);
			g.fillOval((int)p.ix(), (int)p.iy(), 20, 20);
		}
		g.setColor(Color.black);
		//g.drawLine(l.get(0).ix(), l.get(0).iy(), l.get(1).ix(), l.get(1).iy());
	}
	
	private void initGUI() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		
		Menu t = new Menu("Menu");
		menuBar.add(t);
		
		Menu t2 = new Menu("Menu2");
		menuBar.add(t2);
		
		MenuItem item1 = new MenuItem("Item1");
		item1.addActionListener(this);
		t.add(item1);
		
		MenuItem item2 = new MenuItem("Item2");
		item2.addActionListener(this);
		t.add(item2);
		
		this.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String str = e.getActionCommand();
		
		if(str.equals("Item1")) {
			System.out.println("Item1");
		}
		
		if(str.equals("Item2")) {
			System.out.println("Item2");
			Point3D p1 = new Point3D(100,100,0);
			Point3D p2 = new Point3D(200,200,0);
			Point3D p3 = new Point3D(300,400,0);
			l.add(p1);
			l.add(p2);
			l.add(p3);
			
			this.repaint();
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
