package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import constants.Globals;
import dataDrivers.Mod;
import dataDrivers.ModLite;
import giantsweetroll.gui.swing.ScrollPaneManager;
import gui.filter.FilterDate;
import gui.filter.FilterDropDown;
import gui.filter.FilterElement;
import interfaces.FormEssentials;
import methods.Filter;
import methods.Methods;

public class FilterPanel extends JPanel implements FormEssentials, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -280852403640100209L;
	private JButton butFilter, butReset, butDisable;
	private FilterDropDown<ModLite> filterModName;
	private FilterDropDown<String> filterModAuthor;
	private FilterDate filterDateModified;
	private FilterDate filterDateRegistered;
	private List<FilterElement> filters;
	private JPanel panelBelow, panelCenter;
	private JScrollPane scroll;
	
	private String formType;
	
	//Constants
	private final String FILTER = "filter";
	private final String RESET = "reset";
	private final String DISABLE_FILTERS = "disable filters";
	public static final String MOD_FORM = "mod_form";
	public static final String OVERVIEW = "overview";
	
	//Constructor
	public FilterPanel(boolean displayFilterButton, String formType)
	{
		this.init(displayFilterButton);
		this.formType = formType;
	}
	//Create GUI
	private void init(boolean displayFilterButton)
	{
		//Initialization
		this.initPanelBelow(displayFilterButton);
		this.initPanelCenter();
		this.scroll = ScrollPaneManager.generateDefaultScrollPane(this.panelCenter, 10, 10);
		
		//Properties
//		this.setLayout(new BorderLayout());
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Add to panel
		this.add(this.scroll);
	//	this.add(this.panelCenter, BorderLayout.CENTER);
		this.add(this.panelBelow);
	}
	private void initPanelBelow(boolean displayFilterButton)
	{
		//Initialization
		this.panelBelow = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.butFilter = new JButton("Filter");
		this.butReset = new JButton("Reset All");
		this.butDisable = new JButton("Disable All Filters");
		
		//Properties
		this.butReset.addActionListener(this);
		this.butReset.setActionCommand(this.RESET);
		this.butReset.setMnemonic(KeyEvent.VK_R);
		this.butFilter.addActionListener(this);
		this.butFilter.setActionCommand(this.FILTER);
		this.butFilter.setMnemonic(KeyEvent.VK_F);
		this.butDisable.addActionListener(this);
		this.butDisable.setActionCommand(this.DISABLE_FILTERS);
		this.butDisable.setMnemonic(KeyEvent.VK_D);
		
		//Add to panel
		this.panelBelow.add(this.butDisable);
		this.panelBelow.add(this.butReset);
		if (displayFilterButton)
		{
			this.panelBelow.add(this.butFilter);
		}
	}
	private void initPanelCenter()
	{
		//Initialization
		this.panelCenter = new JPanel();
		this.filterModName = new FilterDropDown<ModLite>("Name", Methods.convertRegisteredModsToModLite());
		this.filterModAuthor = new FilterDropDown<String>("Author", Methods.getNamesOfAuthorsFromRegisteredMods());
		this.filterDateModified = new FilterDate("Last Modified");
		this.filterDateRegistered = new FilterDate("Registered");
		this.filters = new ArrayList<FilterElement>();
		
		//Properties
		this.panelCenter.setLayout(new BoxLayout(this.panelCenter, BoxLayout.Y_AXIS));
		this.panelCenter.setBorder(BorderFactory.createTitledBorder("Filter"));
		this.filters.add(this.filterModName);
		this.filters.add(this.filterModAuthor);
		this.filters.add(this.filterDateRegistered);
		this.filters.add(this.filterDateModified);
		
		//Add to panel
		for (int i=0; i<this.filters.size(); i++)
		{
			this.panelCenter.add(this.filters.get(i));
		}
	}
	//Public Methods
	public void disableFilters()
	{
		for (int i=0; i<this.filters.size(); i++)
		{
			this.filters.get(i).setSelected(false);
		}	
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
	public boolean authorFilterSelected()
	{
		return this.filterModAuthor.isSelected();
	}
	public boolean modNameOrIDFilterSelected()
	{
		return this.filterModName.isSelected();
	}
	public boolean dateModifiedFilterSelected()
	{
		return this.filterDateModified.isSelected();
	}
	public boolean dateRegisteredFilterSelected()
	{
		return this.filterDateRegistered.isSelected();
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
	public void applyFilter()
	{
		if (this.formType.equals(FilterPanel.MOD_FORM))		//If for ModForm
		{
			//Update Compatibility before filtering
			ModCheckBox mcb = Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModCheckBox();
		//	System.out.println("Active mod check box: " + mcb.getModName());
			try
			{
				mcb.updateCompatibility();
			}
			catch(NullPointerException ex) {}
			
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
			if (this.dateRegisteredFilterSelected())
			{
				Filter.modsByDateRegistered(mods, this.filterDateRegistered.getDateFrom(), this.filterDateRegistered.getDateTo(), Filter.DESCENDING_ORDER);
			}
			if (this.dateModifiedFilterSelected())
			{
				Filter.modsByDateModified(mods, this.filterDateModified.getDateFrom(), this.filterDateModified.getDateTo(), Filter.DESCENDING_ORDER);
			}
			
			Globals.COMPATIBILITY_MOD_SELECTION_PANEL.setMods(mods);		//Apply filter to the mod selection panel
			Globals.MOD_FORM.revalidate();
			Globals.MOD_FORM.refresh();
			Globals.COMPATIBILITY_MOD_SELECTION_PANEL.setData(Globals.COMPATIBILITY_SELECTION_PANEL.getCompatibilityList());
			Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.resetDefaults();
			Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.setEnabled(false);
			Globals.COMPATIBILITY_MOD_SELECTION_PANEL.disableCurrentlyActiveMod();
//			System.out.println("Active Mod ID after Filter: " + Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModID());
			Methods.refreshModList();
		}
		else if (this.formType.equals(FilterPanel.OVERVIEW))	//If for OverviewPanel
		{
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
			if (this.dateRegisteredFilterSelected())
			{
				Filter.modsByDateRegistered(mods, this.filterDateRegistered.getDateFrom(), this.filterDateRegistered.getDateTo(), Filter.DESCENDING_ORDER);
			}
			if (this.dateModifiedFilterSelected())
			{
				Filter.modsByDateModified(mods, this.filterDateModified.getDateFrom(), this.filterDateModified.getDateTo(), Filter.DESCENDING_ORDER);
			}
			Globals.OVERVIEW.setData(mods);
			Globals.OVERVIEW.revalidate();
			Globals.OVERVIEW.repaint();
			Methods.refreshModList();
		}
	}
	
	//Interfaces
	@Deprecated
	@Override
	public void refresh() {}

	@Override
	public void resetDefaults()
	{
		this.disableFilters();
	}
	
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
	}
}
