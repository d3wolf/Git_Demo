package spider.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			Elements uls = element.getElementsByTag("ul");
			Iterator ulIt = uls.iterator();
			while(ulIt.hasNext()){
				Element ulEle = (Element) ulIt.next();
				Elements lis = ulEle.getElementsByTag("li");
				Iterator liIt = lis.iterator();
				while(liIt.hasNext()){
					Element liEle = (Element) liIt.next();
					Elements contents = liEle.getAllElements();
					for(Element content : contents){
						/*
						 * “()”是分组匹配，
						 * “.”是任意字符匹配，
						 * “+”是匹配一到多个，
						 * “?”是非贪婪匹配，即最少字符匹配，否则会匹配到一些冗余信息。
						 */
						String regex1 = "<a\\s+class=\"unlink\"\\s+href=\"#\">([^<]+)</a>";
						Matcher mainMatcher = Pattern.compile(regex1).matcher(content.toString());
						if(mainMatcher.find()){
							String regex = "<a\\s+href=\"(/file/[^<]+)\">([^<]+)</a>";
							Matcher matcher = Pattern.compile(regex).matcher(content.toString());
							while(matcher.find()) {
							//	System.out.println(content);
								System.out.println(mainMatcher.group(1) + matcher.group(1) + "   " + matcher.group(2));
							}
						}
					}
				}
			}

			System.out.println("####################");
		}
	}
}
