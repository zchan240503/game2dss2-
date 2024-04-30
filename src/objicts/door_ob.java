package objicts;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePannel;

public class door_ob extends SuperObject{
	GamePannel gp;
	public door_ob(GamePannel gp) {
		name = "door";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/door.png"));
			gtool.scaleImage(image, gp.TitleSize, gp.TitleSize);
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
		collision = true;
	}
}
