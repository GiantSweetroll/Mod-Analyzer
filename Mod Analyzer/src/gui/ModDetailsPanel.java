package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import constants.Constants;
import dataDrivers.Mod;
import giantsweetroll.gui.swing.Gbm;
import interfaces.FormEssentials;
import methods.Methods;

public class ModDetailsPanel extends JPanel implements FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3438270466642105159L;

	private JLabel labID, 
					labName, 
					labAuthor,
					labVersion, 
					labLink, 
					labDateCreated, 
					labDateUpdated, 
					labNotes, 
					id,  
					author, 
					version, 
					dateCreated, 
					dateUpdated;
//					notes;
	private JPanel panelForm, panelNotes, panelCenter;
//	private JScrollPane scrollNotes;
	private WrappableJLabel notes;
	private TextFieldLabel name, link;
	
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
		this.setLayout(new BorderLayout(10, 10));
		
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
		this.name = new TextFieldLabel();
		this.labAuthor = new JLabel("Author:");
		this.author = new JLabel();
		this.labVersion = new JLabel("Version:");
		this.version = new JLabel();
		this.labDateCreated = new JLabel("Registered On:");
		this.dateCreated = new JLabel();
		this.labDateUpdated = new JLabel("Last Updated On:");
		this.dateUpdated = new JLabel();
		this.labLink = new JLabel("Link:");
		this.link = new TextFieldLabel();
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.labID.setFont(Constants.GENERAL_FONT_BOLD);
		this.labName.setFont(Constants.GENERAL_FONT_BOLD);
		this.labAuthor.setFont(Constants.GENERAL_FONT_BOLD);
		this.labVersion.setFont(Constants.GENERAL_FONT_BOLD);
		this.labLink.setFont(Constants.GENERAL_FONT_BOLD);
		this.labDateCreated.setFont(Constants.GENERAL_FONT_BOLD);
		this.labDateCreated.setToolTipText("DD/MM/YYYY");
		this.labDateUpdated.setFont(Constants.GENERAL_FONT_BOLD);
		this.labDateUpdated.setToolTipText("DD/MM/YYYY");

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
		c.gridheight = 2;
		this.panelForm.add(this.name, c);					//Name
		Gbm.newGridLine(c);
		Gbm.newGridLine(c);
		c.gridheight = 1;
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
		c.gridheight = 2;
		this.panelForm.add(this.link, c);					//Link
		Gbm.newGridLine(c);
		Gbm.newGridLine(c);
		c.gridheight = 1;
		this.panelForm.add(this.labDateCreated, c);			//Date Created label
		Gbm.nextGridColumn(c);
		this.panelForm.add(this.dateCreated, c);			//Date Created
		Gbm.newGridLine(c);
		this.panelForm.add(this.labDateUpdated, c);			//Date Last Updated Label
		Gbm.nextGridColumn(c);
		this.panelForm.add(this.dateUpdated, c);			//Date Last Updated
		
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
//		this.notes = new JLabel();
		this.notes = new WrappableJLabel();
//		this.scrollNotes = ScrollPaneManager.generateDefaultScrollPane(this.taNotes, 10, 10);
//		this.scrollNotes = ScrollPaneManager.generateDefaultScrollPane(this.notes, 10, 10);
		
		//Properties
		this.panelNotes.setLayout(new BorderLayout(Constants.INSETS_BASE, Constants.INSETS_BASE));
//		TextAreaManager.autoConfigureTextArea(this.taNotes, false);
//		this.scrollNotes.setBorder(null);
//		this.taNotes.setBorder(null);
		this.labNotes.setFont(Constants.GENERAL_FONT_BOLD);
		
		//Add to panel
		this.panelNotes.add(new JPanel(), BorderLayout.WEST);
		this.panelNotes.add(this.labNotes, BorderLayout.NORTH);
		this.panelNotes.add(this.notes, BorderLayout.CENTER);
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
		this.dateCreated.setText(Methods.getDateAsString(mod.getDateCreated()));
		this.dateUpdated.setText(Methods.getDateAsString(mod.getDateModified()));
		this.notes.setText(mod.getNotes());
		this.revalidate();
		this.repaint();
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
		this.dateCreated.setText("");
		this.dateUpdated.setText("");
		this.notes.setText("");
	}
}
