package Process;
import java.util.*;

public class KeywordList {
private LinkedList<Keyword> lst;
	
	public KeywordList(){
		this.lst = new LinkedList<Keyword>();	
	}
	
	public void add(Keyword keyword){
		
		for(int i = 0; i < lst.size(); i++){
			Keyword k = lst.get(i);	
			if(keyword.getCount() <= k.getCount()){ 
				if(keyword.getCount()< k.getCount()) {
					lst.add(i,keyword);
					return;
				}
				else if(keyword.getCount() == k.getCount() && keyword.getCount() <= k.getCount()) {
					lst.add(i,keyword);
					return;
				}	
			}	
		}
		lst.add(keyword);	
	}
	
	private void printKeywordList(LinkedList<Keyword> kLst){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < kLst.size(); i++){
			Keyword k = kLst.get(i);
			if(i > 0)sb.append(" ");
			sb.append(k.toString());
		}
		System.out.println(sb.toString());
	}

}
