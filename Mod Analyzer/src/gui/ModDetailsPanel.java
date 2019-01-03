package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import constants.Constants;
import dataDrivers.Mod;
import giantsweetroll.gui.swing.Gbm;
import giantsweetroll.gui.swing.ScrollPaneManager;
import interfaces.FormEssentials;
import methods.Methods;

public class ModDetailsPanel extends JPanel implements FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3438270466642105159L;

	private JLabel labID, labName, labAuthor, labVersion, labLink, labNotes, id, name, author, version, link, notes;
	private JPanel panelForm, panelNotes, panelCenter;
	private JScrollPane scrollNotes;
	
	public ModDetailsPanel()
	{
		this.initGUI();
	}
	//Create GUI
	private void initGUI()
	{
		//Initialization
		this.initPanelCenter();
		this.initPanelNotes();
		
		//Properties
		this.setLayout(new BorderLayout());
		
		//Add to panel
		this.add(this.panelCenter, BorderLayout.CENTER);
		this.add(this.panelNotes, BorderLayout.SOUTH);
	}
	private void initPanelForm()
	{
		//Initialize GUI
//		this.panelCenter = new JPanel(new SpringLayout());
		this.panelForm = new JPanel(new GridBagLayout());
		this.labID = new JLabel ("Mod ID:", SwingConstants.LEFT);
		this.id = new JLabel();
		this.labName = new JLabel("Mod Name:", SwingConstants.LEFT);
		this.name = new JLabel();
		this.labAuthor = new JLabel("Author:");
		this.author = new JLabel();
		this.labVersion = new JLabel("Version:");
		this.version = new JLabel();
		this.labLink = new JLabel("Link:");
		this.link = new JLabel();
		GridBagConstraints c = new GridBagConstraints();

		//Add to panel
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_TOP_COMPONENT;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		this.panelForm.add(this.labID, c);					//ID Label
		Gbm.nextGridColumn(c);
		this.panelForm.add(this.id, c);						//ID
		Gbm.newGridLine(c);
		c.insets = Constants.INSETS_GENERAL;
		this.panelForm.add(this.labName, c);				//Name label
		Gbm.nextGridColumn(c);
		this.panelForm.add(this.name, c);					//Name
		Gbm.newGridLine(c);
		this.panelForm.add(this.labAuthor, c);				//Author label
		Gbm.nextGridColumn(c);
		this.panelForm.add(this.author, c);					//Author
		Gbm.newGridLine(c);
		this.panelForm.add(this.labVersion, c);				//Version label
		Gbm.nextGridColumn(c);
		this.panelForm.add(this.version, c);				//Version
		Gbm.newGridLine(c);
		this.panelForm.add(this.labLink, c);				//Link label
		Gbm.nextGridColumn(c);
		this.panelForm.add(this.link, c);					//Link
		
//		SpringUtilities.makeCompactGrid(this.panelCenter, 2, 4, 0, 0, Constants.INSETS_BASE, Constants.INSETS_BASE);
	}
	private void initPanelCenter()
	{
		//Initialization
		this.panelCenter = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.initPanelForm();
		
		//Add to panel
		this.panelCenter.add(this.panelForm);
	}
	/*
	private void initPanelCenter2()
	{
		//Initialize GUI
		this.panelForm = new JPanel(new SpringLayout());
		this.labName = new JLabel("Mod Name:", SwingConstants.LEFT);
		this.name = new JLabel();
		this.labAuthor = new JLabel("Author:");
		this.author = new JLabel();
		this.labVersion = new JLabel("Version:");
		this.version = new JLabel();
		this.labLink = new JLabel("Link:");
		this.link = new JLabel();
		this.labNotes = new JLabel("Notes:");
//		this.taNotes = new JTextArea(10, 8);
		this.scrollNotes = ScrollPaneManager.generateDefaultScrollPane(this.taNotes, 10, 10);
		
		//Properties
		TextAreaManager.autoConfigureTextArea(this.taNotes, false);
		
		//Add to panel
		this.panelForm.add(this.labName);				//Name label
		this.panelForm.add(this.name);				//Name
		this.panelForm.add(this.labAuthor);			//Author label
		this.panelForm.add(this.author);				//Author
		this.panelForm.add(this.labVersion);			//Version label
		this.panelForm.add(this.version);				//Version
		this.panelForm.add(this.labLink);				//Link label
		this.panelForm.add(this.link);				//Link
		this.panelForm.add(this.labNotes);			//Notes
		this.panelForm.add(this.taNotes);				//Notes Text Area
		
		SpringUtilities.makeCompactGrid(this.panelForm, 2, 4, 0, 0, Constants.INSETS_BASE, Constants.INSETS_BASE);		
	}
	private void initPanelCenter3()
	{
		//Initialize GUI
		this.panelForm = new JPanel();
		this.labName = new JLabel("Mod Name:", SwingConstants.LEFT);
		this.name = new JLabel();
		this.labAuthor = new JLabel("Author:");
		this.author = new JLabel();
		this.labVersion = new JLabel("Version:");
		this.version = new JLabel();
		this.labLink = new JLabel("Link:");
		this.link = new JLabel();
		this.labNotes = new JLabel("Notes:");
	//	this.taNotes = new JTextArea(10, 8);
		this.scrollNotes = ScrollPaneManager.generateDefaultScrollPane(this.taNotes, 10, 10);
		SpringLayout spr = new SpringLayout();
		
		//Properties
		this.panelForm.setLayout(spr);
		TextAreaManager.autoConfigureTextArea(this.taNotes, false);
		
		//SpringLayout Constraints
		
		//Add to panel
		this.panelForm.add(this.labName);				//Name label
		this.panelForm.add(this.name);				//Name
		this.panelForm.add(this.labAuthor);			//Author label
		this.panelForm.add(this.author);				//Author
		this.panelForm.add(this.labVersion);			//Version label
		this.panelForm.add(this.version);				//Version
		this.panelForm.add(this.labLink);				//Link label
		this.panelForm.add(this.link);				//Link
		this.panelForm.add(this.labNotes);			//Notes
		this.panelForm.add(this.taNotes);				//Notes Text Area
		
//		SpringUtilities.makeCompactGrid(this.panelCenter, 2, 4, 0, 0, Constants.INSETS_BASE, Constants.INSETS_BASE);		
	}	*/
	private void initPanelNotes()
	{
		//Initialization
		this.panelNotes = new JPanel();
		this.labNotes = new JLabel("   Notes:");
//		this.taNotes = new WrappableJLabel(8, 20);
		this.notes = new JLabel();
//		this.scrollNotes = ScrollPaneManager.generateDefaultScrollPane(this.taNotes, 10, 10);
		this.scrollNotes = ScrollPaneManager.generateDefaultScrollPane(this.notes, 10, 10);
		
		//Properties
		this.panelNotes.setLayout(new BorderLayout(Constants.INSETS_BASE, Constants.INSETS_BASE));
//		TextAreaManager.autoConfigureTextArea(this.taNotes, false);
		this.scrollNotes.setBorder(null);
//		this.taNotes.setBorder(null);
		
		//Add to panel
		this.panelNotes.add(new JPanel(), BorderLayout.WEST);
		this.panelNotes.add(this.labNotes, BorderLayout.NORTH);
		this.panelNotes.add(this.scrollNotes, BorderLayout.CENTER);
		this.panelNotes.add(new JPanel(), BorderLayout.EAST);
	}

	//Public methods
	public void displayModDetails(Mod mod)
	{
		this.id.setText(mod.getID());
		this.name.setText(mod.getName());
		this.author.setText(mod.getAuthor());
		this.version.setText(mod.getVersion());
		this.link.setText(mod.getLink());
		this.notes.setText(Methods.getWrappableText(mod.getNotes()));
	}
	
	//Interfaces
	@Deprecated
	@Override
	public void refresh()
	{}
	@Override
	public void resetDefaults() 
	{
		this.id.setText("");
		this.name.setText("");
		this.author.setText("");
		this.version.setText("");
		this.link.setText("");
		this.notes.setText("");
	}
}
