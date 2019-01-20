package gui.filter;

import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import constants.Constants;
import giantsweetroll.date.Date;
import gui.DateRangePanel;

public class FilterDate extends FilterElement implements MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3349894051221119205L;
	private DateRangePanel dateRangePanel;

	//Constructor
	public FilterDate(String text) 
	{
		super(text);
		this.init();
	}
	//Create GUI
	private void init()
	{
		//Initialization
		this.dateRangePanel = new DateRangePanel();
//		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
//		this.setLayout(new GridBagLayout());
		this.setLayout(new FlowLayout(FlowLayout.LEFT, Constants.INSETS_BASE, Constants.INSETS_BASE));
		this.butReset.setText("Reset Dates");
		this.dateRangePanel.setEnabled(false);
		this.dateRangePanel.addMouseListener(this);
		this.butReset.setEnabled(false);
		
		//Add to panel
//		Gbm.goToOrigin(c);
	//	c.fill = GridBagConstraints.BOTH;
//		c.insets = Constants.INSETS_GENERAL;
		this.add(this.check);				//Check box
//		Gbm.nextGridColumn(c);
		this.add(this.dateRangePanel);		//Date Range Panel
//		Gbm.nextGridColumn(c);
		this.add(this.butReset);				//Reset Button	
	}
	
	//Public Methods
	//Setters
	public void setDateFrom(Date date)
	{
		this.dateRangePanel.setFromDate(date);
	}
	public void setDateTo(Date date)
	{
		this.dateRangePanel.setToDate(date);
	}
	//Getters
	public Date getDateFrom()
	{
		return this.dateRangePanel.getFromDate();
	}
	public Date getDateTo()
	{
		return this.dateRangePanel.getToDate();
	}
	
	//Interfaces
	@Deprecated
	@Override
	public void refresh() {}

	@Override
	public void resetDefaults() 
	{
		this.dateRangePanel.resetDefaults();
	}
	@Override
	public void setEnabled(boolean b)
	{
		super.setEnabled(b);
		b = this.isSelected();
		this.dateRangePanel.setEnabled(b);
	}	
	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		boolean b = this.isSelected();
		this.butReset.setEnabled(b);
		this.dateRangePanel.setEnabled(b);
	}
	@Override
	public void mouseClicked(MouseEvent e){}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) 
	{
		if(!this.isSelected())
		{
			this.setSelected(true);
			this.dateRangePanel.setEnabled(true);
			this.butReset.setEnabled(true);
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
}