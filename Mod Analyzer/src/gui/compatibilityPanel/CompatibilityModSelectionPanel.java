package gui.compatibilityPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Globals;
import dataDrivers.CompatibilityList;
import dataDrivers.Mod;
import gui.ModCheckBox;
import gui.ModDetailsPanel;
import interfaces.FormEssentials;

public class CompatibilityModSelectionPanel extends JPanel implements FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6255726460480029514L;

	private JPanel panelTop, panelCenter;
	private JLabel labMod; 
	private List<ModCheckBox> MODS;
	private ModDetailsPanel modDetails;
	private CompatibilityDetailsPanel compatDetails;
	
	public CompatibilityModSelectionPanel(ModDetailsPanel modDetails, CompatibilityDetailsPanel compatDetails)
	{
		this.initGUI();
		this.setModDetailsPanel(modDetails);
		this.setCompatibilityDetailsPanel(compatDetails);
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
	}

	//Public methods
	public void setModDetailsPanel(ModDetailsPanel details)
	{
		this.modDetails = details;
	}
	public void setCompatibilityDetailsPanel(CompatibilityDetailsPanel compat)
	{
		this.compatDetails = compat;
	}
	public void addMod(Set<Mod> mods)
	{
		for (Mod mod : mods)
		{
			ModCheckBox jc = new ModCheckBox(mod);
			jc.addMouseListener(new MouseListener()
					{

						@Override
						public void mouseClicked(MouseEvent arg0) 
						{
							modDetails.displayModDetails(mod);
							compatDetails.setEnabled(jc.isSelected());
						}

						@Override
						public void mouseEntered(MouseEvent arg0) {}

						@Override
						public void mouseExited(MouseEvent arg0) {}

						@Override
						public void mousePressed(MouseEvent arg0) {}

						@Override
						public void mouseReleased(MouseEvent arg0) {}
					});
			this.MODS.add(jc);
		}

		//Sort
		Collections.sort(this.MODS, ModCheckBox.MOD_CHECKER_COMPARATOR);
	}
	public void setMods(Set<Mod> mods)
	{
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
	}
}