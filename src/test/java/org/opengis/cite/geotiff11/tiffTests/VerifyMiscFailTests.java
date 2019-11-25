package org.opengis.cite.geotiff11.tiffTests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.ISuite;
import org.testng.ITestContext;

/**
 * Verifies the behavior of the TiffTagsTests test class. Test stubs replace
 * fixture constituents where appropriate.
 */
public class VerifyMiscFailTests {

	// TODO: this should be expanded greatly...

	private static ITestContext testContext;	
	ShortParamsTests iut;	
	private static final String SUBJ = "testSubject";
	protected static ISuite suite;

	public VerifyMiscFailTests() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		//The below is specifically for those test cases that should fail.
		InputStream inputStream  = this.getClass().getResourceAsStream("/tif/MiscFailValues.txt");
		try {
			when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iut = new ShortParamsTests();	
	}


	@BeforeClass
	public static void setUpClass() throws Exception {
		testContext = mock(ITestContext.class);
		suite = mock(ISuite.class);
		when(testContext.getSuite()).thenReturn(suite);
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		
	}
	
	/* 
	 * A VerticalUnitsGeoKey value of 32767 (user defined) SHALL not be used
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyVerticalUnitsGeoKeyFail() throws Exception {			
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyVerticalUnitsGeoKey();
	}
	
	/* 
	 * If the EllipsoidGeoKey value is 32767 (User-Defined) then the 
	 * GTCitationGeoKey and the EllipsoidSemiMajorAxisGeoKey SHALL be 
	 * populated together with the one of either the EllipsoidSemiMinorAxisGeoKey 
	 * or the EllipsoidInvFlatteningGeoKey 
	 * [test case for both of them there (this one should fail)
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyEllipsoidGeoKeyFail() throws Exception {	
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyEllipsoidGeoKey();
	}
	
	
	/* 
	 * NULL (ASCII code = 0) characters SHALL not be present in the string content written in the GeoAsciiParamsTag
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyGeoAsciiParamsTagNULLWriteFail() throws Exception {	
		AsciiParamsTests iut2 = new AsciiParamsTests();
		iut2 = new AsciiParamsTests();	
		
		iut2.obtainTestSubject(testContext);
		iut2.setUpGeoKeyDirectory();
		iut2.setUpAsciiParamsSet();
		iut2.verifyGeoAsciiParamsTagNULLWrite();
	}
	

}