package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import dataStructure.DGraph;
import dataStructure.edge;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;
import utils.Point3D;

/**
 * This class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2.
 * @author YosefTwito and EldarTakach
 */

public class Graph_Algo implements graph_algorithms{
	public HashMap<Integer, node> nodesMap = new HashMap<Integer, node>();
	public HashMap<Integer, HashMap<Integer,edge>> edgesMap = new HashMap<Integer, HashMap<Integer,edge>>();
	public int edgesCounter=0;
	public int MC=0;

	
	public Graph_Algo(DGraph t) {
		nodesMap.putAll(t.nodesMap);
		edgesMap.putAll(edgesMap);
	}
	
	
	@Override
	public void init(graph g) {
		if(g instanceof DGraph) {
			nodesMap.putAll(((DGraph) g).nodesMap);
			edgesMap.putAll(((DGraph) g).edgesMap);
			edgesCounter=((DGraph) g).edgesCounter;
			MC=((DGraph) g).MC;
		}
		else {
			throw new RuntimeException("Error initialaizing the graph");
		}
		
	}

	@Override
	public void init(String file_name) {
		try {
			File input1 = new File(file_name);
			FileReader stream1 = new FileReader(input1);
			BufferedReader buffer1 = new BufferedReader(stream1);
			String string1 = buffer1.readLine();
			
			while(string1!=null) {
				string1=string1.substring(string1.charAt(6),string1.charAt(string1.length()-1));
				int i=0;
				while(string1.charAt(i)!='\n') {
					if(string1.charAt(i)==',') i++;
					else {
						node n = new node();
						Point3D p = new Point3D((int)(Math.random()+100),(int)(Math.random()+100));
						n.setLocation(p);
						nodesMap.put(n.getKey(), n);
						i++;
					}
					string1 = string1.substring(string1.charAt(2),string1.charAt(string1.length()));
				}
				i++;
				while(string1.charAt(i)!='\n') {
					if(string1.charAt(i)=='(') {
						char x1 = string1.charAt(i+1);
						char x2 = string1.charAt(i+3);
						int a=Character.getNumericValue(x1);  
						int b=Character.getNumericValue(x2);  
						edge n = new edge(a,b,Math.random()+100);
						HashMap<Integer,edge> y = new HashMap<>();
						y.put(b, n);
						edgesMap.put(a, y);
						
					}
					string1=string1.substring(i+4,string1.length());
				}
				
				
			}
		}
		catch(IOException e) {
			System.out.println("Cant read file");
		}

	}


	@Override
	public void save(String file_name) {

		try {
			PrintWriter write = new PrintWriter(new File(file_name));
			StringBuilder sb = new StringBuilder();
			sb.append("Nodes are: ");
			for(int key:nodesMap.keySet())	{
				node f = this.nodesMap.get(key);		
				sb.append(f.toString()+",");
			}
			sb.append("\n");
			sb.append("Edges are: ");
			for(int key:edgesMap.keySet()) {
				for(HashMap<Integer, edge> edges:edgesMap.values()) {
					edge f = edges.get(key);
					sb.append("("+f.getSrc()+","+f.getDest()+")"+",");
				}
			}
			sb.append("\n");
				
			
		
			write.write(sb.toString());
			write.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
	}
	/*For each vertex u of the graph, mark u as unvisited. Let L be empty.
	For each vertex u of the graph do Visit(u), where Visit(u) is the recursive subroutine:
	If u is unvisited then:
	Mark u as visited.
	For each out-neighbour v of u, do Visit(v).
	Prepend u to L.
	Otherwise do nothing.
	For each element u of L in order, do Assign(u,u) where Assign(u,root) is the recursive subroutine:
	If u has not been assigned to a component then:
	Assign u as belonging to the component whose root is root.
	For each in-neighbour v of u, do Assign(v,root).
	Otherwise do nothing.
	*/
	@Override
	public boolean isConnected() {
		
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		List<node_data> ans = new LinkedList<node_data>();
		for(int key:nodesMap.keySet())	{
			this.nodesMap.get(key).setWeight(Double.MAX_VALUE);		
		}
		
		this.nodesMap.get(src).setWeight(0);
		ans.add(this.nodesMap.get(src));
		
		for(int key:nodesMap.keySet())	{
			if(this.nodesMap.get(key)==this.nodesMap.get(dest)) return ans;
			if(this.nodesMap.get(key).getTag()==1) break;
			for(int key2:edgesMap.keySet()) {
				if(edgesMap.get(key).get(src).getSrc()==nodesMap.get(key).getKey()) {
					nodesMap.get(key).setWeight(edgesMap.get(key2).get(key).getWeight());	
				}
				this.nodesMap.get(key).setTag(1);
			}
			//for every node, run though the edges and choose the most light edge, add it to the list.
			}
			
		

		return ans;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		
	
		graph copy = new DGraph(nodesMap,edgesMap,edgesCounter,MC);
		return copy;
		
		
	}
	
	
	 
	

}
