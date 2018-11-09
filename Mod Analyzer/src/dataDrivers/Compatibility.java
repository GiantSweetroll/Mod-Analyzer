package dataDrivers;

public class Compatibility
{
	private String modID, severity, reason, patchLink, notes;
	private boolean compatible, patchAvailable;
	
	//Constants
	public static final String SOFT = "soft",
			MEDIUM = "medium",
			HARD = "hard";
	
	public Compatibility(String modID, boolean compatible)
	{
		this.modID = modID;
		this.compatible = compatible;
	}
	
	//Public methods
	//Getters
	public String getModID()
	{
		return this.modID;
	}
	public boolean isCompatible()
	{
		return this.compatible;
	}
	public String getSeverity()
	{
		return this.severity;
	}
	public String getReason()
	{
		return this.reason;
	}
	public boolean isPatchAvailable()
	{
		return this.patchAvailable;
	}
	public String getPatchLink()
	{
		return this.patchLink;
	}
	public String getNotes()
	{
		return this.notes;
	}
	//Setters
	public void setModID(String id)
	{
		this.modID = id;
	}
	public void setIsCompatible(boolean b)
	{
		this.compatible = b;
	}
	public void setSeverity(String severity)
	{
		this.severity = severity;
	}
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	public void setPatchAvailable(boolean b)
	{
		this.patchAvailable = b;
	}
	public void setPatchLink(String link)
	{
		this.patchLink = link;
	}
	public void setNotes(String notes)
	{
		this.notes = notes;
	}
}
