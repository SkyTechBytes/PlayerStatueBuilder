package com.skytechbytes.playerstatuebuilder.builder;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bukkit.Material;
/**
 * 
 * @author SkyTechBytes
 * This class "slices" the skin image file into parts
 *
 */
public class Reader {
	public static Color pixel(int pixel) {
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;

		return new Color(red,green,blue,alpha);
	}
	public BufferedImage read() throws IOException {
		BufferedImage bi = null;

		bi = ImageIO.read(new File("str2.png"));
		
		return bi;
	}
	public Material[][] part(BufferedImage bi) {
		return part(bi,0,0,bi.getWidth(),bi.getHeight());
	}
	public Material[][] part(BufferedImage bi,int lx, int ly, int sx, int sy) {
		try {
			bi = bi.getSubimage(lx, ly, sx, sy);
		} catch (Exception e) {
			return null;
		}
		Material[][] mats = new Material[bi.getHeight()][bi.getWidth()];
		for (int y = 0 ; y < bi.getHeight() ; y++) {
			for (int x = 0 ; x < bi.getWidth() ; x++) {
				Color val = pixel(bi.getRGB(x, y));
				mats[y][x] = ColorMaps.getMatchingMaterial(val.getRed(), val.getGreen(), val.getBlue(), val.getAlpha());
			}
		}
		return mats;
	}
	
}
