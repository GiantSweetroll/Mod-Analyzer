package methods;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JComponent;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import constants.Constants;
import constants.Globals;
import dataDrivers.Compatibility;
import dataDrivers.CompatibilityList;
import dataDrivers.Mod;
import dataDrivers.ModLite;
import giantsweetroll.date.Date;
import giantsweetroll.numbers.GNumbers;
import giantsweetroll.xml.dom.XMLManager;

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
	
	public static ModLite[] convertRegisteredModsToModLite()
	{
		SortedSet<ModLite> set = new TreeSet<ModLite>();
		
		for (Mod mod : Globals.MODS)
		{
			set.add(new ModLite(mod.getID(), mod.getName()));
		}
		
		return set.toArray(new ModLite[set.size()]);
	}
	
	public static Element createElement(Document doc, String tagName, String text)
	{
		Element elm = doc.createElement(tagName);
		elm.setTextContent(text);
		
		return elm;
	}
	public static String getTextContent(Document doc, String tagName)
	{
		try
		{
			return XMLManager.getElement(doc.getElementsByTagName(tagName), 0).getTextContent();
		}
		catch(Exception ex)
		{
			return "";
		}
	}
	public static String getTextContent(Element elm, String tagName)
	{
		try
		{
			return XMLManager.getElement(elm.getElementsByTagName(tagName), 0).getTextContent();
		}
		catch(Exception ex)
		{
			return "";
		}
	}
	
	public static String generateModID(Date dateCreated)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(Integer.toString(dateCreated.getYear()));
		String temp = Integer.toString(dateCreated.getMonth());
		if (temp.length()==1)
		{
			temp = "0" + temp;
		}
		sb.append(temp);
		temp = Integer.toString(dateCreated.getDay());
		if (temp.length()==1)
		{
			temp = "0" + temp;
		}
		sb.append(temp);
		
		while(true)
		{
			StringBuilder sb2 = new StringBuilder (sb.toString());
			for (int i=1; i<=4; i++)
			{
				sb2.append(Integer.toString(GNumbers.randomize(0, 9)));
			}
			
			//Check for duplicate
			boolean duplicate = false;
			String newID = sb2.toString();
			Set<String> ids = FileOperation.getListOfUsedModIDs();
			for (String id : ids)
			{
				if (newID.equals(id))
				{
					duplicate = true;
					break;
				}
			}
			
			if (!duplicate)
			{
				sb.replace(0, sb.length(), newID);
				break;
			}
		}
		
		return sb.toString();
	}
	
	public static void autoLayout(SpringLayout layout, JComponent c1, JComponent c2, JComponent parent)
	{
		layout.putConstraint(SpringLayout.WEST, c1, Constants.INSETS_BASE, SpringLayout.WEST, parent);
		layout.putConstraint(SpringLayout.NORTH, c1, Constants.INSETS_BASE, SpringLayout.SOUTH, c2);
//		layout.putConstraint(SpringLayout.EAST, c1, InsetsConstants.GENERAL_INSETS_SIZE, SpringLayout.EAST, parent);
	}
	
	public static HashMap<String, Mod> convertToMapByID(Set<Mod> set)
	{
		HashMap<String, Mod> map = new HashMap<String, Mod>();
		for (Mod mod : set)
		{
			map.put(mod.getID(), mod);
		}
		return map;
	}
	
	public static String getWrappableText(String str)
	{
		return "<html>" + str + "</html>";
	}
	
	public static String convertBooleanToYesNo(boolean b)
	{
		if (b)
		{
			return "Yes";
		}
		else
		{
			return "No";
		}
	}
	
	public static void setUIFont(FontUIResource f)
	{
		Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements())
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource)
			{
				UIManager.put(key, f);
			}
		}
	}	
	
	public static void refreshModList()
	{
		Globals.MODS = FileOperation.importAllMods();
	}
	
	public static void printCompatibilityIDs(CompatibilityList compatList)
	{
		System.out.println("Mods in compatibility list:");
		for (Map.Entry<String, Compatibility> entry : compatList.getListOfModCompatibility().entrySet())
		{
			System.out.println(entry.getKey());
		}
		System.out.println();
	}
	
	public static String getDateAsString(Date date)
	{
		return date.getDay() + "/" + date.getMonth() + "/" + date.getYear();
	}
}