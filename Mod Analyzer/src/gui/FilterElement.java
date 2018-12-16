package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.Constants;
import giantsweetroll.gui.swing.Gbm;
import interfaces.FormEssentials;

public class FilterElement<T> extends JPanel implements FormEssentials, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7880129475851965275L;

	private JCheckBox check;
	private JTextField keyword;
	private JComboBox<T> combo;
	private T[] elements;
	private JButton butReset;
	
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
		this.keyword = new JTextField(10);
		this.butReset = new JButton("Reset");
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
		this.add(this.keyword, c);				//Text Field
		Gbm.nextGridColumn(c);
		this.add(this.butReset, c);				//Reset Button
	}
	//Public Methods
	public T[] getItems()
	{
		return this.elements;
	}
	public void setSelection(T[] elements)
	{
		this.elements = elements;
		this.combo.setModel(new DefaultComboBoxModel<T>(elements));
	}
	public T getFilterSelection()
	{
		return this.combo.getItemAt(this.combo.getSelectedIndex());
	}
	public String getFilterKeyword()
	{
		return this.keyword.getText();
	}
	public boolean isSelected()
	{
		return this.check.isSelected();
	}
	public void refresh(T[] elements)
	{
		this.setSelection(elements);
	}
	
	//Interfaces
	@Deprecated
	@Override
	public void refresh(){}
	@Override
	public void resetDefaults() 
	{
		this.combo.setSelectedIndex(0);
		this.keyword.setText("");
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.resetDefaults();
	}
}
