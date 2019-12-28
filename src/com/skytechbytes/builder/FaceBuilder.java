package com.skytechbytes.builder;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import com.skytechbytes.builder.BuildUtils;
/**
 * 
 * @author SkyTechBytes
 * This is where the faces of the statue are built (the walls)
 *
 */
public class FaceBuilder {
	public static int master_orientation = 2;
	public static int minor_orientation = 3;
	public Statue statue;
	public FaceBuilder(Statue s) {
		this.statue = s;
	}
	/**
	 * 
	 * @param o
	 * @param matrix
	 * @param orientation is x/y or x/z or y/z
	 */
	public void buildFace(Location o, Material[][] matrix, int orientation) {
		buildFace(o,matrix,orientation,0,0,0,false,false);
	}
	public void buildFace(Location o, Material[][] matrix, int orientation, int off1, int off2, int off3) {
		buildFace(o,matrix,orientation,off1,off2,off3,false,false);
	}
	public void buildFace(Location o, Material[][] matrix, int orientation, int off1, int off2, int off3, boolean flipH, boolean flipV) {
		World w = o.getWorld();
		
		if (matrix == null) {
			return;
		}
		if (flipH)BuildUtils.flipHorizontal(matrix);
		if (flipV)BuildUtils.flipVertical(matrix);
		
		for (int y = 0 ; y < matrix.length; y++) {
			for (int x = 0 ; x < matrix[y].length; x++) {
				if (master_orientation == 0) {
					switch (orientation) {
					case 0:
						gba(w,o,x+off1,-y+matrix.length+off2,off3,matrix[y][x]);
						break;
					case 1:
						gba(w,o,x+off1,off2, y+off3,matrix[y][x]);
						break;
					case 2:
						gba(w,o,off1,-y+matrix.length+off2, x+off3,matrix[y][x]);
						break;
					}
				} else if (master_orientation == 1) {
					switch (orientation) {
					case 0:
						gba(w,o,off3,off1+x,-y+matrix.length+off2,matrix[y][x]);
						break;
					case 1:
						gba(w,o,y+off3,off1+x,off2,matrix[y][x]);
						break;
					case 2:
						gba(w,o,x+off3,off1,-y+matrix.length+off2,matrix[y][x]);
						break;
					}
				} else if (master_orientation == 2) {
					switch (orientation) {
					case 0:
						gba(w,o,-y+matrix.length+off2,off3,off1+x,matrix[y][x]);
						break;
					case 1:
						gba(w,o,off2,y+off3,off1+x,matrix[y][x]);
						break;
					case 2:
						gba(w,o,-y+matrix.length+off2,x+off3,off1,matrix[y][x]);
						break;
					}
				}
			}
		}
	}
	private void gba(World w, Location o, int off1, int off2, int off3,Material m) {
		switch (minor_orientation) {
		case 0:
			ll(w,o.getBlockX()+off1, o.getBlockY()+off2, o.getBlockZ()+off3,m);
			break;
		case 1:
			ll(w,o.getBlockX()-off1, o.getBlockY()+off2, o.getBlockZ()-off3,m);
			break;
		case 2:
			ll(w,o.getBlockX()+off3, o.getBlockY()+off2, o.getBlockZ()+off1,m);
			break;
		case 3:
			ll(w,o.getBlockX()-off3, o.getBlockY()+off2, o.getBlockZ()-off1,m);
			break;
		}
		
	}
	private void ll(World w, int off1, int off2, int off3, Material m) {
		//w.getBlockAt(off1,off2,off3).setType(m);
		statue.setBlockAt(off1, off2, off3, m);
	}

}
