package searchEngine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@SuppressWarnings("unused")
public class HtmlToText {
	
	public void PHtml_File(String folderPath) throws IOException {
		File myFile=new File(folderPath);
		File[] files=myFile.listFiles();
		for(File f:files) {
			if(!f.isDirectory()) {
			Document doc=Jsoup.parse(f,"UTF-8");
			String text=doc.text().toLowerCase();
			String regex = "[ ](?=[ ]-$%*!#)|[^_@ A-Za-z]+";
			text=text.replaceAll(regex, "");
			FileWriter fw=new FileWriter("D:\\ACC_SearchEngine\\src\\webpages\\Text\\"+f.getName()+".txt");
			fw.write(text);
			fw.close();
			
		}
		}
		
	}
	
	public static void main(String args[]) throws IOException {
		HtmlToText html=new HtmlToText();
		html.PHtml_File("D:\\ACC_SearchEngine\\src\\webpages");
	}
	
	
}
