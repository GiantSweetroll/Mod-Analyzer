package gui.compatibilityPanel;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import dataDrivers.CompatibilityList;
import giantsweetroll.gui.swing.ScrollPaneManager;
import giantsweetroll.gui.swing.TextAreaManager;
import gui.ModDetailsPanel;
import interfaces.FormEssentials;

public class CompatibilitySelectionPanel extends JPanel implements FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6790681056293033085L;
	
	private JTabbedPane panelTab;
	private JPanel panelGeneralCompat, panelCompat;
	private JLabel labGeneralCompat;
	private JTextArea taGeneralCompat;
	private CompatibilityModSelectionPanel modSelection;
	private CompatibilityDetailsPanel compatDetailPanel;
	private ModDetailsPanel modDetails;
	
	//Constructor
	public CompatibilitySelectionPanel()
	{
		super(new BorderLayout());
		this.createGUI();
	}
	
	//Create GUI
	private void createGUI()
	{
		//Initialization
		this.initPanelCompat();
		this.initPanelGeneralCompat();
		this.panelTab = new JTabbedPane();
		
		//Properties
		this.panelTab.addTab("Mod Selection", this.panelCompat);
		this.panelTab.add("General", this.panelGeneralCompat);
		
		//Add to panel
		this.add(this.panelTab, BorderLayout.CENTER);
	}
	private void initPanelGeneralCompat()
	{
		//Initialization
		this.panelGeneralCompat = new JPanel();
		this.labGeneralCompat = new JLabel("General Compatibility");
		this.taGeneralCompat = new JTextArea(15, 20);
		JScrollPane scroll = new JScrollPane(this.taGeneralCompat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//Properties
		this.panelGeneralCompat.setLayout(new BoxLayout(this.panelGeneralCompat, BoxLayout.Y_AXIS));
		TextAreaManager.autoConfigureTextArea(this.taGeneralCompat, true);
		
		//Add to panel
		this.panelGeneralCompat.add(this.labGeneralCompat);
		this.panelGeneralCompat.add(scroll);
	}
	private void initPanelCompat()
	{
		//Initialization
		this.panelCompat = new JPanel();
		this.modDetails = new ModDetailsPanel();
		this.compatDetailPanel = new CompatibilityDetailsPanel();
		this.modSelection = new CompatibilityModSelectionPanel(this.modDetails, this.compatDetailPanel);
		JScrollPane scrollModSelection = ScrollPaneManager.generateDefaultScrollPane(this.modSelection, 10, 10),
					scrollModDetails = ScrollPaneManager.generateDefaultScrollPane(this.modDetails, 10, 10),
					scrollCompatDetails = ScrollPaneManager.generateDefaultScrollPane(this.compatDetailPanel, 10, 10);
		SpringLayout spr = new SpringLayout();
		
		//Properties
		this.panelCompat.setLayout(spr);
		this.modSelection.setModDetailsPanel(this.modDetails);
		this.modSelection.setCompatibilityDetailsPanel(this.compatDetailPanel);
		
		//Add to panel
		spr.putConstraint(SpringLayout.NORTH, scrollModSelection, 0, SpringLayout.NORTH, this.panelCompat);
		spr.putConstraint(SpringLayout.WEST, scrollModSelection, 0, SpringLayout.WEST, this.panelCompat);
		spr.putConstraint(SpringLayout.EAST, scrollModSelection, 0, SpringLayout.HORIZONTAL_CENTER, this.panelCompat);
		spr.putConstraint(SpringLayout.SOUTH, scrollModSelection, 0, SpringLayout.SOUTH, this.panelCompat);
		spr.putConstraint(SpringLayout.WEST, scrollModDetails, 0, SpringLayout.WEST, scrollModSelection);
		spr.putConstraint(SpringLayout.EAST, scrollModDetails, 0, SpringLayout.EAST, this.panelCompat);
		spr.putConstraint(SpringLayout.SOUTH, scrollModDetails, 0, SpringLayout.VERTICAL_CENTER, scrollModSelection);
		spr.putConstraint(SpringLayout.NORTH, scrollCompatDetails, 0, SpringLayout.SOUTH, scrollModDetails);
		spr.putConstraint(SpringLayout.WEST, scrollCompatDetails, 0, SpringLayout.EAST, scrollModSelection);
		spr.putConstraint(SpringLayout.EAST, scrollCompatDetails, 0, SpringLayout.EAST, this.panelCompat);
		spr.putConstraint(SpringLayout.SOUTH, scrollCompatDetails, 0, SpringLayout.SOUTH, this.panelCompat);
		this.panelCompat.add(scrollCompatDetails);
		this.panelCompat.add(scrollModDetails);
		this.panelCompat.add(scrollModSelection);
	}
	
	//Public methods
	public void setData(CompatibilityList compatList)
	{
		this.modSelection.setData(compatList);
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
