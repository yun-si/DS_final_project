package Process;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

public class WordCounter {
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException{
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	
		String retVal = "";
	
		String line = null;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line + "\n";
		}
	
		return retVal;
    }
    
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
    
   public int countKeyword(String keyword) throws IOException{
		if (content == null){
		    content = fetchContent();
		}
		
		
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
	    int retVal = 0; 

		int n=content.length();
		int m=keyword.length();
		int i=BoyerMoore(content,keyword);
		while(i!=-1) {
			retVal++;
			content=content.substring(i+m,n-1);
			n=content.length();
			i=BoyerMoore(content,keyword);
		}
		
		return retVal;
    }

}
