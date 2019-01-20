package gui.compatibilityPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import constants.Globals;
import dataDrivers.Compatibility;
import dataDrivers.CompatibilityList;
import giantsweetroll.gui.swing.ScrollPaneManager;
import giantsweetroll.gui.swing.TextAreaManager;
import interfaces.FormEssentials;

public class CompatibilitySelectionPanel extends JPanel implements FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6790681056293033085L;
	
	private JTabbedPane panelTab;
	private JPanel panelGeneralCompat, panelCompat;		//, panelCompatRight;
//	private JLabel labGeneralCompat;
	private JTextArea taGeneralCompat;
	private CompatibilityList compatList;
	
	//Constructor
	public CompatibilitySelectionPanel()
	{
		super();
		this.createGUI();
		this.compatList = new CompatibilityList();
	}
	
	//Create GUI
	private void createGUI()
	{
		//Initialization
		this.initPanelCompat();
		this.initPanelGeneralCompat();
		this.panelTab = new JTabbedPane();
		
		//Properties
		this.setLayout(new BorderLayout());
		
		//Properties
		this.panelTab.addTab("Mod Selection", this.panelCompat);
		this.panelTab.addTab("General Compatibility", this.panelGeneralCompat);
		
		//Add to panel
		this.add(this.panelTab, BorderLayout.CENTER);
	}
	private void initPanelGeneralCompat()
	{
		//Initialization
		this.panelGeneralCompat = new JPanel();
//		this.labGeneralCompat = new JLabel("General Compatibility");
		this.taGeneralCompat = new JTextArea(15, 10);
		JScrollPane scroll = ScrollPaneManager.generateDefaultScrollPane(this.taGeneralCompat, 10, 10);
		
		//Properties
		this.panelGeneralCompat.setLayout(new BorderLayout());
		TextAreaManager.autoConfigureTextArea(this.taGeneralCompat, true);
		
		//Add to panel
//		this.panelGeneralCompat.add(this.labGeneralCompat);
		this.panelGeneralCompat.add(scroll, BorderLayout.CENTER);
	}
	private void initPanelCompat()
	{
		//Initialization
	//	this.panelCompat = new JPanel(new GridLayout(0, 2));
	//	this.panelCompat = new JPanel(new BorderLayout());
		this.panelCompat = new JPanel();
	//	JScrollPane scrollModSelection = ScrollPaneManager.generateDefaultScrollPane(Globals.COMPATIBILITY_MOD_SELECTION_PANEL, 10, 10);
//		JScrollPane scrollModSelection = new JScrollPane(Globals.COMPATIBILITY_MOD_SELECTION_PANEL, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	//	SpringLayout spr = new SpringLayout();
//		this.initPanelCompatRight();
		
		//Properties
		this.panelCompat.setLayout(new BorderLayout());
//		this.panelCompat.setLayout(new BoxLayout(this.panelCompat, BoxLayout.X_AXIS));
//		Globals.COMPATIBILITY_MOD_SELECTION_PANEL.setMaximumSize(new Dimension(Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getMaximumSize().width, Integer.MAX_VALUE));
//		scrollModSelection.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Globals.COMPATIBILITY_MOD_SELECTION_PANEL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.setBorder(BorderFactory.createLineBorder(Color.black));
//		scrollModSelection.setMaximumSize(new Dimension(200, 500));
//		this.panelCompatRight.setMaximumSize(new Dimension(this.panelCompatRight.getMaximumSize().width, scrollModSelection.getMaximumSize().height));
//		this.panelCompat.setMaximumSize(new Dimension(this.panelCompat.getMaximumSize().width, scrollModSelection.getMaximumSize().height));
//		this.panelCompat.setLayout(spr);
		
		//Add to panel
		/*
	//	spr.putConstraint(SpringLayout.NORTH, scrollModSelection, 0, SpringLayout.NORTH, this.panelCompat);
	//	spr.putConstraint(SpringLayout.WEST, scrollModSelection, 0, SpringLayout.WEST, this.panelCompat);
		spr.putConstraint(SpringLayout.EAST, scrollModSelection, 0, SpringLayout.HORIZONTAL_CENTER, this.panelCompat);
		spr.putConstraint(SpringLayout.SOUTH, scrollModSelection, 0, SpringLayout.SOUTH, this.panelCompat);
		spr.putConstraint(SpringLayout.WEST, scrollModDetails, 0, SpringLayout.EAST, scrollModSelection);
		spr.putConstraint(SpringLayout.EAST, scrollModDetails, 0, SpringLayout.EAST, this.panelCompat);
		spr.putConstraint(SpringLayout.SOUTH, scrollModDetails, 0, SpringLayout.VERTICAL_CENTER, scrollModSelection);
		spr.putConstraint(SpringLayout.NORTH, scrollCompatDetails, 0, SpringLayout.SOUTH, scrollModDetails);
		spr.putConstraint(SpringLayout.WEST, scrollCompatDetails, 0, SpringLayout.EAST, scrollModSelection);
		spr.putConstraint(SpringLayout.EAST, scrollCompatDetails, 0, SpringLayout.EAST, this.panelCompat);
		spr.putConstraint(SpringLayout.SOUTH, scrollCompatDetails, 0, SpringLayout.SOUTH, this.panelCompat);
		this.panelCompat.add(scrollCompatDetails);
		this.panelCompat.add(scrollModDetails);
		this.panelCompat.add(scrollModSelection);		*/
//		this.panelCompat.add(scrollModSelection);
		this.panelCompat.add(Globals.COMPATIBILITY_MOD_SELECTION_PANEL, BorderLayout.WEST);
		this.panelCompat.add(Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL, BorderLayout.EAST);
	}
	/*
	private void initPanelCompatRight()
	{
		//Initialization
		this.panelCompatRight = new JPanel();
	//	JScrollPane scrollModDetails = ScrollPaneManager.generateDefaultScrollPane(Globals.MOD_FORM_MOD_DETAILS_PANEL, 10, 10),
		JScrollPane			scrollCompatDetails = ScrollPaneManager.generateDefaultScrollPane(Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL, 10, 10);
		
		//Properties
		this.panelCompatRight.setLayout(new BoxLayout(this.panelCompatRight, BoxLayout.Y_AXIS));
//		scrollModDetails.setMaximumSize(new Dimension (scrollModDetails.getMaximumSize().width, 150));
//		scrollModDetails.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scrollCompatDetails.setMaximumSize(new Dimension(scrollCompatDetails.getMaximumSize().width, 300));
		scrollCompatDetails.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//Add to panel
//		this.panelCompatRight.add(scrollModDetails);
		this.panelCompatRight.add(scrollCompatDetails);
	}		*/
	
	//Public methods
	public void setData(CompatibilityList compatList)
	{
		this.compatList = compatList;
		Globals.COMPATIBILITY_MOD_SELECTION_PANEL.setData(compatList);
	}
	public CompatibilityList getCompatibilityList()
	{
		return this.compatList;
	}
	public void updateCompatibility(Compatibility compat)
	{
		this.compatList.addCompatibility(compat);
	}
	public void removeCompatibility(String modID)
	{
		this.compatList.removeCompatibility(modID);
	}
	public Compatibility getCompatibility(String modID)
	{
		return this.compatList.getModCompatibility(modID);
	}
	public void setGeneralCompatibility(String compat)
	{
		this.taGeneralCompat.setText(compat);
	}
	public String getGeneralCompatibility()
	{
		return this.taGeneralCompat.getText().trim();
	}
	
	//Interfaces
	@Deprecated
	@Override
	public void refresh() {}

	@Override
	public void resetDefaults()
	{
		this.taGeneralCompat.setText("");
	}
}
