package gui;

import java.awt.Color;

import javax.swing.JTable;

import giantsweetroll.gui.swing.table.renderers.JButtonRenderer;

public class ModTableLite extends JTable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7215885364939934872L;
	
	private String[][] components;
	
	public ModTableLite(String[][] data, String[] headers)
	{
		super(data, headers);
		
		this.components = data;
		
		//Table properties
		this.getTableHeader().setBackground(Color.GRAY);
		this.getTableHeader().setForeground(Color.WHITE);
		this.setBackground(Color.WHITE);
		this.setAutoCreateRowSorter(true);						//Automatically create Row sorter
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);			//Set to manual column size, so it works with the JScrollPane
		this.getColumnModel().getColumn(this.getColumnCount()-1).setCellRenderer(new JButtonRenderer());	//Last Column Renders a JButton
		
		for (int i=0; i<this.getColumnCount()-1; i++)
		{
			this.getColumnModel().getColumn(i).setMinWidth(this.getColumnName(i).length());
		}
	}
	
	//Public Methods
	public String[][] getTableData()
	{
		return this.components;
	}
	
	//Override Methods
	@Override
	public boolean isCellEditable(int row, int column)		//Make the table data un-editable (except the buttons column)
	{
		if (column >= this.getColumnCount()-3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}	
	@Override
	public boolean getScrollableTracksViewportWidth()			//Resizes the table cells width to its preferred size or the viewport size, whichever is greater
	{
		return getPreferredSize().width < getParent().getWidth();
	}	
}