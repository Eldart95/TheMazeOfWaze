package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class DGraph implements graph{
	
	//Static counters:
	private static int edgesCounter=0;
	private static int MC=0;
	
	//DGraph Parameters:
	public HashMap<Integer, node> nodesMap = new HashMap<Integer, node>();
	public HashMap<Integer, HashMap<Integer,edge>> edgesMap = new HashMap<Integer, HashMap<Integer,edge>>();
	
	//Constructor:
	public DGraph() {
		this.nodesMap = new HashMap<Integer, node>();
		this.edgesMap = new HashMap<Integer, HashMap<Integer,edge>>();
	}
	
	//Methods:
	@Override
	public node_data getNode(int key) { 
		if (this.nodesMap.get(key)==null) { return null; }
		return this.nodesMap.get(key); 
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if (this.edgesMap.get(src).get(dest) != null) {
			return (edge_data)(this.edgesMap.get(src).get(dest)); 
		}
		return null;
	}

	@Override
	public void addNode(node_data n) {
		int key=n.getKey();
		this.nodesMap.put(key, (node)n);
	}

	@Override
	public void connect(int src, int dest, double w) {
		if (this.nodesMap.get(src)==null || this.nodesMap.get(dest)== null) {
			System.out.println("Can't connect nodes");
		}
		else {
			edge temp = new edge(src,dest,w);
			this.edgesMap.get(src).put(dest, temp);
			edgesCounter++;
		}
	}

	@Override
	public Collection<node_data> getV() {
		return (Collection<node_data>)this.nodesMap; 
		}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return (Collection<edge_data>)this.edgesMap.get(node_id); 
		}

	@Override
	public node_data removeNode(int key) {///////should update going-into delete with forEach //////
		
		if (this.nodesMap.get(key)==null) { return null; }
		
		node_data ans = new node(nodesMap.get(key));
		
		//remove all edges going into key-node.
		int size=edgesMap.size();
		for (int i=0; i<=size; i++) {
			if (this.edgesMap.get(i).get(key)!=null) {
				this.edgesMap.get(i).remove(key);
				edgesCounter--;
			}
		}
		
		//remove all edges coming out of key-node.
		edgesCounter -= this.edgesMap.get(key).size();
		this.edgesMap.remove(key);
		//remove the key-node.
		this.nodesMap.remove(key);

		return ans;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if (this.edgesMap.get(src).get(dest)==null) { return null; }
		edge_data e = new edge(this.edgesMap.get(src).get(dest));
		
		this.edgesMap.get(src).remove(dest);
		edgesCounter--;
		return e;
	}

	@Override
	public int nodeSize() { return this.nodesMap.size(); }

	@Override
	public int edgeSize() { return edgesCounter; }

	@Override
	public int getMC() { return MC; }
	
}
