package dataDrivers;

public class ModLite 
{
	private String name, id;
	
	//Constructor
	public ModLite()
	{
		this.name = "";
		this.id = "";
	}
	public ModLite(String id, String name)
	{
		this.name = name;
		this.id = id;
	}
	
	//Public Methods
	public String getName()
	{
		return this.name;
	}
	public String getID()
	{
		return this.id;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setID(String id)
	{
		this.id = id;
	}
	
	//Overridden methods
	@Override
	public String toString()
	{
		return this.name;
	}
}
