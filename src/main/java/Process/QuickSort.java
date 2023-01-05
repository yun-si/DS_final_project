package Process;

import java.util.ArrayList;

public class QuickSort {

private ArrayList<WebNode> lst;

	public QuickSort() {
		lst=new ArrayList<WebNode>();
	}
	
	public void add(WebNode webNode) {
		lst.add(webNode);
	}
	
	public void sort() {
		if(lst.size() == 0)	{
			System.out.println("InvalidOperation");
		}
		else {
			quickSort(0, lst.size()-1);
		}
	}
	
	public void quickSort(int start, int end) {
		WebNode left=lst.get(start);
		WebNode right=lst.get(end);
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
			quickSort(start,ind-1);
			quickSort(ind+1,end);
		}
	}
	
	public void swap(int a, int b) {
		WebNode temp = lst.get(a);
		lst.set(a, lst.get(b));
		lst.set(b, temp);
	}
	
	
	public String[][] output() {
		int min = 2;
		int max = 9;
		int range = max-min+1;
		int random = (int)(Math.random()*range)+min;
		if(lst.size() != 0) {
			lst.add(random, new WebNode(new WebPage("Cafe Nomad - 全台網友們推薦的咖啡廳清單，適合工作、看書、喝杯咖啡", "/url?q=https://cafenomad.tw")));
		}
		int len = lst.size();
		
		String[][] matrix=new String[len][2];

		for(int i=0;i<len;i++) {
			matrix[i][0]=(String)((WebNode) lst.get(i)).getPageName().toString();
			matrix[i][1]=(String)((WebNode) lst.get(i)).url.toString();
		}
		return matrix;
	}
	
}
