package dataDrivers;

public class Mod
{
	private String name, author, version, link, notes, id;
	private CompatibilityList compatibility;
	private Tags tags;
	
	public Mod(String name)
	{
		this.name = name;
		this.compatibility = new CompatibilityList();
		this.tags = new Tags();
	}
	
	//Public methods
	//Setters
	public void setName(String name)	{this.name = name;}
	public void setAuthor(String author)	{this.author = author;}
	public void setVersion(String version)	{this.version = version;}
	public void setLink(String link)	{this.link = link;}
	public void setNotes(String notes)	{this.notes = notes;}
	public void setID(String id)	{this.id = id;}
	public void setTags(Tags tags)	{this.tags = tags;}
	public void setCompatibility(CompatibilityList compat)	{this.compatibility = compat;}
	//Getters
	public String getName()	{return this.name;}
	public String getAuthor()	{return this.author;}
	public String getVersion()	{return this.version;}
	public String getLink()	{return this.link;}
	public String getNotes()	{return this.notes;}
	public String getID()	{return this.id;}
	public CompatibilityList getCompatibilities()	{return this.compatibility;}
	public Tags getTags()	{return this.tags;}
}