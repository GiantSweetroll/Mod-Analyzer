package gui;

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

import constants.Constants;
import giantsweetroll.gui.swing.Gbm;
import giantsweetroll.gui.swing.TextAreaManager;
import interfaces.FormEssentials;

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
	private JScrollPane scrollNotes;
	
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
		
		//Properties
		this.setLayout(new BorderLayout());
		
		//Add to panel
		this.add(this.panelCenter, BorderLayout.CENTER);
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
		//Placeholder to place Compatibility panel
		Gbm.newGridLine(c);
		c.gridwidth = 1;
		c.insets = Constants.INSETS_TIGHT;
		this.panelCenter.add(this.labNotes, c);					//Notes
		Gbm.newGridLine(c);
		c.gridwidth = 4;
		this.panelCenter.add(this.scrollNotes, c);					//Notes Text Area
	}
	
	//Interface
	@Override
	public void refresh() 
	{
		// TODO Auto-generated method stub	
	}
	@Override
	public void resetDefaults() 
	{
		this.tfModName.setText("");
		this.tfAuthor.setText("");
		this.tfVersion.setText("");
		this.tfLink.setText("");
		this.taNotes.setText("");
		//Placeholder to call resetDefaults of compatibility panel
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
		case SAVE:
			break;
		}
	}
}
