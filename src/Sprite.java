import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	private BufferedImage image;

	public Sprite(String path) throws IOException {
		image = ImageIO.read(new File(path));
	}

	public BufferedImage getSprite() {
		return image;
	}
}
