import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.HashMap;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
	@@ -20,11 +20,15 @@ public class GoogleQuery {
	public String url;
	public String content;
	public String title;
	public String citeUrl;
	public WordCounter counter;

	public String[] keyNoiseLst = {"accounts.google.com", ".php", "www.youtube.com", "shopee.tw", "tiktok", "inline.app"};

//	public static void main(String[] args) throws IOException {
//		GoogleQuery g = new GoogleQuery("貓");
//		WebNode root = new WebNode(new WebPage("cat cafe", "https://www.walkerland.com.tw/subject/view/325139"));
	@@ -55,76 +59,82 @@ public class GoogleQuery {
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
		conn.setRequestProperty("http.agent", "Chrome/107.0.5304.107");
		conn.setRequestProperty("authorization","Chrome/107.0.5304.107");
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
