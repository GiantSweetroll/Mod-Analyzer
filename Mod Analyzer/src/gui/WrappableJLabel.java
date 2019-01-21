package gui;

import java.awt.Color;
import java.awt.ComponentOrientation;

import javax.swing.JTextArea;

import giantsweetroll.gui.swing.TextAreaManager;

public class WrappableJLabel extends JTextArea
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8032613219151109482L;

	public WrappableJLabel(String text)
	{
		super(1, 1);
		this.init(text);
	}
	public WrappableJLabel(String text, ComponentOrientation orientation)
	{
		super(1, 1);
		this.setComponentOrientation(orientation);
		this.init(text);
	}
	public WrappableJLabel(String text, int row, int col)
	{
		super(row, col);
		this.init(text);
	}
	public WrappableJLabel(String text, int row, int col, ComponentOrientation orientation)
	{
		super(row, col);
		this.setComponentOrientation(orientation);
		this.init(text);
	}
	public WrappableJLabel(int row, int col, ComponentOrientation orientation)
	{
		super(row, col);
		this.setComponentOrientation(orientation);
		this.init("");
	}
	public WrappableJLabel()
	{
		super(1, 1);
		this.init("");
	}
	public WrappableJLabel(int row, int col)
	{
		super(row, col);
		this.init("");
	}
	
	//Methods
	private void init(String text)
	{
		TextAreaManager.autoConfigureTextArea(this, false);
		this.setBorder(null);
		this.setOpaque(false);
		this.setText(text);
		this.setForeground(Color.WHITE);
	}
}