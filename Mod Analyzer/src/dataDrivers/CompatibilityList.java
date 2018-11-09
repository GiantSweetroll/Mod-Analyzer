package dataDrivers;

import java.util.HashMap;

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
	public void setCompatibility(String str)
	{
		this.general = str;
	}
	public void addCompatibility(Compatibility compat)
	{
		this.compat.put(compat.getModID(), compat);
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
}
