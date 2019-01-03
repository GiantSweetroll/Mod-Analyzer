package gui.compatibilityPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import constants.Globals;
import dataDrivers.CompatibilityList;
import dataDrivers.Mod;
import gui.ModCheckBox;
import interfaces.FormEssentials;

public class CompatibilityModSelectionPanel extends JPanel implements FormEssentials, ItemListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6255726460480029514L;

	private JPanel panelTop, panelCenter;
	private JLabel labMod; 
	private List<ModCheckBox> MODS;
	
	private String activeModID;
	private int activeModIndex;
	
	public CompatibilityModSelectionPanel()
	{
		this.initGUI();
		this.activeModID = "";
	}
	//Create GUI
	private void initGUI()
	{
		//Initialization
		this.initPanelCenter();
		this.initPanelTop();
		
		//Properties
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//Add to panel
		this.add(this.panelTop);
		this.add(this.panelCenter);
	}
	private void initPanelTop()
	{
		//Initialization
		this.panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.labMod = new JLabel("Mod");
		
		//Properties
		this.panelTop.setMaximumSize(new Dimension(this.panelTop.getMaximumSize().width, 50));
		this.labMod.setFont(Constants.GENERAL_FONT_BOLD);
		
		//Add to panel
		this.panelTop.add(this.labMod, BorderLayout.CENTER);
	}
	private void initPanelCenter()
	{
		//Initialization
		this.panelCenter = new JPanel();
		this.MODS = new ArrayList<ModCheckBox>();
		
		//Properties
		this.panelCenter.setLayout(new BoxLayout(this.panelCenter, BoxLayout.Y_AXIS));
//		this.panelCenter.setLayout(new GridLayout(1, 0));
	}

	//Public methods
	public void addMod(Set<Mod> mods)
	{
		for (Mod mod : mods)
		{
			ModCheckBox jc = new ModCheckBox(mod);
			jc.setMaximumSize(new Dimension(jc.getMaximumSize().width, 50));
			this.MODS.add(jc);
		}

		//Sort
		Collections.sort(this.MODS, ModCheckBox.MOD_CHECKER_COMPARATOR);
		
		//Create index
		this.recreateModIndex();
		
		//Add to panel
		for (ModCheckBox ck : this.MODS)
		{
			this.panelCenter.add(ck);
		}
	}
	public void recreateModIndex()
	{
		for (int i=0; i<this.MODS.size(); i++)
		{
			this.MODS.get(i).setIndex(i);
		}
	}
	//Setters
	public void setMods(Set<Mod> mods)
	{
		this.removeModsFromSelectionDisplay();
		this.MODS.clear();
		this.addMod(mods);
	}
	public void setData(CompatibilityList compatList)
	{
		for (String id : compatList.getModIDs())
		{
			for (ModCheckBox check : this.MODS)
			{
				if (check.getMod().getID().equals(id))
				{
					check.setSelected(true);
				}
			}
		}
	}
	public void setActiveModID(String modID)
	{
		this.activeModID = modID;
	}
	public void setActiveModIndex(int index)
	{
		this.activeModIndex = index;
	}
	//Getters
	public String getActiveModID()
	{
		return this.activeModID;
	}
	public int getActiveModIndex()
	{
		return this.activeModIndex;
	}
	public ModCheckBox getActiveModCheckBox()
	{
		return this.MODS.get(this.getActiveModIndex());
	}
	//Others
	public void removeModFromList(String id)
	{
		for (int i=0; i<this.MODS.size(); i++)
		{
			if (this.MODS.get(i).getMod().getID().equals(id))
			{
				this.panelCenter.remove(this.MODS.get(i));
				this.MODS.remove(i);
				this.panelCenter.revalidate();
				this.panelCenter.repaint();
				this.recreateModIndex();
				break;
			}
		}
	}
	public void removeModsFromSelectionDisplay()
	{
		for(int i=0; i<this.MODS.size(); i++)
		{
			this.panelCenter.remove(this.MODS.get(i));
		}
		this.panelCenter.revalidate();
		this.panelCenter.repaint();		
	}
	
	//Interfaces
	@Override
	public void refresh() 
	{
		this.setMods(Globals.MODS);
	}
	@Override
	public void resetDefaults() 
	{
		for (ModCheckBox check : this.MODS)
		{
			check.setSelected(false);
		}
		this.activeModID = "";
	}
	@Override
	public void itemStateChanged(ItemEvent e)
	{
	}
}