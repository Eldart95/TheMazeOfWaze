package Gui;

import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node;
import utils.Point3D;

public class Gui_Main {

	public static void main(String[] args) {
		
		Point3D p1 = new Point3D(99, 95);
		Point3D p2 = new Point3D(203, 96);
		Point3D p3 = new Point3D(154, 152);
		Point3D p4 = new Point3D(455, 151);
		Point3D p5 = new Point3D(687, 206);
		Point3D p6 = new Point3D(142, 702);
		Point3D p7 = new Point3D(232, 437);
		Point3D p8 = new Point3D(191, 602);
		node n1 = new node(p1, 0);
		node n2 = new node(p2, 0);
		node n3 = new node(p3, 0);
		node n4 = new node(p4, 0);
		node n5 = new node(p5, 0);
		node n6 = new node(p6, 0);
		node n7 = new node(p7, 0);
		node n8 = new node(p8, 0);
		
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		g.addNode(n6);
		g.addNode(n7);
		g.addNode(n8);
		g.connect(n1.getKey(), n2.getKey(), 7);
		g.connect(n2.getKey(), n1.getKey(), 2.77);
		g.connect(n2.getKey(), n3.getKey(), 4.5);
		g.connect(n5.getKey(), n3.getKey(), 10);
		g.connect(n6.getKey(), n4.getKey(), 4.11);
		g.connect(n1.getKey(), n5.getKey(), 3.55);
		g.connect(n7.getKey(), n4.getKey(), 42);
		g.connect(n1.getKey(), n5.getKey(), 23);
		g.connect(n6.getKey(), n2.getKey(), 4.20);
		
		GraphGui a = new GraphGui(g);
		a.setVisible(true);

	}

}
