package gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import constants.Constants;
import dataDrivers.Mod;
import giantsweetroll.gui.swing.SpringUtilities;
import giantsweetroll.gui.swing.TextAreaManager;
import interfaces.FormEssentials;

public class ModDetailsPanel extends JPanel implements FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3438270466642105159L;

	private JLabel labName, labAuthor, labVersion, labLink, labNotes, name, author, version, link;
	private JPanel panelCenter, panelNotes;
	private JTextArea taNotes;
	private JScrollPane scrollNotes;
	
	public ModDetailsPanel()
	{
		super(new BorderLayout());
		this.initGUI();
	}
	//Create GUI
	private void initGUI()
	{
		//Initialization
		this.initPanelCenter();
		this.initPanelNotes();
		
		//Add to panel
		this.add(this.panelCenter, BorderLayout.CENTER);
		this.add(this.panelNotes, BorderLayout.SOUTH);
	}
	private void initPanelCenter()
	{
		//Initialize GUI
		this.panelCenter = new JPanel(new SpringLayout());
		this.labName = new JLabel("Mod Name:");
		this.name = new JLabel();
		this.labAuthor = new JLabel("Author:");
		this.author = new JLabel();
		this.labVersion = new JLabel("Version:");
		this.version = new JLabel();
		this.labLink = new JLabel("Link:");
		this.link = new JLabel();
		
		//Add to panel
		this.panelCenter.add(labName);
		this.panelCenter.add(this.name);
		this.panelCenter.add(this.labAuthor);
		this.panelCenter.add(this.author);
		this.panelCenter.add(this.labVersion);
		this.panelCenter.add(this.version);
		this.panelCenter.add(this.labLink);
		this.panelCenter.add(this.link);
		
		SpringUtilities.makeCompactGrid(this.panelCenter, 2, 4, 0, 0, Constants.INSETS_BASE, Constants.INSETS_BASE);
	}
	private void initPanelNotes()
	{
		//Initialization
		this.panelNotes = new JPanel();
		this.labNotes = new JLabel("Notes:");
		this.taNotes = new JTextArea(15, 20);
		this.scrollNotes = new JScrollPane(this.taNotes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//Properties
		this.panelNotes.setLayout(new BoxLayout(this.panelNotes, BoxLayout.Y_AXIS));
		TextAreaManager.autoConfigureTextArea(this.taNotes, false);
		
		//Add to panel
		this.panelNotes.add(this.labNotes);
		this.panelNotes.add(this.scrollNotes);
	}

	//Public methods
	public void displayModDetails(Mod mod)
	{
		this.name.setText(mod.getName());
		this.author.setText(mod.getAuthor());
		this.version.setText(mod.getVersion());
		this.link.setText(mod.getLink());
		this.taNotes.setText(mod.getNotes());
	}
	
	//Interfaces
	@Deprecated
	@Override
	public void refresh()
	{}
	@Override
	public void resetDefaults() 
	{
		this.name.setText("");
		this.author.setText("");
		this.version.setText("");
		this.link.setText("");
		this.taNotes.setText("");
	}
}
