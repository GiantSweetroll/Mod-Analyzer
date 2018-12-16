package gui.compatibilityPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JButton butFilter, butReset;
	private FilterElement<ModLite> filterModName;
	private FilterElement<String> filterModAuthor;
	
	//Constants
	private final String FILTER = "filter";
	private final String RESET = "reset";
	
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
		
		//Add to panel
		this.add(this.panelSearchFilters, BorderLayout.NORTH);
		this.add(Globals.COMPATIBILITY_SELECTION_PANEL, BorderLayout.CENTER);
	}
	private void initPanelSearchFilters()
	{
		//Initialization
		this.panelSearchFilters = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.filterModName = new FilterElement<ModLite>("Name", Methods.convertRegisteredModsToModLite());
		this.filterModAuthor = new FilterElement<String>("Author", Methods.getNamesOfAuthorsFromRegisteredMods());
		this.butFilter = new JButton("Filter");
		
		//Properties
		this.panelSearchFilters.setBorder(BorderFactory.createTitledBorder("Filter"));
		this.butFilter.addActionListener(this);
		
		//Add to panel
		this.panelSearchFilters.add(this.filterModName);
		this.panelSearchFilters.add(this.filterModAuthor);
	}
	private void initPanelBelow()
	{
		//Initialization
		this.panelSearchBelow = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.butFilter = new JButton("Filter");
		this.butReset = new JButton("Reset All");
		
		//Properties
		this.butReset.addActionListener(this);
		this.butReset.setActionCommand(this.RESET);
		this.butFilter.addActionListener(this);
		this.butFilter.setActionCommand(this.FILTER);
		
		//Add to panel
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
	
	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case RESET:
				this.resetDefaults();
				break;
				
			case FILTER:
				//Filter Function
				break;
		}
	}

	@Deprecated
	@Override
	public void refresh() 
	{
	}

	@Override
	public void resetDefaults() 
	{
		this.filterModAuthor.resetDefaults();
		this.filterModName.resetDefaults();
	}
}
