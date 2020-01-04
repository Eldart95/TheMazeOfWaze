package gui;

import dataStructure.*;

public class Gui_Main {

	public static void main(String[] args) {

		graphFactory r = new graphFactory();
		graph g = r.randomGraphSmallConnected();
		
		GraphGui a = new GraphGui(g);
		a.setVisible(true);
	}
}

