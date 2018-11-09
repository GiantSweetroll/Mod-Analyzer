package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;

import constants.Constants;
import constants.Globals;
import gui.compatibilityPanel.CompatibilityDetailsPanel;

public class MainFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8026416994513756565L;

	//Constants
	public static final String MOD_FORM = "modform";
	
	public MainFrame()
	{
		//Initialization
		Globals.MOD_FORM = new ModForm();
		
		//Properties
		this.setLayout(new CardLayout());
		this.setSize(Constants.DEFAULT_FRAME_SIZE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//Add to frame
//		this.add(Globals.MOD_FORM, MainFrame.MOD_FORM);
//		this.add(new CompatibilityDetailsPanel(), "sdsd");
	}
}
