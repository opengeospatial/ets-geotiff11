package org.opengis.cite.geotiff11.util;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.experimental.theories.*;
import org.testng.ISuite;

public class CreateDataSets {
	private static final String SUBJ = "testSubject";
	protected static ISuite suite;
	public static @DataPoint InputStream PrivateValuesFile = CreateDataSets.class.getResourceAsStream("/tif/PrivateValues.txt"); 
	public static @DataPoint InputStream UserDefinedFile = CreateDataSets.class.getResourceAsStream("/tif/ComprehensiveAndUserDefined.txt");
	public static @DataPoint InputStream EPSGValuesFile = CreateDataSets.class.getResourceAsStream("/tif/EPSGValues.txt");
	private static String privateValuesString;
	private static String userDefinedString;
	private static String EPSGValuesString;
	
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
			if (inputStream.equals(EPSGValuesFile))
			{
				if (EPSGValuesString == null || EPSGValuesString.isEmpty())
				{
					EPSGValuesString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);				
					System.out.println(EPSGValuesString);
				}
				value = EPSGValuesString;
			}

			when(suite.getAttribute(SUBJ)).thenReturn(value);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
