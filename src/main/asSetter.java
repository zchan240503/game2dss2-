package main;

import entity.NPC_olman;
import entity.ghost;

public class asSetter {
	GamePannel gp;
	 public asSetter(GamePannel gp) {
		 this.gp = gp;
	 }
	 
	 public void setobject() {
		 
	 }
	 
	 // cai dat npc
	 public void setNPC() {
		 gp.npc[0] = new NPC_olman(gp);
		 gp.npc[0].worldX = gp.TitleSize*21;
		 gp.npc[0].worldY = gp.TitleSize*21;
		 
		 gp.npc[1] = new ghost(gp);
		 gp.npc[1].worldX = gp.TitleSize*22;
		 gp.npc[1].worldY = gp.TitleSize*22;
	 }
}
