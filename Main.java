import java.io.PrintWriter;

import processing.core.*;

public class Main extends PApplet {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String[] urls = new String[100];

	
	Crawler crawler;
	
	PrintWriter output;
	
	
	public void setup() {
		size(200,200);
		crawler = new Crawler();
		
		 output = createWriter("positions.txt");
		
		// Initialize all "stripes"
		for (int i = 1; i < 98; i++) 
		{
			//stripes[i] = new Stripe(this);
			String url = "http://thefreshmilk.com/page/" + i;
			urls[i] = url;
			println(url);
			crawler.addURL(url);
			while (!crawler.queueEmpty()) 
			{
				  crawler.crawl();
			}//endwhile
			
		}//endfor
		
		
		//convert the arraylist into an array and then save to a file.
		String[] authors = new String[crawler.getAuthors().size()];
		String[] dates = new String[crawler.getDates().size()];
		
		for (int i=0; i < crawler.getAuthors().size(); i++)
		{
			// Write the coordinate to a file with a
		    // "\t" (TAB character) between each entry
		   
			dates[i] = (String)crawler.getDates().get(i);
			authors[i] = (String)crawler.getAuthors().get(i);
			output.println(dates[i] + "\t" + authors[i]);
			 
		}//endfor
		//saveStrings("authors.txt", authors);
		
		output.flush(); // Write the remaining data
		output.close(); // Finish the file
		exit(); // Stop the program
		  
	}//endsetup

	public void draw() {
	}
}
