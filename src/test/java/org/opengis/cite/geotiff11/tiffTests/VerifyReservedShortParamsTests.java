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
import org.testng.ISuite;
import org.testng.ITestContext;

/**
 * Verifies the behavior of the TiffTagsTests test class. Test stubs replace fixture
 * constituents where appropriate.
 */
public class VerifyReservedShortParamsTests {

	// TODO: this should be expanded greatly...

	private static ITestContext testContext;

	ShortParamsTests iutReserved;

	private static final String SUBJ = "testSubject";

	protected static ISuite suite;

	public VerifyReservedShortParamsTests() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in
		// SuiteFixtureListener.java
		// The below is specifically for those test cases that should fail.
		InputStream inputStream = this.getClass().getResourceAsStream("/tif/ReservedValues.txt");
		try {
			when(suite.getAttribute(SUBJ)).thenReturn(IOUtils.toString(inputStream, StandardCharsets.UTF_8));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		iutReserved = new ShortParamsTests();
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

	/**
	 * Use mockito to define value within the reserved range (4-32766) and to expect an
	 * assertion error
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyGTModelTypeGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyGTModelTypeGeoKey();
	}

	/**
	 * Use mockito to define value within the reserved range (3-32766) and to expect an
	 * assertion error
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void verifyGTRasterTypeGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyGTRasterTypeGeoKey();
	}

	/*
	 * Use mockito to define value within the reserved range (1001-1023) and to expect an
	 * assertion error
	 *
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyProjectedCRSGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyProjectedCRSGeoKey();
	}

	/*
	 * Use mockito to define value within the reserved range (28 to 32766) and to expect
	 * an assertion error
	 *
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyProjMethodGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyProjMethodGeoKey();
	}

	/*
	 * Use mockito to define value within the reserved range (1001-1023) and to expect an
	 * assertion error
	 *
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyGeodeticCRSGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyGeodeticCRSGeoKey();
	}

	/*
	 * Use mockito to define value within the reserved range (1-1023) and to expect an
	 * assertion error
	 *
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyVerticalGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyVerticalGeoKey();
	}

	/*
	 * Use mockito to define value within the reserved range (1-1023) and to expect an
	 * assertion error
	 *
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyVerticalDatumGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyVerticalDatumGeoKey();
	}

	/*
	 * Use mockito to define value within the reserved range (1-1023) and to expect an
	 * assertion error
	 *
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyGeogAngularUnitsGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyGeogAngularUnitsGeoKey();
	}

	/*
	 * Use mockito to define value within the reserved range (1-1023) and to expect an
	 * assertion error
	 *
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyGeogAzimuthUnitsGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyGeogAzimuthUnitsGeoKey();
	}

	/*
	 * Use mockito to define value within the reserved range (1-1023) and to expect an
	 * assertion error
	 *
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyGeogLinearUnitsGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyGeogLinearUnitsGeoKey();
	}

	/*
	 * Use mockito to define value within the reserved range (1-1023) and to expect an
	 * assertion error
	 *
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyProjLinearUnitsGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyProjLinearUnitsGeoKey();
	}

	/*
	 * Use mockito to define value within the reserved range (1-1023) and to expect an
	 * assertion error
	 *
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyVerticalUnitsGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyVerticalUnitsGeoKey();
	}

	/*
	 * Use mockito to define value within the reserved range (1001-1023) and to expect an
	 * assertion error
	 *
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyGeodeticDatumGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyGeodeticDatumGeoKey();
	}

	/*
	 * Use mockito to define value within the reserved range (101-1023) and to expect an
	 * assertion error
	 *
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyPrimeMeridianGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyPrimeMeridianGeoKey();
	}

	/*
	 * Use mockito to define value within the reserved range (1-1023) and to expect an
	 * assertion error
	 *
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void verifyProjectionGeoKeyReserved() throws Exception {
		iutReserved.obtainTestSubject(testContext);
		iutReserved.setUpGeoKeyDirectory();
		iutReserved.verifyProjectionGeoKey();
	}

}