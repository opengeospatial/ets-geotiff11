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
 * Verifies the behavior of the TiffCoreTests test class. Test stubs replace
 * fixture constituents where appropriate.
 */
public class VerifyTiffCoreTests {

	private static final String SUBJ = "testSubject";
	private static ITestContext testContext;
	private static ISuite suite;
	TiffCoreTests iut;

	public VerifyTiffCoreTests() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		InputStream inputStream = this.getClass().getResourceAsStream("/tif/tiffMeta.txt");
		try {
			when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iut = new TiffCoreTests();
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
	public void verifyTiffVersion() {
		iut = new TiffCoreTests();
		iut.obtainTestSubject(testContext);
		iut.verifyTiffVersion();
	}

	@Test
	public void verifyTiffEndianness() {
		iut = new TiffCoreTests();
		iut.obtainTestSubject(testContext);
		iut.verifyTiffEndianness();	
	}
}
