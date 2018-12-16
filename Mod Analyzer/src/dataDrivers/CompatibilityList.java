package dataDrivers;

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
	private String general;
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
		this.general = comp.getGeneralCompatibility();
	}
	public void setGeneralaCompatibility(String str)
	{
		this.general = str;
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
	public String getGeneralCompatibility() {return this.general;}
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
	//Other Methods
	public void removeCompatibility(String modID)
	{
		this.compat.remove(modID);
	}
}
