package org.opengis.cite.geotiff11.old.transparencyMask;

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
import org.xml.sax.SAXException;

/**
 * Verifies the behavior of the NewSubfileType test class. Test stubs replace
 * fixture constituents where appropriate.
 */
public class VerifyNewSubfileType {
	private static final String SUBJ = "testSubject";
	private static ITestContext testContext;
	private static ISuite suite;
	NewSubfileType iut;

	public VerifyNewSubfileType() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		InputStream inputStream = this.getClass().getResourceAsStream("/tmp/tiffMeta.txt");
		try {
			when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.iut = new NewSubfileType();
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
		NewSubfileType iut = new NewSubfileType();
		iut.verifyNewSubfileTypeExists();
	}

	@Test
	public void verifyNewSubfileTypeValue() throws SAXException, IOException {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyNewSubfileTypeValue();
	}
}
