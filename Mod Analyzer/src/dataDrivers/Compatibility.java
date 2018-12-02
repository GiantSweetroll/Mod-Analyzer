package dataDrivers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import methods.Methods;

public class Compatibility
{
	private String modID, severity, reason, patchLink, notes;
	private boolean compatible, patchAvailable;
	
	//Constants
	public static final String SOFT = "soft",
								MEDIUM = "medium",
								HARD = "hard";
	public static final String MOD_ID = "mod_id",
								SEVERITY = "severity",
								REASON = "reason",
								PATCH_LINK = "patch_link",
								NOTES = "compat_notes",
								IS_COMPATIBLE = "is_compatible",
								PATCH_AVAILABLE = "patch_available",
								GENERAL_COMPATIBILITY = "general compatibility",
								COMPATIBILITY_NODE = "compatibility";
	
	//Constructors
	public Compatibility(String modID)
	{
		this.modID = modID;
	}
	public Compatibility(Element compatElement)
	{
		this.setData(compatElement);
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
	public Element getAsElement(Document doc)
	{
		Element elm = doc.createElement(Compatibility.COMPATIBILITY_NODE);
		elm.setAttribute(Compatibility.MOD_ID, this.getModID());
		elm.appendChild(Methods.createElement(doc, Compatibility.IS_COMPATIBLE, Boolean.toString(this.isCompatible())));
		elm.appendChild(Methods.createElement(doc, Compatibility.PATCH_AVAILABLE, Boolean.toString(this.isPatchAvailable())));
		elm.appendChild(Methods.createElement(doc, Compatibility.PATCH_LINK, this.getPatchLink()));
		elm.appendChild(Methods.createElement(doc, Compatibility.SEVERITY, this.getSeverity()));
		elm.appendChild(Methods.createElement(doc, Compatibility.REASON, this.getReason()));
		elm.appendChild(Methods.createElement(doc, Compatibility.NOTES, this.getNotes()));
		return elm;
	}
	//Setters
	public void setModID(String id)
	{
		this.modID = id;
	}
	public void setIsCompatible(boolean b)
	{
		try
		{
			this.compatible = b;
		}
		catch(NullPointerException ex) {}
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
		try
		{
			this.patchAvailable = b;
		}
		catch(NullPointerException ex) {}
	}
	public void setPatchLink(String link)
	{
		this.patchLink = link;
	}
	public void setNotes(String notes)
	{
		this.notes = notes;
	}
	public void setData(Element compatElement)
	{
		this.setModID(compatElement.getAttribute(Compatibility.MOD_ID));
		this.setSeverity(Methods.getTextContent(compatElement, Compatibility.SEVERITY));
		this.setIsCompatible(Boolean.parseBoolean(Methods.getTextContent(compatElement, Compatibility.IS_COMPATIBLE)));
		this.setReason(Methods.getTextContent(compatElement, Compatibility.REASON));
		this.setPatchAvailable(Boolean.parseBoolean(Methods.getTextContent(compatElement, Compatibility.PATCH_AVAILABLE)));
		this.setPatchLink(Methods.getTextContent(compatElement, Compatibility.PATCH_LINK));
		this.setNotes(Methods.getTextContent(compatElement, Compatibility.NOTES));
	}
}
