package main;

import java.util.Arrays;
import java.util.HashSet;

import javax.swing.SwingUtilities;

import constants.Constants;
import constants.Globals;
import dataDrivers.Mod;
import gui.MainFrame;

public class Main 
{
	public static void main (String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						Arrays.sort(Constants.TAGS_SKYRIM);
						Globals.MODS = new HashSet<Mod>();
						Globals.MAIN_FRAME = new MainFrame();
						Globals.MAIN_FRAME.setVisible(true);
					}
				});
	}
}
