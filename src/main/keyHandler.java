package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Entity;

public class keyHandler implements KeyListener{
	public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed;
	GamePannel gp;
	Entity entity;
	public keyHandler(GamePannel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();	
		// game dang chay
		if(gp.gameState == gp.playstate) {

		if(code == KeyEvent.VK_W){
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P) {
				gp.gameState = gp.playpause;
		}
		if(code == KeyEvent.VK_E) {
				ePressed  = true;
		}
		}
		// game dang dung
		else if(gp.gameState == gp.playpause) {
			 if(code == KeyEvent.VK_P) {
				 gp.gameState = gp.playstate;
			 }
			 
		 }
		// dang mo hop thoai
		else if(gp.gameState == gp.dialogue) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playstate;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W){
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}		
	}

}
