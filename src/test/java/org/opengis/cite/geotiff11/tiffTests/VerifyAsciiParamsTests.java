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
public class VerifyAsciiParamsTests {

	// TODO: this should be expanded greatly...
	
	private static final String SUBJ = "testSubject";
	private static ITestContext testContext;
	private static ISuite suite;
	AsciiParamsTests iut;

	public VerifyAsciiParamsTests() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		InputStream inputStream = this.getClass().getResourceAsStream("/tif/ComprehensiveAndUserDefined.txt");
		try {
			when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iut = new AsciiParamsTests();
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
	public void verifyGTCitationGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyGTCitationGeoKey();
	}
	
	@Test
	public void verifyGeodeticCitationGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyGeodeticCitationGeoKey();
	}
	
	@Test
	public void verifyProjectedCitationGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyProjectedCitationGeoKey();
	}
	
	@Test
	public void verifyVerticalCitationGeoKey() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyVerticalCitationGeoKey();
	}
	
	@Test
	public void verifyGeoAsciiParamsTagCount() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyGeoAsciiParamsTagCount();
	}
	
	@Test
	public void verifyGeoAsciiParamsTagType() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyGeoAsciiParamsTagType();
	}
	
	@Test
	public void verifyGeoAsciiParamsTagNULLWrite() throws Exception {
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyGeoAsciiParamsTagNULLWrite();
	}
}