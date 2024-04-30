package entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//import java.io.BufferedReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.GamePannel;
import main.gametool;
import main.keyHandler;

public class Player extends Entity{
	GamePannel gp;
	keyHandler keyr;
	
	public final int ScreenX;
	public final int ScreenY;
	//public int haskey;
	
	public Player(GamePannel gp, keyHandler keyr) {
		super(gp);
		ScreenX = gp.screenWith / 2 - (gp.TitleSize / 2);
		ScreenY = gp.screenHeight / 2 - (gp.TitleSize / 2);
		soidArena = new Rectangle();
		soidArena.x = 8;
		soidArena.y = 16;
		soidArenaDefauldX = soidArena.x;
		soidArenaDefauldY = soidArena.y;
		soidArena.height = 32;
		soidArena.width = 32;
				
		
		this.gp = gp;
		this.keyr = keyr;
		setdefaultValue();
		getplayerImage();
		
		derection = "down";
	}

		public void setdefaultValue() {
		worldX = gp.TitleSize * 23;
		worldY = gp.TitleSize * 21;
		speed = 4;
	}
	public void getplayerImage() {
		up1 = setup("/player/boy_up_1");
		up2 = setup("/player/boy_up_2");
		down1 = setup("/player/boy_down_1");
		down2 = setup("/player/boy_down_2");
		left1 = setup("/player/boy_left_1");
		left2 = setup("/player/boy_left_2");
		right1 = setup("/player/boy_right_1");
		right2 = setup("/player/boy_right_2");
		
	}	



	
	public void update() {
		if(keyr.upPressed == true || keyr.downPressed == true || keyr.leftPressed == true || keyr.rightPressed == true) {
			if(keyr.upPressed == true) {
				derection = "up";
				//worldY -= speed;
			}
			else if(keyr.downPressed == true) {
				derection = "down";
				//worldY += speed; 
			}
			else if(keyr.leftPressed == true) {
				derection = "left";
				//worldX -= speed;
			}
			else if(keyr.rightPressed == true) {
				derection = "right";
				//worldX += speed;
			}
			
			collisionOn = false;
			gp.cCheck.checkTile(this);
			//va cham voi do vat
			int indexobj = gp.cCheck.check_ob(this, true);
			pickup(indexobj);
			// neu collison = true nguoi dung khong the di chuyen
			int indexnpc = gp.cCheck.checkEntity(this, gp.npc);
			interactNPC(indexnpc);
			if(collisionOn == false) {
				switch(derection) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			spriteCounte ++;
			if(spriteCounte > 10) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum =1;
				}
			spriteCounte = 0;	
			}			
		}
		
	}
	public void pickup(int i) {
		if(i != 999) {
		}
	}
	public void interactNPC(int i) {
		if(i != 999) {
			//System.out.println("aa! m phai dua t di kham");
			//gp.ui.currrentDialo = textReceive[0];
			if(gp.keyr.ePressed == true) {
			gp.gameState = gp.dialogue;
			gp.npc[i].speak();				
		}
				
		}
		gp.keyr.ePressed = false;
	}
	public void draw(Graphics2D g2) {
		/*g2.setColor(Color.white);
		g2.fillRect(x, y, gp.TitleSize, gp.TitleSize);	*/
		BufferedImage image = null;
		
		
		switch(derection) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, ScreenX, ScreenY, null);
	}
}
