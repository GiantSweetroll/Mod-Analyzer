package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import constants.Constants;
import constants.Globals;
import dataDrivers.Mod;
import methods.Methods;

public class ModCheckBox extends JPanel implements ItemListener, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5128765770233649402L;

	private Mod mod;
	private JCheckBox cb;
	private ButtonLabel label;
	private JButton details;
	private int index;
	private JPanel panelButton;
	
	//Changing
	private boolean justFilling;
	
	public ModCheckBox(Mod mod)
	{
		super();
		this.cb = new JCheckBox("");
		this.mod = mod;
		this.label = new ButtonLabel(Methods.getWrappableText(mod.getName()));
		this.initPanelButton();
		this.justFilling = !Globals.MOD_FORM.isNewEntry();
		
		//Properties
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setOpaque(false);
		this.setBackground(Constants.HIGHLIGHT_COLOR);
		this.cb.setOpaque(false);
		this.cb.setBackground(Constants.HIGHLIGHT_COLOR);
		this.cb.addItemListener(this);
		this.label.addActionListener(this);
		this.label.setBackground(Constants.HIGHLIGHT_COLOR);
		this.label.setHorizontalAlignment(SwingConstants.LEFT);
//		this.label.setMaximumSize(new Dimension(10, this.label.getMaximumSize().height));
		
		//Add to panel
		this.add(this.cb);
		this.add(this.label);
		this.add(this.panelButton);
	}
	private void initPanelButton()
	{
		//Initialization
		this.panelButton = new JPanel(new FlowLayout(FlowLayout.RIGHT))
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = -6652691960092510947L;
			@Override
			public Dimension getMaximumSize()
			{
				Dimension old = super.getMaximumSize();
				
				return new Dimension(Integer.MAX_VALUE, old.height);
			}
			
		};
		this.details = new JButton("Details");
		
		//Properties
		this.details.addActionListener(this.detailsListener);
		this.panelButton.setOpaque(false);
		this.panelButton.setBackground(Constants.HIGHLIGHT_COLOR);
//		this.panelButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.panelButton.getMaximumSize().height));
		
		//Add to panel
		this.panelButton.add(this.details);
	}
	
	//Public Methods
	public void setMod(Mod mod)
	{
		this.mod = mod;
		this.label.setText(mod.getName());
	}
	public Mod getMod()
	{
		return this.mod;
	}
	public JCheckBox getCheckBox()
	{
		return this.cb;
	}
	public String getModName()
	{
		return this.label.getText();
	}
	public void setSelected(boolean b)
	{
		if(b)
		{
			this.justFilling = !Globals.MOD_FORM.isNewEntry();
		}
		this.cb.setSelected(b);
//		this.highlight(b);
	}
	public void highlight(boolean b)
	{
		this.setOpaque(b);
		this.cb.setOpaque(b);
		this.label.setOpaque(b);
		this.panelButton.setOpaque(b);
		/*
		if (b)
		{
			this.label.setForeground(Color.WHITE);
		}
		else
		{
			this.label.setForeground(Color.BLACK);
		}		*/
		this.revalidate();
		this.repaint();
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	public int getIndex()
	{
		return this.index;
	}
	public boolean isSelected()
	{
		return this.cb.isSelected();
	}
	
	//Private Methods
	private void select()
	{
		if(!Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModID().equals(""))
		{
			Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModCheckBox().highlight(false);
		}
		this.updateCompatibility();
		this.highlight(true);		
	}
	public void updateCompatibility()
	{
	/*	String compatDetailModID = Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.getActiveModID();
		System.out.println(compatDetailModID + " vs " + Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModID());
		if (compatDetailModID.equals(Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModID()))
		{
			if (this.cb.isSelected())
			{
				Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.updateCurrentCompatibility();
			}
		}		*/
//		Globals.MOD_FORM_MOD_DETAILS_PANEL.displayModDetails(mod);
		
//		System.out.println("Active Mod ID before updating compatibility: " + Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModID());
//		System.out.println("Compatibility list before updating:");
//		Globals.COMPATIBILITY_SELECTION_PANEL.getCompatibilityList().printAllCompatibilities();
		if (!Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModID().equals(""))
		{
			//Saves the previous active item
			try
			{
				ModCheckBox mcb = Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModCheckBox();
				if (mcb.isSelected())
				{
		//			System.out.println("Compatibility updated..." + " (" + mcb.getModName() + ")");
					Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.updateCurrentCompatibility();		
				}
				else
				{
		//			System.out.println("Compatibility removed..." + " (" + mcb.getModName() + ")");
					Globals.COMPATIBILITY_SELECTION_PANEL.removeCompatibility(Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.getActiveModID());
				}	
			}
			catch(NullPointerException ex) {}
			//Set the new mod as the new active item
			Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.setEnabled(cb.isSelected());
			String modID = this.getMod().getID();
			Globals.COMPATIBILITY_MOD_SELECTION_PANEL.setActiveModID(modID);
			Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.setActiveModID(modID);
			Globals.COMPATIBILITY_MOD_SELECTION_PANEL.setActiveModIndex(this.getIndex());
			try
			{
				Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.setData(Globals.COMPATIBILITY_SELECTION_PANEL.getCompatibility(modID));			//Display compatibility data in compatibility details panel
			}
			catch(NullPointerException ex) 
			{
	//			System.out.println("null");
				Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.resetDefaults();
				Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.setActiveModID(modID);
			}
		}
		else
		{
			Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.setEnabled(cb.isSelected());
			String modID = this.getMod().getID();
			Globals.COMPATIBILITY_MOD_SELECTION_PANEL.setActiveModID(modID);
			Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.setActiveModID(modID);
			Globals.COMPATIBILITY_MOD_SELECTION_PANEL.setActiveModIndex(this.getIndex());
			//Try to check if existing compatibility data for the selected mod id exists
			try
			{
				Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.setData(Globals.COMPATIBILITY_SELECTION_PANEL.getCompatibility(modID));			//Display compatibility data in compatibility details panel
			}
			catch(NullPointerException ex) {};
		}
//		System.out.println("Active Mod ID after updating compatibility: " + Globals.COMPATIBILITY_MOD_SELECTION_PANEL.getActiveModID());
//		System.out.println("Compatibility list after updating:");
//		Globals.COMPATIBILITY_SELECTION_PANEL.getCompatibilityList().printAllCompatibilities();
//		Methods.printCompatibilityIDs(Globals.COMPATIBILITY_SELECTION_PANEL.getCompatibilityList());
	}
	
	//Comparator
	public static final Comparator<ModCheckBox> MOD_CHECKER_COMPARATOR = new Comparator<ModCheckBox>()
			{
				@Override
				public int compare(ModCheckBox mod1, ModCheckBox mod2) 
				{
					return mod1.getModName().compareTo(mod2.getModName());
				}
			};

	//Interface
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.select();
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		
		if (this.justFilling)
		{
			this.justFilling = false;
		}
		else
		{
			this.select();
		}
	}

	private ActionListener detailsListener = new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					Globals.MOD_FORM_MOD_DETAILS_PANEL.displayModDetails(mod);
		//			JScrollPane scroll = ScrollPaneManager.generateDefaultScrollPane(Globals.MOD_FORM_MOD_DETAILS_PANEL, 10, 10);
		//			scroll.setBorder(null);
		//			scroll.getViewport().setBackground(null);
					Globals.MOD_FORM_MOD_DETAILS_PANEL.repaint();
					JOptionPane.showMessageDialog(null, Globals.MOD_FORM_MOD_DETAILS_PANEL, mod.getName(), JOptionPane.PLAIN_MESSAGE, null);
				}
			};
}
