package objicts;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePannel;
public class key_ob extends SuperObject {
	GamePannel gp;
		public key_ob(GamePannel gp){
			name = "key";
			
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/object/key.png"));
				gtool.scaleImage(image, gp.TitleSize, gp.TitleSize);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
}
