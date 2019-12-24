package com.skytechbytes.builder;

import org.bukkit.Material;

public class BuildUtils {

	public BuildUtils() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Thank you stackoverflow
	 * Assume not weird matrix with non-null values
	 * @param matrix
	 * @return
	 */
	public static void flipVertical(Material[][] imgArray) {
		Material temp;
		for (int i = 0; i < imgArray.length / 2; i++) {
			for (int j = 0; j < imgArray[i].length; j++) {
				temp = imgArray[i][j];
				imgArray[i][j] = imgArray[imgArray.length - 1 - i][j];
				imgArray[imgArray.length - 1 -i][j] = temp;
			}
		}
	}

	public static void flipHorizontal(Material[][] matrix){

		for(int row = 0; row < matrix.length; row++){
			for(int col = 0; col < matrix[row].length / 2; col++) {
				Material temp = matrix[row][col];
				matrix[row][col] = matrix[row][matrix[row].length - col - 1];
				matrix[row][matrix[row].length - col - 1] = temp;
			}
		}

	}

}
