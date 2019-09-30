package org.opengis.cite.geotiff11.old.geotiffBase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opengis.cite.geotiff11.old.geotiffBase.ConfigGeoKeys;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.xml.sax.SAXException;

/**
 * Verifies the behavior of the configuration GeoKeys test class. Test stubs replace
 * fixture constituents where appropriate.
 */
public class VerifyConfigGeoKeys {

	private static final String SUBJ = "testSubjGeotiff";
	private static ITestContext testContext;
	private static ISuite suite;
	ConfigGeoKeys iut;

	public VerifyConfigGeoKeys() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		InputStream inputStream = this.getClass().getResourceAsStream("/tmp/geotiffMeta.txt");
		try {
			when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.iut = new ConfigGeoKeys();
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
	public void supplyNullTestSubject() {
		ConfigGeoKeys iut = new ConfigGeoKeys();
		iut.verifyGTModelTypeGeoKey();
	}
	
	@Test
	public void verifyGTModelTypeGeoKey() {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyGTModelTypeGeoKey();
	}
	
	@Test
	public void verifyGTModelTypeGeoValue()  {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyGTModelTypeGeoValue();
	}
	
	@Test
	public void verifyGTRasterTypeGeoKey() throws SAXException, IOException {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyGTRasterTypeGeoKey();
	}
	
	@Test
	public void verifyGTRasterTypeGeoValue() {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyGTRasterTypeGeoValue();
	}
	
	@Test
	public void verifyGTCitationGeoKey() throws SAXException, IOException {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyGTCitationGeoKey();
	}

	
}
