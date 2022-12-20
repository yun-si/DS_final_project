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
	
	
	
	public void outputIndex(int i){
		if(i >= lst.size()){
		    System.out.println("InvalidOperation");
		    return;
		}
		Keyword k = lst.get(i);	
		System.out.println(k);
	}
		
	public void outputCount(int c){
		LinkedList<Keyword> results = new LinkedList<>();
		for(int i = 0; i < lst.size(); i++){
		    Keyword k = lst.get(i);
		    if(k.getCount() == c){
		    	results.add(k);
		    }
		}
		if(results.isEmpty()){
		    System.out.println("NotFound");
		}else{
		    printKeywordList(results);
		}
	}
	
	public void outputHas(String pattern){
		LinkedList<Keyword> results = new LinkedList<>();
		for(int i = 0; i < lst.size(); i++){
		    Keyword k = lst.get(i);
		    if(k.getName().contains(pattern)){
		    	results.add(k);
		    }
		}
		if(results.isEmpty()){
		    System.out.println("NotFound");
		}else{
		    printKeywordList(results);
		}
	}
	
	public void outputName(String pattern){
		LinkedList<Keyword> results = new LinkedList<>();
		for(int i = 0; i < lst.size(); i++){
		    Keyword k = lst.get(i);
		    if(k.getName().equals(pattern)){
		    	results.add(k);
		    }
		}
		if(results.isEmpty()){
		    System.out.println("NotFound");
		}else{
		    printKeywordList(results);
		}
	}
	
	public void outputFirstN(int n){
		if(n > lst.size()){
		    System.out.println("InvalidOperation");
		    return;
		}
		LinkedList<Keyword> found= new LinkedList<>();
		
		for(int i = 0; i < n; i++){
			Keyword k = lst.get(i);
			found.add(k);
		}		
		printKeywordList(found);		
	}
	
	public void outputScore(){
		float results = 0;
		
		for(int i=0;  i < lst.size(); i++) {
			results+=lst.get(i).getCount()*lst.get(i).getWeight();
		}
		System.out.println(results);
	}
	
	public void deleteIndex(int i){		
		if(i >= lst.size()){
		    return;
		}			
		lst.remove(i);
	}

	public void deleteCount(int c){

		
		LinkedList<Keyword> found= new LinkedList<>();		
		for(int i=0;  i < lst.size(); i++) {
			if(lst.get(i).getCount()==c) {
				found.add(lst.get(i));
			}
		}
		if(!found.isEmpty()){
			lst.removeAll(found);			
		}				
	}

	public void deleteHas(String pattern){
		
		LinkedList<Keyword> results = new LinkedList<>();
		for(int i = 0; i < lst.size(); i++){
		    Keyword k = lst.get(i);
		    if(k.getName().contains(pattern)){
		    	results.add(k);
		    }
		}
		if(!results.isEmpty()){
			lst.removeAll(results);	
		}
	}
			
	
	
	public void deleteName(String name){
<<<<<<< HEAD
		
=======
>>>>>>> 944825f56f43d73f6b63902d0afb8513fd9e2cc8
		LinkedList<Keyword> results = new LinkedList<>();
		for(int i = 0; i < lst.size(); i++){
		    Keyword k = lst.get(i);
		    if(k.getName().contains(name)){
		    	results.add(k);
		    }
		}
		if(!results.isEmpty()){
			lst.removeAll(results);	
		}
	}
	
	public void deleteFirstN(int n){
		
		LinkedList<Keyword> results = new LinkedList<>();
		for(int i = 0; i < n; i++){
		   results.add(lst.get(i));
		}
		if(!results.isEmpty()){
			lst.removeAll(results);	
		}
		
	}
	
	public void deleteAll(){
		lst = new LinkedList<Keyword>();
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
