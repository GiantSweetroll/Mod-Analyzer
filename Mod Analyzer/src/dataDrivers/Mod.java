package dataDrivers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import giantsweetroll.date.Date;
import giantsweetroll.message.MessageManager;
import giantsweetroll.xml.dom.XMLManager;
import methods.Methods;

public class Mod
{
	private String name, 
					author,
					version, 
					link, 
					notes, 
					id, 
					generalCompatibility;
	private CompatibilityList compatibility;
	private Date dateCreated, dateModified;
	
	private Tags tags;			//not used
	
	//Constants
	public static final String NAME = "name",
								AUTHOR = "author",
								VERSION = "version",
								LINK = "link",
								NOTES = "notes",
								ID = "id",
								COMPATIBILITY_COLLECTION = "compatibility_collection",
								GENERAL_COMPATIBILITY = "general_compatibility",
								DATE_CREATED_DAY = "date_created_day",
								DATE_CREATED_MONTH = "date_created_month",
								DATE_CREATED_YEAR = "date_created_year",
								DATE_MODIFIED_DAY = "date_modified_day",
								DATE_MODIFIED_MONTH = "date_modified_month",
								DATE_MODIFIED_YEAR = "date_modified_year",
								ROOT_NODE = "mod_data";
	
	public Mod(String name)
	{
		this.name = name;
		this.compatibility = new CompatibilityList();
		this.author = "";
		this.version = "";
		this.link = "";
		this.notes = "";
		this.id = "";		
	//	this.tags = new Tags();
	}
	public Mod()
	{
		this.name = "";
		this.author = "";
		this.version = "";
		this.link = "";
		this.notes = "";
		this.id = "";
		this.compatibility = new CompatibilityList();
	}
	public Mod(Document doc)
	{
		this.compatibility = new CompatibilityList();
		this.setData(doc);
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
	public void setDateCreated(Date date)	{this.dateCreated = date;}
	public void setDateModified(Date date)	{this.dateModified = date;}
	public void setData(Document doc)
	{
		this.setID(Methods.getTextContent(doc, Mod.ID));
		this.setName(Methods.getTextContent(doc, Mod.NAME));
		this.setAuthor(Methods.getTextContent(doc, Mod.AUTHOR));
		this.setVersion(Methods.getTextContent(doc, Mod.VERSION));
		this.setLink(Methods.getTextContent(doc, Mod.LINK));
		this.setNotes(Methods.getTextContent(doc, Mod.NOTES));
		this.setGeneralCompatibility(Methods.getTextContent(doc, Mod.GENERAL_COMPATIBILITY));
		this.setDateCreated(new Date(Integer.parseInt(Methods.getTextContent(doc, Mod.DATE_CREATED_DAY)),
										Integer.parseInt(Methods.getTextContent(doc, Mod.DATE_CREATED_MONTH)),
										Integer.parseInt(Methods.getTextContent(doc, Mod.DATE_CREATED_YEAR))));
		this.setDateModified(new Date(Integer.parseInt(Methods.getTextContent(doc, Mod.DATE_MODIFIED_DAY)),
										Integer.parseInt(Methods.getTextContent(doc, Mod.DATE_MODIFIED_MONTH)),
										Integer.parseInt(Methods.getTextContent(doc, Mod.DATE_MODIFIED_YEAR))));
		this.compatibility.setData(XMLManager.getElement(doc.getElementsByTagName(Mod.COMPATIBILITY_COLLECTION), 0));
	}
	public void setGeneralCompatibility(String compat) {this.generalCompatibility = compat;}
	//Getters
	public String getName()	{return this.name;}
	public String getAuthor()	{return this.author;}
	public String getVersion()	{return this.version;}
	public String getLink()	{return this.link;}
	public String getNotes()	{return this.notes;}
	public String getID()	{return this.id;}
	public String getGeneralCompatibility() {return this.generalCompatibility;}
	public CompatibilityList getCompatibilities()	{return this.compatibility;}
	public Date getDateCreated()	{return this.dateCreated;}
	public Date getDateModified()	{return this.dateModified;}
	public Tags getTags()	{return this.tags;}
	public Document getXMLDocument()
	{
		try
		{
			Document doc = XMLManager.createDocument();
			Element root = doc.createElement(Mod.ROOT_NODE);
			
			root.appendChild(Methods.createElement(doc, Mod.NAME, this.getName()));
			root.appendChild(Methods.createElement(doc, Mod.AUTHOR, this.getAuthor()));
			root.appendChild(Methods.createElement(doc, Mod.ID, this.getID()));
			root.appendChild(Methods.createElement(doc, Mod.VERSION, this.getVersion()));
			root.appendChild(Methods.createElement(doc, Mod.LINK, this.getLink()));
			root.appendChild(Methods.createElement(doc, Mod.NOTES, this.getNotes()));
			root.appendChild(Methods.createElement(doc, Mod.GENERAL_COMPATIBILITY, this.getGeneralCompatibility()));
			root.appendChild(Methods.createElement(doc, Mod.DATE_CREATED_DAY, Integer.toString(this.getDateCreated().getDay())));
			root.appendChild(Methods.createElement(doc, Mod.DATE_CREATED_MONTH, Integer.toString(this.getDateCreated().getMonth())));
			root.appendChild(Methods.createElement(doc, Mod.DATE_CREATED_YEAR, Integer.toString(this.getDateCreated().getYear())));
			root.appendChild(Methods.createElement(doc, Mod.DATE_MODIFIED_DAY, Integer.toString(this.getDateModified().getDay())));
			root.appendChild(Methods.createElement(doc, Mod.DATE_MODIFIED_MONTH, Integer.toString(this.getDateModified().getMonth())));
			root.appendChild(Methods.createElement(doc, Mod.DATE_MODIFIED_YEAR, Integer.toString(this.getDateModified().getYear())));
			root.appendChild(this.getCompatibilities().getAsElement(doc));
			
			doc.appendChild(root);
			
			return doc;
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
			MessageManager.showErrorDialog(ex.getMessage(), "Exception");
			return null;
		}
	}
}