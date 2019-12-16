package dataStructure;

import java.util.ArrayList;
import java.util.HashMap;

import utils.Point3D;

public class node implements node_data {
	
	public static int keyMaker=0;
	
	/////////////////////////////////////
	//////////// Node Fields ////////////
	/////////////////////////////////////
	
	private int key, tag;
	private Point3D location;
	private double weight;
	private String info;
	
	
	HashMap<Integer, node_data>  toN  = new HashMap<>();
	HashMap<Integer, node_data> fromN = new HashMap<>();
	//public ArrayList<node>  toN  = new ArrayList<node>();
	//public ArrayList<node> fromN = new ArrayList<node>();
	
	HashMap<Integer, edge_data>  edgeTo  = new HashMap<>();
	HashMap<Integer, edge_data> edgeFrom = new HashMap<>();
	//public ArrayList<edge>  eTo  = new ArrayList<edge>();
	//public ArrayList<edge> eFrom = new ArrayList<edge>();

	/////////////////////////////////////
	/////////// Constructors ////////////
	/////////////////////////////////////
	
	public node () {
		this.key=keyMaker;
		this.tag=0;
		this.weight=0;
		this.info="";
		keyMaker++;
	}
	
	public node (Point3D loc, double weight) {
		this.key=keyMaker;
		this.location=loc;
		this.tag=0;
		this.weight=weight;
		this.info="";
		keyMaker++;
	}
	
	/////////////////////////////////////
	////////// Getters/Setters //////////
	/////////////////////////////////////
	
	@Override
	public int getKey() { return this.key; }

	@Override
	public Point3D getLocation() { return this.location; }

	@Override
	public void setLocation(Point3D p) { this.location = p; }

	@Override
	public double getWeight() { return this.weight; }

	@Override
	public void setWeight(double w) { this.weight = w; }

	@Override
	public String getInfo() { return this.info; }

	@Override
	public void setInfo(String s) { this.info = s; }

	@Override
	public int getTag() { return this.tag; }

	@Override
	public void setTag(int t) { this.tag = t; }

}
