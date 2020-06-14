package searchEngine;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SearchFiles 
{
	static HashMap<String,HashMap<String,Integer>> WIndex;  /* WordIndex */
	
	public SearchFiles(String folderPath) throws IOException
	{
		if(WIndex == null)
		{
			WIndex = new HashMap<String, HashMap<String,Integer>>();
			File[] files = new File(folderPath+"\\Text\\").listFiles();//Storing all file names to file object
			System.out.println(files.length);//checking the no of files
			try {
				loadFilesContent(files); // calling 3rd method in this program RETURNS the filename and count
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public List<Entry<String, Integer>> search(String SearchContent,int NRecords) throws IOException {
		SearchContent = SearchContent.toLowerCase(); //converting the searched word to LowerCase to match with the words in the text files
		Map<String, Integer> FileContentCount = new HashMap<String, Integer>();
		if(WIndex.containsKey(SearchContent))// checking all the key Values(Word) in the first HashMap  to find the searched keyword
		{
			FileContentCount=WIndex.get(SearchContent); //store the filename with count 
		}
		System.out.println("fileContentCount: " + FileContentCount);

		List<Entry<String, Integer>> sortedFileContentCount = SortContentByValues(FileContentCount); // to sort the count values ->last method used in the program
		System.out.println("sortedFileContentCount: " + sortedFileContentCount);
		
		
		System.out.println("NRecords: " + NRecords);
		System.out.println("sortedFileContentCount.size(): " + sortedFileContentCount.size());
		if(NRecords > sortedFileContentCount.size()){ // Suppose if the user searches for top 5 files, the word is present in 3 files, so, we have to retrieve only 3 files so, top NRecords= Final Output file count  
			NRecords = sortedFileContentCount.size();
		}
		
		System.out.println("\n");
		
		List<Entry<String, Integer>> NSortedFileContentCount = sortedFileContentCount.subList(0, NRecords); // Just creating another list for only the top NRecords
		System.out.println("NSortedFileContentCount: " + NSortedFileContentCount);
			
		return NSortedFileContentCount;
		
	}
	public List<String> suggest(String SearchContent)
	{
		System.out.println("Method initialized");
		List<String> suggestedList=new ArrayList<String>(); //get the list from index.jsp
		for(String word:WIndex.keySet()) //search the keys of WordIndex HashMap (1st HashMap - Filenames->Word) for the words not found, which were entered by the user
		{ 
			int distance=EditDistance.editDistance(word, SearchContent); // find the not found word in all the entries
			if((distance<=5)&&(word.startsWith(SearchContent)))// words starting with same letter -> retrieve the words where distance<5 between entries
			{
				suggestedList.add(word); // add the words to the suggested list
				System.out.println(word);
			}
		}
		return suggestedList;
	}
	
	
	public static void loadFilesContent(File[] files) throws IOException{
	    InvertedIndex in=new InvertedIndex();
	    WIndex=in.CInvertedIndex(files);
	    
	}
	
	static <K,V extends Comparable<? super V>> List<Entry<K, V>> SortContentByValues(Map<K,V> map) {

		List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());//creating a new HashMap to store the sorted entries

		Collections.sort(sortedEntries, new Comparator<Entry<K,V>>() 
		{
	                @Override
	                public int compare(Entry<K,V> e1, Entry<K,V> e2) {
	                    return e2.getValue().compareTo(e1.getValue());
	                } // getting every entry of the filename(K)->word(V), comparing the values, return the greater entry value first
	            } // .sort method uses merge sort, which is more stable than quick sort
	    );

		return sortedEntries;
	}
}