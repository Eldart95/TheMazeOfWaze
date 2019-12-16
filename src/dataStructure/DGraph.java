package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph{
	
	 HashMap<Integer, node_data> nodeMap = new HashMap<>();
	 ArrayList<edge> edges = new ArrayList<edge>();
	 
	@Override
	public node_data getNode(int key) { 
		if (nodeMap.get(key)==null) { return null; }
		return nodeMap.get(key); 
		}

	@Override
	public edge_data getEdge(int src, int dest) {
		node p = (node)nodeMap.get(src);
		if (p.edgeTo.get(dest) != null) { return p.edgeTo.get(dest); }
		return null;
	}

	@Override
	public void addNode(node_data n) {
		int key=n.getKey();
		nodeMap.put(key, n);
	}

	@Override
	public void connect(int src, int dest, double w) {
		node s = (node)nodeMap.get(src);
		node d = (node)nodeMap.get(dest);
		edge e = new edge(src, dest, w);
		
		edges.add(e);
		
		s.edgeTo.put(dest, e);
		d.edgeFrom.put(src, e);
		s.toN.put(dest, d);
		d.fromN.put(src, s);
	}

	@Override
	public Collection<node_data> getV() { return (Collection<node_data>)nodeMap; }

	@Override
	public Collection<edge_data> getE(int node_id) {
		node_data n = nodeMap.get(node_id);
		return (Collection<edge_data>)((node)n).edgeTo;
	}

	@Override
	public node_data removeNode(int key) {
		if (nodeMap.get(key)==null) { return null; }
		node n = (node)(nodeMap.get(key));
		
		ArrayList<edge> toDeleteTo = new ArrayList<edge>();
		ArrayList<edge> toDeleteFrom = new ArrayList<edge>();
		
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {//////////////////// O(1) ????????????????
		node s = (node)nodeMap.get(src);
		node d = (node)nodeMap.get(dest);
		if (s.edgeTo.get(dest) == null) { return null; }
		edge_data e = s.edgeTo.get(dest); 
		
		s.edgeTo.remove(dest);
		d.edgeFrom.remove(src);
		
		for (int i=0; i<edges.size(); i++) {
			if (edges.get(i)==e) { 
				edges.remove(i);
				return e;
			}
		}
		return e;
	}

	@Override
	public int nodeSize() { return nodeMap.size(); }
	

	@Override
	public int edgeSize() { return edges.size(); }

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
