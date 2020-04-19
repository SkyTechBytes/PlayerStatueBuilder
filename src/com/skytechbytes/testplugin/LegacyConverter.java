package com.skytechbytes.testplugin;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.skytechbytes.builder.ImageUtil;
/**
 * Super useful converter by DarkVamprism!
 * https://www.reddit.com/r/Minecraft/comments/1vl7p7/releaseskin_converter/
 * Added the conversion for slim skin conversions (by SkyTechBytes)
 */
public class LegacyConverter {
	/**
	 * DOES NOT modify the image parameter put inside.
	 * @param legacy
	 * @param slim
	 * @return
	 * @throws Exception
	 */
	public static BufferedImage convertLegacy(BufferedImage legacy, boolean slim) throws Exception {

		BufferedImage inImg = legacy;
		BufferedImage outImg = new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB);
		Graphics outGfx = outImg.getGraphics();

		// Copy old layout(for the body, head and right limbs)
		outGfx.drawImage(inImg, 0, 0, 64, 32, 0, 0, 64, 32, null);

		// Right leg -> left leg
		outGfx.drawImage(inImg, 24, 48, 20, 52, 4, 16, 8, 20, null); // Top
		outGfx.drawImage(inImg, 28, 48, 24, 52, 8, 16, 12, 20, null); // Bottom
		outGfx.drawImage(inImg, 20, 52, 16, 64, 8, 20, 12, 32, null); // Outside -> inside
		outGfx.drawImage(inImg, 24, 52, 20, 64, 4, 20, 8, 32, null); // Front
		outGfx.drawImage(inImg, 28, 52, 24, 64, 0, 20, 4, 32, null); // Inside -> outside
		outGfx.drawImage(inImg, 32, 52, 28, 64, 12, 20, 16, 32, null); // Back

		// Right arm -> left arm
		
		if (slim == false) {
			outGfx.drawImage(inImg, 40, 48, 36, 52, 44, 16, 48, 20, null); // Top
			outGfx.drawImage(inImg, 44, 48, 40, 52, 48, 16, 52, 20, null); // Bottom
			outGfx.drawImage(inImg, 48, 52, 44, 64, 52, 20, 56, 32, null); // Back
			outGfx.drawImage(inImg, 40, 52, 36, 64, 44, 20, 48, 32, null); // Front
			outGfx.drawImage(inImg, 44, 52, 40, 64, 40, 20, 44, 32, null); // Inside -> outside
			outGfx.drawImage(inImg, 36, 52, 32, 64, 48, 20, 52, 32, null); // Outside -> inside
			
		} else {
			/**
			 * A bit more difficult than it seems (slim converter made by SkyTechBytes)
			 */
			BufferedImage front = inImg.getSubimage(44, 20, 3, 12);//front
			BufferedImage inside = inImg.getSubimage(47, 20, 4, 12);//inside
			BufferedImage back = inImg.getSubimage(51, 20, 3, 12);//back
			BufferedImage outside = inImg.getSubimage(40, 20, 4, 12);//outside
			BufferedImage bottom = inImg.getSubimage(47, 16, 3, 4);//bottom
			BufferedImage top = inImg.getSubimage(44, 16, 3, 4);//top
			outGfx.drawImage(front, 36+3, 52, -3, 12, null);
			outGfx.drawImage(inside, 32+4, 52, -4, 12, null);
			outGfx.drawImage(back,43+3, 52, -3, 12, null);
			outGfx.drawImage(outside, 39+4, 52, -4, 12, null);
			outGfx.drawImage(bottom, 39+3, 48, -3, 4, null);
			outGfx.drawImage(top,36+3, 48, -3, 4, null);
			
			
		}

		
		return outImg;


	}
}
