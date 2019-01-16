package constants;

import java.util.Comparator;

import dataDrivers.Mod;
import giantsweetroll.date.Date;

public class Comparators 
{
	public static final Comparator<Mod> DATE_MODIFIED_DESCENDING = new Comparator<Mod>()
	{

		@Override
		public int compare(Mod mod1, Mod mod2) 
		{
			Date modDate1 = mod1.getDateModified();
			Date modDate2 = mod2.getDateModified();
			
			if (modDate1.isEarlierThan(modDate2))
			{
				return 1;
			}
			else if (modDate1.isLaterThan(modDate2))
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
	};	
	
	public static final Comparator<Mod> DATE_MODIFIED_ASCENDING = new Comparator<Mod>()
	{

		@Override
		public int compare(Mod mod1, Mod mod2) 
		{
			Date modDate1 = mod1.getDateModified();
			Date modDate2 = mod2.getDateModified();
			
			if (modDate1.isEarlierThan(modDate2))
			{
				return -1;
			}
			else if (modDate1.isLaterThan(modDate2))
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	};	
	
	public static final Comparator<Mod> DATE_REGISTERED_DESCENDING = new Comparator<Mod>()
	{

		@Override
		public int compare(Mod mod1, Mod mod2) 
		{
			Date modDate1 = mod1.getDateCreated();
			Date modDate2 = mod2.getDateCreated();
			
			if (modDate1.isEarlierThan(modDate2))
			{
				return 1;
			}
			else if (modDate1.isLaterThan(modDate2))
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
	};
	public static final Comparator<Mod> DATE_REGISTERED_ASCENDING = new Comparator<Mod>()
	{

		@Override
		public int compare(Mod mod1, Mod mod2) 
		{
			Date modDate1 = mod1.getDateCreated();
			Date modDate2 = mod2.getDateCreated();
			
			if (modDate1.isEarlierThan(modDate2))
			{
				return -1;
			}
			else if (modDate1.isLaterThan(modDate2))
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	};
}
