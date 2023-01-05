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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
//	    	if(input.exists()!=true) {
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
//				
//				
//			}else {
//				Scanner read = new Scanner(input);
//				while(read.hasNextLine()) {
//					String inputkey=read.next();
//					double value = (double)read.nextInt();
//					Keyword keyword= new Keyword(inputkey, value);
//					key.add(keyword);
//					System.out.print(keyword.getName());
//					System.out.println();
//				}
//				read.close();
//			}
//
//		}catch(FileNotFoundException e) {
////			System.out.println("pro_input.txt Not Found");
//			e.printStackTrace();
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//
//    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnsupportedEncodingException {
    	response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("inputKeyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		
		
		
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
				
			}else {
				Scanner read = new Scanner(input);
				while(read.hasNextLine()) {
					String inputkey=read.next();
					double value = (double)read.nextInt();
					Keyword keyword= new Keyword(inputkey, value);
					key.add(keyword);
				}
				read.close();
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("pro_input.txt Not Found");
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
		String inputKeyword = request.getParameter("inputKeyword");
		Translator translator = new Translator();
		  inputKeyword = translator.translate("", "zh-TW", inputKeyword);
		//  try {
		//   inputKeyword = translator.translate("", "zh-TW", inputKeyword);
		//  } catch (IOException e) {
		//   System.out.println("IOException");
		//  } catch (Exception e) {
		//   System.out.println("");
		//  }
		  
		GoogleQuery google = new GoogleQuery(inputKeyword);
//		GoogleQuery google = new GoogleQuery(request.getParameter("inputKeyword"));
		HashMap<String, String> query = google.query();
		
		for(String title: query.keySet()) {
			String url = query.get(title);
			int trash = url.indexOf("&sa");
			if(trash != -1) {
				url = url.substring(0, trash);
			}
			url = url.substring(7);
			String url_de = URLDecoder.decode(url, "UTF-8");
//			WebPage webPage = new WebPage(title,url_de);
//			webPage.setScore(key);
			page.add(new WebPage(title,url_de));
//			page.add();
			
		}
		for(WebPage p:page) {
			try {
				p.setScore(key);
				node.add(new WebNode(p));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("node len");
		System.out.println(node.size());
		
		
		QuickSort q = new QuickSort();
		for (String key : query.keySet()) {
			String url = query.get(key);
			url = url.substring(7);
			int trash = url.indexOf("&sa");
			if(trash != -1) {
				url = url.substring(0, trash);
			}

		
//		for(WebNode nod:node) {
//			q.add(nod);
//		}
			//q.add(new WebNode(new WebPage(key, url)));
		}
		
		for(WebNode wnode:node) {
			q.add(wnode);
		}
		


		q.sort();
		
//		q.sort(key);

		String[][] s = q.output();

		System.out.println("s len");
		System.out.print(s.length);
		
//		String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);

		
//		System.out.println("the first:" + s[0][0]);
//		System.out.println("the first:" + s[0][1]);
//		System.out.println("the second:" + s[1][0]);
//		System.out.println("the second:" + s[1][1]);
		
		request.getRequestDispatcher("SearchResult.jsp").forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}