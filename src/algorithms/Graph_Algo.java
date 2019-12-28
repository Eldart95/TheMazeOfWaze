package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import dataStructure.DGraph;
import dataStructure.edge;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;
import utils.Point3D;

/**
 * This class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2.
 * @author YosefTwito and EldarTakach
 */

public class Graph_Algo implements graph_algorithms, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DGraph gr;
	
	
	public Graph_Algo() {;}
	
	public Graph_Algo(DGraph g) {
		this.gr=g;
	}
	
	
	@Override
	public void init(graph g) {
		if(g instanceof DGraph) { this.gr=(DGraph)g; }
		else { throw new RuntimeException("Error initialaizing the graph"); }
	}

	@Override
	public void init(String file_name) {
		try {
			 InputStream inStream = new FileInputStream(file_name);
		     ObjectInputStream fileObjectIn = new ObjectInputStream(inStream);
		     fileObjectIn.close();
		     inStream.close();
		}
		catch(Exception e) {
			throw new RuntimeException("Cant load from file");
		}

	}


	@Override
	public void save(String file_name) {
		try {
		OutputStream outStream = new FileOutputStream(file_name);
        ObjectOutputStream fileObjectOut = new ObjectOutputStream(outStream);
        fileObjectOut.writeObject(gr);
        fileObjectOut.close();
        outStream.close();
		}
		catch(Exception e) {
			throw new RuntimeException("error saving to file");
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
	public boolean isConnected() { // WORKING
		DGraph temp = (DGraph) this.copy();
		for(int key:temp.nodesMap.keySet()) {
			temp.nodesMap.get(key).setTag(0);
		}
		Collection<node_data> ns = temp.getV();
		node_data x = gr.getNode(0); //first node always keyd 0

		DFS(x);
		for (node_data node : ns) {
			if (node.getTag() == 0) {
				return false;
			}
		}
		reverse(temp);
		for(int key:temp.nodesMap.keySet()) {
			temp.nodesMap.get(key).setTag(0);
		}
		Collection<node_data> nss = temp.getV();
		node_data y = gr.getNode(0); //first node always keyd 0

		DFS(y);
		for (node_data node1 : nss) {
			if (node1.getTag() == 0) {
				return false;
			}
		}

		return true;
		

	}
	private void DFS(node_data node) {
		node.setTag(1);
		Collection<edge_data> t_c = this.gr.getE(node.getKey());
		if (t_c != null) {
			for (edge_data edge : t_c) {
				if (this.gr.getNode(edge.getDest()) != null && this.gr.getE(edge.getDest()) != null && this.gr.getNode(edge.getDest()).getTag() == 0) {
					DFS(this.gr.getNode(edge.getDest()));
				}
			}
		}
	}

	public void reverse (graph g) {
		Collection<node_data> n = gr.getV();
		for(node_data nodesource:n) {
			Collection<edge_data> e = gr.getE(nodesource.getKey());
			for(edge_data edge:e) {
				edge c = new edge((edge)edge);
				int tag = c.getTag();
				e.remove(c);
				gr.connect(edge.getDest(), edge.getSrc(), edge.getWeight());
				gr.getEdge(edge.getDest(), edge.getSrc()).setTag(tag);
			}
		}
		
		
	}
	

	

	@Override
	public double shortestPathDist(int src, int dest) {
		dijakstra(src);
		return gr.getNode(dest).getWeight();
	}
		
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		dijakstra(src);
	
		List<node_data> ans = new ArrayList<node_data>();
		node_data current = gr.getNode(dest);
		while(!current.getInfo().isEmpty())	{
			ans.add(0, current);
			//if(current.getInfo()=="Island") return null;
			current = gr.getNode(Integer.parseInt(current.getInfo()));
		}
		ans.add(0, current);
		
		return ans;
	

		
	}
	
	public void dijakstra(int src) {
		List<node_data>  unvisited = new LinkedList<node_data>();
		Collection<node_data> n = gr.getV(); 
		for(node_data nd:n) unvisited.add(nd);
		List<node_data>  visited = new LinkedList<node_data>();
		
		for(int key:this.gr.nodesMap.keySet())	{
			this.gr.nodesMap.get(key).setWeight(Double.MAX_VALUE);		
		}
		
		this.gr.nodesMap.get(src).setWeight(0);
		
		node_data current = gr.nodesMap.get(src); //this node
		
		while(unvisited.isEmpty()||Infinity(gr)) {
			
			visited.add(current);
			
			if(!unvisited.isEmpty()) unvisited.remove(current);
			Collection<edge_data> e = gr.getE(current.getKey()); //this is all the edges starts current node
			if(e==null) {
				break;
				
			}
			for(edge_data edge:e) {
				node_data destnode = gr.getNode(edge.getDest()); // this is the neighbour of the current node
				if(current.getWeight()+edge.getWeight()<gr.nodesMap.get(destnode.getKey()).getWeight()) { //if this node weight + the edge starting
				gr.nodesMap.get(destnode.getKey()).setWeight(edge.getWeight()+current.getWeight()); 
				destnode.setInfo(""+current.getKey());																		// at this node weight is less than the dest node weight
				}																							// than set
				
			}
			current = unvistedmin(unvisited);
	
			
		}

		
		
	}
	public node_data unvistedmin(List<node_data> ar) {
		double min = Double.MAX_VALUE;
		node_data mini=null;
		for(int i=0; i<ar.size();i++) {
			if(ar.get(i).getWeight()<min) {
				min=ar.get(i).getWeight();
				mini=ar.get(i);
			}
		}
		return mini;
	}

	
	public boolean Infinity(graph g) {
		Collection<node_data> s = g.getV();
		for(node_data n : s) {
			if(n.getWeight()==Double.MAX_VALUE) return true;
		}
		return false;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		if(!isConnected()) throw new RuntimeException("the group is not strongly connected");
		
		if(gr.nodeSize()==targets.size()) return findPath(targets);
		else {
			DGraph temp = new DGraph();
			for(int i=0;i<targets.size();i++) {
				temp.addNode(gr.getNode(targets.get(i))); // add nodes to new graph
			
			Collection<node_data> n = temp.getV();
			
			for(node_data nd:n) {
				Collection<edge_data> e = temp.getE(nd.getKey());
				if(e==null) continue;
				for(edge_data t:e) {
					edge tem = new edge((edge) t);
					temp.connect(tem.getSrc(), tem.getDest(), tem.getWeight()); //add edges to new graph
				}
			}
		}
			
			Graph_Algo not = new Graph_Algo(temp);
			
			return not.findPath(targets);
	}
		
	
	}
	
	public List<node_data> findPath(List<Integer> nodes) {
		for(int key:gr.nodesMap.keySet()) {
			gr.nodesMap.get(key).setTag(0);
		}
		ArrayList<node_data> ans = new ArrayList<node_data>();
		for(int i=0;i<nodes.size();i++) {
			DFS(gr.getNode(nodes.get(i)));
			Collection<node_data> n = gr.getV();
			for(node_data temp:n) {
				if(temp.getTag()==1 && !ans.contains(temp)) ans.add(temp);
			}
		}

		return ans;
	
	
	}


	@Override
	public graph copy() {
		graph copy = new DGraph(this.gr);
		return copy;
	}
	
	public static void main(String[] args) {
		node a = new node();
		node b = new node();
		
		DGraph c = new DGraph();
		c.addNode(a);
		c.addNode(b);
		
		c.connect(a.getKey(), b.getKey(), 2);
		c.connect(b.getKey(), a.getKey(), 4);
		Graph_Algo d = new Graph_Algo(c);
		
		System.out.println(d.shortestPathDist(a.getKey(), b.getKey()));
		
	}
}
