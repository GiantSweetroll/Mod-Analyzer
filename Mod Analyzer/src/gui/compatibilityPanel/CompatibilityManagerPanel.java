package gui.compatibilityPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import constants.Globals;
import dataDrivers.CompatibilityList;
import dataDrivers.ModLite;
import gui.FilterElement;
import interfaces.FormEssentials;
import methods.Methods;

public class CompatibilityManagerPanel extends JPanel implements ActionListener, FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8062423562858065850L;
	
	private JPanel panelSearchFilters, panelSearchBelow, panelSearch;
	private JButton butFilter, butReset, butDisable;
	private FilterElement<ModLite> filterModName;
	private FilterElement<String> filterModAuthor;
	private List<FilterElement<?>> filters;
	
	//Constants
	private final String FILTER = "filter";
	private final String RESET = "reset";
	private final String DISABLE_FILTERS = "disable filters";
	
	//Constructor
	public CompatibilityManagerPanel()
	{
		this.createGUI();
	}
	
	//Initialize GUI
	private void createGUI()
	{
		//Initialization
		this.initPanelSearch();
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		
		//Add to panel
		this.add(this.panelSearch, BorderLayout.NORTH);
		this.add(Globals.COMPATIBILITY_SELECTION_PANEL, BorderLayout.CENTER);
	}
	private void initPanelSearchFilters()
	{
		//Initialization
		this.panelSearchFilters = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.filterModName = new FilterElement<ModLite>("Name", Methods.convertRegisteredModsToModLite());
		this.filterModAuthor = new FilterElement<String>("Author", Methods.getNamesOfAuthorsFromRegisteredMods());
		this.filters = new ArrayList<FilterElement<?>>();
		
		//Properties
		this.panelSearchFilters.setBorder(BorderFactory.createTitledBorder("Filter"));
		this.filters.add(this.filterModName);
		this.filters.add(this.filterModAuthor);
		
		//Add to panel
		for (int i=0; i<this.filters.size(); i++)
		{
			this.panelSearchFilters.add(this.filters.get(i));
		}
	}
	private void initPanelBelow()
	{
		//Initialization
		this.panelSearchBelow = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.butFilter = new JButton("Filter");
		this.butReset = new JButton("Reset All");
		this.butDisable = new JButton("Disable All Filters");
		
		//Properties
		this.butReset.addActionListener(this);
		this.butReset.setActionCommand(this.RESET);
		this.butFilter.addActionListener(this);
		this.butFilter.setActionCommand(this.FILTER);
		this.butDisable.addActionListener(this);
		this.butDisable.setActionCommand(this.DISABLE_FILTERS);
		
		//Add to panel
		this.panelSearchBelow.add(this.butDisable);
		this.panelSearchBelow.add(this.butReset);
		this.panelSearchBelow.add(this.butFilter);
	}
	private void initPanelSearch()
	{
		//Initialization
		this.panelSearch = new JPanel(new BorderLayout());
		this.initPanelBelow();
		this.initPanelSearchFilters();
		
		//add to panel
		this.panelSearch.add(this.panelSearchFilters, BorderLayout.CENTER);
		this.panelSearch.add(this.panelSearchBelow, BorderLayout.SOUTH);
	}
	//Public Methods
	public void setData(CompatibilityList compatList)
	{
		Globals.COMPATIBILITY_SELECTION_PANEL.setData(compatList);
	}
	public CompatibilityList getCompatibilityData()
	{
		return Globals.COMPATIBILITY_SELECTION_PANEL.getCompatibilityList();
	}
	public String getGeneralCompatibility()
	{
		return Globals.COMPATIBILITY_SELECTION_PANEL.getGeneralCompatibility();
	}
	public void refreshFilters()
	{
		this.filterModAuthor.refresh(Methods.getNamesOfAuthorsFromRegisteredMods());
		this.filterModName.refresh(Methods.convertRegisteredModsToModLite());
	}
	public void resetFilters()
	{
		for (int i=0; i<this.filters.size(); i++)
		{
			this.filters.get(i).resetDefaults();
		}
	}
	public void disableFilters()
	{
		for (int i=0; i<this.filters.size(); i++)
		{
			this.filters.get(i).setSelected(false);
		}	
	}
	
	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case RESET:
				this.resetFilters();
				break; 
				
			case FILTER:
				//Filter Function
				break;
				
			case DISABLE_FILTERS:
				this.disableFilters();
				break;
		}
	}

	@Override
	public void refresh() 
	{
		this.refreshFilters();
	}

	@Override
	public void resetDefaults() 
	{
		this.resetFilters();
		Globals.COMPATIBILITY_MOD_SELECTION_PANEL.resetDefaults();
		Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.resetDefaults();
		Globals.MOD_FORM_MOD_DETAILS_PANEL.resetDefaults();
	}
}
