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
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;
import org.opengis.cite.geotiff11.util.CreateDataSets;
import org.testng.ISuite;
import org.testng.ITestContext;

/**
 * Verifies the behavior of the TiffTagsTests test class. Test stubs replace
 * fixture constituents where appropriate.
 */
@RunWith(Theories.class)
public class VerifyShortParamsTestFails {

	// TODO: this should be expanded greatly...

	private static ITestContext testContext;	
	ShortParamsTests iutReserved;	
	private static final String SUBJ = "testSubject";
	protected static ISuite suite;

	public VerifyShortParamsTestFails() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		//The below is specifically for those test cases that should fail.
		InputStream inputStream  = this.getClass().getResourceAsStream("/tif/ReservedValues.txt");
		try {
			when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iutReserved = new ShortParamsTests();	
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
	
	/**
	 * Use mockito to define value within the reserved range (4-32766)
	 * and to expect an assertion error
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyGTModelTypeGeoKeyReserved() throws Exception {					
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyGTModelTypeGeoKey();
	}
	
	/**
	 * Use mockito to define value within the reserved range (3-32766)
	 * and to expect an assertion error
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void verifyGTRasterTypeGeoKeyReserved() throws Exception {					
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyGTRasterTypeGeoKey();
	}
	
	/* 
	 * Use mockito to define value within the reserved range (1001-1023)
	 * and to expect an assertion error
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyProjectedCRSGeoKeyReserved() throws Exception {			
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyProjectedCRSGeoKey();
	}
	
	/* 
	 * Use mockito to define value within the reserved range (28 to 32766)
	 * and to expect an assertion error
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyProjMethodGeoKeyReserved() throws Exception {			
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyProjMethodGeoKey();
	}

}