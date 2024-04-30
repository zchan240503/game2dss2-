package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//import javax.swing.JFrame;
import javax.swing.JPanel;

import ai.PathFinder;
import entity.Entity;
import entity.Player;
import objicts.SuperObject;
import objicts.key_ob;
import title.TitleManganer;
public class GamePannel extends JPanel implements Runnable{
	public final int originnalTitlesize = 16;
	final int scare = 3;
	
	public final int TitleSize = originnalTitlesize * scare;
	public final int maxScreencol = 16;
	public final int maxScreenrow = 12;
	public final int screenWith = TitleSize * maxScreencol;
	public final int screenHeight = TitleSize * maxScreenrow;
	
	public int currenMap = 0;////
	
	
	public PathFinder pFinder = new PathFinder(this); // npc fl
	
	public final int maxWorldcol = 50;
	public final int maxWorldrow = 50;
	public final int worldWidth = maxWorldcol * TitleSize;
	public final int worldHeight = maxWorldrow * TitleSize;
	
	
	public int FPS = 60;
	
	
	
	public keyHandler keyr = new keyHandler(this); 
	Sound music = new Sound();
	Sound se = new Sound();
	asSetter as = new asSetter(this);
	public UI ui = new UI(this);
	public solidCollectionCheck cCheck = new solidCollectionCheck(this);
	Thread gameThread;
	
	public int playerX = 100;
	public int playerY = 100;
	public int playerSpeed = 4;
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10];
	public int gameState;
	public final int playstate = 1;
	public final int playpause = 2;
	public final int dialogue = 3;
	
	public GamePannel(){
		this.setPreferredSize(new Dimension(screenWith, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(keyr);
		this.setFocusable(true);
		
	}
	public void setgame() {
		as.setobject();
		as.setNPC();
		
		try {
			playmusic(0);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gameState = playstate;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	
	public Player player = new Player(this, keyr);
	
	public TitleManganer tileM = new TitleManganer(this);
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		double drawInterval = 1000000000 / FPS;
		double NextDrawTime = System.nanoTime() + drawInterval;
 		while(gameThread != null) {
			//System.out.println("game is running");
			
			update();
			repaint();
			
			try {
				double remainTime = NextDrawTime - System.nanoTime();
				
				remainTime = remainTime / 1000000;
				
				if(remainTime < 0) {
					remainTime = 0;
				}
				Thread.sleep((long) remainTime);
				
				NextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update() {
		if(gameState == playstate) {
			
			for(int i = 0; i<npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			
			player.update();
		}
		if(gameState == playpause) {
			// khong co gi
			
		}
		
	}
	public void paintComponent( Graphics g ) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		tileM.draw(g2);
		for(int i = 0; i<obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		//npc
		for(int i = 0; i<npc.length; i++) {
			if(npc[i] != null) {
				npc[i].draw(g2);
			}
		}
		player.draw(g2);
		ui.draw(g2);
		g2.dispose();
	}
	public void playmusic(int i) throws UnsupportedAudioFileException, LineUnavailableException {
		music.setfile(i);
		music.play();
		music.loop();
	}
	public void stopmusic() {
		music.stop();
	}
	public void playse(int i) throws UnsupportedAudioFileException, LineUnavailableException {
		se.setfile(i);
		se.play();
	}
	
}
