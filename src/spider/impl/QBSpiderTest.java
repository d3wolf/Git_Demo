package spider.impl;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

public class QBSpiderTest {

	QBSpider spider = new QBSpider("http://www.qiushibaike.com/text");
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetDocumentFromUrl() {
		try {
			Document doc = spider.getDocumentFromUrl();
			System.out.println(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testParseText() {
		try {
			Elements contents = spider.parseText();
			for(Element content : contents){
				System.out.println(content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
