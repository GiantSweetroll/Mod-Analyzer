package constants;

import java.awt.Dimension;
import java.awt.Insets;

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
