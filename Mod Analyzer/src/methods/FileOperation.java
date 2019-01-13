package methods;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import constants.Constants;
import dataDrivers.Mod;
import giantsweetroll.date.Date;
import giantsweetroll.message.MessageManager;
import giantsweetroll.xml.dom.XMLManager;

public class FileOperation
{
	public static void saveModEntry(Mod mod)
	{
		Date dateCreated = mod.getDateCreated();
		String path = Constants.MOD_ENTRY_DATABASE_FOLDER + File.separator +
						Integer.toString(dateCreated.getYear()) + File.separator + 
						Integer.toString(dateCreated.getMonth()) + File.separator + 
						Integer.toString(dateCreated.getDay()) + File.separator;
		File file = new File(path + mod.getID() + Constants.MOD_ENTRY_FILE_EXTENSION);
		if (!file.getParentFile().exists())
		{
			file.getParentFile().mkdirs();
		}
		
		try 
		{
			XMLManager.exportXML(mod.getXMLDocument(), file, 5);
		} 
		catch (TransformerException e)
		{
			e.printStackTrace();
			MessageManager.showErrorDialog(e.getMessageAndLocation(), "Error when saving mod entry");
		}
	}
	public static Set<Mod> importAllMods()
	{
		Set<Mod> mods = new HashSet<Mod>();
		
		Set<File> modFiles = new HashSet<File>();
		getListOfFiles(modFiles, Constants.DATABASE_FOLDER, true);
		for (File file : modFiles)
		{
			try 
			{
				mods.add(new Mod(XMLManager.createDocument(file.getAbsolutePath(), false)));
			} 
			catch (ParserConfigurationException | SAXException | IOException e) {}
			catch (Exception ex) 
			{
				ex.printStackTrace();
				MessageManager.showErrorDialog(ex.getMessage(), "Something went wrong while loading mod entries");
				break;
			}
		}
		
		return mods;
	}
	
	public static Set<String> getListOfUsedModIDs()
	{
		Set<File> modFiles = new HashSet<File>();
		Set<String> ids = new HashSet<String>();
		
		FileOperation.getListOfFiles(modFiles, Constants.MOD_ENTRY_DATABASE_FOLDER, true);
		
		for (File file : modFiles)
		{
			String fileName = file.getName();
			int index = fileName.indexOf(Constants.MOD_ENTRY_FILE_EXTENSION);
			if (index!=-1)
			{
				ids.add(fileName.substring(0, index));
			}
		}
		
		return ids;
	}
	
	public static void getListOfFiles(Set<File> list, String directory, boolean openFolders)
	{
		if (openFolders)
		{
			File file = new File(directory);
			File[] files = file.listFiles();
			if (files != null)
			{
				for (File file2 : files)
				{
					if (file2.isDirectory())
					{
						getListOfFiles(list, file2.getAbsolutePath(), openFolders);
					}
					else
					{
						list.add(file2);
					}
				}
			}
		}
		else
		{
			File file = new File(directory);
			File[] files = file.listFiles();
			for (File file2 : files)
			{
				if (!file2.isDirectory())
				{
					list.add(file2);
				}
			}
		}
	}

	public static void deleteMod(Mod mod)
	{
		Date dateCreated = mod.getDateCreated();
		String path = Constants.MOD_ENTRY_DATABASE_FOLDER + File.separator +
						Integer.toString(dateCreated.getYear()) + File.separator + 
						Integer.toString(dateCreated.getMonth()) + File.separator + 
						Integer.toString(dateCreated.getDay()) + File.separator;
		File file = new File(path + mod.getID() + Constants.MOD_ENTRY_FILE_EXTENSION);
		file.delete();
	}
}
