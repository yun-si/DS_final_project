package Process;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Integration
 */
@WebServlet("/Intergation")
public class Integration extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	public ArrayList<WebPage>page=new ArrayList<WebPage>();
	public ArrayList<WebNode>node=new ArrayList<WebNode>();
	public KeywordList key;
	
    public Integration() {
        super();
    }

//    public static void main(String[] arg) {
//    	KeywordList key=new KeywordList();
//    	try{
//			File input = new File("/pro_input.txt");
////	    	if(input.exists()!=true) {
//				key.add(new Keyword("咖啡廳",5.0));
//				key.add(new Keyword("咖啡",5.0));
//				key.add(new Keyword("coffee",5.0));
//				key.add(new Keyword("Cafe",5.0));
//				key.add(new Keyword("甜點",3.0));
//				key.add(new Keyword("下午茶",3.0));
//				key.add(new Keyword("文青",3.0));
//				key.add(new Keyword("讀書",3.0));
//				key.add(new Keyword("不限時",3.0));
//				key.add(new Keyword("星巴克",2.0));
//				key.add(new Keyword("路易莎",2.0));
//				key.add(new Keyword("伯朗",2.0));
//				for(int i =0;i<key.size();i++) {
//					System.out.print(key.get(i).getName());
//					System.out.print(key.get(i).getWeight());
//				}
//				return;
//				
//			}
//	    	
//			Scanner read = new Scanner(input);
//			while(read.hasNextLine()) {
//				String inputkey=read.next();
//				double value = (double)read.nextInt();
//				Keyword keyword= new Keyword(inputkey, value);
//				key.add(keyword);
//				System.out.print(keyword.getName());
//				System.out.println();
//			}
//			read.close();
//		}catch(FileNotFoundException e) {
////			System.out.println("pro_input.txt Not Found");
//			e.printStackTrace();
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//
//    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setCharacterEncoding("UTF-8");
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/htm");
//		if(request.getParameter("inputKeyword")== null) {
//			String requestUri = request.getRequestURI();
//			request.setAttribute("requestUri", requestUri);
//			request.getRequestDispatcher("Search.jsp").forward(request, response);
//			return;
//		}
//		GoogleQuery google = new GoogleQuery(request.getParameter("inputKeyword"));
////		HashMap<String, String> query = google.query();
//
////		HashMap<String, String> result = g.query();
//		HashMap<String, String> result = google.query();
//		QuickSort q = new QuickSort();
//		for (String key : result.keySet()) {
//			q.add(new WebNode(new WebPage(key, result.get(key))));
//		}
//
//		q.sort();
//		String[][] r = q.output();
//		
//		System.out.println(r.length);
//		request.setAttribute("query", r);
////		int num = 0;
////		for(Entry<String, String> entry : result.entrySet()) {
////		    String key = entry.getKey();
////		    String value = entry.getValue();
////		    s[num][0] = key;
////		    s[num][1] = value;
////		    
////		    
////		    page.add(new WebPage(key,value));
////		    
////		    num++;
////		}
////		
////		
////		
////	    for(int i =0;i<page.size();i++) {
////	    	node.set(i,new WebNode((WebPage)page.get(i)));
////	    }
////	    
////	    QuickSort sort=new QuickSort();
////	    for(int j =0;j<node.size();j++) {
////	    	sort.add((WebNode)node.get(j));
////	    }
////	    
////	    for(int j=1;j<node.size();j++) {
////	    	node.get(0).addChild(node.get(j));
////	    	node.get(j).parent=node.get(0);
////	    }
////	    WebTree tree=new WebTree(page.get(0));
////		
//////(keyword?)	    tree.setPostOrderScore(key);
////	    tree.eularPrintTree();
//		
//	
//		request.getRequestDispatcher("SearchResult.jsp").forward(request, response); 
//		
//	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("inputKeyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		GoogleQuery google = new GoogleQuery(request.getParameter("inputKeyword"));
		HashMap<String, String> query = google.query();
		
		
		key = new KeywordList();
		try{
			File input = new File("pro_input.txt");
			if(input.exists()!=true) {
				key.add(new Keyword("咖啡廳",5.0));
				key.add(new Keyword("咖啡",5.0));
				key.add(new Keyword("coffee",5.0));
				key.add(new Keyword("Cafe",5.0));
				key.add(new Keyword("甜點",3.0));
				key.add(new Keyword("下午茶",3.0));
				key.add(new Keyword("文青",3.0));
				key.add(new Keyword("讀書",3.0));
				key.add(new Keyword("不限時",3.0));
				key.add(new Keyword("星巴克",2.0));
				key.add(new Keyword("路易莎",2.0));
				key.add(new Keyword("伯朗",2.0));
				return;
				
			}
			Scanner read = new Scanner(input);
			while(read.hasNextLine()) {
				String inputkey=read.next();
				double value = (double)read.nextInt();
				Keyword keyword= new Keyword(inputkey, value);
				key.add(keyword);
			}
			read.close();
		}catch(FileNotFoundException e) {
			System.out.println("pro_input.txt Not Found");
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		QuickSort q = new QuickSort();
		for (String key : query.keySet()) {
			String url = query.get(key);
			int trash = url.indexOf("&sa");
			if(trash != -1) {
				url = url.substring(0, trash);
			}
			q.add(new WebNode(new WebPage(key, url)));
		}

		q.sort();
		String[][] s = q.output();
//		String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);
		System.out.println("the first:" + s[0][0]);
		System.out.println("the first:" + s[0][1]);
		System.out.println("the second:" + s[1][0]);
		System.out.println("the second:" + s[1][1]);
		
//		int num = 0;
//		for(Entry<String, String> entry : query.entrySet()) {
//		    String key = entry.getKey();
//		    String value = entry.getValue();
//		    s[num][0] = key;
//		    s[num][1] = value;
//		    num++;
//		}
		request.getRequestDispatcher("SearchResult.jsp").forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

