package org.opengis.cite.geotiff11.tiffTests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.opengis.cite.geotiff11.util.CreateDataSets;
import org.testng.ISuite;
import org.testng.ITestContext;

/**
 * Verifies the behavior of the TiffCoreTests test class. Test stubs replace
 * fixture constituents where appropriate.
 */
@RunWith(Theories.class)
public class VerifyTiffCoreTests extends CreateDataSets{

	private static ITestContext testContext;
	TiffCoreTests iut;

	public VerifyTiffCoreTests() {
	}
	
	private void dataSetSetUp(InputStream inputStream)
	{
		testDataSets(inputStream);
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
	
	@Theory
	public void verifyTiffVersion(InputStream inputStream) {
		dataSetSetUp(inputStream);
		
		iut = new TiffCoreTests();
		iut.obtainTestSubject(testContext);
		iut.verifyTiffVersion();
	}

	@Theory
	public void verifyTiffEndianness(InputStream inputStream) {
		dataSetSetUp(inputStream);
		
		iut = new TiffCoreTests();
		iut.obtainTestSubject(testContext);
		iut.verifyTiffEndianness();	
	}
}
