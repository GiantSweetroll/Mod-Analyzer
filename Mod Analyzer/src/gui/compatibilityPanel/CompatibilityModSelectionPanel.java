package gui.compatibilityPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CompatibilityModSelectionPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6255726460480029514L;

	private JPanel panelTop, panelCenter;
	private JLabel labMod;
	private Set<JCheckBox> MODS;
	
	public CompatibilityModSelectionPanel()
	{
		super(new BorderLayout());
	}
	//Create GUI
	private void initGUI()
	{
		//Initialization
		this.initPanelCenter();
		this.initPanelTop();
		
		//Add to panel
		this.add(this.panelCenter, BorderLayout.CENTER);
		this.add(this.panelTop, BorderLayout.NORTH);
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
		this.MODS = new HashSet<JCheckBox>();
		
		//Properties
		this.panelCenter.setLayout(new BoxLayout(this.panelCenter, BoxLayout.Y_AXIS));
	}
}