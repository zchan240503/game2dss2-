package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import objicts.key_ob;

public class UI {
	GamePannel gp;
	Graphics2D g2;
	//BufferedImage image;
	Font ariral_48, ariral_80;
	public boolean messageOn = false;
	public String message ="";
	public int messageCount = 0;
	public boolean finishgame = false;
	public String currrentDialo = "";                     // text cua hop thoai npc
	//double playTime = 0;
	public UI(GamePannel gp) {
		this.gp = gp;
		//key_ob key = new key_ob(gp);
		ariral_48 = new Font("Arial", Font.PLAIN, 40);
		ariral_80 = new Font("Arial", Font.BOLD, 80);
 	//	image = key.image;
	}
	public void showMessage(String text) {
		this.message = text;
		 this.messageOn = true;
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(ariral_48);
		g2.setColor(Color.white);
		if(gp.gameState == gp.playstate) {}
		if(gp.gameState == gp.playpause) {
			drawPauseScreen();
		}
		if(gp.gameState == gp.dialogue) {
			drawDialoScreen();
		}
		
	}
	public void drawDialoScreen() {
		// window
		int x = gp.TitleSize *2;
		int y = gp.TitleSize / 2;
		int width = gp.screenWith - (gp.TitleSize*4);
		int height = gp.TitleSize * 4;
		drawWindowSub(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
		x+= gp.TitleSize;
		y+= gp.TitleSize;
		for(String line : currrentDialo.split("\n")) {
			g2.drawString(line, x, y);
			y+=40;
		}
		
		
	}
	public void drawWindowSub(int x, int y, int width, int height) {
		Color c = new Color(0,0,0,200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));;
		g2.drawRoundRect(x+5, y+5, width-10, height-10,25, 25);
	}
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80f));
		String text = "PAUSE";
		int x;
		int length = getxforString(text);
		x = gp.screenWith/2 - length / 2;
		int y = gp.screenHeight / 2;
		g2.drawString(text,x,y);
	}
	public int getxforString(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWith/2 - length / 2;
		return x;
	}
}
