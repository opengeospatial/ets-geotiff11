package org.opengis.cite.geotiff11.tiffTests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
public class VerifyShortParamsTests extends CreateDataSets {

	// TODO: this should be expanded greatly...

	private static ITestContext testContext;	
	ShortParamsTests iut;

	public VerifyShortParamsTests() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
	}

	private void dataSetSetUp(InputStream inputStream)
	{
		testDataSets(inputStream);
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
	
	@Theory
	public void verifyGTModelTypeGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGTModelTypeGeoKey();
	}
	
	@Theory
	public void verifyGTRasterTypeGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGTRasterTypeGeoKey();
	}
	
	@Theory
	public void verifyGeodeticCRSGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeodeticCRSGeoKey();
	}
	
	@Theory
	public void verifyGeodeticDatumGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeodeticDatumGeoKey();
	}
	
	@Theory
	public void verifyPrimeMeridianGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyPrimeMeridianGeoKey();
	}
	
	@Theory
	public void verifyGeogLinearUnitsGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeogLinearUnitsGeoKey();
	}
	
	@Theory
	public void verifyGeogAngularUnitsGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeogAngularUnitsGeoKey();
	}
	
	@Theory
	public void verifyEllipsoidGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyEllipsoidGeoKey();
	}
	
	@Theory
	public void verifyGeogAzimuthUnitsGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeogAzimuthUnitsGeoKey();
	}
	
	@Theory
	public void verifyProjectedCRSGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjectedCRSGeoKey();
	}
	
	@Theory
	public void verifyProjectionGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjectionGeoKey();
	}
	
	@Theory
	public void verifyProjMethodGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjMethodGeoKey();
	}
	
	@Test(expected = AssertionError.class)
	public void verifyProjMethodGeoKeyReserved() throws Exception {	
		//use mockito to define value within the reserved range (28 to 32766).
		//and to expect an assertion error.
		
		InputStream inputStream  = this.getClass().getResourceAsStream("/tif/ReservedValues.txt");
		String SUBJ = "testSubject";
		when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
		
		ShortParamsTests iut2 = new ShortParamsTests() {	
			public int processFourthShortForShort(int index, int keyLength) {
				
				return 28;			
			}
		};
		
		iut2.obtainTestSubject(testContext);
		iut2.setUpGeoKeyDirectory();
		iut2.verifyProjMethodGeoKey();
	}
	
	@Theory
	public void verifyProjLinearUnitsGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjLinearUnitsGeoKey();
	}
	
	@Theory
	public void verifyVerticalUnitsGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyVerticalUnitsGeoKey();
	}
	
	@Theory
	public void verifyVerticalGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyVerticalGeoKey();
	}
	
	@Theory
	public void verifyVerticalDatumGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyVerticalDatumGeoKey();
	}
}