package gui.compatibilityPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import constants.Globals;
import dataDrivers.CompatibilityList;
import interfaces.FormEssentials;

public class CompatibilityManagerPanel extends JPanel implements FormEssentials//, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8062423562858065850L;
	
//	private JPanel panelSearchFilters, panelSearch, panelSearchBelow;
//	private JButton butFilter, butReset, butDisable;
//	private FilterDropDown<ModLite> filterModName;
//	private FilterDropDown<String> filterModAuthor;
//	private FilterDate filterDateModified;
//	private List<FilterElement> filters;
	
	//Constants
//	private final String FILTER = "filter";
//	private final String RESET = "reset";
//	private final String DISABLE_FILTERS = "disable filters";
	
	//Constructor
	public CompatibilityManagerPanel()
	{
		this.createGUI();
	}
	
	//Initialize GUI
	private void createGUI()
	{
		//Initialization
	//	this.initPanelSearch();
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		
		//Add to panel
//		this.add(this.panelSearch, BorderLayout.NORTH);
		this.add(Globals.MOD_FORM_FILTER_PANEL, BorderLayout.NORTH);
		this.add(Globals.COMPATIBILITY_SELECTION_PANEL, BorderLayout.CENTER);
	}
	/*
	private void initPanelSearchFilters()
	{
		//Initialization
		this.panelSearchFilters = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.filterModName = new FilterDropDown<ModLite>("Name", Methods.convertRegisteredModsToModLite());
		this.filterModAuthor = new FilterDropDown<String>("Author", Methods.getNamesOfAuthorsFromRegisteredMods());
		this.filterDateModified = new FilterDate("Last Modified");
		this.filters = new ArrayList<FilterElement>();
		
		//Properties
		this.panelSearchFilters.setBorder(BorderFactory.createTitledBorder("Filter"));
		this.filters.add(this.filterModName);
		this.filters.add(this.filterModAuthor);
		this.filters.add(this.filterDateModified);
		
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
	}		*/
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
	/*
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
	public String getModNameFilter()
	{
		ModLite mod = null;
		if (this.filterModName.customKeywordSelected())
		{
			return this.filterModName.getFilterKeyword();
		}
		else
		{
			mod = this.filterModName.getFilterSelection();
			return mod.getName();
		}
	}
	public String getModIDFilter()
	{
		ModLite mod = null;
		if (this.filterModName.customKeywordSelected())
		{
			return this.filterModName.getFilterKeyword();
		}
		else
		{
			mod = this.filterModName.getFilterSelection();
			return mod.getID();
		}
	}
	public String getAuthorFilter()
	{
		if (this.filterModAuthor.customKeywordSelected())
		{
			return this.filterModAuthor.getFilterKeyword();
		}
		else
		{
			return this.filterModAuthor.getFilterSelection();
		}
	}
	public boolean authorFilterSelected()
	{
		return this.filterModAuthor.isSelected();
	}
	public boolean modNameOrIDFilterSelected()
	{
		return this.filterModName.isSelected();
	}		*/
	//Private Methods
	/*
	private void applyFilter()
	{
		//Update Compatibility before filtering
		ModCheckBox mcb = Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModCheckBox();
	//	System.out.println("Active mod check box: " + mcb.getModName());
		mcb.updateCompatibility();
		
		//Filter
		Set<Mod> mods = Globals.MODS;
		if (this.authorFilterSelected())
		{
			Filter.modsByAuthor(mods, this.getAuthorFilter());
		}
		if (this.modNameOrIDFilterSelected())
		{
			Filter.modsByName(mods, this.getModNameFilter());
		}
		
		Globals.COMPATIBILITY_MOD_SELECTION_PANEL.setMods(mods);		//Apply filter to the mod selection panel
		Globals.MOD_FORM.revalidate();
		Globals.MOD_FORM.refresh();
		Globals.COMPATIBILITY_MOD_SELECTION_PANEL.setData(Globals.COMPATIBILITY_SELECTION_PANEL.getCompatibilityList());
		Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.resetDefaults();
		Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.setEnabled(false);
		Globals.COMPATIBILITY_MOD_SELECTION_PANEL.disableCurrentlyActiveMod();
//		System.out.println("Active Mod ID after Filter: " + Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModID());
		Methods.refreshModList();
	}
	
	//Interfaces
	/*
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case RESET:
				this.resetFilters();
				break; 
				
			case FILTER:
				this.applyFilter();
				break;
				
			case DISABLE_FILTERS:
				this.disableFilters();
				break;
		}
	}		*/

	@Override
	public void refresh() 
	{
//		this.refreshFilters();
	}

	@Override
	public void resetDefaults() 
	{
//		this.resetFilters();
		Globals.COMPATIBILITY_MOD_SELECTION_PANEL.resetDefaults();
		Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.resetDefaults();
		Globals.MOD_FORM_MOD_DETAILS_PANEL.resetDefaults();
	}
}
