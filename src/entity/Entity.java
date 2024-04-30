package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePannel;
import main.gametool;
//import ai.PathFinder;

public class Entity {
	public int worldX, worldY;
	public int speed;
	
	
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String derection;
	public int spriteNum = 1;
	public int spriteCounte = 0;
	public Rectangle soidArena = new Rectangle(0,0,48,48);
	public int soidArenaDefauldX, soidArenaDefauldY;
	
	public boolean onPath = false;
	
	
	public boolean collisionOn = false;
	public int actionlockcounter=0;
	String dialos[] = new String [20];
	String textReceive[] = new String[10]; // text kiem tra truoc khi giao tiep npc
	int dialosIndex = 0;
	GamePannel gp;
	public Entity(GamePannel gp) {
		this.gp =gp;
	}
	
	public void setAction() {
		
	}
	public void checkCollision() {
			collisionOn =  false;
			gp.cCheck.checkTile(this);
			gp.cCheck.check_ob(this, false);
			gp.cCheck.checkPlayer(this);		
	}
	public void update() {
		
		    //soidArena = new Rectangle();
			setAction();
			checkCollision();
			//if(soidArena == null) continue;
			gp.cCheck.checkTile(this);	
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
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.ScreenX;
		int screenY = worldY - gp.player.worldY + gp.player.ScreenY;
		
		
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
			g2.drawImage(image, screenX, screenY, gp.TitleSize, gp.TitleSize, null);
	
		
	}
	public BufferedImage setup(String nameImage) {
	gametool gtool = new gametool();
	BufferedImage Image = null;
	try {
		Image = ImageIO.read(getClass().getResourceAsStream(nameImage+".png"));
		Image = gtool.scaleImage(Image, gp.TitleSize, gp.TitleSize);
	}
	catch(IOException e) {
		e.printStackTrace();
	}
	return Image;
	
}
	public void speak() {
		if(dialos[dialosIndex] == null) {
			dialosIndex = 0;
		}
		gp.ui.currrentDialo = dialos[dialosIndex];
		dialosIndex++;			

		switch(gp.player.derection) {
		case"up":
			derection = "down";break;
		case"down":
			derection = "up";break;
		case"left":
			derection = "right";break;
		case"right":
			derection = "left";break;
		}
	}
   
	public void searchPath(int goalcol, int goalrow){
        int startcol = (worldX + soidArena.x) / gp.TitleSize;
        int startrow = (worldY + soidArena.y) / gp.TitleSize;
        gp.pFinder.setnode (startcol, startrow, goalcol, goalrow, this);
        if(gp.pFinder.search() == true){
            // next world x,y	
        	if(!gp.pFinder.pathList.isEmpty()) {
            int nextX = gp.pFinder.pathList.get(0).col * gp.TitleSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.TitleSize; 
            
            
            int enLeftX = worldX + soidArena.x;
            int enRight = worldX + soidArena.width;
            int enTopY = worldY + soidArena.y;
            int enBottomY = worldY + soidArena.y + soidArena.height;
            
            if(enTopY > nextY && enLeftX >= nextX && enRight < nextX + gp.TitleSize) {
            	derection = "up";
            }
            if(enTopY < nextY && enLeftX >= nextX && enRight < nextX + gp.TitleSize) {
            	derection = "down";
            }
            if(enTopY >= nextY && enBottomY < nextY + gp.TitleSize) {
            	if(enLeftX > nextX) {
            		derection =  "left";
            	}
            	if(enLeftX < nextX) {
            		derection = "right";
            	}
            }
            if(enTopY > nextY && enLeftX > nextX) {
            	derection = "up";
            	checkCollision();
            	if(collisionOn == true) {
            		derection = "left";
            	}
            }
            if(enTopY > nextY && enLeftX < nextX) {
            	derection = "up";
            	checkCollision();
            	if(collisionOn == true) {
            		derection = "right";
            	}
            }
            if(enTopY < nextY && enLeftX > nextX) {
            	derection = "down";
            	checkCollision();
            	if(collisionOn == true) {
            		derection = "left";
            	}
            }
            if(enTopY < nextY && enLeftX < nextX) {
            	derection = "down";
            	checkCollision();
            	if(collisionOn == true) {
            		derection = "right";
            	}
            }
            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if(nextCol == goalcol && nextRow == goalrow) {
            	onPath = false;
            }
             



           
        }else {
        	System.out.println("khong co duong di");
        }
    }
	}
	}
