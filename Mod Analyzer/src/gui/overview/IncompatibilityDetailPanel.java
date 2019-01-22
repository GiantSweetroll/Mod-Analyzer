package gui.overview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import constants.Globals;
import dataDrivers.Compatibility;
import dataDrivers.Mod;
import giantsweetroll.gui.swing.Gbm;
import gui.MainFrame;
import gui.TextFieldLabel;
import gui.WrappableJLabel;
import interfaces.FormEssentials;
import methods.Methods;

public class IncompatibilityDetailPanel extends JPanel implements ActionListener, FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5525708515327170302L;
	
	private JLabel 	labSeverity, 
					labReason, 
					labPatch,
					labPatchLink,
					labNotes, 
					severity, 
					patchAvailable;
	private WrappableJLabel reason, notes;
	private JButton butEdit;
	private TextFieldLabel patchLink;
	
	private JPanel panelButton, panelMain;
	
	private String modID;
	
//	private Border defaultBorder = BorderFactory.createLineBorder(Color.BLACK);
	
	public IncompatibilityDetailPanel()
	{
		super(new BorderLayout());
		this.init();
	}
	
	//Create GUI
	private void init()
	{
		//Initialization
		this.initPanelButton();
		this.initPanelMain();
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		panel.add(this.panelMain);
		
		this.add(panel, BorderLayout.CENTER);
		this.add(panelButton, BorderLayout.SOUTH);		
	}
	private void initPanelButton()
	{
		//Initialization
		this.panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.butEdit = new JButton("Edit");
	}
	private void initPanelMain()
	{
		//Initialization
		this.panelMain = new JPanel(new GridBagLayout());
		//Initialization
		this.labSeverity = new JLabel();
		this.severity = new JLabel();
		this.labReason = new JLabel();
		this.reason = new WrappableJLabel();
		this.labPatch = new JLabel();
		this.patchAvailable = new JLabel();
		this.labPatchLink = new JLabel();
		this.patchLink = new TextFieldLabel();
		this.labNotes = new JLabel();
		this.notes = new WrappableJLabel();
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.butEdit.addActionListener(this);
		this.labSeverity.setFont(Constants.GENERAL_FONT_BOLD);
		this.labReason.setFont(Constants.GENERAL_FONT_BOLD);
		this.labPatch.setFont(Constants.GENERAL_FONT_BOLD);
		this.labPatchLink.setFont(Constants.GENERAL_FONT_BOLD);
		this.labNotes.setFont(Constants.GENERAL_FONT_BOLD);
		
		//Add to panel
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_TOP_COMPONENT;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		this.panelMain.add(this.labSeverity, c);				//Severity Label
		Gbm.nextGridColumn(c, 2);
		c.gridwidth = 1;
		this.panelMain.add(this.severity, c);				//Severity
		Gbm.newGridLine(c);
		this.panelMain.add(this.labReason, c);				//Reason Label
		Gbm.newGridLine(c);
		c.gridwidth = 3;
		this.panelMain.add(this.reason, c);					//Reason
		Gbm.newGridLine(c);
		c.gridwidth = 2;
		this.panelMain.add(this.labPatch, c);				//Patch Available Label
		Gbm.nextGridColumn(c, 2);
		c.gridwidth = 1;
		this.panelMain.add(this.patchAvailable, c);			//Patch Available
		Gbm.newGridLine(c);
		Gbm.nextGridColumn(c);
		this.panelMain.add(this.labPatchLink, c);			//Patch Link label
		Gbm.nextGridColumn(c);
		this.panelMain.add(this.patchLink, c);				//Patch Link
		Gbm.newGridLine(c);
		this.panelMain.add(this.labNotes, c);				//Notes Label
		Gbm.newGridLine(c);
		c.gridwidth = 3;
		this.panelMain.add(this.notes, c);					//Notes		
	}
	
	//Public Methods
	//Setters
	public void setMod(Mod mod)
	{
		this.modID = mod.getID();
	}
	//Getters
	public String getModID()
	{
		return this.modID;
	}
	//Others
	public void display(Compatibility compat)
	{
		this.labSeverity.setText("Level of Severity:");
		this.labReason.setText("Reason:");
		this.labPatch.setText("Patch Available?");
		this.labPatchLink.setText("Link:");
		this.labNotes.setText("Notes:");	
		this.severity.setText(Methods.toUpperCase(compat.getSeverity()));
		this.reason.setText(compat.getReason());
		this.patchAvailable.setText(Methods.convertBooleanToYesNo(compat.isPatchAvailable()));
		this.patchLink.setText(compat.getPatchLink());
		this.notes.setText(compat.getNotes());
		this.panelButton.add(this.butEdit);
		this.repaint();
	}
	
	//Interfaces
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Globals.MOD_FORM.resetDefaults();
		try
		{
			Globals.MOD_FORM.setData(Globals.OVERVIEW.getSelectedMod());
			Globals.MAIN_FRAME.changeActivePanel(MainFrame.MOD_FORM);
		}
		catch(NullPointerException ex){}		
	}

	@Deprecated
	@Override
	public void refresh() {}

	@Override
	public void resetDefaults()
	{
		this.labSeverity.setText("");
		this.severity.setText("");
		this.labReason.setText("");
		this.reason.setText("");
		this.labPatch.setText("");
		this.patchAvailable.setText("");
		this.labPatchLink.setText("");
		this.patchLink.setText("");
		this.labNotes.setText("");
		this.notes.setText("");
		this.panelButton.remove(this.butEdit);
		this.repaint();
	}
}
