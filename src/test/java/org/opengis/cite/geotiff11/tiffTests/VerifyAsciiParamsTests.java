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
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.opengis.cite.geotiff11.util.CreateDataSets;
import org.testng.ISuite;
import org.testng.ITestContext;

/**
 * Verifies the behavior of the TiffTagsTests test class. Test stubs replace
 * fixture constituents where appropriate.
 */
@RunWith(Theories.class)
public class VerifyAsciiParamsTests extends CreateDataSets{

	// TODO: this should be expanded greatly...
	
	private static ITestContext testContext;
	AsciiParamsTests iut;

	public VerifyAsciiParamsTests() {
	}
	
	private void dataSetSetUp(InputStream inputStream)
	{
		testDataSets(inputStream);
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
	
	@Theory
	public void verifyGTCitationGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyGTCitationGeoKey();
	}
	
	@Theory
	public void verifyGeodeticCitationGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyGeodeticCitationGeoKey();
	}
	
	@Theory
	public void verifyProjectedCitationGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyProjectedCitationGeoKey();
	}
	
	@Theory
	public void verifyVerticalCitationGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyVerticalCitationGeoKey();
	}
	
	@Theory
	public void verifyGeoAsciiParamsTagCount(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyGeoAsciiParamsTagCount();
	}
	
	@Theory
	public void verifyGeoAsciiParamsTagType(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyGeoAsciiParamsTagType();
	}
	
	@Theory
	public void verifyGeoAsciiParamsTagNULLWrite(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.setUpAsciiParamsSet();
		iut.verifyGeoAsciiParamsTagNULLWrite();
	}
}