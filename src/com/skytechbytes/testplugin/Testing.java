package com.skytechbytes.testplugin;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Testing {

	public static void main(String[] args) throws IOException {
		//String x = APIWrapper.readJsonFromUrl("https://skytechbytes-api.herokuapp.com/api/playerstatuebuilderx/skin/skytechbytes");
//		System.out.println(x);
//	    ObjectMapper mapper = new ObjectMapper();
//	    SkinReference obj = mapper.readValue(x, SkinReference.class);
//	    System.out.println(obj.getUrl());
//	    URL url = new URL(obj.getUrl());
//	    BufferedImage bi = ImageIO.read(url);
//	    System.out.println(bi.getHeight());
		BufferedImage bi = APIWrapper.readFallback("SkyTechBytes");
		System.out.println(bi.getHeight());
	}

}
