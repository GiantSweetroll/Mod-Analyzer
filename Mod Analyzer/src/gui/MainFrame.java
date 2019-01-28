package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import constants.Constants;
import constants.Globals;
import dataDrivers.Mod;
import gui.compatibilityPanel.CompatibilityDetailsPanel;
import gui.compatibilityPanel.CompatibilityModSelectionPanel;
import gui.compatibilityPanel.CompatibilitySelectionPanel;
import gui.overview.OverviewPanel;
import methods.FileOperation;
import methods.Methods;

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
	private JMenuItem newEntry, delEntry, editEntry, refreshModList, filterMenu;
	
	//Constants
	public static final String MOD_FORM = "modform",
								OVERVIEW = "overview";
	private final String NEW_ENTRY = "newEntry",
							DELETE_ENTRY = "delEntry",
							REFRESH = "refresh",
							REFRESH_MOD_LIST = "refresh_mod_list",
							FILTER = "filter",
							FILTER_MENU = "filter_meny",
							SETTINGS = "settings",
							EDIT_ENTRY = "editEntry",
							FILE = "file";
	//Constructor
	public MainFrame()
	{
		super("Mod Analyzer");
		//Initialization
		Globals.COMPATIBILITY_MOD_SELECTION_PANEL = new CompatibilityModSelectionPanel();
		Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL = new CompatibilityDetailsPanel();
		Globals.MOD_FORM_MOD_DETAILS_PANEL = new ModDetailsPanel();
		Globals.COMPATIBILITY_SELECTION_PANEL = new CompatibilitySelectionPanel();
		Globals.MOD_FORM_FILTER_PANEL = new FilterPanel(true, FilterPanel.MOD_FORM);
		Globals.MOD_FORM = new ModForm();
		Globals.OVERVIEW = new OverviewPanel();
		Globals.OVERVIEW_FILTER_PANEL = new FilterPanel(false, FilterPanel.OVERVIEW);
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
		this.filterMenu = new JMenuItem("Filter Settings");
		this.newEntry = new JMenuItem("New Entry", KeyEvent.VK_N);
		this.delEntry = new JMenuItem("Delete Entry");
		this.editEntry = new JMenuItem("Edit Entry");
		this.refreshModList = new JMenuItem("Refresh Mod List");
		
		//Properties
		this.mFile.setActionCommand(this.FILE);
		this.mFile.addMenuListener(this);
		this.mFile.setMnemonic(KeyEvent.VK_F);
		this.newEntry.setActionCommand(this.NEW_ENTRY);
		this.newEntry.addActionListener(this);
		this.newEntry.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		this.delEntry.setActionCommand(this.DELETE_ENTRY);
		this.delEntry.addActionListener(this);
		this.delEntry.setEnabled(false);
		this.delEntry.setMnemonic(KeyEvent.VK_D);
		this.delEntry.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.CTRL_MASK));
		this.editEntry.setActionCommand(this.EDIT_ENTRY);
		this.editEntry.addActionListener(this);
		this.editEntry.setEnabled(false);
		this.editEntry.setMnemonic(KeyEvent.VK_E);
		this.editEntry.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		this.mFilter.setActionCommand(this.FILTER);
//		this.mFilter.addMenuListener(this);
		this.mFilter.setMnemonic(KeyEvent.VK_I);
		this.mSettings.setActionCommand(this.SETTINGS);
		this.mSettings.addActionListener(this);
		this.mSettings.setMnemonic(KeyEvent.VK_S);
		this.refreshModList.setActionCommand(this.REFRESH_MOD_LIST);
		this.refreshModList.addActionListener(this);
		this.refreshModList.setMnemonic(KeyEvent.VK_R);
		this.refreshModList.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
		this.mRefresh.setActionCommand(this.REFRESH);
		this.mRefresh.setMnemonic(KeyEvent.VK_R);
//		this.mRefresh.addMenuListener(this);
		this.filterMenu.setActionCommand(this.FILTER_MENU);
		this.filterMenu.addActionListener(this);
		this.filterMenu.setMnemonic(KeyEvent.VK_F);
		this.filterMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
		
		//Merge File menu
		this.mFile.add(this.newEntry);
		this.mFile.add(this.editEntry);
		this.mFile.addSeparator();
		this.mFile.add(this.delEntry);
		
		//Add to Refresh menu
		this.mRefresh.add(this.refreshModList);
		
		//Add to Filter menu
		this.mFilter.add(this.filterMenu);
		
		//Add to menu bar
		this.menuBar.add(this.mFile);
		this.menuBar.add(this.mFilter);
		this.menuBar.add(this.mRefresh);
//		this.menuBar.add(this.mSettings);
	}
	
	//Public Methods
	public void changeActivePanel(String keyword)
	{
		this.cl.show(this.panel, keyword);
		this.menuBar.setVisible(keyword.equals(MainFrame.OVERVIEW));
	}
	public void setEditButtonEnabled(boolean b)
	{
		this.editEntry.setEnabled(b);
	}
	public void setDeleteButtonEnabled(boolean b)
	{
		this.delEntry.setEnabled(b);
	}
	public void refreshModList()
	{
		Methods.refreshModList();
		Globals.OVERVIEW.refresh();
		Globals.OVERVIEW.repaint();
		this.editEntry.setEnabled(false);
		this.delEntry.setEnabled(false);		
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
				Globals.MOD_FORM_FILTER_PANEL.applyFilter();
				this.changeActivePanel(MainFrame.MOD_FORM);
				break;
				
			case DELETE_ENTRY:
				Mod mod = Globals.OVERVIEW.getSelectedMod();
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete \"" + mod.getName() + "\" (ID: " + mod.getID() + ")?", "Confirm delete", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (response == JOptionPane.YES_OPTION)
				{
					FileOperation.deleteMod(mod);
					this.refreshModList();
					//Delete mod reference in other mod's compatibility list
					for (Mod m : Globals.MODS)
					{
						m.getCompatibilities().removeCompatibility(mod.getID());
					}
				}
				break;
				
			case EDIT_ENTRY:
				Globals.MOD_FORM.resetDefaults();
				Globals.MOD_FORM_FILTER_PANEL.applyFilter();
				try
				{
					Globals.MOD_FORM.setData(Globals.OVERVIEW.getSelectedMod());
					this.changeActivePanel(MainFrame.MOD_FORM);
				}
				catch(NullPointerException ex){}
				break;
				
			case REFRESH_MOD_LIST:
				this.refreshModList();
				break;
				
			case FILTER_MENU:
				JOptionPane.showMessageDialog(null, Globals.OVERVIEW_FILTER_PANEL, "Filter", JOptionPane.PLAIN_MESSAGE, null);
				Globals.OVERVIEW_FILTER_PANEL.applyFilter();
				break;
		}
	}

	@Override
	public void menuCanceled(MenuEvent arg0) {}

	@Override
	public void menuDeselected(MenuEvent arg0) {}

	@Override
	public void menuSelected(MenuEvent e)
	{
		JMenu menu = (JMenu)e.getSource();
		switch(menu.getActionCommand())
		{
			case FILE:
	//			this.editEntry.setEnabled(Globals.OVERVIEW.hasModHighlighted());
				break;

			case SETTINGS:
				break;
				
			case FILTER:
				break;
		}
		
	}
}
