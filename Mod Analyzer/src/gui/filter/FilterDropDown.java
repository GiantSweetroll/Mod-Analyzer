package gui.filter;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import constants.Constants;
import giantsweetroll.gui.swing.Gbm;

public class FilterDropDown<T> extends FilterElement
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1832831792734523231L;
	private JTextField keyword;
	private JComboBox<T> combo;
	private T[] elements;
	
	//Constructor
	public FilterDropDown(String text)
	{
		super(text);
		this.init();
		this.setEnabled(this.hasElements());
	}
	public FilterDropDown(String text, T[] selections)
	{
		super(text);
		this.init();
		this.setSelection(selections);
		this.setEnabled(this.hasElements());
	}
	
	//Create GUI
	private void init()
	{
		//Initialization
		this.combo = new JComboBox<T>();
		this.keyword = new JTextField(10);
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.setLayout(new GridBagLayout());
		this.combo.addMouseListener(this.checkBoxSelect);
		this.combo.setBackground(Color.WHITE);
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
	public void setEnabled(boolean b)
	{
		super.setEnabled(b);
		b = this.isSelected();
		this.keyword.setEditable(b);
		this.combo.setEnabled(b);
	}	
	@Override
	public void itemStateChanged(ItemEvent e)
	{	
		boolean b = this.isSelected();
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
			this.keyword.setText("");
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
					if (!check.isSelected())
					{
						check.setSelected(true);
					}
				}
				@Override
				public void mouseReleased(MouseEvent arg0) {}		
			};	
}
