package com.skytechbytes.builder;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.bukkit.Location;
/**
 * 
 * @author SkyTechBytes
 * This is where the magic happens- all the mappings!
 *
 */
public class Splicer {

	public Splicer() {

	}
	public void makeStatue(Location l, BufferedImage ss) throws IOException {
		//0 is vertical |_
		//1 is flat |-
		//2 is sideways |_
		//Bottom, Right, Left, Top, Back, Front
		//Right Leg
		FaceBuilder fb = new FaceBuilder();
		Reader r = new Reader();
		//BufferedImage ss = r.read();
		fb.buildFace(l, r.part(ss, 8, 16, 4, 4), 1,0,1,-3,false,true);
		fb.buildFace(l, r.part(ss, 0, 20, 4, 12), 2,0,0,-3);
		//fb.buildFace(l, r.part(ss, 8, 16, 4, 4), 1,0,12,-3);
		fb.buildFace(l, r.part(ss, 12, 20, 4, 12), 0,0,0,-3,true,false);
		fb.buildFace(l, r.part(ss, 4, 20, 4, 12), 0,0,0,0);
		//Right Pantleg
		fb.buildFace(l, r.part(ss, 8, 16+16, 4, 4), 1,0,0,-3,false,true);
		fb.buildFace(l, r.part(ss, 0, 20+16, 4, 12), 2,-1,0,-3);
		//fb.buildFace(l, r.part(ss, 8, 16, 4, 4), 1,0,12,-3);
		fb.buildFace(l, r.part(ss, 12, 20+16, 4, 12), 0,0,0,-4,true,false);
		fb.buildFace(l, r.part(ss, 4, 20+16, 4, 12), 0,0,0,1);
		//Left Leg
		fb.buildFace(l, r.part(ss, 8+16, 16+32, 4, 4), 1,0+4,1,-3,false,true);
		fb.buildFace(l, r.part(ss, 24, 52, 4, 12), 2,0+7,0,-3,true,false);
		//fb.buildFace(l, r.part(ss, 8+16, 16+32, 4, 4), 1,0+4,12,-3);
		fb.buildFace(l, r.part(ss, 12+16, 20+32, 4, 12), 0,0+4,0,-3,true,false);
		fb.buildFace(l, r.part(ss, 4+16, 20+32, 4, 12), 0,0+4,0,0);

		//Left Pantleg
		fb.buildFace(l, r.part(ss, 8+16-16, 16+32, 4, 4), 1,0+4,1-1,-3,false,true);
		fb.buildFace(l, r.part(ss, 24-16, 52, 4, 12), 2,0+8,0,-3,true,false);
		//fb.buildFace(l, r.part(ss, 8+16, 16+32, 4, 4), 1,0+4,12,-3);
		fb.buildFace(l, r.part(ss, 12+16-16, 20+32, 4, 12), 0,0+4,0,-4,true,false);
		fb.buildFace(l, r.part(ss, 4+16-16, 20+32, 4, 12), 0,0+4,0,1);
		//Body
		fb.buildFace(l, r.part(ss, 20, 20, 8, 12), 0,0,12,0);
		fb.buildFace(l, r.part(ss, 32, 20, 8, 12), 0,0,12,-3,true,false);
		//Jacket
		fb.buildFace(l, r.part(ss, 20, 20+16, 8, 12), 0,0,12,1);
		fb.buildFace(l, r.part(ss, 32, 20+16, 8, 12), 0,0,12,-4,true,false);
		//Right Arm
		fb.buildFace(l, r.part(ss, 8+40, 16, 4, 4), 1,0-4,1+12,-3,false,true);
		fb.buildFace(l, r.part(ss, 0+40, 20, 4, 12), 2,0-4,0+12,-3);
		fb.buildFace(l, r.part(ss, 8+40-4, 16, 4, 4), 1,0-4,12+12,-3);
		fb.buildFace(l, r.part(ss, 12+40, 20, 4, 12), 0,0-4,0+12,-3,true,false);
		fb.buildFace(l, r.part(ss, 4+40, 20, 4, 12), 0,0-4,0+12,0);
		//Right Sleeve
		fb.buildFace(l, r.part(ss, 8+40, 16+16, 4, 4), 1,0-4,1+12-1,-3,false,true);
		fb.buildFace(l, r.part(ss, 0+40, 20+16, 4, 12), 2,0-4-1,0+12,-3);
		fb.buildFace(l, r.part(ss, 44, 16+16, 4, 4), 1,0-4,12+12+1,-3);
		fb.buildFace(l, r.part(ss, 12+40, 20+16, 4, 12), 0,0-4,0+12,-3-1,true,false);
		fb.buildFace(l, r.part(ss, 4+40, 20+16, 4, 12), 0,0-4,0+12,1);
		//Left Arm
		fb.buildFace(l, r.part(ss, 8+16+16, 16+32, 4, 4), 1,0+4+4,1+12,-3,false,true);
		fb.buildFace(l, r.part(ss, 24+16, 52, 4, 12), 2,0+7+4,0+12,-3,true,false);
		fb.buildFace(l, r.part(ss, 8+16+16-4, 16+32, 4, 4), 1,0+4+4,12+12,-3);
		fb.buildFace(l, r.part(ss, 12+16+16, 20+32, 4, 12), 0,0+4+4,0+12,-3,true,false);
		fb.buildFace(l, r.part(ss, 4+16+16, 20+32, 4, 12), 0,0+4+4,0+12,0);
		//Left Sleeve
		fb.buildFace(l, r.part(ss, 8+16+16+16, 16+32, 4, 4), 1,0+4+4,1+12-1,-3,false,true);
		fb.buildFace(l, r.part(ss, 24+16+16, 52, 4, 12), 2,0+7+4+1,0+12,-3,true,false);
		fb.buildFace(l, r.part(ss, 8+16+16-4+16, 16+32, 4, 4), 1,0+4+4,12+12+1,-3);
		fb.buildFace(l, r.part(ss, 12+16+16+16, 20+32, 4, 12), 0,0+4+4,0+12,-3-1,true,false);
		fb.buildFace(l, r.part(ss, 4+16+16+16, 20+32, 4, 12), 0,0+4+4,0+12,1);
		//Head
		fb.buildFace(l, r.part(ss, 16, 0, 8, 8), 1,0,1+24,-5,false,true);
		fb.buildFace(l, r.part(ss, 0, 8, 8, 8), 2,0,0+24,-5);
		fb.buildFace(l, r.part(ss, 16, 8, 8, 8), 2,0+7,0+24,-5,true,false);
		fb.buildFace(l, r.part(ss, 8, 0, 8, 8), 1,0,1+24+7,-5);
		fb.buildFace(l, r.part(ss, 24, 8, 8, 8), 0,0,24,-5,true,false);
		fb.buildFace(l, r.part(ss, 8, 8, 8, 8), 0,0,24,2);
		//Hat
		fb.buildFace(l, r.part(ss, 0+32, 8, 8, 8), 2,0-1,0+24,-5);
		fb.buildFace(l, r.part(ss, 16+32, 8, 8, 8), 2,0+7+1,0+24,-5,true,false);
		fb.buildFace(l, r.part(ss, 8+32, 0, 8, 8), 1,0,1+24+7+1,-5);
		fb.buildFace(l, r.part(ss, 24+32, 8, 8, 8), 0,0,24,-5-1,true,false);
		fb.buildFace(l, r.part(ss, 8+32, 8, 8, 8), 0,0,24,2+1);
	}
	public void makeLegacyStatue(Location l, BufferedImage ss) {
		//0 is vertical |_
		//1 is flat |-
		//2 is sideways |_
		//Bottom, Right, Left, Top, Back, Front
		//Right Leg
		FaceBuilder fb = new FaceBuilder();
		Reader r = new Reader();
		//BufferedImage ss = r.read();
		fb.buildFace(l, r.part(ss, 8, 16, 4, 4), 1,0,1,-3,false,true);
		fb.buildFace(l, r.part(ss, 0, 20, 4, 12), 2,0,0,-3);
		//fb.buildFace(l, r.part(ss, 8, 16, 4, 4), 1,0,12,-3);
		fb.buildFace(l, r.part(ss, 12, 20, 4, 12), 0,0,0,-3,true,false);
		fb.buildFace(l, r.part(ss, 4, 20, 4, 12), 0,0,0,0);

		//Left Leg
		fb.buildFace(l, r.part(ss, 8, 16, 4, 4), 1,0+4,1,-3,true,true);
		fb.buildFace(l, r.part(ss, 0, 20, 4, 12), 2,0+7,0,-3,false,false);
		//fb.buildFace(l, r.part(ss, 8+16, 16+32, 4, 4), 1,0+4,12,-3);
		fb.buildFace(l, r.part(ss, 12, 20, 4, 12), 0,0+4,0,-3,false,false);
		fb.buildFace(l, r.part(ss, 4, 20, 4, 12), 0,0+4,0,0,true,false);

		//Body
		fb.buildFace(l, r.part(ss, 20, 20, 8, 12), 0,0,12,0);
		fb.buildFace(l, r.part(ss, 32, 20, 8, 12), 0,0,12,-3,true,false);

		//Right Arm
		fb.buildFace(l, r.part(ss, 8+40, 16, 4, 4), 1,0-4,1+12,-3,false,true);
		fb.buildFace(l, r.part(ss, 0+40, 20, 4, 12), 2,0-4,0+12,-3);
		fb.buildFace(l, r.part(ss, 8+40-4, 16, 4, 4), 1,0-4,12+12,-3);
		fb.buildFace(l, r.part(ss, 12+40, 20, 4, 12), 0,0-4,0+12,-3,true,false);
		fb.buildFace(l, r.part(ss, 4+40, 20, 4, 12), 0,0-4,0+12,0);

		//Left Arm
		fb.buildFace(l, r.part(ss, 8+40, 16, 4, 4), 1,0+4+4,1+12,-3,true,true);
		fb.buildFace(l, r.part(ss, 0+40, 20, 4, 12), 2,0+7+4,0+12,-3,false,false);
		fb.buildFace(l, r.part(ss, 8+40-4, 16, 4, 4), 1,0+4+4,12+12,-3,true,false);
		fb.buildFace(l, r.part(ss, 12+40, 20, 4, 12), 0,0+4+4,0+12,-3,false,false);
		fb.buildFace(l, r.part(ss, 4+40, 20, 4, 12), 0,0+4+4,0+12,0,true,false);

		//Head
		fb.buildFace(l, r.part(ss, 16, 0, 8, 8), 1,0,1+24,-5,false,true);
		fb.buildFace(l, r.part(ss, 0, 8, 8, 8), 2,0,0+24,-5);
		fb.buildFace(l, r.part(ss, 16, 8, 8, 8), 2,0+7,0+24,-5,true,false);
		fb.buildFace(l, r.part(ss, 8, 0, 8, 8), 1,0,1+24+7,-5);
		fb.buildFace(l, r.part(ss, 24, 8, 8, 8), 0,0,24,-5,true,false);
		fb.buildFace(l, r.part(ss, 8, 8, 8, 8), 0,0,24,2);
		//Hat
		fb.buildFace(l, r.part(ss, 0+32, 8, 8, 8), 2,0-1,0+24,-5);
		fb.buildFace(l, r.part(ss, 16+32, 8, 8, 8), 2,0+7+1,0+24,-5,true,false);
		fb.buildFace(l, r.part(ss, 8+32, 0, 8, 8), 1,0,1+24+7+1,-5);
		fb.buildFace(l, r.part(ss, 24+32, 8, 8, 8), 0,0,24,-5-1,true,false);
		fb.buildFace(l, r.part(ss, 8+32, 8, 8, 8), 0,0,24,2+1);
	}
	public void makeSlimStatue(Location l, BufferedImage ss) {
		//0 is vertical |_
		//1 is flat |-
		//2 is sideways |_
		//Bottom, Right, Left, Top, Back, Front
		//Right Leg
		FaceBuilder fb = new FaceBuilder();
		Reader r = new Reader();
		//BufferedImage ss = r.read();
		fb.buildFace(l, r.part(ss, 8, 16, 4, 4), 1,0,1,-3,false,true);
		fb.buildFace(l, r.part(ss, 0, 20, 4, 12), 2,0,0,-3);
		//fb.buildFace(l, r.part(ss, 8, 16, 4, 4), 1,0,12,-3);
		fb.buildFace(l, r.part(ss, 12, 20, 4, 12), 0,0,0,-3,true,false);
		fb.buildFace(l, r.part(ss, 4, 20, 4, 12), 0,0,0,0);
		//Right Pantleg
		fb.buildFace(l, r.part(ss, 8, 16+16, 4, 4), 1,0,0,-3,false,true);
		fb.buildFace(l, r.part(ss, 0, 20+16, 4, 12), 2,-1,0,-3);
		//fb.buildFace(l, r.part(ss, 8, 16, 4, 4), 1,0,12,-3);
		fb.buildFace(l, r.part(ss, 12, 20+16, 4, 12), 0,0,0,-4,true,false);
		fb.buildFace(l, r.part(ss, 4, 20+16, 4, 12), 0,0,0,1);
		//Left Leg
		fb.buildFace(l, r.part(ss, 8+16, 16+32, 4, 4), 1,0+4,1,-3,false,true);
		fb.buildFace(l, r.part(ss, 24, 52, 4, 12), 2,0+7,0,-3,true,false);
		//fb.buildFace(l, r.part(ss, 8+16, 16+32, 4, 4), 1,0+4,12,-3);
		fb.buildFace(l, r.part(ss, 12+16, 20+32, 4, 12), 0,0+4,0,-3,true,false);
		fb.buildFace(l, r.part(ss, 4+16, 20+32, 4, 12), 0,0+4,0,0);

		//Left Pantleg
		fb.buildFace(l, r.part(ss, 8+16-16, 16+32, 4, 4), 1,0+4,1-1,-3,false,true);
		fb.buildFace(l, r.part(ss, 24-16, 52, 4, 12), 2,0+8,0,-3,true,false);
		//fb.buildFace(l, r.part(ss, 8+16, 16+32, 4, 4), 1,0+4,12,-3);
		fb.buildFace(l, r.part(ss, 12+16-16, 20+32, 4, 12), 0,0+4,0,-4,true,false);
		fb.buildFace(l, r.part(ss, 4+16-16, 20+32, 4, 12), 0,0+4,0,1);
		//Body
		fb.buildFace(l, r.part(ss, 20, 20, 8, 12), 0,0,12,0);
		fb.buildFace(l, r.part(ss, 32, 20, 8, 12), 0,0,12,-3,true,false);
		//Jacket
		fb.buildFace(l, r.part(ss, 20, 20+16, 8, 12), 0,0,12,1);
		fb.buildFace(l, r.part(ss, 32, 20+16, 8, 12), 0,0,12,-4,true,false);
		//Bottom, Right, Left, Top, Back, Front
		
		//Right Arm
		fb.buildFace(l, r.part(ss, 8+40-1, 16, 3, 4), 1,0-4+1,1+12,-3,false,true);
		fb.buildFace(l, r.part(ss, 0+40, 20, 4, 12), 2,0-4+1,0+12,-3);//Done
		fb.buildFace(l, r.part(ss, 8+40-4, 16, 3, 4), 1,0-4+1,12+12,-3);
		fb.buildFace(l, r.part(ss, 12+40-1, 20, 3, 12), 0,0-4+1,0+12,-3,true,false);
		fb.buildFace(l, r.part(ss, 4+40, 20, 3, 12), 0,0-4+1,0+12,0);
		//Right Sleeve
		fb.buildFace(l, r.part(ss, 8+40-1, 16+16, 3, 4), 1,0-4+1,1+12-1,-3,false,true);
		fb.buildFace(l, r.part(ss, 0+40, 20+16, 4, 12), 2,0-4+1-1,0+12,-3);//Done
		fb.buildFace(l, r.part(ss, 44, 16+16, 3, 4), 1,0-4+1,12+12+1,-3);//Unsure
		fb.buildFace(l, r.part(ss, 12+40-1, 20+16, 3, 12), 0,0-4+1,0+12,-3-1,true,false);
		fb.buildFace(l, r.part(ss, 4+40, 20+16, 3, 12), 0,0-4+1,0+12,1);
		//Left Arm
		fb.buildFace(l, r.part(ss, 8+16+16-1, 16+32, 3, 4), 1,0+4+4,1+12,-3,false,true);
		fb.buildFace(l, r.part(ss, 24+16-1, 52, 4, 12), 2,0+7+4-1,0+12,-3,true,false);//Done
		fb.buildFace(l, r.part(ss, 8+16+16-4, 16+32, 3, 4), 1,0+4+4,12+12,-3);
		fb.buildFace(l, r.part(ss, 12+16+16-1, 20+32, 3, 12), 0,0+4+4,0+12,-3,true,false);
		fb.buildFace(l, r.part(ss, 4+16+16, 20+32, 3, 12), 0,0+4+4,0+12,0);
		//Left Sleeve
		fb.buildFace(l, r.part(ss, 8+16+16+16-1, 16+32, 3, 4), 1,0+4+4,1+12-1,-3,false,true);
		fb.buildFace(l, r.part(ss, 24+16+16-1, 52, 4, 12), 2,0+7+4+1-1,0+12,-3,true,false);//Done
		fb.buildFace(l, r.part(ss, 8+16+16-4+16, 16+32, 3, 4), 1,0+4+4,12+12+1,-3);
		fb.buildFace(l, r.part(ss, 12+16+16+16-1, 20+32, 3, 12), 0,0+4+4,0+12,-3-1,true,false);
		fb.buildFace(l, r.part(ss, 4+16+16+16, 20+32, 3, 12), 0,0+4+4,0+12,1);
		
		//Head
		fb.buildFace(l, r.part(ss, 16, 0, 8, 8), 1,0,1+24,-5,false,true);
		fb.buildFace(l, r.part(ss, 0, 8, 8, 8), 2,0,0+24,-5);
		fb.buildFace(l, r.part(ss, 16, 8, 8, 8), 2,0+7,0+24,-5,true,false);
		fb.buildFace(l, r.part(ss, 8, 0, 8, 8), 1,0,1+24+7,-5);
		fb.buildFace(l, r.part(ss, 24, 8, 8, 8), 0,0,24,-5,true,false);
		fb.buildFace(l, r.part(ss, 8, 8, 8, 8), 0,0,24,2);
		//Hat
		fb.buildFace(l, r.part(ss, 0+32, 8, 8, 8), 2,0-1,0+24,-5);
		fb.buildFace(l, r.part(ss, 16+32, 8, 8, 8), 2,0+7+1,0+24,-5,true,false);
		fb.buildFace(l, r.part(ss, 8+32, 0, 8, 8), 1,0,1+24+7+1,-5);
		fb.buildFace(l, r.part(ss, 24+32, 8, 8, 8), 0,0,24,-5-1,true,false);
		fb.buildFace(l, r.part(ss, 8+32, 8, 8, 8), 0,0,24,2+1);
	}
}
