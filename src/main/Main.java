package main;

import javax.swing.JFrame;

public class Main{
	public static void main(String args[]) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("game 2D ss2");
		
		
		GamePannel gamepannel = new GamePannel();
		window.add(gamepannel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamepannel.setgame();
		
		gamepannel.startGameThread();
		
	}

}
