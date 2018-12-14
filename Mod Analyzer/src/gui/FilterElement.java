package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.Constants;
import giantsweetroll.gui.swing.Gbm;

public class FilterElement<T> extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7880129475851965275L;

	private JCheckBox check;
	private JTextField tf;
	private JComboBox<T> combo;
	private T[] elements;
	
	//Constructor
	public FilterElement(String text)
	{
		this.init(text);
	}
	public FilterElement(String text, T[] selections)
	{
		this.init(text);
		this.setSelection(selections);
	}
	
	//Method
	//Create GUI
	private void init(String text)
	{
		//Initialization
		this.check = new JCheckBox(text);
		this.combo = new JComboBox<T>();
		this.tf = new JTextField(10);
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.setLayout(new GridBagLayout());
		
		//Add to panel
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_GENERAL;
		c.fill = GridBagConstraints.BOTH;
		this.add(this.check, c);				//Check box
		Gbm.nextGridColumn(c);
		this.add(this.combo, c);				//JComboBox
		Gbm.nextGridColumn(c);
		this.add(this.tf, c);					//Text Field
	}
	//Public Methods
	public void setSelection(T[] elements)
	{
		this.elements = elements;
		this.combo.setModel(new DefaultComboBoxModel<T>(elements));
	}
}
