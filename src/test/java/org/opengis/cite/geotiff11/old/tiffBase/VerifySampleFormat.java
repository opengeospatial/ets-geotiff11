package org.opengis.cite.geotiff11.old.tiffBase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opengis.cite.geotiff11.old.tiffBase.SampleFormat;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.xml.sax.SAXException;

public class VerifySampleFormat {
	private static final String SUBJ = "testSubject";
	private static ITestContext testContext;
	private static ISuite suite;
	SampleFormat iut;

	public VerifySampleFormat() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		InputStream inputStream = this.getClass().getResourceAsStream("/tmp/tiffMeta.txt");
		try {
			when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.iut = new SampleFormat();
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
		SampleFormat iut = new SampleFormat();
		iut.verifySampleFormatExists();
	}

	@Test
	public void verifySampleFormatExists() throws SAXException, IOException {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifySampleFormatExists();
	}

	@Test
	public void verifySampleFormatValue() {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifySampleFormatValue();
	}
}
