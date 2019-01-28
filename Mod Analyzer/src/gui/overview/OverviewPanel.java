package gui.overview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import constants.Constants;
import constants.Globals;
import dataDrivers.CompatibilityList;
import dataDrivers.Mod;
import giantsweetroll.gui.swing.ScrollPaneManager;
import gui.ButtonLabel;
import gui.ModDetailsPanel;
import interfaces.FormEssentials;

/**
 * @author user
 *
 */
public class OverviewPanel extends JPanel implements ActionListener, FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 915590567046127765L;
	
	private JPanel panelCompat,
					panelRightTop, 
					panelRight,
					panelModList;

	private CompatibleOverviewPanel compatible;
	private IncompatibilityOverviewPanel incompatible;
	private JScrollPane scrollCompatible, scrollModList, scrollModDetails;
	private JTabbedPane tabCompat;
	private ModDetailsPanel modDetails;
	private GeneralCompatibilityPanel generalCompat;
	private JLabel labMod;
	
	private List<ButtonLabel> buttons;
	private HashMap<String, Mod> modMap;
	
	//Constants
	private final Border SECTION_SEPARATOR = BorderFactory.createLineBorder(Constants.SECTION_BORDER_COLOR, 2);
	
	//Changing
	private int highlightedButtonIndex;
	
	public OverviewPanel()
	{
		this.init();
	}
	
	//Create GUI
	private void init()
	{
		//Initialization
		this.initPanelModList();
		this.initPanelRight();
		this.scrollModList = ScrollPaneManager.generateDefaultScrollPane(this.panelModList, 10, 10);
		this.highlightedButtonIndex = -1;
		
		//Properties
		this.setLayout(new BorderLayout());
		
		//Add to panel
		this.add(this.scrollModList, BorderLayout.WEST);
		this.add(this.panelRight, BorderLayout.CENTER);
	}
	private void initPanelCompat()
	{
		//Initialization
		this.panelCompat = new JPanel(new BorderLayout());
		this.compatible = new CompatibleOverviewPanel();
		this.incompatible = new IncompatibilityOverviewPanel();
		this.scrollCompatible = ScrollPaneManager.generateDefaultScrollPane(this.compatible, 10, 10);
//		this.scrollIncompatible = ScrollPaneManager.generateDefaultScrollPane(this.incompatible, 10, 10);
		this.tabCompat = new JTabbedPane();
		
		//Properties
		this.panelCompat.setBorder(this.SECTION_SEPARATOR);
		this.compatible.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//Add to tab
		this.tabCompat.addTab("Incompatible Mods", this.incompatible);
		this.tabCompat.addTab("Compatible Mods", this.scrollCompatible);
		
		//Add to panel
		this.panelCompat.add(this.tabCompat, BorderLayout.CENTER);
	}
	private void initPanelRightTop()
	{
		//Initialization
		this.panelRightTop = new JPanel();
		this.generalCompat = new GeneralCompatibilityPanel();
		this.modDetails = new ModDetailsPanel();
		this.scrollModDetails = ScrollPaneManager.generateDefaultScrollPane(this.modDetails, 10, 10);
		//			scrollGeneralCompat = ScrollPaneManager.generateDefaultScrollPane(this.generalCompat, 10, 10);
		
		//Properties
		this.panelRightTop.setLayout(new GridLayout(1, 2));
		this.modDetails.setBorder(this.SECTION_SEPARATOR);
		this.generalCompat.setBorder(this.SECTION_SEPARATOR);
//		this.scrollModDetails.getVerticalScrollBar().setUnitIncrement(10);
		
		//Add to panel
		this.panelRightTop.add(scrollModDetails);
//		this.panelRightTop.add(scrollGeneralCompat);
		this.panelRightTop.add(this.generalCompat);
	}
	private void initPanelRight()
	{
		//Initialization
		this.panelRight = new JPanel();
		this.initPanelCompat();
		this.initPanelRightTop();
		
		//Properties
		this.panelRight.setLayout(new GridLayout(0,1));
		
		//Add to panel
		this.panelRight.add(this.panelRightTop, BorderLayout.NORTH);
		this.panelRight.add(this.panelCompat, BorderLayout.CENTER);
	}
	private void initPanelModList()
	{
		//Initialization
		this.panelModList = new JPanel();
		this.labMod = new JLabel("Mods", SwingConstants.CENTER);
		this.modMap = new HashMap<String, Mod>();
		this.buttons = new ArrayList<ButtonLabel>();
	
		//Properties
		this.panelModList.setLayout(new BoxLayout(this.panelModList, BoxLayout.Y_AXIS));
		this.panelModList.setBorder(this.SECTION_SEPARATOR);
		this.labMod.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.labMod.getMaximumSize().height));
		this.labMod.setFont(Constants.GENERAL_FONT_BOLD);
		this.labMod.setBackground(new Color (67, 70, 75));
		this.labMod.setOpaque(true);
		this.labMod.setBorder(BorderFactory.createRaisedBevelBorder());
		
		//Add to panel
	//	this.panelModList.add(Box.createRigidArea(new Dimension(3, 3)));
		this.panelModList.add(this.labMod);
		this.panelModList.add(Box.createRigidArea(new Dimension(Constants.INSETS_BASE, Constants.INSETS_BASE)));
	}
	
	//Public Methods
	public void setData(Collection<Mod> mods)
	{
		this.resetDefaults();
		this.removeDisplayedButtonsFromPanel();
		this.modMap.clear();
		this.buttons.clear();
		this.highlightedButtonIndex = -1;
		//Set Mod Map
		for (Mod mod : mods)
		{
			this.modMap.put(mod.getID(), mod);
			
			//Create Buttons
			ButtonLabel b = new ButtonLabel(" " + mod.getName() + " ");
			b.setActionCommand(mod.getID());
			b.addActionListener(this);
//			b.setBorderPainted(true);
//			b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			b.setMaximumSize(new Dimension(Integer.MAX_VALUE, b.getMaximumSize().height));
			b.setHorizontalAlignment(SwingConstants.LEFT);
			b.setBackground(Constants.HIGHLIGHT_COLOR);
			this.buttons.add(b);
		}
		
		//Sort Buttons
		Collections.sort(this.buttons, Constants.MOD_NAME_BUTTON_COMPARATOR);
		
		//Add index identifier
		for (int i=0; i<this.buttons.size(); i++)
		{
			this.buttons.get(i).setName(Integer.toString(i));
		}
		
		//Display
		this.displayButtonsOnPanel();
	}
	public boolean hasModHighlighted()
	{
		return (this.highlightedButtonIndex!=-1);
	}
	public Mod getSelectedMod()
	{
		if (this.hasModHighlighted())
		{
			return this.modMap.get(this.buttons.get(this.highlightedButtonIndex).getActionCommand());
		}
		else
		{
			return null;
		}
	}
	//Private Methods
	private void displayButtonsOnPanel()
	{
		for(ButtonLabel b : this.buttons)
		{
			this.panelModList.add(b);
		}
		this.panelModList.revalidate();
		this.panelModList.repaint();
	}
	private void removeDisplayedButtonsFromPanel()
	{
		for (ButtonLabel b : this.buttons)
		{
			this.panelModList.remove(b);
		}
		this.panelModList.revalidate();
		this.panelModList.repaint();
	}
	private void display(Mod mod)
	{
		this.modDetails.displayModDetails(mod);
		this.generalCompat.setData(mod.getGeneralCompatibility());
		CompatibilityList compat = mod.getCompatibilities();
		this.compatible.display(compat.getListOfCompatibleMods());
		this.incompatible.resetDefaults();
		this.incompatible.setCompatibilities(compat.getListOfIncompatibleMods());
		this.modDetails.repaint();
		this.generalCompat.repaint();
		this.panelRight.repaint();
		this.scrollModDetails.getVerticalScrollBar().setValue(0);
		this.scrollModDetails.getHorizontalScrollBar().setValue(0);
		this.repaint();
	}
	
	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.generalCompat.resetDefaults();
		this.display(this.modMap.get(e.getActionCommand()));
		
		//Highlight
		if (this.highlightedButtonIndex!=-1)
		{
	//		this.buttons.get(this.highlightedButtonIndex).setForeground(Color.BLACK);
			this.buttons.get(this.highlightedButtonIndex).setOpaque(false);
		}
		this.highlightedButtonIndex = Integer.parseInt(((ButtonLabel)e.getSource()).getName());
		this.buttons.get(this.highlightedButtonIndex).setForeground(Color.WHITE);
		this.buttons.get(this.highlightedButtonIndex).setOpaque(true);
		Globals.MAIN_FRAME.setEditButtonEnabled(this.hasModHighlighted());
		Globals.MAIN_FRAME.setDeleteButtonEnabled(this.hasModHighlighted());
	}

	@Override
	public void refresh()
	{
		this.setData(Globals.MODS);
	}

	@Override
	public void resetDefaults()
	{
		this.modDetails.resetDefaults();
		this.generalCompat.resetDefaults();
		this.compatible.resetDefaults();
		this.incompatible.resetDefaults();
		this.repaint();
	}
}