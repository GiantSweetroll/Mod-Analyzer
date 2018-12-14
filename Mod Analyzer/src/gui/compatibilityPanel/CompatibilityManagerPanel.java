package gui.compatibilityPanel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.Constants;
import giantsweetroll.gui.swing.Gbm;
import interfaces.FormEssentials;
import methods.Methods;

public class CompatibilityManagerPanel extends JPanel implements ActionListener, FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8062423562858065850L;
	
	private JPanel panelSearch;
	private JLabel labFilter;
	private JCheckBox checkName, checkAuthor;
	private JTextField tfName, tfAuthor;
	private JButton butFilter;
	private JComboBox<String> comboName, comboAuthor;
	private CompatibilitySelectionPanel compatSelection;
	
	//Constructor
	public CompatibilityManagerPanel()
	{
		this.createGUI();
	}
	
	//Initialize GUI
	private void createGUI()
	{
		//Initialization
		this.initPanelSearch();
		this.compatSelection = new CompatibilitySelectionPanel();
		
		//Properties
		this.setLayout(new BorderLayout());
		
		//Add to panel
		this.add(this.panelSearch, BorderLayout.NORTH);
		this.add(this.compatSelection, BorderLayout.CENTER);
	}
	private void initPanelSearch()
	{
		//Initialization
		this.panelSearch = new JPanel(new GridBagLayout());
		this.labFilter = new JLabel("Filter");
		this.checkName = new JCheckBox("Name");
		this.checkAuthor = new JCheckBox();
		this.tfName = new JTextField(15);
		this.tfAuthor = new JTextField(25);
		this.comboAuthor = new JComboBox<String>(Methods.getNamesOfAuthorsFromRegisteredMods());
		this.butFilter = new JButton("Filter");
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.butFilter.addActionListener(this);
		
		//Add to panel
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_TOP_COMPONENT;
		c.gridwidth = 100;
		this.panelSearch.add(this.labFilter, c);				//Filter label
		Gbm.newGridLine(c);
		c.fill = GridBagConstraints.BOTH;
		c.insets = Constants.INSETS_GENERAL;
		c.gridwidth = 1;
		this.panelSearch.add(this.checkName, c);				//Name check box
		Gbm.nextGridColumn(c);
		this.panelSearch.add(this.comboName, c);				//Name Combo box
		Gbm.nextGridColumn(c);
		this.panelSearch.add(this.tfName, c);					//Name text field
	}
	
	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
	}

	@Deprecated
	@Override
	public void refresh() 
	{
	}

	@Override
	public void resetDefaults() 
	{
	}
}
