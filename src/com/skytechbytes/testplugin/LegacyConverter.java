package com.skytechbytes.testplugin;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 * Super useful converter by DarkVamprism!
 * https://www.reddit.com/r/Minecraft/comments/1vl7p7/releaseskin_converter/
 *
 */
public class LegacyConverter {
	
	public static BufferedImage convertLegacy(BufferedImage legacy) throws Exception {

		BufferedImage inImg = legacy;
		BufferedImage outImg = new BufferedImage(64,64,BufferedImage.TYPE_INT_ARGB);
		Graphics outGfx = outImg.getGraphics();

		if(inImg.getHeight() == 32 && inImg.getWidth() == 64)
		{
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
			outGfx.drawImage(inImg, 40, 48, 36, 52, 44, 16, 48, 20, null); // Top
			outGfx.drawImage(inImg, 44, 48, 40, 52, 48, 16, 52, 20, null); // Bottom
			outGfx.drawImage(inImg, 36, 52, 32, 64, 48, 20, 52, 32, null); // Outside -> inside
			outGfx.drawImage(inImg, 40, 52, 36, 64, 44, 20, 48, 32, null); // Front
			outGfx.drawImage(inImg, 44, 52, 40, 64, 40, 20, 44, 32, null); // Inside -> outside
			outGfx.drawImage(inImg, 48, 52, 44, 64, 52, 20, 56, 32, null); // Back

		} else {
			throw new Exception("This is not a legacy skin (64x32) but you tried to convert it to one!");
		}
		
		return outImg;


	}
}
