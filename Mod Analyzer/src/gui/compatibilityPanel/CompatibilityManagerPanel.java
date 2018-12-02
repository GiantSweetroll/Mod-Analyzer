package gui.compatibilityPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import methods.Methods;

public class CompatibilityManagerPanel extends JPanel implements ActionListener
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
		this.panelSearch = new JPanel();
		this.labFilter = new JLabel("Filter");
		this.checkName = new JCheckBox("Name");
		this.checkAuthor = new JCheckBox();
		this.tfName = new JTextField(15);
		this.tfAuthor = new JTextField(25);
		this.comboAuthor = new JComboBox<String>(Methods.getNamesOfAuthorsFromRegisteredMods());
	}
	
	
	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
	}
}
