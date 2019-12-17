package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class DGraph implements graph{

	HashMap<Integer, node_data> nodeMap = new HashMap<Integer, node_data>();
	ArrayList<edge> edges = new ArrayList<edge>();
	
	public DGraph() {
		this.nodeMap = new HashMap<Integer, node_data>();
		this.edges = new ArrayList<edge>();
	}
	

	@Override
	public node_data getNode(int key) { 
		if (this.nodeMap.get(key)==null) { return null; }
		return this.nodeMap.get(key); 
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		node p = (node)this.nodeMap.get(src);
		if (p.edgeTo.get(dest) != null) { return p.edgeTo.get(dest); }
		return null;
	}

	@Override
	public void addNode(node_data n) {
		int key=n.getKey();
		this.nodeMap.put(key, n);
	}

	@Override
	public void connect(int src, int dest, double w) {
		node s = (node)this.nodeMap.get(src);
		node d = (node)this.nodeMap.get(dest);
		edge e = new edge(src, dest, w);

		this.edges.add(e);

		s.edgeTo.put(dest, e);
		d.edgeFrom.put(src, e);
		s.toN.put(dest, d);
		d.fromN.put(src, s);
	}

	@Override
	public Collection<node_data> getV() { return (Collection<node_data>)this.nodeMap; }

	@Override
	public Collection<edge_data> getE(int node_id) {
		node_data n = this.nodeMap.get(node_id);
		return (Collection<edge_data>)((node)n).edgeTo;
	}

	/**
	 * Assistant method for removeNode.
	 * @param node_id
	 * @return a collection of all the edges going towards our node.
	 */
	public Collection<edge_data> getEFrom(int node_id) {
		node_data n = this.nodeMap.get(node_id);
		return (Collection<edge_data>)((node)n).edgeFrom;
	}

	@Override
	public node_data removeNode(int key) {/////////////////////// gotta check!!! ///////

		if (this.nodeMap.get(key)==null) { return null; }

		node n = (node)(this.nodeMap.get(key));

		//remove all the edges coming out of node(key) and their's link to nodes.
		Collection<edge_data> toDeleteTo = getE(key);
		Iterator<edge_data> itTo = toDeleteTo.iterator();
		while (itTo.hasNext()) {
			edge t = (edge) itTo.next();
			int s=t.getSrc();
			int d=t.getDest();
			removeEdge(s,d);
		}

		//remove all the edges coming into node(key) and their's link to nodes.
		Collection<edge_data> toDeleteFrom = getEFrom(key);
		Iterator<edge_data> itFrom = toDeleteFrom.iterator();
		while (itFrom.hasNext()) {
			edge t = (edge) itFrom.next();
			int s=t.getSrc();
			int d=t.getDest();
			removeEdge(s,d);
		}

		this.nodeMap.remove(key);
		return n;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {//////////////////// O(1) ????????????????
		node s = (node)this.nodeMap.get(src);
		node d = (node)this.nodeMap.get(dest);
		if (s.edgeTo.get(dest) == null) { return null; }
		edge_data e = s.edgeTo.get(dest); 

		s.edgeTo.remove(dest);
		d.edgeFrom.remove(src);

		for (int i=0; i<this.edges.size(); i++) {
			if (this.edges.get(i)==e) { 
				this.edges.remove(i);
				return e;
			}
		}
		return e;
	}

	@Override
	public int nodeSize() { return this.nodeMap.size(); }


	@Override
	public int edgeSize() { return this.edges.size(); }

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
