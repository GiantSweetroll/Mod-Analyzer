package dataDrivers;

import org.w3c.dom.Document;

import giantsweetroll.xml.dom.XMLManager;
import methods.Methods;

public class Mod
{
	private String name, author, version, link, notes, id;
	private CompatibilityList compatibility;
	private Tags tags;
	
	//Constants
	public static final String NAME = "name",
								AUTHOR = "author",
								VERSION = "version",
								LINK = "link",
								NOTES = "notes",
								ID = "id",
								COMPATIBILITY_COLLECTION = "compatibility_collection";
	
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
	public void setData(Document doc)
	{
		this.setName(Methods.getTextContent(doc, Mod.NAME));
		this.setAuthor(Methods.getTextContent(doc, Mod.AUTHOR));
		this.setVersion(Methods.getTextContent(doc, Mod.VERSION));
		this.setLink(Methods.getTextContent(doc, Mod.LINK));
		this.setNotes(Methods.getTextContent(doc, Mod.NOTES));
		this.compatibility.setData(XMLManager.getElement(doc.getElementsByTagName(Mod.COMPATIBILITY_COLLECTION), 0));
	}
	//Getters
	public String getName()	{return this.name;}
	public String getAuthor()	{return this.author;}
	public String getVersion()	{return this.version;}
	public String getLink()	{return this.link;}
	public String getNotes()	{return this.notes;}
	public String getID()	{return this.id;}
	public CompatibilityList getCompatibilities()	{return this.compatibility;}
	public Tags getTags()	{return this.tags;}
	public Document getXMLDocument()
	{
		try
		{
			Document doc = XMLManager.createDocument();
			
			doc.appendChild(Methods.createElement(doc, Mod.NAME, this.getName()));
			doc.appendChild(Methods.createElement(doc, Mod.AUTHOR, this.getAuthor()));
			doc.appendChild(Methods.createElement(doc, Mod.ID, this.getID()));
			doc.appendChild(Methods.createElement(doc, Mod.VERSION, this.getVersion()));
			doc.appendChild(Methods.createElement(doc, Mod.LINK, this.getLink()));
			doc.appendChild(Methods.createElement(doc, Compatibility.GENERAL_COMPATIBILITY, this.getCompatibilities().getGeneralCompatibility()));
			doc.appendChild(this.getCompatibilities().getAsElement(doc));
			
			return doc;
		}
		catch(Exception ex) 
		{
			return null;
		}
	}
}