package test.spider;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetDataByURL {

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

	public static void main(String[] a) {
		String htmlString = getContentByURL("http://www.mhxz002.com/");
		
		Document html = Jsoup.parse(htmlString);
		String title = html.title();
		Elements ele = html.getElementById("home-index").getElementsByClass("top10-list");
		Iterator it = ele.iterator();
		while(it.hasNext()){
			Element element = (Element) it.next();
			System.out.println("###############        "+element);
		}
//		String h1 = html.body().getElementsByTag("h1").text();

//		System.out.println("Input HTML String to JSoup :" + htmlString);
//		System.out.println("After parsing, Title : " + title);
	//	System.out.println("Afte parsing, Heading : " + h1);
	}

}
