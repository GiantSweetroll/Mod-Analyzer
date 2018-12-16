package constants;

import java.util.Set;

import dataDrivers.Mod;
import gui.MainFrame;
import gui.ModDetailsPanel;
import gui.ModForm;
import gui.compatibilityPanel.CompatibilityDetailsPanel;
import gui.compatibilityPanel.CompatibilityModSelectionPanel;
import gui.compatibilityPanel.CompatibilitySelectionPanel;

public class Globals 
{
	public static ModForm MOD_FORM;
	public static MainFrame MAIN_FRAME;
	public static CompatibilitySelectionPanel COMPATIBILITY_SELECTION_PANEL;
	public static CompatibilityModSelectionPanel COMPATIBILITY_MOD_SELECTION_PANEL;
	public static CompatibilityDetailsPanel MOD_FORM_COMPATIBILITY_DETAILS_PANEL;
	public static ModDetailsPanel MOD_FORM_MOD_DETAILS_PANEL;
	
	public static Set<Mod> MODS;
}
