package org.opengis.cite.geotiff10.geotiffBase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opengis.cite.geotiff10.geotiffBase.ProjCSParamKeys;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.xml.sax.SAXException;

public class VerifyProjCSParamKeys {

	private static final String SUBJ = "testSubjGeotiff";
	private static ITestContext testContext;
	private static ISuite suite;
	ProjCSParamKeys iut;

	public VerifyProjCSParamKeys() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
		InputStream inputStream = this.getClass().getResourceAsStream("/tmp/geotiffMeta.txt");
		try {
			when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.iut = new ProjCSParamKeys();
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
		ProjCSParamKeys iut = new ProjCSParamKeys();
		iut.verifyProjectedCSTypeGeoKey();
	}

	@Test
	public void verifyProjectedCSTypeGeoKey() throws SAXException, IOException {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyProjectedCSTypeGeoKey();
	}
	
	@Test
	public void verifyProjectedCSTypeGeoValue()  {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyProjectedCSTypeGeoValue();
	}
	
	@Test
	public void verifyPCSCitationGeoKey() throws SAXException, IOException {
		this.iut.obtainTestSubject(testContext);
		this.iut.verifyPCSCitationGeoKey();
	}
}