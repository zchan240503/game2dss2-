package entity;


import java.awt.Rectangle;
import java.util.Random;

import main.GamePannel;

public class NPC_olman extends Entity{

	public NPC_olman(GamePannel gp) {
		
		super(gp);
		soidArena = new Rectangle();
		soidArena.x = 8;
		soidArena.y = 16;
		soidArenaDefauldX = soidArena.x;
		soidArenaDefauldY = soidArena.y;
        soidArena.width = 30;
        soidArena.height = 30;
		
		// TODO Auto-generated constructor stub
		derection = "down";
		speed = 1;
		getImage();
		setDialo();
		
		
		
	}
	public void getImage() {
		up1 = setup("/NPC/oldman_up_1");
		up2 = setup("/NPC/oldman_up_2");
		down1 = setup("/NPC/oldman_down_1");
		down2 = setup("/NPC/oldman_down_2");
		left1 = setup("/NPC/oldman_left_1");
		left2 = setup("/NPC/oldman_left_2");
		right1 = setup("/NPC/oldman_right_1");
		right2 = setup("/NPC/oldman_right_2");
		
	}	
	// cac text cua hop thoai
	public void setDialo() {
		textReceive[0] = "E";
		dialos[0] = "toi muon \n thach den";
		dialos[1] = "y";
		dialos[2] = "khong co 20tr \n bo deo mo mat";
	}
	
	public void setAction() {
		if(onPath == true) {
			int goalCol = 12;
			int goalRow = 9;
			searchPath(goalCol, goalRow);
		}
		else {
		actionlockcounter++;
		Random random = new Random();
		int i = random.nextInt(4)+1;
		System.out.println(i);
		System.out.println(actionlockcounter);
		if(actionlockcounter == 150) {
		
		if(i==1) {
			derection = "up";
		}
		if(i==2) {
			derection = "down";
		}
		if(i==3) {
			derection = "left";
		}
		if(i==4) {
			derection = "right";
		}
		actionlockcounter = 0;
		}	
		}
		


	}
	public void speak() {
		super.speak();
		onPath = true;
	}
}
