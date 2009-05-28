

import java.io.IOException;
import java.util.*;
import java.util.regex.*;

public class Crawler 
{
	private LinkedList<String> urlsToVisit;
	private HashMap<String, String> urlsVisited;
	
	//get the authors
	private Pattern author; //matching the pattern author.
	private ArrayList<String> authors;
	
	//get the dates
	private Pattern date; //matching the pattern date.
	private ArrayList<String> dates;
	
	
	public Crawler()
	{
		urlsToVisit = new LinkedList<String>();
		urlsVisited = new HashMap<String, String>();
		
		authors = new ArrayList<String>();
		author = Pattern.compile("A: </b>([a-z]*)", Pattern.CASE_INSENSITIVE);

		dates = new ArrayList<String>();
		date = Pattern.compile("D: </b>([0-9]*/[0-9]*/[0-9]*)", Pattern.CASE_INSENSITIVE);

		
		//TODO:create the regex.
	}
	
	public void addURL(String urlPath)
	{
		urlsToVisit.add(urlPath);
		urlsVisited.put(urlPath, urlPath);
	}//endfunction
	
	public boolean queueEmpty()
	{
		return urlsToVisit.isEmpty();
	}//endfunction
	
	
	public void crawl()
	{
		String urlPath = (String) urlsToVisit.removeFirst();
		read(urlPath);
		
	}//endfunction
	
	
	private void read(String urlPath)
	{
		URLReader urlr;
		try {
			urlr = new URLReader(urlPath);
			String stuff = urlr.getContent();
	

			
			Matcher m = author.matcher(stuff);
			while (m.find()) 
			{
				String aut = m.group(1);
				//System.out.println(aut);
				authors.add(aut);	
			}//endwhile
			
			Matcher d = date.matcher(stuff);
			while (d.find()) 
			{
				String $date = d.group(1);
			//	System.out.println($date);
				dates.add($date);	
			}//endwhile
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}//endfunction
	
	public ArrayList<String> getAuthors() 
	{
		return authors;
	}
	
	public ArrayList<String> getDates() 
	{
		return dates;
	}
	
		
}
