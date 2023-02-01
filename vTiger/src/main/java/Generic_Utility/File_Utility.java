package Generic_Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class File_Utility {
	
	/**
	 * this method is used to read data from property file
	 * @param key
	 * @return
	 * @throws Throwable
	 * @author Admin
	 */

	public String getKeyAndValue(String key) throws Throwable
	{
		FileInputStream fis = new FileInputStream("./src/test/resources/vtigercommondata.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String value = pobj.getProperty(key);
		return value;
	}
}
