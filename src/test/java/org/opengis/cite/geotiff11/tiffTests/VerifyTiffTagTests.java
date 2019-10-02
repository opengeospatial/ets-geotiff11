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
import org.opengis.cite.geotiff11.old.tiffBase.BitsPerSample;
import org.opengis.cite.geotiff11.old.tiffBase.ResolutionUnit;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.xml.sax.SAXException;

/**
 * Verifies the behavior of the Capability1Tests test class. Test stubs replace
 * fixture constituents where appropriate.
 */
public class VerifyTiffTagTests {

	private static final String SUBJ = "testSubject";
	private static ITestContext testContext;
	private static ISuite suite;
	TiffTagsTests iut;

	public VerifyTiffTagTests() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		InputStream inputStream = this.getClass().getResourceAsStream("/tmp/tiffMeta.txt");
		try {
			when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iut = new TiffTagsTests();
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
	public void verifyTiffTags() throws Exception {
		iut = new TiffTagsTests();
		iut.obtainTestSubject(testContext);
		iut.verifyTiffTags();
	}
}
