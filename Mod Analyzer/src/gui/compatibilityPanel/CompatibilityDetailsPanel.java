package gui.compatibilityPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import constants.Constants;
import constants.Globals;
import dataDrivers.Compatibility;
import giantsweetroll.gui.swing.Gbm;
import giantsweetroll.gui.swing.ScrollPaneManager;
import giantsweetroll.gui.swing.TextAreaManager;
import interfaces.FormEssentials;

public class CompatibilityDetailsPanel extends JPanel implements FormEssentials, ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2038053529114446886L;

	private JLabel labCompatible, labSeverity, labReason, labPatch, labLink, labNotes;
	private JPanel panelButtons;
	private JRadioButton radCompatYes, radCompatNo, radCompatSoft, radCompatMed, radCompatHard, radPatchYes, radPatchNo;
	private JTextArea taReason, taNotes;
	private JTextField tfLink;
	private ButtonGroup groupCompat, groupSeverity, groupPatch;
	private JScrollPane scrollReason, scrollNotes;
	private String activeModID;
	private JButton butCancel, butSet, butReset;
	
	//Constants
	private final String CANCEL = "cancel",
							SET = "set",
							RESET = "reset";
	private final Color DISABLED_COLOR = Color.GRAY;
	
	public CompatibilityDetailsPanel()
	{
		this.activeModID = "";
		this.initGUI();
		this.setEnabled(false);
	}
	//Create GUI
	private void initGUI()
	{
		//Initialization
		this.labCompatible = new JLabel("Compatible?");
		this.radCompatYes = new JRadioButton("Yes");
		this.radCompatNo = new JRadioButton("No");
		this.labSeverity = new JLabel("Level of Severity");
		this.radCompatSoft = new JRadioButton("Soft");
		this.radCompatMed = new JRadioButton("Medium");
		this.radCompatHard = new JRadioButton("Hard");
		this.labReason = new JLabel("Reason");
		this.taReason = new JTextArea(10, 5);
		this.labPatch = new JLabel("Path Available?");
		this.radPatchYes = new JRadioButton("Yes");
		this.radPatchNo = new JRadioButton("No");
		this.labLink = new JLabel("Link");
		this.tfLink = new JTextField(30);
		this.labNotes = new JLabel("Notes");
		this.taNotes = new JTextArea(10, 5);
		this.groupCompat = new ButtonGroup();
		this.groupPatch = new ButtonGroup();
		this.groupSeverity = new ButtonGroup();
		this.scrollNotes = new JScrollPane(this.taNotes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.scrollReason = new JScrollPane(this.taReason, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.initPanelButtons();
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.setLayout(new GridBagLayout());
		this.groupCompat.add(this.radCompatYes);
		this.groupCompat.add(this.radCompatNo);
		this.groupSeverity.add(this.radCompatSoft);
		this.groupSeverity.add(this.radCompatMed);
		this.groupSeverity.add(this.radCompatHard);
		this.groupPatch.add(this.radPatchNo);
		this.groupPatch.add(this.radPatchYes);
		TextAreaManager.autoConfigureTextArea(this.taNotes, true);
		TextAreaManager.autoConfigureTextArea(this.taReason, true);
		ScrollPaneManager.autoConfigureScrollPane(this.scrollNotes);
		ScrollPaneManager.autoConfigureScrollPane(this.scrollReason);
		this.radCompatYes.addItemListener(this.compatibilityListener);
		this.radCompatYes.setOpaque(false);
		this.radCompatNo.addItemListener(this.compatibilityListener);
		this.radCompatNo.setOpaque(false);
		this.radPatchNo.addItemListener(this.patchListener);
		this.radPatchNo.setOpaque(false);
		this.radPatchYes.setOpaque(false);
		this.radPatchYes.addItemListener(this.patchListener);
		this.radCompatHard.setOpaque(false);
		this.radCompatMed.setOpaque(false);
		this.radCompatSoft.setOpaque(false);
		this.radCompatSoft.setSelected(true);
		this.radPatchNo.setSelected(true);
		this.radCompatYes.setSelected(true);
		this.tfLink.setCaretColor(Color.WHITE);
		
		//Add to panel
		Gbm.goToOrigin(c);
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 3;
		c.insets = Constants.INSETS_GENERAL;
		this.add(this.labCompatible, c);				//Compatible? label
		Gbm.newGridLine(c);
		c.gridwidth = 1;
		this.add(this.radCompatYes, c);					//Compatible Yes Radio Button
		Gbm.nextGridColumn(c);
		this.add(this.radCompatNo, c);					//Compatible No Radio Button
		Gbm.newGridLine(c);
		c.gridwidth = 3;
		this.add(this.labSeverity, c);					//Severity Label
		Gbm.newGridLine(c);
		c.gridwidth = 1;
		this.add(this.radCompatSoft, c);				//Severity Soft Radio Button
		Gbm.nextGridColumn(c);
		this.add(this.radCompatMed, c);					//Severity Medium Radio Button
		Gbm.nextGridColumn(c);
		this.add(this.radCompatHard, c);				//Severity Hard radio button
		Gbm.newGridLine(c);
		c.gridwidth = 3;
		this.add(this.labReason, c);					//Reason label
		Gbm.newGridLine(c);
		this.add(this.scrollReason, c);					//Reason Text Area
		Gbm.newGridLine(c);
		this.add(this.labPatch, c);						//Patch Available Label
		Gbm.newGridLine(c);
		c.gridwidth = 1;
		this.add(this.radPatchYes, c);					//Patch Yes Radio Button
		Gbm.nextGridColumn(c);
		this.add(this.radPatchNo, c);					//Patch No Radio Button
		Gbm.newGridLine(c);
		this.add(this.labLink, c);						//Link label
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		this.add(this.tfLink, c);						//Link text area
		Gbm.newGridLine(c);
		c.gridwidth = 3;
		this.add(this.labNotes, c);						//Notes label
		Gbm.newGridLine(c);
		this.add(this.scrollNotes, c);					//Notes Text Area
		Gbm.newGridLine(c);
		c.gridwidth = 100;
		this.add(this.panelButtons, c);					//Buttons Panel
	}
	private void initPanelButtons()
	{
		//Initialization
		this.panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.butCancel = new JButton("Cancel");
		this.butReset = new JButton("Reset");
		this.butSet = new JButton("Set");
		
		//Properties
		this.butCancel.setActionCommand(this.CANCEL);
		this.butCancel.addActionListener(this);
		this.butSet.setActionCommand(this.SET);
		this.butSet.addActionListener(this);
		this.butReset.setActionCommand(this.RESET);
		this.butReset.addActionListener(this);
		this.butReset.setEnabled(false);
		
		//Add to panel
//		this.panelButtons.add(this.butCancel);
		this.panelButtons.add(this.butReset);
//		this.panelButtons.add(this.butSet);
	}
	//Public Methods
	//Getters
	public boolean isCompatible()
	{
		return !this.radCompatNo.isSelected();
	}
	public String getSeverityOfIncompatibility()
	{
		if (this.radCompatSoft.isSelected())
		{
			return Compatibility.SOFT;
		}
		else if (this.radCompatMed.isSelected())
		{
			return Compatibility.MEDIUM;
		}
		else if (this.radCompatHard.isSelected())
		{
			return Compatibility.HARD;
		}
		else
		{
			return "";
		}
	}
	public String getReasonOfIncompatibility()
	{
		return this.taReason.getText().trim();
	}
	public boolean isPatchAvailable()
	{
		return this.radPatchYes.isSelected();
	}
	public String getPatchLink()
	{
		return this.tfLink.getText().trim();
	}
	public String getNotes()
	{
		return this.taNotes.getText().trim();
	}
	public Compatibility getData()
	{
		Compatibility compat = new Compatibility(this.activeModID);
		
		compat.setIsCompatible(this.isCompatible());
		compat.setNotes(this.getNotes());
		compat.setPatchAvailable(this.isPatchAvailable());
		compat.setPatchLink(this.getPatchLink());
		compat.setReason(this.getReasonOfIncompatibility());
		compat.setSeverity(this.getSeverityOfIncompatibility());
		
		return compat;
	}
	public String getActiveModID()
	{
		return this.activeModID;
	}
	//Setters
	public void setCompatible(boolean compatible)
	{
		try
		{
			if (compatible)
			{
				this.radCompatYes.setSelected(true);
			}
			else
			{
				this.radCompatNo.setSelected(true);
			}
		}
		catch(NullPointerException ex) {}
	}
	public void setSeverityOfIncompatibility(String severity)
	{
		if (severity.equals(Compatibility.SOFT))
		{
			this.radCompatSoft.setSelected(true);
		}
		else if (severity.equals(Compatibility.MEDIUM))
		{
			this.radCompatMed.setSelected(true);
		}
		else if (severity.equals(Compatibility.HARD))
		{
			this.radCompatHard.setSelected(true);
		}
	}
	public void setReasonOfIncompatibility(String reason)
	{
		this.taReason.setText(reason);
	}
	public void setPatchAvailable(boolean available)
	{
		try
		{
			if (available)
			{
				this.radPatchYes.setSelected(true);
			}
			else
			{
				this.radPatchNo.setSelected(true);
			}
		}
		catch(NullPointerException ex) {}
	}
	public void setPatchLink(String link)
	{
		this.tfLink.setText(link);
	}
	public void setNotes(String notes)
	{
		this.taNotes.setText(notes);
	}
	public void setData(Compatibility compat)
	{
		this.activeModID = compat.getModID();
		this.setCompatible(compat.isCompatible());
		this.setSeverityOfIncompatibility(compat.getSeverity());
		this.setReasonOfIncompatibility(compat.getReason());
		this.setPatchAvailable(compat.isPatchAvailable());
		this.setPatchLink(compat.getPatchLink());
		this.setNotes(compat.getNotes());
	}
	public void setActiveModID(String modID)
	{
		this.activeModID = modID;
	}
	public void resetComponentsToDefaults()
	{
		this.radCompatYes.setSelected(true);
		this.radCompatNo.setSelected(false);
		this.radCompatSoft.setSelected(true);
		this.radCompatMed.setSelected(false);
		this.radCompatHard.setSelected(false);
		this.radPatchYes.setSelected(false);
		this.radPatchNo.setSelected(true);
		this.taReason.setText("");
		this.tfLink.setText("");
		this.taNotes.setText("");
	}
	//Others
	public void updateCurrentCompatibility()
	{
//		System.out.println("Mod Details ACTIVE MOD ID: " + Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.getActiveModID());
		Globals.COMPATIBILITY_SELECTION_PANEL.getCompatibilityList().setCompatibility(Globals.MOD_FORM_COMPATIBILITY_DETAILS_PANEL.getActiveModID(), this.getData());
	}
	//Private Methods
	private void setCompatibilityButtonsEnabled(boolean enabled)
	{
		this.radCompatYes.setEnabled(enabled);
		this.radCompatNo.setEnabled(enabled);
	}
	private void setSeverityButtonsEnabled(boolean b)
	{
		this.radCompatSoft.setEnabled(b);
		this.radCompatMed.setEnabled(b);
		this.radCompatHard.setEnabled(b);
	}
	private void setPatchButtonsEnabled(boolean b)
	{
		this.radPatchNo.setEnabled(b);
		this.radPatchYes.setEnabled(b);
	}
	
	//Overridden Methods
	@Override
	public void setEnabled(boolean b)
	{
		this.setCompatibilityButtonsEnabled(b);
		this.butReset.setEnabled(b);
		this.taNotes.setEditable(b);
		if (b)
		{
			this.taNotes.setBackground(Color.WHITE);
		}
		else
		{
			this.taNotes.setBackground(this.DISABLED_COLOR);
		}
		b = this.radCompatNo.isSelected();
		this.setSeverityButtonsEnabled(b);
		this.setPatchButtonsEnabled(b);
		this.taReason.setEditable(b);
		if (b)
		{
			this.taReason.setBackground(Color.WHITE);
		}
		else
		{
			this.taReason.setBackground(this.DISABLED_COLOR);
		}
		b = this.radPatchYes.isSelected();
		this.tfLink.setEditable(b);
		if (b)
		{
			this.tfLink.setBackground(Constants.MENU_BAR_COLOR);
		}
		else
		{
			this.tfLink.setBackground(this.DISABLED_COLOR);
		}
	}
	
	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case CANCEL:
				this.resetDefaults();
				this.setActiveModID("");
				break;
				
			case RESET:
				this.resetComponentsToDefaults();
				break;
				
			case SET:
				this.updateCurrentCompatibility();
				break;
		}
	}
	@Deprecated
	@Override
	public void refresh() 
	{}
	@Override
	public void resetDefaults() 
	{
		this.resetComponentsToDefaults();
		this.activeModID = "";
	}
	private ItemListener compatibilityListener = new ItemListener()
			{

				@Override
				public void itemStateChanged(ItemEvent e) 
				{
					boolean b = radCompatNo.isSelected();
					setSeverityButtonsEnabled(b);
					taReason.setEditable(b);
					if (b)
					{
						taReason.setBackground(Color.WHITE);
					}
					else
					{
						taReason.setBackground(DISABLED_COLOR);
					}
					setPatchButtonsEnabled(b);
			//		tfLink.setEditable(b);
				}
			};
	private ItemListener patchListener = new ItemListener()
			{
				@Override
				public void itemStateChanged(ItemEvent e)
				{
					if (radPatchNo.isSelected())
					{
						tfLink.setText("");
						tfLink.setEditable(false);
						tfLink.setBackground(DISABLED_COLOR);
					}
					else
					{
						tfLink.setEditable(true);
						tfLink.setBackground(Constants.MENU_BAR_COLOR);
					}
				}
			};
}