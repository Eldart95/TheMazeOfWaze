package gui;

import dataStructure.DGraph;
import dataStructure.graph;
import gui.graphFactory;

public class Gui_Main {

	public static void main(String[] args) {

		graphFactory r = new graphFactory();
		graph g= new DGraph(); // = r.randomGraphSmallConnected();
		
		g.addNode(r.nodeGenerator());
		g.addNode(r.nodeGenerator());
		g.connect(0, 1, 2);
		
		GraphGui a = new GraphGui(g);
		a.setVisible(true);
	}
}

