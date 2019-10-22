package org.opengis.cite.geotiff11.old.geotiffBase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.opengis.cite.geotiff11.old.geotiffBase.GeogCSParamKeys;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

@Test(enabled=false)
public class VerifyGeogCSParamKeys {

	private static final String SUBJ = "testSubjGeotiff";
	private static ITestContext testContext;
	private static ISuite suite;
	GeogCSParamKeys iut;

	public VerifyGeogCSParamKeys() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		InputStream inputStream = this.getClass().getResourceAsStream("/tmp/geotiffMeta.txt");
		try {
			when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.iut = new GeogCSParamKeys();
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

	@Test(expectedExceptions = NullPointerException.class)
	public void supplyNullTestSubject() throws SAXException, IOException {
		GeogCSParamKeys iut = new GeogCSParamKeys();
		iut.verifyGeogTypeGeoKey();
	}
	
	@Test
	public void verifyGeogTypeGeoKey() throws SAXException, IOException {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyGeogTypeGeoKey();
	}
	
	@Test
	public void verifyGeogTypeGeoValue() {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyGeogTypeGeoValue();
	}
	
	@Test
	public void verifyGeogCitationGeoKey() throws SAXException, IOException {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyGeogCitationGeoKey();
	}

	@Test
	public void verifyGeogCitationGeoValue() {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyGeogCitationGeoValue();
	}
}