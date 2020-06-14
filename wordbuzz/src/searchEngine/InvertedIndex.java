package searchEngine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


@SuppressWarnings("unused")
public class InvertedIndex
{
	
static HashMap<String,HashMap<String,Integer>> WIndex;

public HashMap<String,HashMap<String,Integer>> CInvertedIndex(File[] files) throws IOException{
		int occ;
		if(WIndex==null) 
		{
		WIndex=new HashMap<String,HashMap<String,Integer>>();// fILE(WORD)-> WORD->COUNT
		for(File f: files) 
		{
		 BufferedReader br = null;
		 String CurrentLine;
		 br = new BufferedReader(new FileReader(f));
		 while((CurrentLine=br.readLine())!=null) {
		 String token[]=CurrentLine.split("\\W"); //splitting text lines into tokens by white space to get each word using the concept of  STRING TOKENIZER
		 for(String word:token) {
		 word=word.toLowerCase();
		 if(!WIndex.containsKey(word)) 
		 {
		 WIndex.put(word,new HashMap<String,Integer>());//If word is not already present then place the word in a new HashMap 
		 HashMap<String,Integer> doc_ct=WIndex.get(word);
		 occ=1;
		 doc_ct.put(f.getName(),occ);
		}
		else
		{
		HashMap<String,Integer> doc_ct=WIndex.get(word);
		if(!doc_ct.containsKey(f.getName())) // if word is present then get the file name and check for the word in the HashMap
		{ 
			occ=1; // if the word is not present in HashMap, then initialize the occurrence to 1 and create an entry
			doc_ct.put(f.getName(),occ);
		}
		else 
		{
			occ=doc_ct.get(f.getName())+1; // if word already present in the HashMap, increment the value by 1 each time
			doc_ct.replace(f.getName(),occ); // creating a HashMap with filename and occurrence of word in each file
		}
		}					
		}
		}
		br.close();
		}
		}
		return WIndex;
	}
	
	public static void main (String args[]) throws IOException {
		File[] files=new File("D:\\ACC_SearchEngine\\src\\webpages\\Text").listFiles();
		InvertedIndex in=new InvertedIndex();
		in.CInvertedIndex(files);
		System.out.println(WIndex.entrySet());
	}
	
}



