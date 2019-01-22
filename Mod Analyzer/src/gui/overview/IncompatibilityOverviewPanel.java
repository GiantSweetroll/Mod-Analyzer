package gui.overview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import constants.Constants;
import constants.Globals;
import dataDrivers.Compatibility;
import dataDrivers.Mod;
import giantsweetroll.gui.swing.ScrollPaneManager;
import gui.ButtonLabel;
import interfaces.FormEssentials;
import methods.Methods;

public class IncompatibilityOverviewPanel extends JPanel implements ActionListener, FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7643177698384788375L;
	
	private JPanel panelLeft;
	private IncompatibilityDetailPanel details;
	private JScrollPane scrollMods, scrollDetails;
	
	private HashMap<String, Compatibility> compat;
	private List<ButtonLabel> buttons;
	
	//Constants
	private final Border SECTION_SEPARATOR = BorderFactory.createLineBorder(Constants.SECTION_BORDER_COLOR, 1);	
	
	//Changing
	private int highlightedButtonIndex;	
	
	public IncompatibilityOverviewPanel()
	{
		super();
		this.init();
	}
	
	//Create GUI
	private void init()
	{
		//Initialization
		this.initPanelLeft();
		this.details = new IncompatibilityDetailPanel();
		this.compat = new HashMap<String, Compatibility>();
		this.scrollMods = ScrollPaneManager.generateDefaultScrollPane(this.panelLeft, 10, 10);
		this.scrollDetails = ScrollPaneManager.generateDefaultScrollPane(this.details, 10, 10);
		
		//Properties
//		this.setLayout(new BorderLayout());
		this.setLayout(new GridLayout(1, 0));
//		this.panelLeft.setBorder(this.SECTION_SEPARATOR);
//		this.details.setBorder(this.SECTION_SEPARATOR);
		
		//Add to panel
		this.add(this.scrollMods);
		this.add(this.scrollDetails);
	}
	private void initPanelLeft()
	{
		//Initialization
		this.panelLeft = new JPanel();
		this.buttons = new ArrayList<ButtonLabel>();
		
		//Properties
		this.panelLeft.setLayout(new BoxLayout(this.panelLeft, BoxLayout.Y_AXIS));
	}
	
	//Public Methods
	public void setCompatibilities(List<Compatibility> list)
	{
		this.clearButtonsOnPanel();
		this.compat.clear();
		this.buttons.clear();
		this.highlightedButtonIndex = -1;
		
		if (list.size() > 0)
		{
			//Create buttons
			HashMap<String, Mod> map = Methods.convertToMapByID(Globals.MODS);
			for (Compatibility compat : list)
			{
				ButtonLabel b = new ButtonLabel(" " + map.get(compat.getModID()).getName() + " ");
				b.setActionCommand(compat.getModID());
				b.addActionListener(this);
			//	b.setBorderPainted(true);
			//	b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				b.setMaximumSize(new Dimension(Integer.MAX_VALUE, b.getMaximumSize().height));
				b.setHorizontalAlignment(SwingConstants.LEFT);
				b.setBackground(Constants.HIGHLIGHT_COLOR);			
				this.buttons.add(b);
				
				this.compat.put(compat.getModID(), compat);
			}
			
			//Sort Buttons
			Collections.sort(this.buttons, Constants.MOD_NAME_BUTTON_COMPARATOR);
			
			//Add index identifier
			for (int i=0; i<this.buttons.size(); i++)
			{
				this.buttons.get(i).setName(Integer.toString(i));
			}		
			
			//Display buttons
			this.displayButtonsOnPanel();			
			this.panelLeft.setBorder(this.SECTION_SEPARATOR);
			this.details.setBorder(this.SECTION_SEPARATOR);
		}
	}
	
	//Private methods
	private void displayButtonsOnPanel()
	{
		for (ButtonLabel button : this.buttons)
		{
			this.panelLeft.add(button);
		}
		this.panelLeft.revalidate();
		this.panelLeft.repaint();
	}
	private void clearButtonsOnPanel()
	{
		for (ButtonLabel button : this.buttons)
		{
			this.panelLeft.remove(button);
		}
		this.panelLeft.setBorder(null);
		this.details.setBorder(null);
		this.panelLeft.revalidate();
		this.panelLeft.repaint();
	}
	
	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.details.display(this.compat.get(e.getActionCommand()));
		
		//Highlight
		if (this.highlightedButtonIndex!=-1)
		{
			this.buttons.get(this.highlightedButtonIndex).setForeground(Color.BLACK);
			this.buttons.get(this.highlightedButtonIndex).setOpaque(false);
		}
		this.highlightedButtonIndex = Integer.parseInt(((ButtonLabel)e.getSource()).getName());
		this.buttons.get(this.highlightedButtonIndex).setForeground(Color.WHITE);
		this.buttons.get(this.highlightedButtonIndex).setOpaque(true);		
	}

	@Deprecated
	@Override
	public void refresh() {}

	@Override
	public void resetDefaults() 
	{
		this.details.resetDefaults();
		this.clearButtonsOnPanel();
		this.compat.clear();
		this.buttons.clear();
	}
}
