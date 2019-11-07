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
public class VerifyShortParamsTests {

	// TODO: this should be expanded greatly...
	
	private static final String SUBJ = "testSubject";
	private static ITestContext testContext;
	private static ISuite suite;
	ShortParamsTests iut;

	public VerifyShortParamsTests() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		InputStream inputStream = this.getClass().getResourceAsStream("/tif/ComprehensiveAndUserDefined.txt");
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
	
	@Test
	public void verifyGTModelTypeGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGTModelTypeGeoKey();
	}
	
	@Test
	public void verifyGTRasterTypeGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGTRasterTypeGeoKey();
	}
	
	@Test
	public void verifyGeodeticCRSGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeodeticCRSGeoKey();
	}
	
	@Test
	public void verifyGeodeticDatumGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeodeticDatumGeoKey();
	}
	
	@Test
	public void verifyPrimeMeridianGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyPrimeMeridianGeoKey();
	}
	
	@Test
	public void verifyGeogLinearUnitsGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeogLinearUnitsGeoKey();
	}
	
	@Test
	public void verifyGeogAngularUnitsGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeogAngularUnitsGeoKey();
	}
	
	@Test
	public void verifyEllipsoidGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyEllipsoidGeoKey();
	}
	
	@Test
	public void verifyGeogAzimuthUnitsGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeogAzimuthUnitsGeoKey();
	}
	
	@Test
	public void verifyProjectedCRSGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjectedCRSGeoKey();
	}
	
	@Test
	public void verifyProjectionGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjectionGeoKey();
	}
	
	@Test
	public void verifyProjMethodGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjMethodGeoKey();
	}
	
	@Test
	public void verifyProjLinearUnitsGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjLinearUnitsGeoKey();
	}
	
	@Test
	public void verifyVerticalUnitsGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyVerticalUnitsGeoKey();
	}
	
	@Test
	public void verifyVerticalGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyVerticalGeoKey();
	}
	
	@Test
	public void verifyVerticalDatumGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyVerticalDatumGeoKey();
	}
}