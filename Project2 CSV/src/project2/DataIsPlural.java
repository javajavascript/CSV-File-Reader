package project2;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * This class stores the information from the CSV file into a DataSetList.
 * This class is user interactive.
 * The class allows the user to input keywords to search the DataSetList by title, description, and url.
 * The class allows users to combine two entries for a search (such as title and url), but not three.
 * The keywords are case insensitive.
 * The class will print a shortened DataSetList with DataSets that contain the matching keyword(s).
 * The printed DataSetList will contain information for a title, description, links, and a date (if one exists).
 * 
 * @author Dan Lu
 *
 */

public class DataIsPlural 
{
	/**
	 * The main() method of this program. 
	 * @param args is the array of Strings provided on the command line when the program starts. 
	 * The first string should be the name of the CSV file named DataIsPlural. 
	 * Lines 32 to 61 are credited to "Color project" by @author Joanna Klukowska
	 * "Color project" source: https://edstem.org/us/courses/3906/workspaces/pBSLx4PAZFAusHxD2yDEPMV9zXksyGgt 
	 */
	public static void main(String[] args) throws FileNotFoundException 
	{
		//check if args exists 
		if (args.length == 0) 
		{
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}

		//check if args contains a file name
		File file = new File(args[0]); 
		if (!file.exists())
		{
			System.err.println("Error: the file "+file.getAbsolutePath()+" does not exist.\n");
			System.exit(1);
		}
		if (!file.canRead())
		{
			System.err.println("Error: the file "+file.getAbsolutePath()+ " cannot be opened.\n");
			System.exit(1);
		}

		//check if file can be found and read
		try
		{
			Scanner scanner = new Scanner(file);
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("Error: the file "+file.getAbsolutePath()+ " cannot be opened for reading.\n");
			System.exit(1);
		}
		
		//read the entire file row by row
		DataSetList list = new DataSetList();		
		CSV csv = new CSV(new Scanner(file)); 
		for (int i = 0; i < csv.getNumOfRows(); i++) 
		{
			ArrayList<String> row = csv.getNextRow();
			//read the url
			String[] links = row.get(4).split("\n");
			ArrayList<URL> url = new ArrayList<URL>();				
			for (int k = 0; k < links.length; k++)
			{
				//convert from type string to type url
				try 
				{
					URL temp = new URL(links[k]);
					url.add(temp);
				}
				catch (MalformedURLException e)
				{
					//skip this and continue
				}
			}
			//add each row of the CSV file into a DataSet, then add those DataSets into a DataSetList
			try 
			{
				DataSet set = new DataSet(row.get(2), row.get(3), url);
				//checks if a date exists, if yes, add a date (YYYY-MM-DD) into the DataSet
				if (!row.get(0).equals(""))
				{
					//split the year, month, and day by a period character
					String[] nums = row.get(0).split("\\.");
					Date date = new Date(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), Integer.parseInt(nums[2]));
					set.setDate(date);
				}
				list.add(set);
			}
			catch (IllegalArgumentException e)
			{
				//skip this and continue
			}
		}
		
		//user interactive mode
			
		Scanner user = new Scanner(System.in);
		//input gets the next line from user input
		String input = "";
	
		System.out.println("Welcome to the Data Is Plural data explorer!");
		System.out.println("You can use the following queries to search through the data:");
		System.out.println("   title KEYWORD");
		System.out.println("   description KEYWORD");
		System.out.println("   url KEYWORD");
		System.out.println("You can combine up to two queries to narrow down the results, for example:");
		System.out.println("   title KEYWORD1 url KEYWORD2");
		System.out.println();
		
		while (!input.equalsIgnoreCase("quit"))
		{
			System.out.println("Enter query or \"quit\" to stop");
			//input gets the next line from user input
			input = user.nextLine();
			//the words of the user input
			String[] words = input.split(" ");
			if (input.equalsIgnoreCase("quit"))
			{
				System.exit(0);
			}
			//if the number of words is odd, then there is missing information and the query is not valid
			while(words.length %2 == 1 && !input.equalsIgnoreCase("quit"))
			{
				System.out.println("This is not a valid query. Try again.");
				input = user.nextLine();
			}
			//search by title only
			if (input.toLowerCase().contains("title".toLowerCase()) && !input.toLowerCase().contains("description".toLowerCase())
					&& !input.toLowerCase().contains("url".toLowerCase()))
			{
				DataSetList customList = list.getByTitle(words[1]);
				//check if the list exists before looping through it
				if (customList == null)
				{
					System.out.println("No matches found. Try again.");
				}
				else
				{
					for (int i = 0; i< customList.size(); i++)
					{
						//prints using custom toString() from DataSet
						System.out.println(customList.get(i));
						System.out.println("-----");
					}
				}
			}
			//search by description only
			else if (input.toLowerCase().contains("description".toLowerCase()) && !input.toLowerCase().contains("title".toLowerCase())
					&& !input.toLowerCase().contains("url".toLowerCase()))
			{
				DataSetList customList = list.getByDescription(words[1]);
				if (customList == null)
				{
					System.out.println("No matches found. Try again.");
				}
				for (int i = 0; i< customList.size(); i++)
				{
					//prints using custom toString() from DataSet
					System.out.println(customList.get(i));
					System.out.println("-----");
				}
			}
			//search by url only
			else if (input.toLowerCase().contains("url".toLowerCase()) && !input.toLowerCase().contains("title".toLowerCase())
					&& !input.toLowerCase().contains("description".toLowerCase()))
			{
				DataSetList customList = list.getByURL(words[1]);
				//check if the list exists before looping through it
				if (customList == null)
				{
					System.out.println("No matches found. Try again.");
				}
				for (int i = 0; i< customList.size(); i++)
				{
					//prints using custom toString() from DataSet
					System.out.println(customList.get(i));
					System.out.println("-----");
				}
			}
			//search by title and description 
			else if (input.toLowerCase().contains("title".toLowerCase()) && input.toLowerCase().contains("description".toLowerCase()) 
					&& !input.toLowerCase().contains("url".toLowerCase()))
			{
				if (words[0].equalsIgnoreCase("title") && words[2].equalsIgnoreCase("description"))//if title is first
				{
					DataSetList customList1 = list.getByTitle(words[1]);
					DataSetList customList2 = list.getByDescription(words[3]);
					//check if the lists exist before looping through them
					if (customList1 == null || customList2 == null)
					{
						System.out.println("No matches found. Try again.");
					}
					for (int i = 0; i< customList1.size(); i++)
					{
						//prints using custom toString() from DataSet
						System.out.println(customList1.get(i));
						System.out.println("-----");
					}
				}
				if (words[2].equalsIgnoreCase("title") && words[0].equalsIgnoreCase("description"))//if description is first
				{
					DataSetList customList1 = list.getByTitle(words[3]);
					DataSetList customList2 = list.getByDescription(words[1]);
					//check if the lists exist before looping through them
					if (customList1 == null || customList2 == null)
					{
						System.out.println("No matches found. Try again.");
					}
					for (int i = 0; i< customList1.size(); i++)
					{
						//prints using custom toString() from DataSet
						System.out.println(customList1.get(i));
						System.out.println("-----");
					}
				}
			}
			//search by title and url
			else if (input.toLowerCase().contains("title".toLowerCase()) && input.toLowerCase().contains("url".toLowerCase()) 
					&& !input.toLowerCase().contains("description".toLowerCase()))
			{
				if (words[0].equalsIgnoreCase("title") && words[2].equalsIgnoreCase("url"))//if title is first
				{
					DataSetList customList1 = list.getByTitle(words[1]);
					DataSetList customList2 = list.getByURL(words[3]);
					//check if the lists exist before looping through them
					if (customList1 == null || customList2 == null)
					{
						System.out.println("No matches found. Try again.");
					}
					for (int i = 0; i< customList1.size(); i++)
					{
						//prints using custom toString() from DataSet
						System.out.println(customList1.get(i));
						System.out.println("-----");
					}
				}
				if (words[2].equalsIgnoreCase("title") && words[0].equalsIgnoreCase("url"))//if description is first
				{
					DataSetList customList1 = list.getByTitle(words[3]);
					DataSetList customList2 = list.getByURL(words[1]);
					//check if the lists exist before looping through them
					if (customList1 == null || customList2 == null)
					{
						System.out.println("No matches found. Try again.");
					}
					for (int i = 0; i< customList1.size(); i++)
					{
						//prints using custom toString() from DataSet
						System.out.println(customList1.get(i));
						System.out.println("-----");
					}
				}
			}
			//search by description and url
			else if (input.toLowerCase().contains("description".toLowerCase()) && input.toLowerCase().contains("url".toLowerCase()) 
					&& !input.toLowerCase().contains("title".toLowerCase()))
			{
				if (words[0].equalsIgnoreCase("description") && words[2].equalsIgnoreCase("url"))//if title is first
				{
					DataSetList customList1 = list.getByDescription(words[1]);
					DataSetList customList2 = list.getByURL(words[3]);
					//check if the lists exist before looping through them
					if (customList1 == null || customList2 == null)
					{
						System.out.println("No matches found. Try again.");
					}
					for (int i = 0; i< customList1.size(); i++)
					{
						//prints using custom toString() from DataSet
						System.out.println(customList1.get(i));
						System.out.println("-----");
					}
				}
				if (words[2].equalsIgnoreCase("description") && words[0].equalsIgnoreCase("url"))//if description is first
				{
					DataSetList customList1 = list.getByDescription(words[3]);
					DataSetList customList2 = list.getByURL(words[1]);
					//check if the lists exist before looping through them
					if (customList1 == null || customList2 == null)
					{
						System.out.println("No matches found. Try again.");
					}
					for (int i = 0; i< customList1.size(); i++)
					{
						//prints using custom toString() from DataSet
						System.out.println(customList1.get(i));
						System.out.println("-----");
					}
				}
			}
			else
			{
				System.out.println("This is not a valid query. Try again.");
			}
		}
	}
}