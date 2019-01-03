package constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Constants 
{
	public static final int INSETS_BASE = 5;
	public static final Insets INSETS_TIGHT = new Insets (1, 1, 1, 1);
	public static final Insets INSETS_GENERAL = new Insets (INSETS_BASE, 
															INSETS_BASE, 
															INSETS_BASE, 
															INSETS_BASE);
	public static final Insets INSETS_GENERAL_MORE_LEFT = new Insets (INSETS_GENERAL.top, 
																		INSETS_GENERAL.left, 
																		INSETS_GENERAL.bottom, 
																		INSETS_GENERAL.right + 45);
	public static final Insets INSETS_TOP_COMPONENT = new Insets (INSETS_GENERAL.top + 10, 
																	INSETS_GENERAL.left, 
																	INSETS_GENERAL.bottom, 
																	INSETS_GENERAL.right);
	public static final Insets INSETS_TOP_COMPONENT_MORE_LEFT = new Insets (INSETS_TOP_COMPONENT.top,
																			INSETS_TOP_COMPONENT.left, 
																			INSETS_TOP_COMPONENT.bottom,
																			INSETS_GENERAL_MORE_LEFT.right);
	public static final Insets INSETS_TITLE = new Insets (INSETS_TOP_COMPONENT.top, 
															INSETS_GENERAL.left, 
															INSETS_GENERAL.bottom + 45, 
															INSETS_GENERAL.right);
	
	public static final Dimension DEFAULT_FRAME_SIZE = new Dimension(1280, 768);
	
	//Database
	public static final String DATABASE_FOLDER = "./database" + File.separator;
	public static final String MOD_ENTRY_FILE_EXTENSION = ".mod";
	public static final String MOD_ENTRY_DATABASE_FOLDER = DATABASE_FOLDER + "mod_entries" + File.separator;
	
	//Font Settings
	public static final String FONT_TYPE_GENERAL = "verdana";
	public static final int FONT_GENERAL_SIZE = 15;
	public static final Font GENERAL_FONT_BOLD = new Font(Constants.FONT_TYPE_GENERAL, Font.BOLD, Constants.FONT_GENERAL_SIZE);
	
	//Color
	public static final Color HIGHLIGHT_COLOR = new Color(0, 120, 215);
	
	//Comparators
	public static final Comparator<JButton> MOD_NAME_BUTTON_COMPARATOR = new Comparator<JButton>()
	{
		@Override
		public int compare(JButton b1, JButton b2) 
		{
			return b1.getText().compareTo(b2.getText());
		}
	};	
	public static final Comparator<JLabel> JLABEL_COMPARATOR = new Comparator<JLabel>()
			{
				@Override
				public int compare(JLabel b1, JLabel b2)
				{
					return b1.getText().compareTo(b2.getText());
				}
			};
	
	//Tags
	public static String[] TAGS_SKYRIM = {"Ammunition - Overwrite",
											"Ammunition - New",
											"Armor - Overwrite",
											"Armor - New",
											"Book - Overwrite",
											"Book - New",
											"Cell - Overwrite",
											"Cell - New",
											"Weather - Overwrite",
											"Weather - New",
											"Dialog - Overwrite",
											"Dialog - New",
											"Encounter Zone - Overwrite",
											"Encounter Zone - New",
											"Flora - Overwrite",
											"Flora - New",
											"Location - Overwrite",
											"Location - New",
											"Lighting",
											"Loading Screen - Overwrite",
											"Loading Screen - New",
											"Main Menu Wallpaper",
											"Music - Overwrite",
											"Music - New",
											"Leveled List",
											"NPC - Overwrite",
											"NPC - New",
											"Clothing - Overwrite",
											"Clothing - New",
											"Perk Overhaul",
											"Combat",
											"Combat Overhaul",
											"Quest - Overwrite",
											"Quest - New",
											"Race - Overwrite",
											"Race - New",
											"Shouts - Overwrite",
											"Shouts - New",
											"Spell - Overwrite",
											"Spell - New",
											"Water Overhaul",
											"Scripts",
											"Scripts - Cloaking",
											"NavMesh - Overwrite",
											"NavMesh - New",
											"Gameplay Overhaul",
											"Dirty Edits"};
}
