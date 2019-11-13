package org.opengis.cite.geotiff11.util;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;
import org.testng.ISuite;

public class CreateDataSets {
	private static final String SUBJ = "testSubject";
	protected static ISuite suite;
	public static @DataPoint InputStream PrivateValuesFile = CreateDataSets.class.getResourceAsStream("/tif/PrivateValues.txt"); 
	public static @DataPoint InputStream UserDefinedFile = CreateDataSets.class.getResourceAsStream("/tif/ComprehensiveAndUserDefined.txt");
	private static String privateValuesString;
	private static String userDefinedString;
	
	
	public void testDataSets(InputStream inputStream)
	{		
		try {
			String value = null;
			if(inputStream.equals(PrivateValuesFile))
			{
				if (privateValuesString == null || privateValuesString.isEmpty())
				{
					privateValuesString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);				
					System.out.println(privateValuesString);			
				}				
				value = privateValuesString;		
			}
			
			if (inputStream.equals(UserDefinedFile))
			{
				if (userDefinedString == null || userDefinedString.isEmpty())
				{
					userDefinedString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);				
					System.out.println(userDefinedString);
				}
				value = userDefinedString;
			}

			when(suite.getAttribute(SUBJ)).thenReturn(value);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
