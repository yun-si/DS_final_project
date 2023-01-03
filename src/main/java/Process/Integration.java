package Process;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;

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
	public KeywordList key=new KeywordList();
	
    public Integration() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/htm");
		if(request.getParameter("inputKeyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		GoogleQuery google = new GoogleQuery(request.getParameter("inputKeyword"));
		HashMap<String, String> query = google.query();

//		for(int i =0;i<query.size();i++) {
//			String title=query.get(i);
//		}
		
		
		
		String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		for(Entry<String, String> entry : query.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    s[num][0] = key;
		    s[num][1] = value;
		    
		    
		    page.add(new WebPage(key,value));
		    
		    num++;
		}
		
		
		
	    for(int i =0;i<page.size();i++) {
	    	node.set(i,new WebNode((WebPage)page.get(i)));
	    }
	    
	    QuickSort sort=new QuickSort();
	    for(int j =0;j<node.size();j++) {
	    	sort.add((WebNode)node.get(j));
	    }
//	    
//	    for(int j=1;j<node.size();j++) {
//	    	node.get(0).addChild(node.get(j));
//	    	node.get(j).parent=node.get(0);
//	    }
//	    WebTree tree=new WebTree(page.get(0));
//		
////(keyword?)	    tree.setPostOrderScore(key);
//	    tree.eularPrintTree();
		
	    
		
		request.getRequestDispatcher("SearchResult.jsp").forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

