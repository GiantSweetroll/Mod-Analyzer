package giantsweetroll.modAnalyzer.constants;

import java.awt.Insets;

public class Constants
{
	//Insets
	public static final int INSETS_GENERAL_SIZE = 5;
	public static final Insets INSETS_GENERAL = new Insets(Constants.INSETS_GENERAL_SIZE, 
															Constants.INSETS_GENERAL_SIZE, 
															Constants.INSETS_GENERAL_SIZE,
															Constants.INSETS_GENERAL_SIZE),
								INSETS_TOP_COMPONENT = new Insets(Constants.INSETS_GENERAL.top,
																	Constants.INSETS_GENERAL.left,
																	Constants.INSETS_GENERAL.bottom*2,
																	Constants.INSETS_GENERAL.right);
}
