package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import constants.Constants;
import constants.Globals;
import dataDrivers.Compatibility;
import dataDrivers.CompatibilityList;
import dataDrivers.Mod;
import giantsweetroll.date.Date;
import giantsweetroll.gui.swing.Gbm;
import giantsweetroll.gui.swing.ScrollPaneManager;
import giantsweetroll.gui.swing.TextAreaManager;
import giantsweetroll.message.MessageManager;
import gui.compatibilityPanel.CompatibilityManagerPanel;
import interfaces.FormEssentials;
import methods.FileOperation;
import methods.Methods;

public class ModForm extends JPanel implements FormEssentials, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 265415808112194839L;
	
	private JLabel labModName, labAuthor, labVersion, labLink, labCompatibility, labNotes;
	private JTextField tfModName, tfAuthor, tfVersion, tfLink;
	private JTextArea taNotes;
	private JButton butCancel, butSave;
	private JPanel panelCenter, panelBelow;
	private JScrollPane scrollNotes, scrollCenter;
	private String activeID;
	private CompatibilityManagerPanel compatPanel;
	private Date dateCreated, dateModified;
	
	private boolean newEntry;
	
	private final String SAVE = "Save";
	private final String CANCEL = "Cancel";
	
	public ModForm()
	{
		this.init();
	}
	//Initialize GUI
	public void init()
	{
		//Initialization
		this.initPanelBelow();
		this.initPanelCenter();
		this.scrollCenter = ScrollPaneManager.generateDefaultScrollPane(this.panelCenter, 20, 20);
		this.activeID = Methods.generateModID(new Date());
		this.dateCreated = new Date();
		this.dateModified = new Date();
		this.newEntry = true;
		
		//Properties
		this.setLayout(new BorderLayout());
		
		//Add to panel
		this.add(this.scrollCenter, BorderLayout.CENTER);
		this.add(this.panelBelow, BorderLayout.SOUTH);
	}
	public void initPanelBelow()
	{
		//Initialization
		this.panelBelow = new JPanel();
		this.butCancel = new JButton(this.CANCEL);
		this.butSave = new JButton(this.SAVE);
		JPanel panelLeft = new JPanel();
		JPanel panelRight = new JPanel();
		
		//Properties
		this.panelBelow.setLayout(new BorderLayout());
		this.panelBelow.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.butCancel.addActionListener(this);
		this.butSave.addActionListener(this);
		panelLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelRight.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		//Add to panel
		panelLeft.add(this.butCancel);
		panelRight.add(this.butSave);
		this.panelBelow.add(panelLeft, BorderLayout.WEST);
		this.panelBelow.add(panelRight, BorderLayout.EAST);
	}
	public void initPanelCenter()
	{
		//Initialization
		this.panelCenter = new JPanel();
		this.labModName = new JLabel("Mod Name", SwingConstants.RIGHT);
		this.tfModName = new JTextField(25);
		this.labAuthor = new JLabel("Author", SwingConstants.RIGHT);
		this.tfAuthor = new JTextField(15);
		this.labVersion = new JLabel("Version", SwingConstants.RIGHT);
		this.tfVersion = new JTextField(10);
		this.labLink = new JLabel("Link", SwingConstants.RIGHT);
		this.tfLink = new JTextField(50);
		this.labCompatibility = new JLabel("Compatibility", SwingConstants.RIGHT);
		this.compatPanel = new CompatibilityManagerPanel();
		this.labNotes = new JLabel("Notes", SwingConstants.LEFT);
		this.taNotes = new JTextArea(15, 20);
		this.scrollNotes = new JScrollPane(this.taNotes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.panelCenter.setLayout(new GridBagLayout());
		TextAreaManager.autoConfigureTextArea(this.taNotes, true);
		
		//Add to panel
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_TOP_COMPONENT;
		c.fill = GridBagConstraints.BOTH;
		this.panelCenter.add(this.labModName, c);				//Mod Name
		c.gridwidth = 2;
		Gbm.nextGridColumn(c);
		this.panelCenter.add(this.tfModName, c);				//Mod Name Text Field
		Gbm.newGridLine(c);
		c.gridwidth = 1;
		this.panelCenter.add(this.labAuthor, c);				//Author
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		this.panelCenter.add(this.tfAuthor, c);					//Author text field
		Gbm.newGridLine(c);
		c.gridwidth = 1;
		this.panelCenter.add(this.labVersion, c);				//Version
		Gbm.nextGridColumn(c);
		this.panelCenter.add(this.tfVersion, c);				//Version Text Field
		Gbm.newGridLine(c);
		this.panelCenter.add(this.labLink, c);					//Link
		Gbm.nextGridColumn(c);
		c.gridwidth = 3;
		this.panelCenter.add(this.tfLink, c);
		Gbm.newGridLine(c);
		c.gridwidth = 1;
		this.panelCenter.add(this.labCompatibility, c);			//Compatibility
		Gbm.newGridLine(c);
		c.gridwidth = 4;
		this.panelCenter.add(this.compatPanel, c);				//Compatibility Manager Panel
		Gbm.newGridLine(c);
		c.gridwidth = 1;
		c.insets = Constants.INSETS_TIGHT;
		this.panelCenter.add(this.labNotes, c);					//Notes
		Gbm.newGridLine(c);
		c.gridwidth = 4;
		this.panelCenter.add(this.scrollNotes, c);					//Notes Text Area
	}
	//Private Methods
	/*
	private void prepareForExport()			//Operations to be done before exporting
	{
		//Final update to current active mod item
		if(Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModCheckBox().isSelected())
		{
			Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.updateCurrentCompatibility();
		}
		else
		{
			Globals.COMPATIBILITY_SELECTION_PANEL.removeCompatibility(Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModID());
		}
		//Update compatibility to the mods in the list
		CompatibilityList compat = Globals.COMPATIBILITY_SELECTION_PANEL.getCompatibilityList();
		HashMap<String, Mod> map = Methods.convertToMapByID(Globals.MODS);
		for (Map.Entry<String, Compatibility> entry : compat.getListOfModCompatibility().entrySet())
		{
			Mod mod = new Mod(map.get(entry.getKey()).getXMLDocument());
			mod.getCompatibilities().addCompatibility(entry.getValue());
			mod.getCompatibilities().getModCompatibility(entry.getKey()).setModID(this.activeID);
			FileOperation.saveModEntry(mod);
		}
	}		*/
	//Public methods
	public void setData(Mod mod)
	{
		this.newEntry = false;
		this.compatPanel.resetDefaults();
		this.tfModName.setText(mod.getName());
		this.tfAuthor.setText(mod.getName());
		this.tfVersion.setText(mod.getVersion());
		this.tfLink.setText(mod.getLink());
		this.taNotes.setText(mod.getNotes());
		this.activeID = mod.getID();
		this.compatPanel.setData(mod.getCompatibilities());
		this.activeID = mod.getID();
		this.dateCreated = mod.getDateCreated();
		this.dateModified = mod.getDateModified();
		//Remove same mod id from mod list
		Globals.COMPATIBILITY_MOD_SELECTION_PANEL.removeModFromList(mod.getID());
	}
	public Mod getData()
	{
		Mod mod = new Mod(this.tfModName.getText().trim());
		
		mod.setID(this.activeID);
		mod.setAuthor(this.tfAuthor.getText().trim());
		mod.setLink(this.tfLink.getText().trim());
		mod.setVersion(this.tfVersion.getText().trim());	
		mod.setGeneralCompatibility(this.compatPanel.getGeneralCompatibility());
		mod.setCompatibility(Globals.COMPATIBILITY_SELECTION_PANEL.getCompatibilityList());
		mod.setDateCreated(this.dateCreated);
		mod.setDateModified(this.dateModified);
		mod.setNotes(this.taNotes.getText().trim());
		
		return mod;
	}
	public boolean allRequiredFieldsFilled()
	{
		return !this.tfModName.getText().trim().equals("");
	}
	public void refreshModSelectionList()
	{
		Globals.COMPATIBILITY_MOD_SELECTION_PANEL.setMods(Globals.MODS);
	}
	public boolean isNewEntry()
	{
		return this.newEntry;
	}
	public void setAsNewEntry()
	{
		this.newEntry = true;
		this.activeID = Methods.generateModID(new Date());
	}
	
	//Interface
	@Override
	public void refresh() 
	{
		this.refreshModSelectionList();
	}
	@Override
	public void resetDefaults() 
	{
		this.tfModName.setText("");
		this.tfAuthor.setText("");
		this.tfVersion.setText("");
		this.tfLink.setText("");
		this.taNotes.setText("");
		this.activeID = "";
		this.compatPanel.resetDefaults();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case SAVE:
				if (this.allRequiredFieldsFilled())
				{
					//Final update to current active mod item
					if(Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModCheckBox().isSelected())
					{
						Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.updateCurrentCompatibility();
					}
					else
					{
						Globals.COMPATIBILITY_SELECTION_PANEL.removeCompatibility(Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModID());
					}					
//					this.prepareForExport();
					
					//Export Data
					if (this.newEntry)
					{
						FileOperation.saveModEntry(this.getData());
					}
					else
					{
						this.dateModified = new Date();
						FileOperation.saveModEntry(this.getData());
					}
					
					//Update compatibility to the mods in the list
					CompatibilityList compat = Globals.COMPATIBILITY_SELECTION_PANEL.getCompatibilityList();
					HashMap<String, Mod> map = Methods.convertToMapByID(Globals.MODS);
					for (Map.Entry<String, Compatibility> entry : compat.getListOfModCompatibility().entrySet())
					{
						Mod mod = new Mod(map.get(entry.getKey()).getXMLDocument());
						mod.getCompatibilities().addCompatibility(entry.getValue());
						mod.getCompatibilities().getModCompatibility(entry.getKey()).setModID(this.activeID);
						FileOperation.saveModEntry(mod);
					}
					
					Methods.refreshModList();
					Globals.OVERVIEW.setData(Globals.MODS);
					Globals.OVERVIEW.resetDefaults();
					Globals.MAIN_FRAME.changeActivePanel(MainFrame.OVERVIEW);
					this.compatPanel.refreshFilters();
					this.refreshModSelectionList();
				}
				else
				{
					MessageManager.showErrorDialog("Please enter mod name", "Cannot export");
				}
				break;
			
			case CANCEL:
				Globals.MAIN_FRAME.changeActivePanel(MainFrame.OVERVIEW);
				break;
		}
	}
}
