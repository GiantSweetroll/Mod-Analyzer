package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedHashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import constants.Constants;
import giantsweetroll.date.Date;
import giantsweetroll.date.DateSelectionPanel;
import giantsweetroll.gui.swing.Gbm;
import interfaces.FormEssentials;

public class DateRangePanel extends JPanel implements FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3166809849008171874L;
	public DateSelectionPanel dateFrom, dateTo;
	private JLabel labFrom, labTo;
	private GridBagConstraints c;
	
	//Constants
	public static final String FROM = "from";
	public static final String TO = "to";
	
	public DateRangePanel()
	{
		this.init();
	}
	
	public DateRangePanel(Date dateFrom, Date dateTo)
	{
		this.init();
		this.setFromDate(dateFrom);
		this.setToDate(dateTo);
	}
	
	private void init()
	{
		//Initialization
		this.dateFrom = new DateSelectionPanel(true, "Auto", "Default", "Automatically set date to local time", "Returns date to previous setting");
		this.dateTo = new DateSelectionPanel(true, "Auto", "Default", "Automatically set date to local time", "Returns date to previous setting");
		this.c = new GridBagConstraints();
		this.labFrom = new JLabel ("From:", SwingConstants.RIGHT);
		this.labTo = new JLabel ("To:", SwingConstants.RIGHT);
		
		//Properties
		this.dateFrom.autoSetDate();
		this.dateFrom.setAsDefaultDataThis();
		this.dateTo.autoSetDate();
		this.dateTo.setAsDefaultDataThis();
		this.setLayout(new GridBagLayout());
		this.setOpaque(false);
		
		//Add to panel
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_TOP_COMPONENT;
		c.fill = GridBagConstraints.BOTH;
		this.add(this.labFrom, c);
		Gbm.nextGridColumn(c);
		this.add(this.dateFrom, c);
		Gbm.newGridLine(c);
		c.insets = Constants.INSETS_GENERAL;
		this.add(this.labTo, c);
		Gbm.nextGridColumn(c);
		this.add(this.dateTo, c);		
	}
	
	public void setFromDate(Date date)
	{
		this.dateFrom.setDate(date);
	}
	public void setToDate(Date date)
	{
		this.dateTo.setDate(date);
	}
	
	public Date getFromDate()
	{
		return Date.getEarlierDate(this.dateFrom.getDate(), this.dateTo.getDate());
	}
	public Date getToDate()
	{
		return Date.getLaterDate(this.dateFrom.getDate(), this.dateTo.getDate());
	}
	
	public String getDateRangeAsString()
	{
		return this.dateFrom.getDateAsString() + " - " + this.dateTo.getDateAsString();
	}
	
	public LinkedHashMap<String, Date> getDateRange()
	{
		LinkedHashMap<String, Date> map = new LinkedHashMap<String, Date>();
		
		map.put(FROM, this.dateFrom.getDate());
		map.put(TO, this.dateTo.getDate());
		
		return map;
	}

	public void autoSetDateRanges()
	{
		this.setFromDate(new Date());
		this.setToDate(new Date());
	}
	
	//Overridden Methods
	@Override
	public void setEnabled(boolean b)
	{
		this.dateFrom.setEnabled(b);
		this.dateTo.setEnabled(b);
	}

	@Deprecated
	@Override
	public void refresh() {}

	@Override
	public void resetDefaults() 
	{
		this.dateFrom.autoSetDate();
		this.dateTo.autoSetDate();
	}
}