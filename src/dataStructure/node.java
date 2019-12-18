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

	public HashMap<Integer, node_data>  toN  = new HashMap<Integer, node_data>();
	public HashMap<Integer, node_data> fromN = new HashMap<Integer, node_data>();

	public HashMap<Integer, edge_data>  edgeTo  = new HashMap<Integer, edge_data>();
	public HashMap<Integer, edge_data> edgeFrom = new HashMap<Integer, edge_data>();
	

	/////////////////////////////////////
	/////////// Constructors ////////////
	/////////////////////////////////////

	public node () {
		this.key=keyMaker;
		this.tag=0;
		this.weight=0;
		this.info="";
		keyMaker++;
		
		toN   = new HashMap<Integer, node_data>();
		fromN = new HashMap<Integer, node_data>();

		edgeTo   = new HashMap<Integer, edge_data>();
		edgeFrom = new HashMap<Integer, edge_data>();
	}

	public node (Point3D loc, double weight) {
		this.key=keyMaker;
		this.location=loc;
		this.tag=0;
		this.weight=weight;
		this.info="";
		keyMaker++;
		
		toN   = new HashMap<Integer, node_data>();
		fromN = new HashMap<Integer, node_data>();

		edgeTo   = new HashMap<Integer, edge_data>();
		edgeFrom = new HashMap<Integer, edge_data>();
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
