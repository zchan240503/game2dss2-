package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class gametool {
	public  BufferedImage scaleImage(BufferedImage originnal, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, originnal.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(originnal, 0, 0, width, height, null);
		g2.dispose();
		return scaledImage;
	};
	public gametool() {
		
	}
}
