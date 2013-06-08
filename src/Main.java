import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;


public class Main {

	
	public static void main(String []args) throws IOException{
		 String path="/home/user/Desktop/workspace/NLPResearch/src/news/inputNews.txt";
		 String stopwords="/home/user/Desktop/workspace/NLPResearch/src/stopwords/stopword.txt";
		 
	try{
		ReadFile file = new ReadFile(path);
		//get news
		HashMap<Integer,ArrayList<String>> news=file.getNews("/home/user/Desktop/workspace/NLPResearch/src/news/");
		// System.out.println(news.get(0));
	        
		ReadFile stop= new ReadFile(stopwords);		
		String[] output= file.OpenFile();
		String[] stopList=stop.stopWord();
	    //create stopWords set
		Set<String> stopWords = new LinkedHashSet<String>();
		    for(String line:Arrays.asList(stopList))
		           stopWords.add(line); 
		  //  System.out.print(Arrays.asList(stopList));
	       	        
		  HashMap<String,Integer> frequencies=new HashMap<String,Integer>();
		  //experimenting with oder
		  /*
		  ValueComparator bvc = new ValueComparator(frequencies2);
		  TreeMap<String,Integer> frequencies=new TreeMap<String,Integer>(bvc);
		  */
		  ArrayList<String>lewords=new ArrayList<String>();
			Porter porter = new Porter();
			for(int i=0;i<output.length;i++){
		
				lewords.add(output[i]);
			}
			ArrayList<String> list= new ArrayList<String>();
			for(String stemmed : lewords){
				if ((stemmed.indexOf("?") == -1 && stemmed.length() > 2 && !stopwords.contains(stemmed)))
					stemmed = porter.stripAffixes(stemmed);

				if (stemmed.length() > 2)
					list.add(stemmed);
			}
			//	String base="";
				//base=porter.stripAffixes(stemmed);
				//list.add(base);
				//String stemmed=porter.stripAffixes(stemmed);
			//}
			
		//	System.out.print(list);
		
			/*String are="are";
			//are.toLowerCase();
			if(stopWords.contains("are")){
				System.out.print("yes");
			}*/
			
					//System.out.print(output[i].split(" "));
					for (String word : list) {
				
						if(stopWords.contains(word.toLowerCase())||stopWords.contains(word.toUpperCase())){
							continue;
						}else{
							  Integer j=frequencies.get(word);
							  if(j!=null)
							  frequencies.put(word,j+1);
							  else
								  frequencies.put(word, 1);
							
						}
					
						}
				
			
				 System.out.println(frequencies);
	


		
	}catch ( IOException e ) {
		System.out.println( e.getMessage() );
	}
	
	}
}



