package Process;

import java.io.IOException;
import java.util.ArrayList;

public class QuickSort {

private ArrayList<WebNode> lst;

	public QuickSort() {
		lst = new ArrayList<WebNode>();
	}
	
	public void add(WebNode webNode) {
		lst.add(webNode);
	}
	
	public void sort(KeywordList keywords) throws IOException {
		if(lst.size() == 0)	{
			System.out.println("InvalidOperation");
		}
		else {
			quickSort(0, lst.size()-1, keywords);
		}
	}
	
	public void quickSort(int start, int end, KeywordList keywords) throws IOException {
		WebNode left = lst.get(start);
		WebNode right = lst.get(end);
		left.setNodeScore(keywords);
		right.setNodeScore(keywords);
		
		if(left.nodeScore < right.nodeScore) {
			int ind=start;
			WebNode pivot=lst.get(end);
			int index =start;
			for(int i =start;i<end;i++) { 
				double a =lst.get(i).nodeScore; 
				double p =pivot.nodeScore;
				if(a<p){ 
					swap(i,index); 
					index++;
				}
			}
			swap(index,end);
			ind=index;
			quickSort(start,ind-1, keywords);
			quickSort(ind+1,end, keywords);
		}
	}
	
	public void swap(int a, int b) {
		WebNode temp = lst.get(a);
		lst.set(a, lst.get(b));
		lst.set(b, temp);
	}
	
	
	public String[][] output() {		
		ArrayList<WebNode> temp = new ArrayList<>();
		ArrayList<Integer> t = new ArrayList<>();
		int ind = 0;
		for(WebNode webNode: lst) {
			System.out.println("name: " + webNode.webPage.name);
			if(webNode.webPage.name.indexOf("咖啡廳") != -1 || webNode.webPage.name.indexOf("cafe") != -1) {
				System.out.println("find");
				t.add(ind);
				temp.add(webNode);
				ind++;
			}
		}
		
		for(int j = t.size()-1; j > -1; j--) {
			lst.remove(j);
		}
		for(WebNode webNode: temp) {
			System.out.println("add");
			lst.add(0, webNode);
		}
		
		int min = 2;
		int max = 7;
		int range = max-min+1;
		int random = (int)(Math.random()*range)+min;
		if(lst.size() != 0) {
			lst.add(random, new WebNode(new WebPage("Cafe Nomad - 全台網友們推薦的咖啡廳清單，適合工作、看書、喝杯咖啡", "https://cafenomad.tw")));
		}
		
		int len = lst.size();
		String[][] matrix=new String[len][2];

		for(int i=0; i<len; i++) {
			matrix[i][0]=(String)((WebNode) lst.get(i)).getPageName().toString();
			matrix[i][1]=(String)((WebNode) lst.get(i)).url.toString();
		}
		return matrix;
	}
	
}
