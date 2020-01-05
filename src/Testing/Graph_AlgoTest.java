package Testing;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;
import gui.graphFactory;

class Graph_AlgoTest {

	@Test
	void testInitSave() {
	graphFactory g = new graphFactory();
	graph t = g.randomGraphLarge();
	Graph_Algo ga = new Graph_Algo(t);
	ga.save("Test");
	Graph_Algo n_ga = new Graph_Algo();
	n_ga.init("Test");
	if(!ga.equals(n_ga)) {fail();}
	
	}

	@Test
	void testIsConnected() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testShortestPathDist() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testShortestPath() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testTSP() {
		fail("Not yet implemented"); // TODO
	}

}
