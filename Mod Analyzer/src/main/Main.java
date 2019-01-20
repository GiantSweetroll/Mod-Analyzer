package main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import constants.Constants;
import constants.Globals;
import gui.MainFrame;
import methods.FileOperation;
import methods.Methods;

public class Main 
{
	public static void main (String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
						UIManager.put("OptionPane.background", Color.WHITE);
						UIManager.put("Panel.background", Color.white);
						
	//					Arrays.sort(Constants.TAGS_SKYRIM);
						Globals.MODS = FileOperation.importAllMods();
						Globals.MAIN_FRAME = new MainFrame();
						Globals.OVERVIEW.setData(Globals.MODS);
						Globals.COMPATIBILITY_MOD_SELECTION_PANEL.setMods(Globals.MODS);
						Globals.MAIN_FRAME.setVisible(true);
					}
				});
	}
}
