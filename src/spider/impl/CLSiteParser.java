package spider.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CLSiteParser {
	private static String url = "http://cl.1024live.org/thread0806.php?fid=16&search=&page=1";
	private static String enc = "UTF-8";

	public static String getContentByURL(String urlStr) {
		StringBuffer temp = new StringBuffer();
		InputStream in = null;
		try {
			URL url = new URL(urlStr);
			in = url.openStream();
			Reader rd = new InputStreamReader(in, enc);
			int c = 0;
			while ((c = rd.read()) != -1) {
				temp.append((char) c);
			}
//			System.out.println(temp.toString());
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return temp.toString();
	}
	
	private void parseHomePageTop10(){
		String htmlString = getContentByURL(url);
		
		Document html = Jsoup.parse(htmlString);
	
			System.out.println(htmlString);
		
	}
	
	public static void main(String[] args) {
		CLSiteParser parser = new CLSiteParser();
		parser.parseHomePageTop10();
	}
}
