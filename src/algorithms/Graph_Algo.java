package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import dataStructure.*;

/**
 * This class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2.
 * @author YosefTwito and EldarTakach
 */
public class Graph_Algo implements graph_algorithms, Serializable{

	private static final long serialVersionUID = 1L;
	
	private DGraph gr;

	public Graph_Algo() {
		this.gr=new DGraph();
	}

	public Graph_Algo(DGraph g) {
		this.gr=g;
	}

	@Override
	public void init(graph g) {
		if(g instanceof DGraph) { this.gr=(DGraph)g; }
		else { throw new RuntimeException("Error initialaizing the graph"); }
	}
	
	/**
	 * deSerialize
	 */
	@Override
	public void init(String file_name) {
		try {
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file_name));
			graph ng = (graph) in.readObject(); 
			init(ng);
			in.close();
		}
		catch(Exception e) {
			throw new RuntimeException("Can't load from file");
		}
	}
	
	/**
	 * Serialize 
	 */
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
			throw new RuntimeException("Error saving to file");
		}	
	}

	/**
	 * Check if the current graph is strongly connected.
	 * return true if it is, else false.
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
	
	/**
	 * famous DFS algorithm.
	 * used for traversing a graph.
	 * for further reading:
	 * https://en.wikipedia.org/wiki/Depth-first_search
	 * @param node
	 */
	private void DFS(node_data node) {
		node.setTag(1);
		Collection<edge_data> t_c = this.gr.getE(node.getKey());
		if (t_c != null) {
			for (edge_data edge : t_c) {
				if (gr.getNode(edge.getDest()) != null && gr.getE(edge.getDest()) != null && gr.getNode(edge.getDest()).getTag() == 0) {
					DFS(gr.getNode(edge.getDest()));
				}
			}
		}
	}
	
	/**
	 * reverse garph's edges
	 * for each edge, replace dest with source.
	 * @param g
	 */
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
		try {
			dijakstra(src);
			return gr.getNode(dest).getWeight();
		} catch (NullPointerException e) {
			return -1;
		}
	}
	
	/**
	 * Calculates shortest path between two selected nodes.
	 * if there is'nt a path
	 * returns double.MAX_VALUE
	 */
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
	
	/**
	 * The famous dijakstra algorithm for calculating the paths from a selected node.
	 * to all other nodes in graph.
	 * for further reading:
	 * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
	 * @param src
	 */
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
				}
			}
			current = unvistedmin(unvisited);	
		}
	}
	
	/**
	 * Creates a list of unvisited nodes.
	 * @param ar
	 * @return list of unvisited nodes
	 */
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

	/**
	 * Method that check if there are nodes that weight infinity.
	 * @param g
	 * @return true if there are, else false.
	 */
	public boolean Infinity(graph g) {
		Collection<node_data> s = g.getV();
		for(node_data n : s) {
			if(n.getWeight()==Double.MAX_VALUE) return true;
		}
		return false;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) { // ~~ELDAR: NEED TO CHECK AGAIN
		if(!isConnected()) return null;
		if(targets.size()==0) throw new RuntimeException("Empty list of targets");
		ArrayList<node_data> ans = new ArrayList<node_data>();
		if(targets.size()==1) {
			ans.add(gr.getNode(targets.get(0)));
			return ans;
		}
		
		Collection<node_data> all_nodes = gr.getV(); //collection of all nodes in the graph
		ArrayList<node_data> target_nodes = new ArrayList<node_data>();//array list that will contain target nodes ONLY
		for(node_data nd:all_nodes) {
			if(targets.contains(nd.getKey())) {
				target_nodes.add(nd);
			}
		}
		boolean flag = false;
		for(int i=1;i<2020;i++) {
			for(int j=1;j<target_nodes.size();j++) {
				flag=isTherePath(target_nodes.get(j-1),target_nodes.get(j));
				
			
			}
			if(flag==true) return target_nodes;
			shuffle(target_nodes);
		}
		
		return target_nodes;
		

		
	}

	private void shuffle(ArrayList<node_data> target_nodes) {
		Collections.shuffle(target_nodes);
		
	}

	private boolean isTherePath(node_data node_data, node_data node_data2) {
		if(shortestPath(node_data.getKey(), node_data2.getKey())!=null) return true;
		return false;
	}

	@Override
	public graph copy() {
		graph copy = new DGraph(this.gr);
		return copy;
	}
	
	public static void main(String[] args) {
		node a = new node();
		node b = new node();
		
		DGraph t = new DGraph();
		t.addNode(a);
		t.addNode(b);
		
		Graph_Algo temp = new Graph_Algo(t);
		ArrayList<Integer> ar = new ArrayList<Integer>();
		ar.add(0);
		
		temp.TSP(ar);
	}
}
