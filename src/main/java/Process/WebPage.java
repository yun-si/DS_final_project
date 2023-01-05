package Process;

import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	public String url;
	public String name;
	public WordCounter counter;
	public double score;
	
	public WebPage(String name, String url){
		this.url = url;
		this.name = name;
		this.counter = new WordCounter(url, name);
		
	}
	
	public void setScore(KeywordList keywords) throws IOException{
		score = 0;
<<<<<<< HEAD
		for(int i =0; i < keywords.size(); i++) {
			score += keywords.get(i).getWeight()*counter.countKeyword(keywords.get(i).getName());
//			score+=counter.countKeyword(keywords.get(i).getName())*keywords.get(i).getWeight();
=======
		//1. calculate score
		for(int i =0;i<keywords.size();i++) {
			score += counter.countKeyword(keywords.get(i).getName())*keywords.get(i).getWeight();
>>>>>>> branch 'master' of https://github.com/yun-si/DS_final_project.git
		}
//		for(Keyword k : keywords){		
//			score += k.weight * counter.countKeyword(k.name);	
//		}
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public double getScore() {
		return this.score;
	}

}
