import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//remember Split in Java 7 just calls indexOf for this input.
public class ReadFile {
	private String path;
	
	public ReadFile(String file_path){
		path=file_path;
	}
	
	public String[] OpenFile() throws IOException{	
		FileReader file = new FileReader(path);
		BufferedReader textreader = new BufferedReader(file);
	    StringBuffer sb = new StringBuffer();
	    String line=null;
	    while((line = textreader.readLine())!= null)
	    {
	        sb.append(line);
	    }
	    String [] data = sb.toString().split("[\\p{Punct}\\s]+");
		file.close();
		return data;
		
	}
	public String [] stopWord() throws IOException{
		FileReader file = new FileReader(path);
		BufferedReader textreader = new BufferedReader(file);
	    StringBuffer sb = new StringBuffer();
	    String line=null;
	    while((line = textreader.readLine())!= null)
	    {
	        sb.append(line+"\n");
	    }
	    String [] data2 = sb.toString().split("\n");
		file.close();
		return data2;
	
	
}
//just for testing :p
	public static void main(String [] args) throws IOException{
		FileReader file = new FileReader("/home/user/Desktop/workspace/TopicPredictor/src/inputNews.txt");
		BufferedReader textreader = new BufferedReader(file);
		System.out.print(textreader.readLine());
		int numLines = 0;
		String aLine=null;
		while((aLine=textreader.readLine())!=null){
			numLines++;
		}
		
		
	
		String[] data = new String[numLines];
		
		for(int i=0;i<numLines;i++){

			data[i]=textreader.readLine();	
		
		}
		
		
		file.close();
	
	}
	
	public  int readLines() throws IOException{
		FileReader file = new FileReader(path);
		BufferedReader bf = new BufferedReader(file);
		int numLines = 0;
		String aLine=null;
		while((aLine=bf.readLine())!=null){
			numLines++;
		}
		bf.close();
		return numLines;
		
		
	}
	
	
	public HashMap<Integer,ArrayList<String>> getNews() throws IOException{
		File folder = new File("/home/user/Desktop/workspace/TopicPredictor/src/news/");
		File[] listOfFiles = folder.listFiles();
		HashMap<Integer,ArrayList<String>>  documents= new HashMap<Integer,ArrayList<String>>();

		for (int i = 0; i < listOfFiles.length; i++) {
		  File file = listOfFiles[i];
		  if (file.isFile() && file.getName().endsWith(".txt")) {
			    FileReader lefile= new FileReader(file);
				BufferedReader textreader = new BufferedReader(lefile);
			    StringBuffer sb = new StringBuffer();
			    String line=null;
			    while((line = textreader.readLine())!= null)
			    {
			        sb.append(line);
			    }
			    String [] data = sb.toString().split("[\\p{Punct}\\s]+");
				lefile.close();
		        ArrayList<String> ledata= new ArrayList<String>();
				  for(String line2:Arrays.asList(data))
			           ledata.add(line2);
				  
				  Integer num= i;
				  documents.put(num, ledata);
		  }

		}	
		return documents;
		
	}
	
	
	  static double cosine_similarity(Map<String, Double> v1, Map<String, Double> v2) {
          Set<String> both = new HashSet<String>(v1.keySet());
          both.retainAll(v2.keySet());
          double sclar = 0, norm1 = 0, norm2 = 0;
          for (String k : both) sclar += v1.get(k) * v2.get(k);
          for (String k : v1.keySet()) norm1 += v1.get(k) * v1.get(k);
          for (String k : v2.keySet()) norm2 += v2.get(k) * v2.get(k);
          return sclar / Math.sqrt(norm1 * norm2);
  }
	
	
	}
	


