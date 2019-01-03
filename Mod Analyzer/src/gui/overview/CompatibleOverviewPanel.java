package gui.overview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import constants.Globals;
import dataDrivers.Compatibility;
import dataDrivers.Mod;
import interfaces.FormEssentials;
import methods.Methods;

public class CompatibleOverviewPanel extends JPanel implements FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 756574871577390824L;

	private List<JLabel> labels;
	
	public CompatibleOverviewPanel()
	{
		//Initialization
		this.labels = new ArrayList<JLabel>();
		
		//Properties
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	//Public Methods
	public void display(List<Compatibility> list)
	{
		this.removeLabelsFromPanel();
		this.labels.clear();
		
		HashMap<String, Mod> map = Methods.convertToMapByID(Globals.MODS);
		for (Compatibility compat : list)
		{
			this.labels.add(new JLabel(map.get(compat.getModID()).getName()));
		}
		
		//Sort labels
		Collections.sort(this.labels, Constants.JLABEL_COMPARATOR);
		
		//Display
		this.addLabelsToPanel();
	}
	//Private Methods
	private void addLabelsToPanel()
	{
		for (JLabel label : labels)
		{
			this.add(label);
		}
		this.revalidate();
		this.repaint();
	}
	private void removeLabelsFromPanel()
	{
		for (JLabel label : labels)
		{
			this.remove(label);
		}
		this.revalidate();
		this.repaint();
	}
	
	//Interfaces
	@Deprecated
	@Override
	public void refresh() {}

	@Override
	public void resetDefaults() 
	{
		this.removeLabelsFromPanel();
		this.labels.clear();
	}
}
