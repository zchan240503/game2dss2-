package objicts;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePannel;
import main.gametool;

public class SuperObject {
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle soidArena = new Rectangle(0,0,48,48);
	public int soidArenaDefaultX = 0;
	public int soidArenaDefaultY = 0;
	gametool gtool = new gametool();
	public void draw(Graphics2D g2, GamePannel gp) {
		int screenX = worldX - gp.player.worldX + gp.player.ScreenX;
		int screenY = worldY - gp.player.worldY + gp.player.ScreenY;
		
		g2.drawImage(image, screenX, screenY, gp.TitleSize, gp.TitleSize, null);
	}
}
