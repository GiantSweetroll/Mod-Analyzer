package gui.overview;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import constants.Constants;
import giantsweetroll.gui.swing.ScrollPaneManager;
import giantsweetroll.gui.swing.TextAreaManager;
import gui.WrappableJLabel;
import interfaces.FormEssentials;

public class GeneralCompatibilityPanel extends JPanel implements FormEssentials
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -335827532445419253L;

	private JLabel labGeneralCompat;
//					generalCompat;
	private WrappableJLabel taGeneralCompat;
	private JScrollPane scrollGeneralCompat;
	
	public GeneralCompatibilityPanel()
	{
		super();
		//Initialization
		this.labGeneralCompat = new JLabel(" ");
//		this.generalCompat = new JLabel();
		this.taGeneralCompat = new WrappableJLabel();
		this.scrollGeneralCompat = ScrollPaneManager.generateDefaultScrollPane(this.taGeneralCompat, 10, 10);
		
		//Properties
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.labGeneralCompat.setHorizontalAlignment(SwingConstants.CENTER);
		this.labGeneralCompat.setFont(Constants.GENERAL_FONT_BOLD);
		this.labGeneralCompat.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.labGeneralCompat.getMaximumSize().height));
		TextAreaManager.autoConfigureTextArea(this.taGeneralCompat, false);
		this.taGeneralCompat.setBorder(null);
		this.scrollGeneralCompat.setBorder(null);
		
		//Add to panel
		this.add(Box.createRigidArea(new Dimension(3, 3)));
		this.add(this.labGeneralCompat);
		this.add(Box.createRigidArea(new Dimension(Constants.INSETS_BASE, Constants.INSETS_BASE)));
//		this.add(this.generalCompat);		
		this.add(this.scrollGeneralCompat);
	}
	
	//Public Methods
	public void setData(String str)
	{
		if (!str.equals(""))
		{
			this.labGeneralCompat.setText("General Compatibility");
	//		this.generalCompat.setText(Methods.getWrappableText(str));		
			this.taGeneralCompat.setText(str);
			this.taGeneralCompat.setCaretPosition(0);
		}
	}

	//Interfaces
	@Deprecated
	@Override
	public void refresh()	{}
	@Override
	public void resetDefaults() 
	{
		this.labGeneralCompat.setText(" ");
	//	this.generalCompat.setText("");
		this.taGeneralCompat.setText("");
	}
}
