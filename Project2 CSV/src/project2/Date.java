package project2;

/**
 * This class represents the date for each row in the CSV file.
 * If the date exists, it is stored in a DataSet.
 * The date is in the format YYYY-MM-DD.
 * Months and days are that single digits will have a "0" in front of them.
 * Years are assumed to be 4 digits.
 * The date accounts for leap years.
 * 
 * @author Dan Lu
 *
 */

public class Date implements Comparable<Date>
{
	private int year;
	private int month;
	private int day;
	private boolean leap; 
	/**
	 * Constructs a new Date object with a year, month, and day. 
	 * @param year must be positive to be valid
	 * @param month must be in the range of 1-12 to be valid
	 * @param day must be in the range of 1-31, 1-30, 1-29, or 1-28 depending on the month and year
	 * @throws IllegalArgumentException if the year, month, or day is invalid
	 */
	public Date(int year, int month, int day)
	{
		if (year > 0)
		{
			this.year = year;
		}
		else
		{
			throw new IllegalArgumentException();
		}
		if (month >= 1 && month <= 12)
		{
			this.month = month;
		}
		else
		{
			throw new IllegalArgumentException();
		}
		
		//months with 31 days
		if (month == 1 || month == 3 || month == 5 || month == 7
			|| month == 8	|| month == 10 || month == 12)
		{
			if (day <= 31 && day >= 1)
			{
				this.day = day;
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
		
		//months with 30 days
		if (month == 4 || month == 6 || month == 9 || month == 11)
		{
			if (day <= 30 && day >= 1)
			{
				this.day = day; 
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
		
		//check for a leap year
		//if the year is divisible by 4
	    if (year % 4 == 0)
	    {
	      //if the year is a century
	      if (year % 100 == 0) 
	      {
	        //if the year is divided by 400, then it is a leap year
	        if (year % 400 == 0)
	        {
	        	leap = true;
	        }
	        else
	        {
	        	leap = false;
	        }
	      }
	      //if the year is not century
	      else
	        leap = true;
	    }
	    //if the year is not divisible by 4
	    else
	    {
	    	leap = false;
	    }
	    
		//if it is a leap year, February can have 29 days
	    //if it is not a leap year, February can have 28 days
		if (month == 2)
		{
			if (leap == true)
			{
				if (day <= 29 && day >= 1)
				{
					this.day = day;
				}
				else
				{
					throw new IllegalArgumentException();
				}
			}
			if (leap == false)
			{
				if (day <= 28 && day >= 1)
				{
					this.day = day;
				}
				else
				{
					throw new IllegalArgumentException();
				}
			}
		}
	}
	
	/**
	 * Returns the year of this Date object
	 * @return the year of this Date object
	 */
	public int getYear() 
	{
		return year;
	}

	/**
	 * Returns the month of this Date object
	 * @return the month of this Date object
	 */
	public int getMonth() 
	{
		return month;
	}

	/**
	 * Returns the date of this Date object
	 * @return the date of this Date object
	 */
	public int getDay() 
	{
		return day;
	}

	/**
	 * Compares two date objects by their year, month, and day (this Date object and @param Date object)
	 * @return an integer to show whether this Date object is smaller or bigger than the parameter Date object
	 * A negative integer means this Date object is smaller (earlier) than the parameter Date object.
	 * A positive integer means this Date object is bigger (later) than the parameter Date object.
	 * Zero means the two Date objects have the same year, month, and day
	 */
	public int compareTo(Date date) 
	{
		if (this.year > date.year)
		{
			return 1;
		}
		if (this.year < date.year)
		{
			return -1; 
		}
		if (this.year == date.year)
		{
			if (this.month > date.month)
			{
				return 1;
			}
			if (this.month < date.month)
			{
				return -1;
			}
			if (this.month == date.month)
			{
				if (this.day > date.day)
				{
					return 1;
				}
				if (this.day < date.day)
				{
					return -1;
				}
				if (this.day == date.day)
				{
					return 0;
				}
			}
		}
		return 0;
	}
	
	/**
	 * Check if some object date is "equal to" this one. 
	 * Date objects are considered equal if they have the same year, month, and day
	 * @return true if this object is the same as the date argument; false otherwise.
	 */
	public boolean equals(Object date)
	{
		if (this == date)
		{
			return true;
		}
		if (date == null)
		{
			return false;
		}
		//if Object date is not of type Date
		if (!(date instanceof Date))
		{
			return false; 
		}
		//if this date object has invalid fields
		if (day == 0 || month == 0 || year == 0)
		{
			return false; 
		}
		//if the Object date object has invalid fields
		if (((Date)date).day == 0 || ((Date)date).month == 0 || ((Date)date).year == 0)
		{
			return false; 
		}
		//if the fields of this date object and the Object date object are not the same
		if (day != ((Date)date).day || month != ((Date)date).month || year != ((Date)date).year)
		{
			return false;
		}
		return true; 
	}
	
	/**
	 * Returns the string representation of this Date in the form (YYYY-MM-DD)
	 * Assume all years have 4 digits.
	 * Months and days are that single digits will have a "0" in front of them.
	 * @returns the string representation of this Date object
	 */
	public String toString()//assume all years are 4 digits
	{
		if (day >= 10 && month >= 10)
		{
			return (year + "-" + month + "-" + day);
		}
		else if (day >= 10 && month < 10)
		{
			return (year + "-0" + month + "-" + day);
		}
		else if (day < 10 && month >= 10)
		{
			return (year + "-" + month + "-0" + day);
		}
		else //day<10 and month<10
		{
			return (year + "-0" + month + "-0" + day);
		}
	}
}
