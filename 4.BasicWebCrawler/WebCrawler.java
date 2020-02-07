import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Example program to list links from a URL.
 */
public class WebCrawler 
{
	public static void main(String args[])
	{
		int i;
		int sponsor_count = 0;
		List<Image_logo> logo_datas = new ArrayList<Image_logo>();
		Document document;
    	try {
    		//Get Document object after parsing the html from given url.
			document = Jsoup.connect("https://theinternship.io/").get();
 	
			//Get images from document object.
			Elements images = document.select("img.center-logos");
			Elements texts = document.select("span.list-company");
 
			//Iterate images and print image attributes.
			for (Element image : images)
			{
				sponsor_count++;
			}
			for(i=0;i<sponsor_count;i++)
			{
				Image_logo logo_data = new Image_logo(images.eq(i).attr("src").toString(), texts.eq(i).text().length());
				logo_datas.add(logo_data);
			}
			Collections.sort(logo_datas, new Custom_comparator());
			//System.out.println("All = " + logo_datas.get(18).description_length);
			for(i=sponsor_count-1;i>=0;i--)
			{
				System.out.println(logo_datas.get(i).image_url);
			}
			String json = "{ \"companies\" : [" + System.lineSeparator();
			for(i=sponsor_count-1;i>=0;i--)
			{
				json = json + "{ \"logo\": \"https://theinternship.io/" + logo_datas.get(i).image_url + "\"}";
				if(i > 0)
					json = json + ",";
				json = json + System.lineSeparator();
			}
			json = json + "] }";

			try (FileWriter file = new FileWriter("sponsor.json")) {
            	file.write(json);
            	file.flush();
        	} catch (IOException e) {
            	e.printStackTrace();
        	}
    	} catch (IOException e) {
			e.printStackTrace();
    	}
  	}
}

class Image_logo
{
	public String image_url;
	public int description_length;

	public Image_logo(String image_url,int description_length)
	{
		this.image_url = image_url;
		this.description_length = description_length;
	}
}

class Custom_comparator implements Comparator<Image_logo>
{
	@Override
    public int compare(Image_logo firstItem, Image_logo secondItem) 
    {
        return Integer.compare(secondItem.description_length, firstItem.description_length);
    }
}