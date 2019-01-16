package gui.filter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;

import constants.Constants;
import giantsweetroll.date.Date;
import giantsweetroll.gui.swing.Gbm;
import gui.DateRangePanel;

public class FilterDate extends FilterElement
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
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.setLayout(new GridBagLayout());
		this.butReset.setText("Reset Dates");
		
		//Add to panel
		Gbm.goToOrigin(c);
		c.fill = GridBagConstraints.BOTH;
		c.insets = Constants.INSETS_GENERAL;
		this.add(this.check, c);				//Check box
		Gbm.nextGridColumn(c);
		this.add(this.dateRangePanel, c);		//Date Range Panel
		Gbm.nextGridColumn(c);
		this.add(this.butReset, c);				//Reset Button	
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
}