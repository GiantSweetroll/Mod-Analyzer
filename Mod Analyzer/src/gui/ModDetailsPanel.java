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

public class ModDetailsPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3438270466642105159L;

	private JLabel labName, labAuthor, labVersion, labLink, labNotes;
	private JPanel panelCenter, panelNotes;
	private JTextArea taNotes;
	private JScrollPane scrollNotes;
	
	public ModDetailsPanel(Mod mod)
	{
		super(new BorderLayout());
		this.initGUI(mod);
	}
	//Create GUI
	private void initGUI(Mod mod)
	{
		//Initialization
		this.initPanelCenter(mod);
		this.initPanelNotes(mod);
		
		//Add to panel
		this.add(this.panelCenter, BorderLayout.CENTER);
		this.add(this.panelNotes, BorderLayout.SOUTH);
	}
	private void initPanelCenter(Mod mod)
	{
		//Initialize GUI
		this.panelCenter = new JPanel(new SpringLayout());
		this.labName = new JLabel("Mod Name:");
		this.labAuthor = new JLabel("Author:");
		this.labVersion = new JLabel("Version:");
		this.labLink = new JLabel("Link:");
		
		//Add to panel
		this.panelCenter.add(labName);
		this.panelCenter.add(new JLabel(mod.getName()));
		this.panelCenter.add(this.labAuthor);
		this.panelCenter.add(new JLabel(mod.getAuthor()));
		this.panelCenter.add(this.labVersion);
		this.panelCenter.add(new JLabel(mod.getVersion()));
		this.panelCenter.add(this.labLink);
		this.panelCenter.add(new JLabel(mod.getLink()));
		
		SpringUtilities.makeCompactGrid(this.panelCenter, 2, 4, 0, 0, Constants.INSETS_BASE, Constants.INSETS_BASE);
	}
	private void initPanelNotes(Mod mod)
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
}
