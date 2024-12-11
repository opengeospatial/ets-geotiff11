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
 * Verifies the behavior of the TiffTagsTests test class. Test stubs replace fixture
 * constituents where appropriate.
 */
@RunWith(Theories.class)
public class VerifyTiffTagTests extends CreateDataSets {

	private static ITestContext testContext;

	TiffTagsTests iut;

	public VerifyTiffTagTests() {
	}

	private void dataSetSetUp(InputStream inputStream) {
		testDataSets(inputStream);
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

	@Theory
	public void verifyTiffTags(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);

		iut = new TiffTagsTests();
		iut.obtainTestSubject(testContext);
		iut.verifyTiffTags();
	}

}
