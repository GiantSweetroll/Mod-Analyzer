package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
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
						UIManager.put("Panel.background", Constants.PANEL_COLOR);
						UIManager.put("Label.foreground", Color.WHITE);
						UIManager.put("Button.foreground", Color.WHITE);
						UIManager.put("Button.font", Constants.GENERAL_FONT_BOLD);
			//			UIManager.put("Button.background", new Color(192, 195, 198));
						UIManager.put("Button.background", Constants.BUTTON_COLOR);
						UIManager.put("TabbedPane.background", new Color (67, 70, 75));
						UIManager.put("TabbedPane.foreground", Color.WHITE);
						UIManager.put("TabbedPane.selected", new Color (64, 111, 151));
						UIManager.put("TabbedPane.tabsOverlapBorder", new Color(48, 58, 60));
						UIManager.put("TabbedPane.contentBorderInsets", new Insets(1, 0, 1, 0));
						UIManager.put("RadioButton.foreground", Color.WHITE);
						UIManager.put("TitledBorder.titleColor", Color.WHITE);
						UIManager.put("TitledBorder.border", BorderFactory.createLineBorder(Constants.SECTION_BORDER_COLOR, 1));
						UIManager.put("TextField.background", Constants.MENU_BAR_COLOR);
						UIManager.put("TextField.foreground", Color.WHITE);
						UIManager.put("TextField.border", BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
						UIManager.put("OptionPane.background", Constants.PANEL_COLOR);
						UIManager.put("OptionPane.messageForeground", Color.WHITE);
						
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
