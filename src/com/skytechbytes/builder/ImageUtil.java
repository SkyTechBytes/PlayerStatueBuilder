package com.skytechbytes.builder;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageUtil {
	public static BufferedImage overlayImage(BufferedImage below, BufferedImage above) {
		BufferedImage newImage = new BufferedImage(below.getWidth(),below.getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(below,0,0,null);
	    g.drawImage(above, (below.getWidth() - above.getWidth()) / 2,
	        (below.getHeight() - above.getHeight()) / 2, null);
	    g.dispose();

	    return newImage;
		
	}
	/**
	 * Thanks Stackoverflow
	 * @param bi
	 * @return
	 */
	static BufferedImage deepCopy(BufferedImage source) {
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics2D g = b.createGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
}
