package com.skytechbytes.testplugin;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;



public class APIWrapper {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static String readJsonFromUrl(String url) throws IOException {
	  URLConnection connection = new URL(url).openConnection();
	  
	  /**
	   * Another brilliant stackoverflow solution by https://stackoverflow.com/questions/13670692/403-forbidden-with-java-but-not-web-browser
	   * What it does? I'm not really sure, but it works!
	   */
	  
	  connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
	  connection.connect();
	  
		InputStream is = connection.getInputStream();
		try {
		  BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		  String jsonText = readAll(rd);

		  return jsonText;
		} finally {
		  is.close();
		}
  }

}