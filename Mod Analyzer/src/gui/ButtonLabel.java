package gui;

import java.awt.Insets;

import javax.swing.JButton;

public class ButtonLabel extends JButton
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8832994072277855145L;
	
	public ButtonLabel(String txt)
	{
		super(txt);
		this.setFocusPainted(false);
		this.setMargin(new Insets(0, 0, 0, 0));
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setOpaque(false);
	}
}