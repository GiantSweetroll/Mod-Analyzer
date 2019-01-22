package dataDrivers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import giantsweetroll.xml.dom.XMLManager;

public class CompatibilityList 
{
	private HashMap<String, Compatibility> compat;
	
	//Constructor
	public CompatibilityList()
	{
		//Initialization
		this.compat = new HashMap<String, Compatibility>();
	}
	
	//Public methods
	//Setters
	public void setCompatibility(CompatibilityList comp)
	{
	}
	public void setCompatibility(String modID, Compatibility compat)
	{
		this.compat.put(modID, compat);
	}
	public void addCompatibility(Compatibility compat)
	{
		this.compat.put(compat.getModID(), compat);
	}
	public void setData(Element compatListElement)
	{
		List<Element> compats = XMLManager.getElements(compatListElement.getElementsByTagName(Compatibility.COMPATIBILITY_NODE));
		for (Element compat : compats)
		{
			Compatibility comp = new Compatibility(compat);
			this.compat.put(comp.getModID(), comp);
		}
	}
	//Getters
	public HashMap<String, Compatibility> getListOfModCompatibility()
	{
		return this.compat;
	}
	public Compatibility getModCompatibility(String modID)
	{
		return this.compat.get(modID);
	}
	public Element getAsElement(Document doc)
	{
		Element parent = doc.createElement(Mod.COMPATIBILITY_COLLECTION);
		
		for (Map.Entry<String, Compatibility> entry : this.getListOfModCompatibility().entrySet())
		{
			parent.appendChild(entry.getValue().getAsElement(doc));
		}
		
		return parent;
	}
	public SortedSet<String> getModIDs()
	{
		SortedSet<String> set = new TreeSet<String>();
		
		for (Map.Entry<String, Compatibility> entry: this.getListOfModCompatibility().entrySet())
		{
			set.add(entry.getValue().getModID());
		}
		
		return set;
	}
	public List<Compatibility> getListOfCompatibleMods()
	{
		List<Compatibility> list = new ArrayList<Compatibility>();
		
		for (Map.Entry<String, Compatibility> entry : this.compat.entrySet())
		{
			Compatibility comp = entry.getValue();
			if (comp.isCompatible())
			{
				list.add(comp);
			}
		}
		
		return list;
	}
	public List<Compatibility> getListOfIncompatibleMods()
	{
		List<Compatibility> list = new ArrayList<Compatibility>();
		
		for (Map.Entry<String, Compatibility> entry : this.compat.entrySet())
		{
			Compatibility comp = entry.getValue();
			if (!comp.isCompatible())
			{
				list.add(comp);
			}
		}
		
		return list;		
	}
	//Other Methods
	public void removeCompatibility(String modID)
	{
		try
		{
			this.compat.remove(modID);
		}
		catch(NullPointerException ex) {}
	}
	public void printAllCompatibilities()
	{
		for (Map.Entry<String, Compatibility> entry : this.compat.entrySet())
		{
			entry.getValue().printCompatibility();
		}
	}
	public void clear()
	{
		this.compat.clear();
	}
}
