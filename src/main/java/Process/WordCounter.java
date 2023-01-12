package Process;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;


public class WordCounter {
	private String urlStr;
    private String content;
    private String name;
    
    long start =System.currentTimeMillis();
    long end=start+20*1000;
    
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
//    	this.name=name;
    }
    
<<<<<<< HEAD
    private String fetchContent() throws IOException, FileNotFoundException{
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
//		conn.setRequestProperty("http.agent","Chrome/107.0.5304.107");
		conn.setRequestProperty("authorization","Chrome/107.0.5304.107");
		conn.setRequestProperty("Accept", "*/*");
//		conn.setRequestProperty("accept", "application/json");
		conn.setReadTimeout(20*1000);
		conn.setDoInput(true);
		conn.setDoOutput(false);
=======
//    public void run() throws InterruptedException {
//    	while(!Thread.interrupted()) {
//    		Thread.sleep(300);
//    	}
//    }
    
    private String fetchContent() throws IOException,FileNotFoundException {
>>>>>>> branch 'master' of https://github.com/yun-si/DS_final_project
		
<<<<<<< HEAD
		System.out.println("runnung...");
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));

=======
    	do {
	    	URL url = new URL(this.urlStr);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent","Chrome/107.0.5304.107");
			conn.setRequestProperty("Accept", "*/*");
			conn.setDoInput(true);
			conn.setDoOutput(false);
>>>>>>> branch 'master' of https://github.com/yun-si/DS_final_project
		
		
			InputStream in = conn.getInputStream();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
		
			
		
			String retVal = "";
		
			String line = null;
			
			while ((line = br.readLine()) != null){
			    retVal = retVal + line + "\n";
			}
				
    	
			return retVal;
    	}while(System.currentTimeMillis()<30*1000);
    }
		
    
//    private String fetchContent() throws IOException{
//		URL url = new URL(this.urlStr);
//		URLConnection conn = url.openConnection();
//		InputStream in = conn.getInputStream();
//		BufferedReader br = new BufferedReader(new InputStreamReader(in));
//	
//		String retVal = "";
//	
//		String line = null;
//		
//		while ((line = br.readLine()) != null){
//		    retVal = retVal + line + "\n";
//		}
//	
//		return retVal;
//    }
    
    public int BoyerMoore(String T, String P){
          int i = P.length() -1;
          int j = P.length() -1;
          
         while(i<=T.length()-1) {
        	 if(T.charAt(i)==P.charAt(j)) {
            	 if(j==0) {
            		return i;
            	 }else {
            		 i=i-1;
            		 j=j-1;
            	 }
             }else {
            	 int l=last(T.charAt(i),P);
            	 i=i+P.length()-min(j,1+l);
            	 j=P.length()-1;
             }
           
         }
         return -1;
    }

    public int last(char c, String P){
    	    if(P.indexOf(c)!=-1) {
    		int n=P.length()-1;
    		for(int i=n;i>=0;i--) {
    			if(P.charAt(i)==c) {
    				return i;
    			}
    			
    		}
    	}
        return -1;
    }

    public int min(int a, int b){
        if (a < b)
            return a;
        else if (b < a)
            return b;
        else 
            return a;
    }
    
    

    public int countKeyword(String keyword) throws IOException, FileNotFoundException{
    	if (content == null){
			
 			content = fetchContent();
 			System.out.println("success get content");
		}
		
 		content = content.toUpperCase();
 		keyword = keyword.toUpperCase();

 	    int retVal = 0; 

 		int n = content.length();
 		int m = keyword.length();
 		int i = BoyerMoore(content,keyword);
 		while(i != -1) {
 			retVal++;
 			content = content.substring(i+m,n-1);
 			n = content.length();
 			i = BoyerMoore(content,keyword);
 		}
 		
 		return retVal;
     }



}
