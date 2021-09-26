package project2;

import java.net.URL;
import java.util.ArrayList; 

/**
 * This class represents a DataSet, which holds information for 
 * a date, title, description, and links for each row in the CSV file.
 * This class inherits all of its properties from Comparable<DataSet>.
 * 
 * @author Dan Lu
 *
 */

public class DataSet implements Comparable<DataSet>
{
	private Date date;
	private String title;
	private String description;
	private ArrayList<URL> links; 
	private String hatTips; 
	
	/**
	 * Constructs a new DataSet object with a title, description, and url. 
	 * @param title is the title of the DataSet
	 * @param description is the description of the DataSet
	 * @param links is a list of urls in the DataSet
	 * @throws IllegalArgumentException if any of the parameters are null or empty
	 */
	public DataSet(String title, String description, ArrayList<URL> links)
	{
		if (title == null || title.equals(""))
		{
			throw new IllegalArgumentException("Title is null or empty");
		}
		if (description == null || description.equals(""))
		{
			throw new IllegalArgumentException("Description is null or empty");
		}
		if (links == null || links.isEmpty())
		{
			throw new IllegalArgumentException("Link is null or empty");
		}
		this.title = title;
		this.description = description;
		this.links = links; 
	}
	
	/**
	 * Sets the date in the DataSet
	 * @param date is the date of the DataSet
	 * @throws IllegalArgumentException if the date is null or before 2000
	 */
	public void setDate(Date date)
	{
		if (date == null || date.getYear() < 2000)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			this.date = date;
		}
	}
	
	/**
	 * Returns the date of this DataSet object
	 * @return the date of this DataSet object
	 */
	public Date getDate()
	{
		if (date == null || date.getYear() < 2000)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			return date;
		}
	}
	
	/**
	 * Sets the hat tips of this Date object
	 * @param hatTips is the hat tips of the DataSet
	 * @throws IllegalArgumentException if the hat tips is null
	 */
	public void setHatTips(String hatTips)
	{
		if (hatTips == null)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			this.hatTips = hatTips;
		}
	}
	
	/**
	 * Returns the hat tips of this DataSet object
	 * @return the hat tips of this DataSet object
	 */
	public String getHatTips()
	{
		if (hatTips == null || hatTips.equals(""))
		{
			return "";
		}
		else
		{
			return hatTips;
		}
	}
	
	/**
	 * Returns the title of this DataSet object
	 * @return the title of this DataSet object
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Returns the description of this DataSet object
	 * @return the description of this DataSet object
	 */
	public String getDescription()
	{
		return description; 
	}
	
	/**
	 * Returns a list of the urls of this DataSet object
	 * @return a list of the urls of this DataSet object
	 */
	public ArrayList<URL> getURL()
	{
		return links;
	}

	/**
	 * Compares two data set objects by their date, this DataSet object and @param DataSet object
	 * If they have the same date or dates are missing, compare by their titles
	 * @return an integer to show whether this DataSet object is smaller or bigger than the parameter DataSet object
	 * A negative integer means this DataSet object's date is smaller (earlier) than the parameter DataSet object's date.
	 * A positive integer means this DataSet object's date is bigger (later) than the parameter DataSet object's date.
	 * If the dates are the same or missing, a negative integer means this DataSet object's title comes before the parameter DataSet object's title alphabetically
	 * If the dates are the same or missing, a positive integer means this DataSet object's title comes after the parameter DataSet object's title alphabetically
	 */
	public int compareTo(DataSet dataSet) 
	{
		if (date == null)
		{
			return title.compareToIgnoreCase(dataSet.title);
		}
		if (((DataSet)dataSet).date == null)
		{
			return title.compareToIgnoreCase(dataSet.title);
		}
		if (date.getYear() == 0 || date.getMonth() == 0 || date.getDay() == 0)
		{
			return title.compareToIgnoreCase(dataSet.title);
		}
		if (dataSet.date.getYear() == 0 || dataSet.date.getMonth() == 0 || dataSet.date.getDay() == 0)
		{
			return title.compareToIgnoreCase(dataSet.title);
		}
		if (date.getYear() > dataSet.date.getYear())
		{
			return 1;
		}
		if (date.getYear() < dataSet.date.getYear())
		{
			return -1; 
		}
		if (date.getYear() == dataSet.date.getYear())
		{
			if (date.getMonth() > dataSet.date.getMonth())
			{
				return 1;
			}
			if (date.getMonth() < dataSet.date.getMonth())
			{
				return -1;
			}
			if (date.getMonth() == dataSet.date.getMonth())
			{
				if (date.getDay() > dataSet.date.getDay())
				{
					return 1;
				}
				if (date.getDay() < dataSet.date.getDay())
				{
					return -1;
				}
				if (date.getDay() == dataSet.date.getDay())
				{
					return title.compareToIgnoreCase(dataSet.title);
				}
			}
		}
		return 0;
	}
	
	/**
	 * Check if some object dateSet is "equal to" this one. 
	 * DateSet objects are considered equal if they have the same dates and titles, case insensitive 
	 * @return true if this object is the same as the dateSet argument; false otherwise.
	 */
	public boolean equals(Object dataSet)
	{
		if (this == dataSet)
		{
			return true;
		}
		if (dataSet == null)
		{
			return false;
		}
		//if this dataSet object's date is null
		if (date == null)
		{
			return false; 
		}
		//if the Object dataSet is not of type DataSet
		if (!(dataSet instanceof DataSet))
		{
			return false;
		}
		//if the Object dataSet object's date is null 
		if (((DataSet)dataSet).date == null)
		{
			return false; 
		}
		//if the two dataSet objects do not have the same date
		if (!date.equals(((DataSet)dataSet).date))//using custom equals() method
		{
			return false;
		}
		//if the two dataSet objects do not have the same title, case insensitive
		if (title.compareToIgnoreCase(((DataSet)dataSet).title) != 0)
		{
			return false;
		}
		return true;//if the two dataSet objects have the same date and title, case insensitive 
	}
	
	/**
	 * Returns the string representation of this DateSet that contains 
	 * information about date, title, description, and links in separate lines. 
	 * If the date is null, do not return it in the string.
	 * @returns the string representation of this DateSet object.
	 */
	public String toString()
	{
		//convert from type url to type string 
		String string = "";
		for (int i = 0; i < links.size(); i++)
		{
			string += links.get(i) + "\n";
		}
		if (date == null)
		{
			return (title + "\n" + description + "\n" + string);
		}
		else//date not null
		{
			return (date +"\n" + title + "\n" + description + "\n" + string);
		}
	}
}
