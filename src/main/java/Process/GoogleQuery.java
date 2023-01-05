package Process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery {
	public String searchKeyword;
	public String url;
	public String content;
	public String title;
	public String citeUrl;
	public WordCounter counter;
	
//	public static void main(String[] args) throws IOException {
//		GoogleQuery g = new GoogleQuery("貓");
//		HashMap<String, String> result = g.query();
//		QuickSort q = new QuickSort();
//		for (String key : result.keySet()) {
//			q.add(new WebNode(new WebPage(key, result.get(key))));
//		}
//
//		q.sort();
//		String[][] r = q.output();
//		System.out.print(r.length);
////		System.out.println("the first:" + r[0][0]);
////		System.out.println("the first:" + r[0][1]);
////		System.out.println("the second:" + r[1][0]);
////
////		QuickSort quickSort = new QuickSort();
////		String[][] result_sorted = (String[][]) quickSort.output();
//		
//	}
	
	public GoogleQuery(String searchKeyword) {
//		this.searchKeyword = searchKeyword.concat("+coffee");
		this.url = "http://www.google.com/search?q="+searchKeyword+ "+cafe" +"&oe=utf8&num=20";
		System.out.println(url);
	}
	
	private String fetchContent() throws IOException {
		String retVal = "";

		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		//set HTTP header
		conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line = bufReader.readLine()) != null) {
			retVal += line;
		}
		return retVal;
	}
	

	
	public HashMap<String, String> query() throws IOException {
		if(content == null) {
			content = fetchContent();
		}

		HashMap<String, String> retVal = new HashMap<String, String>();
		
		
		/* 
		 * some Jsoup source
		 * https://jsoup.org/apidocs/org/jsoup/nodes/package-summary.html
		 * https://www.1ju.org/jsoup/jsoup-quick-start
 		 */
		
		//using Jsoup analyze html string
		Document doc = Jsoup.parse(content);
		
		//select particular element(tag) which you want 
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		
		for(Element li : lis) {
			try {
				citeUrl = li.select("a").get(0).attr("href");
				
				title = li.select("a").get(0).select(".vvjwJb").text();
				counter = new WordCounter(citeUrl);
				
				if(title.equals("")) {
					continue;
				}
				
				System.out.println("Title: " + title + " , url: " + citeUrl);
				
				//put title and pair into HashMap
				retVal.put(title, citeUrl);

			} catch (IndexOutOfBoundsException e) {
//				e.printStackTrace();
			}
		}
		return retVal;
	}
	
}