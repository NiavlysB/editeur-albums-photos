package structure;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class ByteImage implements Serializable {
	private static final long serialVersionUID = 3304747948794854549L;
	public byte[] bytes;
	
	public ByteImage(BufferedImage bi, String format)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bi, format, baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bytes = baos.toByteArray();
	}
}
