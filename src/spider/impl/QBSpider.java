package spider.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class QBSpider {

	private String url;
	public QBSpider(String url){
		this.url = url;
	}
	
	public Document getDocumentFromUrl() throws IOException{
		 Document doc = Jsoup.connect(this.url) 
				  .data("query", "Java")   // 请求参数
				  .userAgent("I ’ m jsoup") // 设置 User-Agent 
				  .cookie("auth", "token") // 设置 cookie 
				  .timeout(3000)           // 设置连接超时时间
				  .get();                 // 使用 POST 方法访问 URL 
		 
		 return doc;
	}
	
	public Document getDocumentFromUrl(String url) throws IOException{
		 Document doc = Jsoup.connect(url) 
				  .data("query", "Java")   // 请求参数
				  .userAgent("I ’ m jsoup") // 设置 User-Agent 
				  .cookie("auth", "token") // 设置 cookie 
				  .timeout(3000)           // 设置连接超时时间
				  .get();                 // 使用 POST 方法访问 URL 
		 
		 return doc;
	}
	
	public Elements parseText() throws IOException{
		Document doc = getDocumentFromUrl();
		Elements contents = doc.select("div.content");
		for(Element content : contents){
			System.out.println(content.text());
			System.out.println("-----------------------------------------");
		}
		return contents;
	}
	
	public Elements parseText(Document doc) throws IOException{
		Elements content = doc.select("div.content");
		return content;
	}
	
	public void findMoreLinks() throws IOException{
		Document doc = getDocumentFromUrl();
		Elements pages = doc.select("div.pagenumber");
		for(Element page:pages){
			Elements hrefs = page.select("a[href]");
			for(Element href : hrefs){
			//	System.out.println(href);
				String url = "http://www.qiushibaike.com/"+href.attr("href");
			//	System.out.println(url);
				Elements contents = parseText(getDocumentFromUrl(url));
				for(Element content : contents){
					
					System.out.println(content.text());
					System.out.println("-------------------------------------");
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		QBSpider spider = new QBSpider("http://www.qiushibaike.com/text");
		spider.parseText();
	}

}
