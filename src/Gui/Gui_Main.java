package Gui;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;
import utils.Point3D;

public class Gui_Main {

	public static void main(String[] args) {
		
		Point3D p1 = new Point3D(100, 100);
		Point3D p2 = new Point3D(200, 100);
		Point3D p3 = new Point3D(150, 150);
		node n1 = new node(p1, 0);
		node n2 = new node(p2, 0);
		node n3 = new node(p3, 0);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 7);
		g.connect(n2.getKey(), n1.getKey(), 2.77);
		g.connect(n2.getKey(), n3.getKey(), 4.5);

		GraphGui a = new GraphGui(g);
		a.setVisible(true);
	
	
		
	}
	                                                                                         

}
