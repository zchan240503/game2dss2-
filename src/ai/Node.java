package ai;

import objicts.boots_ob;

public class Node {
	Node parent;
	public int col;
	public int row;
	int gCost;
	int hCost;
	int fCost;
	boolean solid;
	boolean open;
	boolean checker;
	public Node(int col, int row) {
		this.col = col;
		this.row = row;
	}
}
