package title;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePannel;
import main.gametool;

public class TitleManganer {
	GamePannel gp;
	public Title tile[];
	public int mapTileNum[][];
	boolean drawPath = true;
	public TitleManganer(GamePannel gp){
		this.gp = gp;
		
		tile = new Title[10];
		mapTileNum = new int[gp.maxWorldcol][gp.maxWorldrow];
		
		getTitleImage();
		try {
			loadmap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getTitleImage() {
			setup(0, "grass", false);
			setup(1, "wall", true);
			setup(2, "water", true);
			setup(3, "earth", false);
			setup(4, "tree", true);
			setup(5, "sand", false);
	}

	public void setup(int index, String imagePath, boolean collision) {
		gametool gtool = new gametool();
		try {
			tile[index] = new Title();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/titles/" + imagePath + ".png"));
			tile[index].image = gtool.scaleImage(tile[index].image, gp.TitleSize, gp.TitleSize);
			tile[index].collision = collision;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadmap() throws IOException {
		InputStream in = getClass().getResourceAsStream("/map/world01.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		
	    String line;
	    int row = 0;
	    while ((line = br.readLine()) != null && row < gp.maxWorldrow) {
	        String[] numberarr = line.split(" ");
	        for (int col = 0; col < gp.maxWorldcol && col < numberarr.length; col++) {
	            int num = Integer.parseInt(numberarr[col]);
	            mapTileNum[col][row] = num;
	        }
	        row++;
	    }
			

		br.close();
	}
	public void draw(Graphics2D g2) {
		//g2.drawImage(tile[0].image, 0, 0, gp.TitleSize, gp.TitleSize, null);
		
		int WorldCol = 0;
		int WorldRow = 0;
		//int x = 0;
		//int y = 0;
		
		
		while(WorldCol < gp.maxWorldcol && WorldRow < gp.maxWorldrow) {
			int tilenum = mapTileNum[WorldCol][WorldRow];
			
			int worldX = WorldCol * gp.TitleSize;
			int worldY = WorldRow * gp.TitleSize;
			int screenX = worldX - gp.player.worldX + gp.player.ScreenX;
			int screenY = worldY - gp.player.worldY + gp.player.ScreenY;
			
			g2.drawImage(tile[tilenum].image, screenX, screenY, null);
			WorldCol++;
			//x+=gp.TitleSize;
			if(WorldCol == gp.maxWorldcol) {
				WorldCol = 0;
				//x = 0;
				WorldRow++;
				//y+=gp.TitleSize;
			}
			if(drawPath == true) {
				g2.setColor(new Color(255, 0, 0, 70));
				for(int i = 0; i<gp.pFinder.pathList.size(); i++) {
					int worldX1 = gp.pFinder.pathList.get(i).col * gp.TitleSize;
					int worldY1 = gp.pFinder.pathList.get(i).row * gp.TitleSize;
					int screenX1 = worldX - gp.player.worldX + gp.player.ScreenX;
					int screenY1 = worldY - gp.player.worldY + gp.player.ScreenY;	
					g2.fillRect(screenX, screenY, gp.TitleSize, gp.TitleSize);
				}
			}
		}
	}
}
