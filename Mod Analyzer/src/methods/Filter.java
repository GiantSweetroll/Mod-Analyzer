package methods;

import java.util.Collection;
import java.util.Iterator;

import dataDrivers.Mod;

public class Filter 
{
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
			return source.equals(keyword);
		}
		else if (source.length() > keyword.length())
		{
			int leap = keyword.length();
			for (int i=0; i<source.length()-leap; i++)
			{
				if(source.substring(i, i+leap).equals(keyword))
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
}
