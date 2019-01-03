package gui.editor;

import java.awt.Component;

import javax.swing.JTable;

import giantsweetroll.gui.swing.table.editors.JButtonEditor;

public class ModDetailEditor extends JButtonEditor
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4389856940257581057L;

	private int selectedIndex;
	
	public ModDetailEditor()
	{
		this.selectedIndex = -1;
	}
	
	//Public Methods
	public int getSelectedIndex()
	{
		return this.selectedIndex;
	}
	
	//Overridden Methods
	@Override
	public Component getTableCellEditorComponent(JTable table, Object obj, boolean isSelected, int row, int column) 
	{
		this.selectedIndex = table.convertRowIndexToModel(table.getSelectedRow());
		return super.getTableCellEditorComponent(table, obj, isSelected, row, column);
	}	
	@Override
	public void clickedAction()
	{
		// TODO Auto-generated method stub
		
	}

}
