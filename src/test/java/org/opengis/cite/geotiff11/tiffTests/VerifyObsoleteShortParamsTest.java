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
public class VerifyObsoleteShortParamsTest {

	// TODO: this should be expanded greatly...

	private static ITestContext testContext;	
	ShortParamsTests iut;	
	private static final String SUBJ = "testSubject";
	protected static ISuite suite;

	public VerifyObsoleteShortParamsTest() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		//The below is specifically for those test cases that should fail.
		InputStream inputStream  = this.getClass().getResourceAsStream("/tif/ObsoleteValues.txt");
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
	 * ProjectedCRSGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POC Datum Codes.
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyProjectedCRSGeoKeyObsolete() throws Exception {			
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjectedCRSGeoKey();
	}
	
	/* 
	 * GeodeticCRSGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POC Geographic Codes
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyGeodeticCRSGeoKeyObsolete() throws Exception {			
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeodeticCRSGeoKey();
	}
	
	/* 
	 * GeodeticDatumGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POS Datum Codes.
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyGeodeticDatumGeoKeyObsolete() throws Exception {			
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeodeticDatumGeoKey();
	}
	
	/* 
	 * PrimeMeridianGeoKey values in the range 1-100 SHALL be obsolete EPSG/POSC Datum Codes
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyPrimeMeridianGeoKeyObsolete() throws Exception {			
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyPrimeMeridianGeoKey();
	}
	
	/* 
	 * EllipsoidGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POSC Datum Codes
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyEllipsoidGeoKeyObsolete() throws Exception {	
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyEllipsoidGeoKey();
	}
	
	
	

}