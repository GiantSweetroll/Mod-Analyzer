package methods;

import java.util.SortedSet;
import java.util.TreeSet;

import constants.Globals;
import dataDrivers.Mod;

public class Methods
{
	public static String[] getNamesOfAuthorsFromRegisteredMods()
	{
		SortedSet<String> set = new TreeSet<String>();
		
		for (Mod mod : Globals.MODS)
		{
			set.add(mod.getAuthor());
		}
		
		return set.toArray(new String[set.size()]);
	}
}
