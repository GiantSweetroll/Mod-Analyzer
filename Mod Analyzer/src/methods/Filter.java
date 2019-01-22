package methods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import constants.Comparators;
import constants.Constants;
import dataDrivers.Mod;
import giantsweetroll.date.Date;
import giantsweetroll.files.FileManager;
import giantsweetroll.xml.dom.XMLManager;

public class Filter 
{
	public static final int ASCENDING_ORDER = 0,
							DESCENDING_ORDER = 1;
	
	public static final void modsByAuthor(Collection<Mod> mods, String keyword)
	{
		Iterator<Mod> itr = mods.iterator();
		while(itr.hasNext())
		{
			Mod mod = itr.next();
			String author = mod.getAuthor();
			if(!Filter.contains(author, keyword))
			{
				itr.remove();
			}
		}
	}
	
	public static final void  modsByName(Collection<Mod> mods, String keyword)
	{
		Iterator<Mod> itr = mods.iterator();
		while(itr.hasNext())
		{
			Mod mod = itr.next();
			String name = mod.getName();
			if(!Filter.contains(name, keyword))
			{
				itr.remove();
			}
		}
	}
	
	public static final void modsByID(Collection<Mod> mods, String keyword)
	{
		Iterator<Mod> itr = mods.iterator();
		while(itr.hasNext())
		{
			Mod mod = itr.next();
			String id = mod.getID();
			if(!Filter.contains(id, keyword))
			{
				itr.remove();
			}
		}
	}
	
	private static final boolean contains(String source, String keyword)
	{
		if (source.length() == keyword.length())
		{
			return source.equalsIgnoreCase(keyword);
		}
		else if (source.length() > keyword.length())
		{
			int leap = keyword.length();
			for (int i=0; i<source.length()-leap; i++)
			{
				if(source.substring(i, i+leap).equalsIgnoreCase(keyword))
				{
					return true;
				}
			}
			
			return false;
		}
		else
		{
			return false;
		}
	}
	
	public static final Set<Mod> modsByDateRegistered(Date from, Date to, int order)
	{
		Set<Mod> list = new LinkedHashSet<Mod>();
		try
		{
			//Filtering Start
			String modDatabasePath = Constants.DATABASE_FOLDER + File.separator;
			//Get year range
			List<String> legibleYears = new ArrayList<String>();
			FileManager.getListOfFiles(legibleYears, 
										modDatabasePath,
										false, FileManager.FOLDER_ONLY, 
										FileManager.NAME_ONLY);
//			MessageManager.printLine("Size of years: " + legibleYears.size());
			for (int i=0; i<legibleYears.size(); i++)
			{
				try
				{
					int yearNow = Integer.parseInt(legibleYears.get(i));
					int yearMin = from.getYear();
					int yearMax = to.getYear();
					if (yearNow < yearMin || yearNow > yearMax)
					{
						legibleYears.remove(i);		//Remove Illegible year
						i = -1;			//Reset index to loop from beginning of array (it's -1 because at the end of loop will be added by 1 = 0)
					}
				}
				catch (NumberFormatException ex)
				{
					legibleYears.remove(i);		//Remove Illegible year
					i = -1;			//Reset index to loop from beginning of array (it's -1 because at the end of loop will be added by 1 = 0)
				}
			}
			
			//Add leading zeroes to String, then sort
			Methods.addZeroesToList(legibleYears);
			Collections.sort(legibleYears);
			if (order == Filter.DESCENDING_ORDER)
			{
				Collections.reverse(legibleYears);
			}
	//		MessageManager.printLine("Size of elligible years: " + legibleYears.size());
			
			//Get month range
			LinkedHashMap<String, List<String>> legibleMonthsMap = new LinkedHashMap<String, List<String>>();
	//		MessageManager.printLine("Size of eligible months before: " + legibleMonthsMap.size());
			if (legibleYears.size() == 1)
			{
				ArrayList<String> legibleMonths = new ArrayList<String>();
				FileManager.getListOfFiles(legibleMonths, modDatabasePath + legibleYears.get(0), false, FileManager.FOLDER_ONLY, FileManager.NAME_ONLY);
		//		MessageManager.printLine("Number of months: " + legibleMonths.size());
				
				if (legibleYears.get(0).equals(Integer.toString(from.getYear())))			//If the first legible year is the same as the min year
				{
					for (int i=0; i<legibleMonths.size(); i++)
					{
				//		MessageManager.printLine("Iteration month: " + i);
						try
						{
							int monthNow = Integer.parseInt(legibleMonths.get(i));
							int monthMin = from.getMonth();
							int monthMax = to.getMonth();
							if (legibleYears.get(0).equals(Integer.toString(to.getYear())))		//If the only legible year is the same as the max year
							{
								if (monthNow < monthMin || monthNow > monthMax)
								{
						//			MessageManager.printLine("Month " + legibleMonths.get(i) + " is not within range of " + monthMin + " and " + monthMax);
									legibleMonths.remove(i);	//remove Illegible month
									i = -1;		//Reset index to loop from beginning of array (it's -1 because at the end of loop will be added by 1 = 0)
								}
							}
							else
							{
								if (monthNow < monthMin)
								{
				//					MessageManager.printLine("Month " + legibleMonths.get(i) + " is less than " + monthMin);
									legibleMonths.remove(i);	//remove Illegible month
									i = -1;		//Reset index to loop from beginning of array (it's -1 because at the end of loop will be added by 1 = 0)
								}
							}
						}
						catch (NumberFormatException ex)
						{
							legibleMonths.remove(i);	//remove Illegible month
							i = -1;		//Reset index to loop from beginning of array (it's -1 because at the end of loop will be added by 1 = 0)
						}
					}
				}
				//If not, accept all months
				
//				MessageManager.printLine("Number of months after filter: " + legibleMonths.size() + " (" + legibleYears.get(0) + ")");
				legibleMonthsMap.put(legibleYears.get(0), legibleMonths);
			}
			else
			{
	//			System.out.println(legibleYears.size());
				for (int i=0; i<legibleYears.size(); i++)
				{
					List<String> legibleMonths = new ArrayList<String>();
					FileManager.getListOfFiles(legibleMonths, modDatabasePath + legibleYears.get(i), false, FileManager.FOLDER_ONLY, FileManager.NAME_ONLY);
					/*
					 * Program only needs to check the first year for the min month,
					 * and the last year for the max month, 
					 * as all months in the year between will be selected regardless
					 */
					if (i==0)
					{
						for (int a=0; a<legibleMonths.size(); a++)
						{
							try
							{
								int monthNow = Integer.parseInt(legibleMonths.get(a));
								int monthMin = from.getMonth();
								if (monthNow < monthMin)
								{
									legibleMonths.remove(a);	//remove Illegible month
									a = -1;		//Reset index to loop from beginning of array (it's -1 because at the end of loop will be added by 1 = 0)
								}
							}
							catch (NumberFormatException ex)
							{
								legibleMonths.remove(a);	//remove Illegible month
								a = -1;		//Reset index to loop from beginning of array (it's -1 because at the end of loop will be added by 1 = 0)
							}
						}
					}
					else if (i==legibleYears.size()-1)
					{
						for (int a=0; a<legibleMonths.size(); a++)
						{
							try
							{
								int monthNow = Integer.parseInt(legibleMonths.get(a));
								int monthMax = to.getMonth();
								if (monthNow > monthMax)
								{
									legibleMonths.remove(a);	//remove Illegible month
									a = -1;		//Reset index to loop from beginning of array (it's -1 because at the end of loop will be added by 1 = 0)
								}
							}
							catch (NumberFormatException ex)
							{
								legibleMonths.remove(a);	//remove Illegible month
								a = -1;		//Reset index to loop from beginning of array (it's -1 because at the end of loop will be added by 1 = 0)
							}
						}
					}
					
					//Add leading zeroes to String, then sort
					Methods.addZeroesToList(legibleMonths);
					Collections.sort(legibleMonths);
					if (order == Filter.DESCENDING_ORDER)
					{
						Collections.reverse(legibleMonths);
					}
					
					legibleMonthsMap.put(legibleYears.get(i), legibleMonths);
				}
			}
	//		MessageManager.printLine("Size of eligible months after: " + legibleMonthsMap.size());
			
			//Get day range
//			LinkedHashMap<LinkedHashMap<String, String>, List<String>> legibleDaysMap = new LinkedHashMap<LinkedHashMap<String, String>, List<String>>();
			LinkedHashMap<String, LinkedHashMap<String, List<String>>> legibleDaysMap = new LinkedHashMap<String, LinkedHashMap<String, List<String>>>();
			/*
			 * Program only needs to check the first month of the first year to get the min day,
			 * and the last month of the last year to get the max day,
			 * as all other days in the months between will be selected regardless
			 */
			
			for (Map.Entry<String, List<String>> entry : legibleMonthsMap.entrySet())
			{	
				LinkedHashMap<String, List<String>> monthDayMap = new LinkedHashMap<String, List<String>>();
				for (int i=0; i<entry.getValue().size(); i++)
				{
					List<String> legibleDays = new ArrayList<String>();
					FileManager.getListOfFiles(legibleDays, modDatabasePath + entry.getKey() + File.separator + entry.getValue().get(i), false, FileManager.FOLDER_ONLY, FileManager.NAME_ONLY);
		//			MessageManager.printLine("Amount of legible days from month " + entry.getValue().get(i) + "before filter: " + legibleDays.size());
					
					if (entry.getKey().equals(Integer.toString(from.getYear())))		//if the first eligible year is equal to the min year
					{
						if (entry.getValue().get(i).equals(Integer.toString(from.getMonth())))		//If the month is equal to the min month
						{
							int dayMin = from.getDay();
							for (int a=0; a<legibleDays.size(); a++)
							{
								try
								{
									int dayNow = Integer.parseInt(legibleDays.get(a));
									if (dayNow < dayMin)		//If day is less than the min day
									{
										legibleDays.remove(a);
										a = -1;
									}
								}
								catch (NumberFormatException ex)
								{
									legibleDays.remove(a);
									a = -1;
								}
							}
						}
					}
					if (entry.getKey().equals(Integer.toString(to.getYear())))	//if the last eligible year is equal to the max year
					{
						if (entry.getValue().get(i).equals(Integer.toString(to.getMonth())))	//if the month is equal to the max month
						{
							int dayMax = to.getDay();
							for (int a=0; a<legibleDays.size(); a++)
							{
								try
								{
									int dayNow = Integer.parseInt(legibleDays.get(a));
									if (dayNow > dayMax)		//If day is greater than the max day
									{
										legibleDays.remove(a);
										a = -1;
									}
								}
								catch (NumberFormatException ex)
								{
									legibleDays.remove(a);
									a = -1;
								}
							}
						}
					}
					
					//Add leading zeroes to String, then sort
					Methods.addZeroesToList(legibleDays);
					Collections.sort(legibleDays);
					if (order == Filter.DESCENDING_ORDER)
					{
						Collections.reverse(legibleDays);
					}
					
					monthDayMap.put(entry.getValue().get(i), legibleDays);
				}
				legibleDaysMap.put(entry.getKey(), monthDayMap);
			}
			//Filtering end
			
			//Gather list of files from each folder
			List<String> filePaths = new ArrayList<String>();
			for (Map.Entry<String, LinkedHashMap<String, List<String>>> entryYear : legibleDaysMap.entrySet())
			{
				//Remove leading zeroes
				String year = entryYear.getKey();
				if (year.substring(0, 1).equals("0"))
				{
					year = year.substring(1);
				}
				
				for (Map.Entry<String, List<String>> entryMonth : entryYear.getValue().entrySet())
				{	
					//Remove leading zeroes
					String month = entryMonth.getKey();
					if (month.substring(0, 1).equals("0"))
					{
						month = month.substring(1);
					}
					
					for (int i=0; i<entryMonth.getValue().size(); i++)
					{
						//Remove leading zeroes
						String day = entryMonth.getValue().get(i);
						if (day.substring(0, 1).equals("0"))
						{
							day = day.substring(1);
						}
						
						List<String> fileList = new ArrayList<String>();
//						FileManager.getListOfFiles(fileList, userDatabasePath + entryYear.getKey() + File.separator + entryMonth.getKey() + File.separator + entryMonth.getValue().get(i), false, FileManager.FILE_ONLY, FileManager.ABSOLUTE_PATH);
						FileManager.getListOfFiles(fileList, modDatabasePath + year + File.separator + month + File.separator + day, false, FileManager.FILE_ONLY, FileManager.ABSOLUTE_PATH);
						for (int a=0; a<fileList.size(); a++)
						{
							filePaths.add(fileList.get(a));
						}
					}
				}
			}
			
			//Parse into Mod
			for (int i=0; i<filePaths.size(); i++)
			{
				try
				{
					list.add(new Mod(XMLManager.createDocument(filePaths.get(i), false)));
				}
				catch (ParserConfigurationException | SAXException | IOException ex)
				{
					ex.printStackTrace();
				}
			}
			
	//		Collections.sort(list, new SortByDate());
			
//			MessageManager.printLine("Size of entries: " + list.size());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return list;		
	}
	
	public static final void modsByDateRegistered(Collection<Mod> mods, Date from, Date to, int order)
	{
		Iterator<Mod> iterator = mods.iterator();
		while(iterator.hasNext())
		{
			Date date = iterator.next().getDateCreated();
			if (date.isEarlierThan(from) || date.isLaterThan(to))
			{
				iterator.remove();
			}
		}
		
		//Sort by date
		List<Mod> list = new ArrayList<Mod>(mods);
		if (order == Filter.ASCENDING_ORDER)
		{
			Collections.sort(list, Comparators.DATE_REGISTERED_ASCENDING);
		}
		else
		{
			Collections.sort(list, Comparators.DATE_REGISTERED_DESCENDING);
		}
		
		//Update the mods
		mods.clear();
		mods.addAll(list);		
	}
	
	public static final void modsByDateModified(Collection<Mod> mods, Date from, Date to, int order)
	{
		Iterator<Mod> iterator = mods.iterator();
		while(iterator.hasNext())
		{
			Date date = iterator.next().getDateModified();
			if (date.isEarlierThan(from) || date.isLaterThan(to))
			{
				iterator.remove();
			}
		}
		
		//Sort by date
		List<Mod> list = new ArrayList<Mod>(mods);
		if (order == Filter.ASCENDING_ORDER)
		{
			Collections.sort(list, Comparators.DATE_MODIFIED_ASCENDING);
		}
		else
		{
			Collections.sort(list, Comparators.DATE_MODIFIED_DESCENDING);
		}
		
		//Update the mods
		mods.clear();
		mods.addAll(list);
	}
}