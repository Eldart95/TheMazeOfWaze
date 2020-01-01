package Gui;

import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node;
import utils.Point3D;

public class graphFactory {
	
	public graphFactory () {;}
	
	public node randomNode() {
		Point3D p = new Point3D((int)(Math.random()*1260)+10, (int)(Math.random()*700)+10);
		node ans = new node(p, 0);
		return ans;
	}
	
	public graph randomGraphSmallConnected() {
		graph g = new DGraph();
		node t1 = randomNode();
		node t2 = randomNode();
		node t3 = randomNode();
		node t4 = randomNode();
		node t5 = randomNode();
		node t6 = randomNode();
		node t7 = randomNode();
		node t8 = randomNode();
		node t9 = randomNode();
		node t10 = randomNode();

		g.addNode(t1);
		g.addNode(t2);
		g.addNode(t3);
		g.addNode(t4);
		g.addNode(t5);
		g.addNode(t6);
		g.addNode(t7);
		g.addNode(t8);
		g.addNode(t9);
		g.addNode(t10);
		
		g.connect(t1.getKey(), t9.getKey(), 1.2);
		g.connect(t1.getKey(), t6.getKey(), 1.56);
		g.connect(t2.getKey(), t5.getKey(), 6.4);
		g.connect(t2.getKey(), t1.getKey(), 4.1);
		g.connect(t3.getKey(), t2.getKey(), 42);
		g.connect(t3.getKey(), t5.getKey(), 6.5);
		g.connect(t4.getKey(), t7.getKey(), 2.22);
		g.connect(t4.getKey(), t8.getKey(), 3.23);
		g.connect(t5.getKey(), t9.getKey(), 12.1);
		g.connect(t5.getKey(), t3.getKey(), 6.3);
		g.connect(t6.getKey(), t1.getKey(), 4.28);
		g.connect(t6.getKey(), t3.getKey(), 8.3);
		g.connect(t7.getKey(), t6.getKey(), 1);
		g.connect(t7.getKey(), t2.getKey(), 1);
		g.connect(t8.getKey(), t4.getKey(), 1.22);
		g.connect(t8.getKey(), t10.getKey(), 5.98);
		g.connect(t9.getKey(), t4.getKey(), 7);
		g.connect(t9.getKey(), t10.getKey(), 6.9);
		g.connect(t10.getKey(), t8.getKey(), 7.2);
		g.connect(t10.getKey(), t7.getKey(), 1.88);
		
		return g;
	}
	
}
