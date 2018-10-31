package dataDrivers;

import java.util.SortedSet;
import java.util.TreeSet;

public class Tags 
{
	private SortedSet<String> tags;
	
	public Tags()
	{
		this.tags = new TreeSet<String>();
	}
	
	//public methods
	//Setters
	public void setTags(SortedSet<String> tags) {this.tags = tags;}
	//Getters
	public SortedSet<String> getTags()	{return this.tags;}
	//Others
	public void addTag(String tag) {this.tags.add(tag);}
}
