package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import constants.Constants;
import giantsweetroll.gui.swing.Gbm;
import interfaces.FormEssentials;

public class FilterElement<T> extends JPanel implements FormEssentials, ActionListener, ItemListener
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
		this.setEnabled(this.hasElements());
	}
	public FilterElement(String text, T[] selections)
	{
		this.init(text);
		this.setSelection(selections);
		this.setEnabled(this.hasElements());
	}
	
	//Methods
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
		this.butReset.addActionListener(this);
		this.check.setOpaque(false);
		this.check.addItemListener(this);
		this.combo.addMouseListener(this.checkBoxSelect);
		this.keyword.addMouseListener(this.checkBoxSelect);
		
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
	public boolean hasElements()
	{
		if (elements == null || elements.length == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public boolean customKeywordSelected()
	{
		return this.keyword.isEditable();
	}
	public void setSelected(boolean b)
	{
		this.check.setSelected(b);
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
	@Override
	public void setEnabled(boolean b)
	{
		super.setEnabled(b);
		this.check.setEnabled(b);
		b = this.check.isSelected();
		this.keyword.setEditable(b);
		this.butReset.setEnabled(b);
		this.combo.setEnabled(b);
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{	
		boolean b = this.check.isSelected();
		this.keyword.setEditable(false);
		this.butReset.setEnabled(b);
		this.combo.setEnabled(b);
		if(b)
		{
			this.combo.addMouseListener(this.comboSelect);
			this.keyword.addMouseListener(this.textFieldSelect);
		}
		else
		{
			this.combo.removeMouseListener(this.comboSelect);
			this.keyword.removeMouseListener(this.textFieldSelect);
		}
	}
	private MouseListener textFieldSelect = new MouseListener()
	{
		@Override
		public void mouseClicked(MouseEvent arg0){}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) 
		{
			if (!keyword.isEditable())
			{
				keyword.setEditable(true);
				combo.setEnabled(false);
			}
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {}		
	};	
	private MouseListener comboSelect = new MouseListener()
	{
		@Override
		public void mouseClicked(MouseEvent arg0){}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) 
		{
			if (!combo.isEnabled())
			{
				combo.setEnabled(true);
				keyword.setEditable(false);
			}
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {}		
	};	
	private MouseListener checkBoxSelect = new MouseListener()
			{
				@Override
				public void mouseClicked(MouseEvent arg0){}
				@Override
				public void mouseEntered(MouseEvent arg0) {}
				@Override
				public void mouseExited(MouseEvent arg0) {}
				@Override
				public void mousePressed(MouseEvent arg0) 
				{
					if (check.isSelected())
					{
						check.setSelected(true);
					}
				}
				@Override
				public void mouseReleased(MouseEvent arg0) {}		
			};			
}