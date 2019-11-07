package org.opengis.cite.geotiff11.tiffTests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;
import org.opengis.cite.geotiff11.SuiteAttribute;
import org.testng.ISuite;
import org.testng.ITestContext;

/**
 * Verifies the behavior of the TiffTagsTests test class. Test stubs replace
 * fixture constituents where appropriate.
 */
@RunWith(Theories.class)
public class VerifyDoubleParamsTests {

	// TODO: this should be expanded greatly...
	
	private static final String SUBJ = "testSubject";
	private static ITestContext testContext;
	private static ISuite suite;
	DoubleParamsTests iut;
	public static @DataPoint InputStream tiffMetaFile = VerifyDoubleParamsTests.class.getResourceAsStream("/tif/tiffMeta.txt"); 
	public static @DataPoint InputStream UserDefinedFile = VerifyDoubleParamsTests.class.getResourceAsStream("/tif/ComprehensiveAndUserDefined.txt");
	private static String tiffMetaString;
	private static String userDefinedString;
	
	public VerifyDoubleParamsTests() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
	}
	
	public void testDataSets(InputStream inputStream)
	{		
		try {
			String value = null;
			if(inputStream.equals(tiffMetaFile))
			{
				if (tiffMetaString == null || tiffMetaString.isEmpty())
				{
					tiffMetaString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);				
					System.out.println(tiffMetaString);			
				}				
				value = tiffMetaString;		
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
		iut = new DoubleParamsTests();
	}
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		testContext = mock(ITestContext.class);
		suite = mock(ISuite.class);
		when(testContext.getSuite()).thenReturn(suite);
	}

	@AfterClass
	public static void tearDownClass() throws Exception { }
	
	@Theory
	public void verifyGeogLinearUnitSizeGeoKey(InputStream inputStream) throws Exception {
		testDataSets(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeogLinearUnitSizeGeoKey();
	}
	
	@Theory
	public void verifyGeogAngularUnitSizeGeoKey(InputStream inputStream) throws Exception {
		testDataSets(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeogAngularUnitSizeGeoKey();
	}
	
	@Theory
	public void verifyEllipsoidSemiMajorAxisGeoKey(InputStream inputStream) throws Exception {
		testDataSets(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyEllipsoidSemiMajorAxisGeoKey();
	}
	
	@Theory
	public void verifyEllipsoidSemiMinorAxisGeoKey(InputStream inputStream) throws Exception {
		testDataSets(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyEllipsoidSemiMinorAxisGeoKey();
	}
	
	@Theory
	public void verifyEllipsoidInvFlatteningGeoKey(InputStream inputStream) throws Exception {
		testDataSets(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyEllipsoidInvFlatteningGeoKey();
	}
	
	@Theory
	public void verifyPrimeMeridianLongitudeGeoKey(InputStream inputStream) throws Exception {
		testDataSets(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyPrimeMeridianLongitudeGeoKey();
	}
	
	@Theory
	public void verifyProjLinearUnitSizeGeoKey(InputStream inputStream) throws Exception {
		testDataSets(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjLinearUnitSizeGeoKey();
	}
	
	@Theory
	public void verifyProjScalarParameters(InputStream inputStream) throws Exception {
		testDataSets(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjScalarParameters();
	}
	
	@Theory
	public void verifyProjAzimuthAngleGeoKey(InputStream inputStream) throws Exception {
		testDataSets(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjAzimuthAngleGeoKey();
	}
	
	@Theory
	public void verifyProjAngularParameters(InputStream inputStream) throws Exception {
		testDataSets(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjAngularParameters();
	}
	
	@Theory
	public void verifyProjLinearParameters(InputStream inputStream) throws Exception {
		testDataSets(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjLinearParameters();
	}
}