package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node_data;

/**
 * This class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2.
 * @author YosefTwito and EldarTakach
 */

public class Graph_Algo implements graph_algorithms{
	LinkedList<graph> gr = new LinkedList<graph>();

	@Override
	public void init(graph g) {
		gr.add(g);
		
	}

	@Override
	//init from json, each iteration the loop takes the node from
	//the node array and the vertices from the vertices 2d array
	//construct a node_data out of it and then add to list that
	//is holding the DGraph
	public void init(String file_name) {
		Gson gson = new Gson();	
		GRAPH G;
		graph temp = new DGraph();
		try {
			
			Reader r = new FileReader(file_name);
				G = gson.fromJson(r, GRAPH.class);
				for (int i = 0; i < G.nodes.length; i++) {
					int n = G.nodes[i];
					//int v = G.vertices[0][0];
					//temp.addNode(n);
					gr.add(temp);
					
				}
				
			} catch (FileNotFoundException e) {
				
			}
		
	}
	public Iterator<graph> iterator() {
		return gr.iterator();
	}

	@Override
	//ver1: write the graph to file with toString
	//ver2: write the graph to json
	public void save(String file_name) {
	/*	Iterator<graph> it = gr.iterator();
		try {
			PrintWriter write = new PrintWriter(new File(file_name));
			StringBuilder sb = new StringBuilder();
			
			while (it.hasNext()) {
				graph f = it.next();
				sb.append(f.toString()+"\n");
			}	
			write.write(sb.toString());
			write.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
*/		Iterator<graph> it = gr.iterator();
		Gson gson = new Gson();	
		GRAPH G;
		try {
			JsonWriter  writer = new JsonWriter(new FileWriter("C:\\file.json"));
			writer.beginObject();
			writer.name("nodes:");
			writer.beginArray();
			
			
			while (it.hasNext()) {
				graph f = it.next();
				
				writer.value(f.edgeSize());
				
			}
	        writer.endArray();
	        writer.endObject();
	        writer.close();
			
			
		} 
		catch (IOException e) {
				e.printStackTrace();
			}
	}
	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		graph ng = new DGraph();
		//copy shit
		return ng;
		
	}
	private class GRAPH{
		int []nodes = {0};
		//int [][]arr = new int[2][];
		
		
	}
	

}
