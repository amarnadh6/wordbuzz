package searchEngine;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//This program reads every line in text file and store it in a list, so that it can be separated by a delimiter
public class ReadTextFiles {

	public static List<String> ReadTextFileByLines(String fileName)
	{
		List<String> linesList = new ArrayList<String>();
		BufferedReader br = null;
		try {
			String CurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((CurrentLine = br.readLine()) != null) 
			{
				linesList.add(CurrentLine);
			}
 
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
			  if (br != null)br.close();
			}
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
		return linesList;
	}
}
