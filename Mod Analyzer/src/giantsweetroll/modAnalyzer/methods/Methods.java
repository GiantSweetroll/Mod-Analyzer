package giantsweetroll.modAnalyzer.methods;

import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class Methods 
{
	public static void addDocumentFilter(JTextField tf, DocumentFilter filter)
	{
		((PlainDocument)tf.getDocument()).setDocumentFilter(filter);
	}
}
