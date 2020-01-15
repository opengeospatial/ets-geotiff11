package org.opengis.cite.geotiff11.tiffTests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.InputStream;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;
import org.opengis.cite.geotiff11.util.CreateDataSets;
import org.testng.ISuite;
import org.testng.ITestContext;

/**
 * Verifies the behavior of the TiffTagsTests test class. Test stubs replace
 * fixture constituents where appropriate.
 */
@RunWith(Theories.class)
public class VerifyDoubleParamsTests extends CreateDataSets{

	// TODO: this should be expanded greatly...
	
	private static ITestContext testContext;
	DoubleParamsTests iut;
	
	public VerifyDoubleParamsTests() {
		// This is the code for setting up the objects for the environment.
		// The code should be parallel with processSuiteParameters(ISuite suite) in SuiteFixtureListener.java
	}
	
	private void dataSetSetUp(InputStream inputStream)
	{
		testDataSets(inputStream);
		iut = new DoubleParamsTests();
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
	public void verifyGeogLinearUnitSizeGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeogLinearUnitSizeGeoKey();
	}
	
	@Theory
	public void verifyGeogAngularUnitSizeGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyGeogAngularUnitSizeGeoKey();
	}
	
	@Theory
	public void verifyEllipsoidSemiMajorAxisGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyEllipsoidSemiMajorAxisGeoKey();
	}
	
	@Theory
	public void verifyEllipsoidSemiMinorAxisGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyEllipsoidSemiMinorAxisGeoKey();
	}
	
	@Theory
	public void verifyEllipsoidInvFlatteningGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyEllipsoidInvFlatteningGeoKey();
	}
	
	@Theory
	public void verifyPrimeMeridianLongitudeGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyPrimeMeridianLongitudeGeoKey();
	}
	
	@Theory
	public void verifyProjLinearUnitSizeGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjLinearUnitSizeGeoKey();
	}
	
	@Theory
	public void verifyProjScalarParameters(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjScalarParameters();
	}
	
	@Theory
	public void verifyProjAzimuthAngleGeoKey(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjAzimuthAngleGeoKey();
	}
	
	@Theory
	public void verifyProjAngularParameters(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjAngularParameters();
	}
	
	@Theory
	public void verifyProjLinearParameters(InputStream inputStream) throws Exception {
		dataSetSetUp(inputStream);
		
		iut.obtainTestSubject(testContext);
		iut.setUpGeoKeyDirectory();
		iut.verifyProjLinearParameters();
	}
}