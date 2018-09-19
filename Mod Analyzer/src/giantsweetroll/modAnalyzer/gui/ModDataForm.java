package giantsweetroll.modAnalyzer.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import giantsweetroll.gui.swing.Gbm;
import giantsweetroll.gui.swing.ScrollPaneManager;
import giantsweetroll.gui.swing.TextAreaManager;
import giantsweetroll.modAnalyzer.constants.Constants;

public class ModDataForm extends JPanel implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6439582578057312098L;

	private JPanel panelCenter, panelBelow;
	private JLabel labName, labAuthor, labVersion, labLink, labCompatibility, labNotes, labGame;
	private JTextField tfName, tfAuthor, tfVersion, tfLink;
	private JTextArea taNotes;
	private JButton butCancel, butDelete, butSave;
	private JScrollPane scrollCenter;
	
	private boolean isNewEntry;
	
	//Constants
	private final String CANCEL = "cancel",
							DELETE = "delete",
							SAVE = "save";
	
	//Constructors
	public ModDataForm()
	{
		//Initialize
		this.initPanelBelow();
		this.initPanelCenter();
		ScrollPaneManager.generateDefaultScrollPane(this.panelCenter, 10, 10);
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setAsNewEntry(true);
		
		//Add to panel
		this.add(this.panelBelow, BorderLayout.SOUTH);
		this.add(this.scrollCenter, BorderLayout.CENTER);
	}
	//Initialize GUIs
	private void initPanelBelow()
	{
		//Initialization
		this.panelBelow = new JPanel();
		this.butCancel = new JButton("Cancel");
		this.butDelete = new JButton("Delete");
		this.butSave = new JButton("Save");
		
		//Properties
		this.panelBelow.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.panelBelow.setOpaque(false);
		this.butCancel.setActionCommand(this.CANCEL);
		this.butCancel.addActionListener(this);
		this.butDelete.setActionCommand(this.DELETE);
		this.butDelete.addActionListener(this);
		this.butSave.setActionCommand(this.SAVE);
		this.butSave.addActionListener(this);
		
		//Add to panel
		this.panelBelow.add(this.butCancel);
		this.panelBelow.add(this.butDelete);
		this.panelBelow.add(this.butSave);
	}
	private void initPanelCenter()
	{
		//Initialization
		this.panelCenter = new JPanel();
		this.labName = new JLabel ("Mod Name", SwingConstants.RIGHT);
		this.tfName = new JTextField(30);
		this.labAuthor = new JLabel("Author", SwingConstants.RIGHT);
		this.tfAuthor = new JTextField(20);
		this.labVersion = new JLabel("Version", SwingConstants.RIGHT);
		this.tfVersion = new JTextField(5);
		this.labLink = new JLabel("Link", SwingConstants.RIGHT);
		this.tfLink = new JTextField(50);
		this.labCompatibility = new JLabel ("Compatibility");
		this.labNotes = new JLabel("Notes");
		this.taNotes = new JTextArea(15, 30);
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.panelCenter.setLayout(new GridBagLayout());
		this.panelCenter.setOpaque(false);
		this.tfName.setHorizontalAlignment(SwingConstants.CENTER);
		this.tfAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		this.tfVersion.setHorizontalAlignment(SwingConstants.CENTER);
		TextAreaManager.autoConfigureTextArea(this.taNotes, true);
		
		//Add to panel
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_TOP_COMPONENT;
		c.fill = GridBagConstraints.BOTH;
		this.panelCenter.add(this.labName, c);
		c.gridwidth = 2;
		Gbm.nextGridColumn(c);
		this.panelCenter.add(this.tfName, c);
		Gbm.newGridLine(c);
		c.gridwidth = 1;
		c.insets = Constants.INSETS_GENERAL;
		this.panelCenter.add(this.labAuthor, c);
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		this.panelCenter.add(this.tfAuthor, c);
		c.gridwidth = 1;
		Gbm.newGridLine(c);
		this.panelCenter.add(this.labVersion, c);
		Gbm.nextGridColumn(c);
		this.panelCenter.add(this.tfVersion, c);
		Gbm.newGridLine(c);
		this.panelCenter.add(this.labLink, c);
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		this.panelCenter.add(this.tfLink, c);
		Gbm.newGridLine(c);
		c.gridwidth = 3;
		this.panelCenter.add(this.labCompatibility, c);
		Gbm.newGridLine(c);
		this.panelCenter.add(this.labNotes, c);
		Gbm.newGridLine(c);
		this.panelCenter.add(this.taNotes, c);
	}
	
	//Public Methods
	public void setAsNewEntry(boolean newEntry)
	{
		this.isNewEntry = newEntry;
		
		if (newEntry)
		{
			this.butDelete.setEnabled(false);
		}
		else
		{
			this.butDelete.setEnabled(true);
		}
	}
	public boolean isNewEntry()
	{
		return this.isNewEntry;
	}
	
	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case CANCEL:
				break;
				
			case DELETE:
				break;
				
			case SAVE:
				break;
		}
	}
}