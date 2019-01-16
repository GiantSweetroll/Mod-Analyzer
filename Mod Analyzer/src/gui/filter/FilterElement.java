package gui.filter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import interfaces.FormEssentials;

public abstract class FilterElement extends JPanel implements FormEssentials, ItemListener, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8052748168184458893L;
	protected JCheckBox check;
	protected JButton butReset;
	
	//Constructor
	public FilterElement(String text)
	{
		//Initialization
		this.check = new JCheckBox(text);
		this.butReset = new JButton("Reset");
		
		//Properties
		this.butReset.addActionListener(this);
		this.check.setOpaque(false);
		this.check.addItemListener(this);
	}
	
	//Public Methods
	public boolean isSelected()
	{
		return this.check.isSelected();
	}
	public void setSelected(boolean b)
	{
		this.check.setSelected(b);
	}
	
	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.resetDefaults();
	}
	@Override
	public void setEnabled(boolean b)
	{
		super.setEnabled(b);
		this.check.setEnabled(b);
		b = this.check.isSelected();
		this.butReset.setEnabled(b);
	}
}
