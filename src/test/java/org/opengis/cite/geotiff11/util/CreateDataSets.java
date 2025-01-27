package org.opengis.cite.geotiff11.util;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.experimental.theories.DataPoint;
import org.testng.ISuite;

public class CreateDataSets {

	private static final String SUBJ = "testSubject";

	protected static ISuite suite;

	public static @DataPoint InputStream PrivateValuesFile = CreateDataSets.class
		.getResourceAsStream("/tif/PrivateValues.txt");

	public static @DataPoint InputStream UserDefinedFile = CreateDataSets.class
		.getResourceAsStream("/tif/ComprehensiveAndUserDefined.txt");

	public static @DataPoint InputStream EPSGValuesFile = CreateDataSets.class
		.getResourceAsStream("/tif/EPSGValues.txt");

	public static @DataPoint InputStream OneValuesFile = CreateDataSets.class.getResourceAsStream("/tif/OneValues.txt");

	public static @DataPoint InputStream TwoValuesFile = CreateDataSets.class.getResourceAsStream("/tif/TwoValues.txt");

	public static @DataPoint InputStream ThreeValuesFile = CreateDataSets.class
		.getResourceAsStream("/tif/ThreeValuesAndSupplement.txt");

	public static @DataPoint InputStream ZeroValuesFile = CreateDataSets.class
		.getResourceAsStream("/tif/ZeroValues.txt");

	public static @DataPoint InputStream AsciiValuesFile = CreateDataSets.class
		.getResourceAsStream("/tif/AsciiValues.txt");

	private static String privateValuesString;

	private static String userDefinedString;

	private static String EPSGValuesString;

	private static String OneValuesString;

	private static String TwoValuesString;

	private static String ThreeValuesString;

	private static String ZeroValuesString;

	private static String AsciiValuesString;

	public void testDataSets(InputStream inputStream) {

		try {
			// check each input to set value for validation
			String value = null;

			if (inputStream.equals(PrivateValuesFile)) {
				// set the return value for validation
				if (privateValuesString == null || privateValuesString.isEmpty()) {
					privateValuesString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
				}
				value = privateValuesString;
			}
			else if (inputStream.equals(UserDefinedFile)) {
				// set the return value for validation
				if (userDefinedString == null || userDefinedString.isEmpty()) {
					userDefinedString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
				}
				value = userDefinedString;
			}
			else if (inputStream.equals(EPSGValuesFile)) {
				// set the return value for validation
				if (EPSGValuesString == null || EPSGValuesString.isEmpty()) {
					EPSGValuesString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
				}
				value = EPSGValuesString;
			}
			else if (inputStream.equals(OneValuesFile)) {
				// set the return value for validation
				if (OneValuesString == null || OneValuesString.isEmpty()) {
					OneValuesString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
				}
				value = OneValuesString;
			}
			else if (inputStream.equals(TwoValuesFile)) {
				// set the return value for validation
				if (TwoValuesString == null || TwoValuesString.isEmpty()) {
					TwoValuesString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
				}
				value = TwoValuesString;
			}
			else if (inputStream.equals(ThreeValuesFile)) {
				// set the return value for validation
				if (ThreeValuesString == null || ThreeValuesString.isEmpty()) {
					ThreeValuesString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
				}
				value = ThreeValuesString;
			}
			else if (inputStream.equals(ZeroValuesFile)) {
				// set the return value for validation
				if (ZeroValuesString == null || ZeroValuesString.isEmpty()) {
					ZeroValuesString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
				}
				value = ZeroValuesString;
			}
			else if (inputStream.equals(AsciiValuesFile)) {
				// set the return value for validation
				if (AsciiValuesString == null || AsciiValuesString.isEmpty()) {
					AsciiValuesString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
				}
				value = AsciiValuesString;
			}

			when(suite.getAttribute(SUBJ)).thenReturn(value);

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
