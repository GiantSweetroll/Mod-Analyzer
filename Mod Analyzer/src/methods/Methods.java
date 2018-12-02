package methods;

import java.util.SortedSet;
import java.util.TreeSet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import constants.Globals;
import dataDrivers.Mod;
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
	
	public static Element createElement(Document doc, String tagName, String text)
	{
		Element elm = doc.createElement(tagName);
		elm.setTextContent(text);
		
		return elm;
	}
	public static String getTextContent(Document doc, String tagName)
	{
		return XMLManager.getElement(doc.getElementsByTagName(Mod.NAME), 0).getTextContent();
	}
	public static String getTextContent(Element elm, String tagName)
	{
		return XMLManager.getElement(elm.getElementsByTagName(tagName), 0).getTextContent();
	}
}
