package objicts;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePannel;

public class boots_ob extends SuperObject{
	GamePannel gp;
	public boots_ob(GamePannel gp){
		name = "boot";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/boots.png"));
			gtool.scaleImage(image, gp.TitleSize, gp.TitleSize);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
