package ai;

import java.util.ArrayList;

import entity.Entity;
import main.GamePannel;

public class PathFinder {
	GamePannel gp;
	Node[][] node;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode, goalNode, currenNode;
	boolean goalReacher = false;
	int step = 0;
	
	
	public PathFinder(GamePannel gp) {
		this.gp = gp;
		instantilateNodes();
	}
	public void instantilateNodes() {
		node = new Node[gp.maxScreencol][gp.maxScreenrow];
		int col = 0;
		int row = 0;
		while(col<gp.maxScreencol && row<gp.maxScreenrow) {
			node[col][row] = new Node(col,row);
			col++;
			if(col == gp.maxScreencol) {
				col = 0;
				row ++ ;
			}
		}
	}
	// reset other setting
	public void resetsNode() {
		int col = 0;
		int row = 0;
		while(col<gp.maxScreencol && row<gp.maxScreenrow) {
			if(node[col][row] != null) {
			// reset open, checker, solid
			node[col][row].open = false;
			node[col][row].checker = false;
			
			col++;
			}	
			
			if(col == gp.maxScreencol) {
				col = 0;
				row ++;
			}
		}
		openList.clear();
		pathList.clear();
		goalReacher = false;
		step = 0;
		
	}
	 public void setnode(int startcol, int startrow, int goalcol, int goalrow, Entity entity){
	        resetsNode();

	        // set start and goal node

	        if(startcol >= 0 && startcol < gp.maxScreencol && startrow >= 0 && startrow < gp.maxScreenrow) {
	            startNode = node[startcol][startrow];
	        }
	        currenNode = startNode;
	        if(goalcol >= 0 && goalcol < gp.maxScreencol && goalrow >= 0 && goalrow < gp.maxScreenrow) {
	            goalNode = node[goalcol][goalrow];
	        }

	        
	        openList.add(currenNode);


	        int col =0;
	        int row = 0; 

	        while(col < gp.maxWorldcol && row< gp.maxWorldrow){
	            // set solid node
	            // check titles
	            int tilenum = gp.tileM.mapTileNum[col][row];
	            if(gp.tileM.tile[tilenum].collision == true){

	            	if(col < gp.maxScreencol && row < gp.maxScreenrow){
	            		node[col][row].solid = true;
	            	}
	                
	            }
	            // set cost
            	if(col < gp.maxScreencol && row < gp.maxScreenrow){
            		getCost(node[col][row]);
            	}
	                
	                col++;


	                if(col == gp.maxWorldcol){
	                    col = 0; row ++;
	                }

	        }
	    }

    public void getCost(Node node){
    	int xDistance;
    	int yDistance;
        //g cost
    	if(startNode != null) {
        xDistance = Math.abs(node.col - startNode.col);
        yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;    		
    	}

        //h cost
    	if(goalNode != null) {
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);

        node.hCost = xDistance + yDistance;    		
    	}


        // f cost
        node.fCost = node.hCost + node.gCost;

    }
    public boolean search(){
        while(goalReacher == false && step < 300){
        	int col;
        	int row;
        	if(currenNode != null) {
            col = currenNode.col;
            row = currenNode.row;
            // kiem tra node
            currenNode.checker = true;
            openList.remove(currenNode);

            // open up node
            if((row - 1)>=0){
                openNode(node[col][row - 1]);
            }
            // left node
            if((col - 1) >= 0){
                openNode(node[col - 1][row]);
            }
            // down node
            if(row + 1 < gp.maxWorldrow){
                openNode(node[col][row + 1]);
            }
            // right node
            if(col + 1 < gp.maxWorldcol){
                openNode(node[col + 1][row]);
            }   
                    		
        	}

            // tim loi di tot nhat
            int bestNodeIndex = 0;
            int bestNodefcost = 999;
            for(int i = 0; i<openList.size(); i++){
                if(openList.get(i)!=null && openList.get(i).fCost < bestNodefcost){
                    bestNodeIndex = i;
                    bestNodefcost = openList.get(i).fCost;
                }
                // neu f cost bang nhau kiem tra g cost
                else if(openList.get(i) != null && openList.get(i).fCost == bestNodefcost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
                 
            }
           // neu khong con nut nao -> end
           if(openList.size() == 0){
            break;
           }

           currenNode = openList.get(bestNodeIndex);
           if(currenNode == goalNode){
            goalReacher = true;
            trackthePath();
           }
           step++;
        }
        return goalReacher;
    }
    public void openNode(Node node){
        if(node.open == false && node.checker ==  false && node.solid == false){
            node.open = true;
            node.parent = currenNode;
            openList.add(node);
        }
    }
    public void trackthePath(){
        Node current = goalNode;
        while(current != startNode){
            pathList.add(0, current);
            current = current.parent;
        }
    }
}
