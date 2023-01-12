package Process;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
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
	
	public String[] keyNoiseLst = {"accounts.google.com", ".php", "www.youtube.com", "shopee.tw", "tiktok", "inline.app"};
	
//	public static void main(String[] args) throws IOException {
//		GoogleQuery g = new GoogleQuery("貓");
//		WebNode root = new WebNode(new WebPage("cat cafe", "https://www.walkerland.com.tw/subject/view/325139"));
//		
//		WebTree webTree = g.getAllLink(root);
//		
//		KeywordList key = new KeywordList();
//		key.add(new Keyword("咖啡",5.0));
//////		
//		webTree.setPostOrderScore(key);
//		System.out.println("Total: " + Double.toString(webTree.getScore()));
////		webTree.eularPrintTree(root);
////		System.out.println("ArrayList Size: " + Integer.toString(webTree.root.children.size()));
////		HashMap<String, String> result = g.query();
////		QuickSort q = new QuickSort();
////		for (String key : result.keySet()) {
////			q.add(new WebNode(new WebPage(key, result.get(key))));
////		}
////
////		q.sort();
////		String[][] r = q.output();
////		System.out.print(r.length);
////		System.out.println("the first:" + r[0][0]);
////		System.out.println("the first:" + r[0][1]);
////		System.out.println("the second:" + r[1][0]);
////
////		QuickSort quickSort = new QuickSort();
////		String[][] result_sorted = (String[][]) quickSort.output();
////		
//	}
	
//	public GoogleQuery() {
////		this.url = "http://www.google.com/search?q="+searchKeyword+ " +咖啡廳" +"&oe=utf8&num=20";
//		this.url = "https://www.beauty321.com/post/47144";
////		System.out.println(url);
//	}
	
	public GoogleQuery(String searchKeyword) {
		this.url = "http://www.google.com/search?q="+searchKeyword+ "+咖啡廳" +"&oe=utf8&num=20";
//		System.out.println(url);
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
		HashMap<String, WebTree> retValPro = new HashMap<String, WebTree>();
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
				title = li.select("a").get(0).select(".vvjwJb").text();
				citeUrl = li.select("a").get(0).attr("href");
				
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
	
	public WebTree getAllLink(WebNode root) throws IOException {		
		System.out.println(root.webPage.name);
		System.out.println(root.webPage.url);
		
		WebTree webTree = new WebTree(root);
		
		if(content == null) {
			content = fetchContent();
		}
		Document doc = Jsoup.parse(content);
		Elements aLinls = doc.select("a[href]");
		int i = 1;
		for(Element element: aLinls) {
			if(i >= 5) {break;}
			
			String url = element.attr("href");
			
			if(!url.contains("https://") && !url.contains("http://")) {
				continue;
			}
			
			if(url.contains("/url?q=")) {
				url = url.substring(7);
			}
			
			int temp = url.indexOf("&sa=");
			if(temp != -1) {
				url = url.substring(0, temp);
			}
			
			if(url.startsWith("\uFEFF")){ 
				url = url.substring(1); 
			}
			
			String url_de = URLDecoder.decode(url, "UTF-8");
			
			boolean n = false;
			for(String noice: keyNoiseLst) {
				if(url.contains(noice)) {
					System.out.println("True");
					n = true;
				}
			}
			if(n) {continue;}
//			
//			int index = url.indexOf("http");
//			if(index != -1) {
//				url = url.substring(index);
//				int temp = url.indexOf("&sa=");
//				if(temp != -1) {
//					url = url.substring(0, temp);
//				}
//			}
			
			System.out.println("child: " + url_de);
			
			String childName = "child" + Integer.toString(i);
			WebPage webPage = new WebPage(childName, url);
			root.addChild(new WebNode(webPage));
//			System.out.println("add" + Integer.toString(i));
			i++;
		}
		System.out.println("child num: " + Integer.toString(i));
		return webTree;
	}
	
}
