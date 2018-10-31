package gui;

import java.awt.GridLayout;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class TagsSelectionPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6598723935878814525L;
	
	private Set<JCheckBox> tagsSelection;
	
	public TagsSelectionPanel(String[] tags)
	{
		//Initialization
		this.tagsSelection = new LinkedHashSet<JCheckBox>();
		
		//Properties
		this.setLayout(new GridLayout(2, 0));
		this.createCheckBoxes(tags);
		
		//Add to panel
		for (JCheckBox jc : this.tagsSelection)
		{
			this.add(jc);
		}
	}
	
	//Public Methods
	public SortedSet<String> getSelectedTags()
	{
		SortedSet<String> set = new TreeSet<String>();
		
		for (JCheckBox jc : this.tagsSelection)
		{
			if (jc.isSelected())
			{
				set.add(jc.getText());
			}
		}
		
		return set;
	}
	public void setSelectedTags(Set<String> tags)
	{
		for (String tag : tags)
		{
			for (JCheckBox jc : this.tagsSelection)
			{
				if (jc.getText().equals(tag))
				{
					jc.setSelected(true);
					break;
				}
			}
		}
	}
	
	//Private Methods
	private void createCheckBoxes(String[] tags)
	{
		this.tagsSelection.clear();
		for (String str : tags)
		{
			JCheckBox jc = new JCheckBox(str);
			this.tagsSelection.add(jc);
		}
	}
}
