package dataDrivers;

public class Compatibility 
{
	private String general;
	//Placeholder for not manual compatibility
	
	//Constants
	public static final String SOFT = "soft",
								MEDIUM = "medium",
								HARD = "hard";
	
	//Public methods
	//Setters
	public void setCompatibility(Compatibility comp)
	{
		this.general = comp.getGeneralCompatibility();
	}
	public void setCompatibility(String str)
	{
		this.general = str;
	}
	//Getters
	public String getGeneralCompatibility() {return this.general;}
}
