package com.skytechbytes.playerstatuebuilder.builder;

import com.skytechbytes.playerstatuebuilder.ColorMode;
import org.bukkit.Material;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * 
 * @author SkyTechBytes
 * This class converts slices of images into a 2D matrix of minecraft blocks
 *
 */
public class Converter {
	private ColorDiffable diffFunc = new RGBDiff();
	private float w1;
	private float w2;
	private float w3;
	public Converter(ColorMode mode, float w1, float w2, float w3) {
		if (mode == ColorMode.ABSRGB) {
			diffFunc = new AbsRGBDiff();
		} else if (mode == ColorMode.HSB) {
			diffFunc = new HSBDiff();
		} else if (mode == ColorMode.HSL) {
			diffFunc = new HSLDiff();
		} else if (mode == ColorMode.LAB) {
			diffFunc = new LABDiff();
		}
		this.w1 = w1;
		this.w2 = w2;
		this.w3 = w3;
	}
	public static Color pixel(int pixel) {
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;

		return new Color(red,green,blue,alpha);
	}
	public BufferedImage read() throws IOException {
		BufferedImage bi;

		bi = ImageIO.read(new File("str2.png"));
		
		return bi;
	}
	public Material[][] part(BufferedImage bi) {
		return part(bi,0,0,bi.getWidth(),bi.getHeight());
	}
	public Material[][] part(BufferedImage bi, int lx, int ly, int sx, int sy) {
		try {
			bi = bi.getSubimage(lx, ly, sx, sy);
		} catch (Exception e) {
			return null;
		}
		Material[][] mats = new Material[bi.getHeight()][bi.getWidth()];
		for (int y = 0 ; y < bi.getHeight() ; y++) {
			for (int x = 0 ; x < bi.getWidth() ; x++) {
				Color val = pixel(bi.getRGB(x, y));
				mats[y][x] = ColorMaps.getMatchingMaterial(val, this.diffFunc, this.w1, this.w2, this.w3);
			}
		}
		return mats;
	}
	
}
