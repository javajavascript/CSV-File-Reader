package project2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a DataSetList, which is a list of DataSet objects.
 * This class has the methods to return custom DataSetLists that contain the DataSets with a 
 * matching title, description, or url keywords.
 * The keywords are case insensitive.
 * This class inherits all of its properties from an ArrayList<DataSet>.
 * The class sorts the DataSets using the compareTo method from the DataSet class which
 * sorts DataSets by date, and if the dates are the same, then they are sorted by titles.
 * 
 * @author Dan Lu
 *
 */

public class DataSetList extends ArrayList<DataSet>
{
	/**
	 * Constructs a new DataSetList which has properties of an array list 
	 * The DataSetList is made up of DataSets.
	 */
	public DataSetList()
	{
		super();
	}
	
	/**
	 * Searches through all DataSets and returns the DataSetList that has a matching title keyword
	 * The keyword is case insensitive.
	 * @param keyword is the title keyword that the user inputs
	 * @return the DataSetList that has a matching title keyword
	 * @throws IllegalArgumentException is the keyword is null or blank
	 */
	public DataSetList getByTitle(String keyword)
	{
		//counter is used to check if there are matches found
		int counter = 0;
		if (keyword == null || keyword.equals(""))
		{
			throw new IllegalArgumentException("Invalid, input is null or blank");
		}
		DataSetList list = new DataSetList();
		for (DataSet set: this)
		{
			if (set.getTitle().toLowerCase().contains(keyword.toLowerCase()))
			{
				list.add(set);
				counter++;
			}
		}
		//sorts the list using compareTo() from DataSet
		Collections.sort(list);
		if (counter != 0)
		{
			return list;
		}
		//counter is 0 means no matches
		else
		{
			return null;
		}
	}
	
	/**
	 * Searches through all DataSets and returns DataSetList that has a matching description keyword
	 * The keyword is case insensitive.
	 * @param keyword is the description keyword that the user inputs
	 * @return the DataSetList that has a matching description keyword
	 * @throws IllegalArgumentException is the keyword is null or blank
	 */
	public DataSetList getByDescription(String keyword)
	{
		//counter is used to check if there are matches found
		int counter = 0;
		if (keyword == null || keyword.equals(""))
		{
			throw new IllegalArgumentException("Invalid, input is null or blank");
		}
		DataSetList list = new DataSetList();
		for (DataSet set: this)
		{
			if (set.getDescription().toLowerCase().contains(keyword.toLowerCase()))
			{
				list.add(set);
				counter++;
			}
		}
		//sorts the list using compareTo() from DataSet
		Collections.sort(list);
		if (counter != 0)
		{
			return list;
		}
		//counter = 0 meaning no matches
		else 
		{
			return null;
		}
	}
	
	/**
	 * Searches through all DataSets and returns the DataSetList that has a matching url keyword
	 * The keyword is case insensitive.
	 * @param keyword is the url keyword that the user inputs
	 * @return the DataSetList that has a matching url keyword
	 * @throws IllegalArgumentException is the keyword is null or blank
	 */
	public DataSetList getByURL(String keyword)
	{
		//counter is used to check if there are matches found
		int counter = 0;
		if (keyword == null || keyword.equals(""))
		{
			throw new IllegalArgumentException("Invalid, input is null or blank");
		}
		DataSetList list = new DataSetList();
		for (DataSet set: this)
		{
			//uses toString() from the URL class
			String link = set.getURL().toString();
			if (link.toLowerCase().contains(keyword.toLowerCase()))
			{
				list.add(set);
				counter++;
			}
		}
		//sorts the list using compareTo() from DataSet
		Collections.sort(list);
		if (counter != 0)
		{
			return list;
		}
		//counter = 0 meaning no matches
		else 
		{
			return null;
		}
	}
}
