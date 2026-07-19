package com.skytechbytes.playerstatuebuilder.builder;

import com.jhlabs.image.ContrastFilter;
import com.jhlabs.image.HSBAdjustFilter;
import com.jhlabs.image.PosterizeFilter;
import com.jhlabs.image.SaturationFilter;

import java.awt.*;
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
	 * @param source
	 * @return
	 */
	public static BufferedImage deepCopy(BufferedImage source) {
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
	    Graphics2D g = b.createGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
	/**
	 * Required to un-index a file (change it to RGB mode from Index color mode)
	 * @param i
	 * @return
	 */
	public static BufferedImage toRGB(Image i) {
	    BufferedImage rgb = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    rgb.createGraphics().drawImage(i, 0, 0, null);
	    return rgb;
	}
	
	public static BufferedImage applyFilters(BufferedImage bi, float contrast, float brightness,
											 float saturation, float hue, float posterize) {
		// BufferedImage newImage = new BufferedImage(bi.getWidth(),bi.getHeight(),BufferedImage.TYPE_INT_ARGB);

		if (contrast != -1) {
			checkParams("contrast", contrast, 0, 1);
			ContrastFilter cf = new ContrastFilter();
			cf.setContrast(contrast);
			bi = cf.filter(bi, null);
		}
		if (brightness != -1) {
			checkParams("brightness", brightness, 0, 1);
			ContrastFilter cf = new ContrastFilter();
			cf.setBrightness(brightness);
			bi = cf.filter(bi, null);
		}
		if (saturation != -1) {
			checkParams("saturation", saturation, 0, 1);
			SaturationFilter sf = new SaturationFilter();
			sf.setAmount(saturation);
			bi = sf.filter(bi,  null);
		}
		if (hue != -1) {
			checkParams("hue", hue, 0, 1);
			HSBAdjustFilter hsb = new HSBAdjustFilter();
			hsb.setHFactor(hue);
			bi = hsb.filter(bi, null);
		}
		if (posterize != -1) {
			checkParams("posterize", posterize, 0, 128);
			PosterizeFilter p = new PosterizeFilter();
			p.setNumLevels(Math.round(posterize));
			bi = p.filter(bi, null);
		}

		return bi;
		
	}
	private static void checkParams(String name, float in, float low, float high) throws IllegalArgumentException {
		if (in < low || in > high) {
			throw new IllegalArgumentException("Parameter " + name + " must be in the range " + low + " to " + high);
		}
	}
}
