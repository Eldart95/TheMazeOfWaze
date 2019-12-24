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
import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


import algorithms.*;
import dataStructure.*;
import utils.*;

/**
 * This class makes a gui window to represent a graph and
 * use the Algorithms from class Graph_Algo on live.
 * (use the methods and represent it on the gui window while it is still up).
 * @author YosefTwito and EldarTakach
 */

public class GraphGui extends JFrame implements ActionListener, MouseListener{
	
	graph gr;
	
	public GraphGui(graph g){
		initGUI(g);
	}
	
	
	public void paint(Graphics d) {
		super.paint(d);
		
		if (gr != null) {
			//get nodes
			Collection<node_data> nodes = gr.getV();
			
			for (node_data n : nodes) {
				//draw nodes
				Point3D p = n.getLocation();
				d.setColor(Color.BLACK);
				d.fillOval(p.ix(), p.iy(), 10, 10);
				
				//draw nodes-key's
				d.setColor(Color.BLUE);
				d.drawString(""+n.getKey(), p.ix()-4, p.iy()-4);
				
				//check if there are edges
				if (gr.edgeSize()==0) { continue; }
				if ((gr.getE(n.getKey())!=null)) {
					//get edges
					Collection<edge_data> edges = gr.getE(n.getKey());
					for (edge_data e : edges) {
						//draw edges
						d.setColor(Color.GREEN);
						
						Point3D p2 = gr.getNode(e.getDest()).getLocation();
						d.drawLine(p.ix()+5, p.iy()+5, p2.ix()+5, p2.iy()+5);
						//draw direction
						d.setColor(Color.MAGENTA);
						d.fillOval((int)((p.ix()*0.7)+(0.3*p2.ix()))+5, 1+(int)((p.iy()*0.7)+(0.3*p2.iy())), 8, 8);
						//draw weight
						String sss = ""+String.valueOf(e.getWeight());
						d.drawString(sss, 1+(int)((p.ix()*0.7)+(0.3*p2.ix())), 1+(int)((p.iy()*0.7)+(0.3*p2.iy())));
					}
				}	
			}
		}	
	}
	
	
	private void initGUI(graph g) {
		this.gr=g;
		this.setSize(1280, 720);
		this.setTitle("The Maze Of Waze !");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		
		Menu file = new Menu("File ");
		menuBar.add(file);
		
		Menu alg  = new Menu("Algorithms ");
		menuBar.add(alg);
		
		MenuItem item1 = new MenuItem("Init Graph");
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
	public void actionPerformed(ActionEvent Command) {
		String str = Command.getActionCommand();		
		Graph_Algo t=new Graph_Algo();
		JFileChooser j;
		FileNameExtensionFilter filter;
		
		switch(str) {
		
		case "Init Graph": 
			System.out.println("Init Graph: ");
			initGUI(this.gr);
			break;
		
		case "Init From textFile ": ////////////////////////////////////// gotta check /////////////////
			System.out.println("Init From textFile: ");
			t=new Graph_Algo();

			j = new JFileChooser(FileSystemView.getFileSystemView());
			j.setDialogTitle("Init graph out of text file.."); 
			filter = new FileNameExtensionFilter(" .txt","txt");
			j.setFileFilter(filter);

			int returnVal = j.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + j.getSelectedFile().getName());
				t.init(j.getSelectedFile().getAbsolutePath());
			}			
			break;

		case "Save as textFile ": ////////////////////////////////////// gotta check /////////////////
			System.out.println("Save as textFile: ");
			t=new Graph_Algo();

			j = new JFileChooser(FileSystemView.getFileSystemView());
			j.setDialogTitle("Save graph to text file.."); 
			filter = new FileNameExtensionFilter(" .txt","txt");
			j.setFileFilter(filter);

			int userSelection = j.showSaveDialog(null);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				System.out.println("Save as file: " + j.getSelectedFile().getAbsolutePath());
				t.save(j.getSelectedFile().getAbsolutePath());
			}
			break;

		case "Save as png ": 
			System.out.println("Save as png ");
			
			break;
			
		case "Show Shortest Path ":
			System.out.println("Show Shortest Path ");
			
			break;
			
		case "$$ TSP $$ ": 
			System.out.println("$$ TSP $$ ");
			break;
			
		case "Is Conncected ":
			System.out.println("Is Conncected ");
			
			break;
			
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
