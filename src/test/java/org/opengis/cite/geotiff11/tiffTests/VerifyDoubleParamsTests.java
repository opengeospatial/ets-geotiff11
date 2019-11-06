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
public class VerifyDoubleParamsTests {

	// TODO: this should be expanded greatly...
	
	private static final String SUBJ = "testSubject";
	private static ITestContext testContext;
	private static ISuite suite;
	DoubleParamsTests iut;

	public VerifyDoubleParamsTests() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		InputStream inputStream = this.getClass().getResourceAsStream("/tif/tiffMeta.txt");
		try {
			when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
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
	
	@Test
	public void verifyGeogLinearUnitSizeGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeogLinearUnitSizeGeoKey();
	}
	
	@Test
	public void verifyGeogAngularUnitSizeGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeogAngularUnitSizeGeoKey();
	}
	
	@Test
	public void verifyEllipsoidSemiMajorAxisGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyEllipsoidSemiMajorAxisGeoKey();
	}
	
	@Test
	public void verifyEllipsoidSemiMinorAxisGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyEllipsoidSemiMinorAxisGeoKey();
	}
	
	@Test
	public void verifyEllipsoidInvFlatteningGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyEllipsoidInvFlatteningGeoKey();
	}
	
	@Test
	public void verifyPrimeMeridianLongitudeGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyPrimeMeridianLongitudeGeoKey();
	}
	
	@Test
	public void verifyProjLinearUnitSizeGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjLinearUnitSizeGeoKey();
	}
	
	@Test
	public void verifyProjScalarParameters() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjScalarParameters();
	}
	
	@Test
	public void verifyProjAzimuthAngleGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjAzimuthAngleGeoKey();
	}
	
	@Test
	public void verifyProjAngularParameters() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjAngularParameters();
	}
	
	@Test
	public void verifyProjLinearParameters() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjLinearParameters();
	}
}