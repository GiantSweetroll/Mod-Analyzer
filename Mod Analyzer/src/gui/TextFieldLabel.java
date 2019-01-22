package gui;

import java.awt.Color;

import javax.swing.JTextField;

public class TextFieldLabel extends JTextField
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2883850276420977646L;
	
	public TextFieldLabel()
	{
		super();
		this.init();
	}
	public TextFieldLabel(String text)
	{
		super(text);
		this.init();
	}
	public TextFieldLabel(String text, int columns)
	{
		super(text, columns);
		this.init();
	}
	public TextFieldLabel(int columns)
	{
		super(columns);
		this.init();
	}
	
	//Methods
	private void init()
	{
		this.setBorder(null);
		this.setOpaque(false);
		this.setForeground(Color.WHITE);
		this.setEditable(false);
	}
}
