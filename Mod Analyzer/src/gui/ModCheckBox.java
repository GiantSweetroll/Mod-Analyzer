package gui;

import java.util.Comparator;

import javax.swing.JCheckBox;

import dataDrivers.Mod;

public class ModCheckBox extends JCheckBox
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5128765770233649402L;

	private Mod mod;
	
	public ModCheckBox(Mod mod)
	{
		super(mod.getName());
		this.mod = mod;
	}
	
	//Public Methods
	public void setMod(Mod mod)
	{
		this.mod = mod;
		this.setText(mod.getName());
	}
	public Mod getMod()
	{
		return this.mod;
	}

	//Comparator
	public static final Comparator<ModCheckBox> MOD_CHECKER_COMPARATOR = new Comparator<ModCheckBox>()
			{

				@Override
				public int compare(ModCheckBox mod1, ModCheckBox mod2) 
				{
					return mod1.getText().compareTo(mod2.getText());
				}
			};
}
