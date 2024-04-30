package objicts;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePannel;

public class chess_ob extends SuperObject{
	GamePannel gp;
	public chess_ob(GamePannel gp) {
		name = "chess";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/chest.png"));
			gtool.scaleImage(image, gp.TitleSize, gp.TitleSize);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
