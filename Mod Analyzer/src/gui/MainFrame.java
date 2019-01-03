package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import constants.Constants;
import constants.Globals;
import giantsweetroll.message.MessageManager;
import gui.compatibilityPanel.CompatibilityDetailsPanel;
import gui.compatibilityPanel.CompatibilityModSelectionPanel;
import gui.compatibilityPanel.CompatibilitySelectionPanel;
import gui.overview.OverviewPanel;

public class MainFrame extends JFrame implements ActionListener, MenuListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8026416994513756565L;

	private JPanel panel;
	private CardLayout cl;
	
	private JMenuBar menuBar;
	private JMenu mFile, mFilter, mSettings, mRefresh;
	private JMenuItem newEntry, delEntry, editEntry;
	
	//Constants
	public static final String MOD_FORM = "modform",
								OVERVIEW = "overview";
	private final String NEW_ENTRY = "newEntry",
							DELETE_ENTRY = "delEntry",
							REFRESH = "refresh",
							FILTER = "filter",
							SETTINGS = "settings",
							EDIT_ENTRY = "editEntry";
	
	public MainFrame()
	{
		super("Mod Analyzer");
		//Initialization
		Globals.COMPATIBILITY_MOD_SELECTION_PANEL = new CompatibilityModSelectionPanel();
		Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL = new CompatibilityDetailsPanel();
		Globals.MOD_FORM_MOD_DETAILS_PANEL = new ModDetailsPanel();
		Globals.COMPATIBILITY_SELECTION_PANEL = new CompatibilitySelectionPanel();
		Globals.MOD_FORM = new ModForm();
		Globals.OVERVIEW = new OverviewPanel();
		this.cl = new CardLayout();
		this.panel = new JPanel(cl);
//		Globals.OVERVIEW_MOD_DETAILS_PANEL = new ModDetailsPanel();
		this.initMenuBar();
		
		//Properties
		this.setSize(Constants.DEFAULT_FRAME_SIZE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(this.menuBar);
		
		//Add to panel
		this.panel.add(Globals.OVERVIEW, MainFrame.OVERVIEW);
		this.panel.add(Globals.MOD_FORM, MainFrame.MOD_FORM);
//		this.add(Globals.COMPATIBILITY_SELECTION_PANEL, "sdsd");
//		this.add(new ModForm(), "sdsd");
		
		//Add to frame
		this.add(panel);
	}
	
	//Create GUI
	private void initMenuBar()
	{
		//Initialization
		this.menuBar = new JMenuBar();
		this.mFile = new JMenu("File");
		this.mFilter = new JMenu("Filter");
		this.mSettings = new JMenu("Settings");
		this.mRefresh = new JMenu("Refresh");
		this.newEntry = new JMenuItem("New Entry");
		this.delEntry = new JMenuItem("Delete Entry");
		this.editEntry = new JMenuItem("Edit Entry");
		
		//Properties
		this.mFile.addMenuListener(this);
		this.newEntry.setActionCommand(this.NEW_ENTRY);
		this.newEntry.addActionListener(this);
		this.delEntry.setActionCommand(this.DELETE_ENTRY);
		this.delEntry.addActionListener(this);
		this.editEntry.setActionCommand(this.EDIT_ENTRY);
		this.editEntry.addActionListener(this);
		this.editEntry.setEnabled(false);
		this.mFilter.setActionCommand(this.FILTER);
		this.mFile.addActionListener(this);
		this.mSettings.setActionCommand(this.SETTINGS);
		this.mSettings.addActionListener(this);
		this.mRefresh.setActionCommand(this.REFRESH);
		this.mRefresh.addActionListener(this);
		
		//Merge File menu
		this.mFile.add(this.newEntry);
		this.mFile.add(this.editEntry);
		this.mFile.addSeparator();
		this.mFile.add(this.delEntry);
		
		//Add to menu bar
		this.menuBar.add(this.mFile);
		this.menuBar.add(this.mFilter);
		this.menuBar.add(this.mRefresh);
		this.menuBar.add(this.mSettings);
	}
	
	//Public Methods
	public void changeActivePanel(String keyword)
	{
		this.cl.show(this.panel, keyword);
		this.menuBar.setVisible(keyword.equals(MainFrame.OVERVIEW));
	}
	
	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case NEW_ENTRY:
				Globals.MOD_FORM.resetDefaults();
				Globals.MOD_FORM.setAsNewEntry();
				this.changeActivePanel(MainFrame.MOD_FORM);
				break;
				
			case DELETE_ENTRY:
				break;
				
			case EDIT_ENTRY:
				Globals.MOD_FORM.resetDefaults();
				try
				{
					Globals.MOD_FORM.setData(Globals.OVERVIEW.getSelectedMod());
					this.changeActivePanel(MainFrame.MOD_FORM);
				}
				catch(NullPointerException ex){}
				break;
				
			case SETTINGS:
				break;
				
			case FILTER:
				break;
				
			case REFRESH:
				break;
		}
	}

	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuSelected(MenuEvent arg0)
	{
		this.editEntry.setEnabled(Globals.OVERVIEW.hasModHighlighted());
	}
}
