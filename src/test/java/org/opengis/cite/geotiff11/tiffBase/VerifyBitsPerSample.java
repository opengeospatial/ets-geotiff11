package org.opengis.cite.geotiff10.tiffBase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opengis.cite.geotiff10.tiffBase.BitsPerSample;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.xml.sax.SAXException;

/**
 * Verifies the behavior of the Capability1Tests test class. Test stubs replace
 * fixture constituents where appropriate.
 */
public class VerifyBitsPerSample {

	private static final String SUBJ = "testSubject";
	private static ITestContext testContext;
	private static ISuite suite;
	BitsPerSample iut;

	public VerifyBitsPerSample() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		InputStream inputStream = this.getClass().getResourceAsStream("/tmp/tiffMeta.txt");
		try {
			when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.iut = new BitsPerSample();
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

	@Test(expected = NullPointerException.class)
	public void supplyNullTestSubject() throws SAXException, IOException {
		BitsPerSample iut = new BitsPerSample();
		iut.verifyBitsPerSampleExists();
	}

	@Test
	public void verifyBitsPerSampleExists() throws SAXException, IOException {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyBitsPerSampleExists();
	}

	@Test
	public void verifyBitsPerSampleValue() {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyBitsPerSampleValue();
	}

}
