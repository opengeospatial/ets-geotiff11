package org.opengis.cite.geotiff11.tiffTests;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;

import org.opengis.cite.geotiff11.TestRunArg;
import org.opengis.cite.geotiff11.util.TestSuiteLogger;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import edu.harvard.hul.ois.jhove.App;
import edu.harvard.hul.ois.jhove.JhoveBase;
import edu.harvard.hul.ois.jhove.Module;
import edu.harvard.hul.ois.jhove.OutputHandler;
import edu.harvard.hul.ois.jhove.handler.XmlHandler;

// https://github.com/opengeospatial/geotiff/blob/5d6ab0ba54f1ed0174901dd84240817dc9dbe011/GeoTIFF_Standard/standard/abstract_tests/TIFF_Tests/TEST_TIFF_Core.adoc
/**
 * <p>
 * TiffCoreTests class.
 * </p>
 *
 */
public class TiffCoreTests extends CommonTiffMeta {

	private static final String CWD = System.getProperty("user.dir");

	private static final String TCR = "tiff-compliance-report.txt";

	private static final String FULL_TCR = Paths.get(CWD, TCR).toString();

	private String tiffPath;

	private static final String VALID = "";

	/**
	 * <p>
	 * Setter for the field <code>tiffPath</code>.
	 * </p>
	 * @param testContext a {@link org.testng.ITestContext} object
	 */
	@BeforeClass
	public void setTiffPath(ITestContext testContext) {
		ISuite suite = testContext.getSuite();
		Map<String, String> params = suite.getXmlSuite().getParameters();
		TestSuiteLogger.log(Level.CONFIG, "Suite parameters\n" + params.toString());
		tiffPath = params.get(TestRunArg.IUT.toString());
	}

	/*
	 * TIFF Core Tests
	 *
	 * Test id: http://www.opengis.net/spec/GeoTIFF/1.1/conf/Core
	 *
	 * Requirements:
	 *
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/TIFF
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ByteOrder
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/DataTypes
	 *
	 * Purpose: Verify that the GeoTIFF file conforms to the TIFF specification.
	 * Pre-conditions: None Test Variables: None
	 */

	/**
	 * <p>
	 * verifyTiffEndianness.
	 * </p>
	 */
	@Test(description = "TIFF Core Endianness Test") // may be covered by
														// verifyTiffVersionSixCompliance
														// but can't hurt
	public void verifyTiffEndianness() {
		// verify endianness is 0x4949 or 0X4D4D
		Assert.assertTrue(tiffDump.getMagic().equals("0x4949") || tiffDump.getMagic().equals("0x4d4d"));
	}

	/**
	 * <p>
	 * verifyTiffVersion.
	 * </p>
	 */
	@Test(description = "TIFF Core Version Test") // may be covered by
													// verifyTiffVersionSixCompliance but
													// can't hurt
	public void verifyTiffVersion() {
		// verify version is 42
		Assert.assertTrue(tiffDump.getVersion().contains("2a"));
	}

	// a GeoTIFF file SHALL be compliant with the TIFF 6.0 specification
	/**
	 * <p>
	 * verifyTiffVersionSixCompliance.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "TIFF Core 6.0 Compliance Test")
	public void verifyTiffVersionSixCompliance() throws Exception {

		// https://www.javatips.net/api/jhove-master/jhove-apps/src/main/java/Jhove.java
		App app = App.newAppWithName("Tiff Test");

		JhoveBase je = new JhoveBase();
		je.setLogLevel("SEVERE");
		je.setEncoding("utf-8");
		// je.setTempDirectory (Paths.get(tmpFile).getParent().toString());
		je.setBufferSize(4096);
		je.setChecksumFlag(false);
		je.setShowRawFlag(false);
		je.setSignatureFlag(false);

		Module tiffModule = je.getModule("TIFF-hul");
		// TiffModule tiffModule = new TiffModule();

		OutputHandler handler = new XmlHandler();

		boolean isValid = false;

		File tempFile = File.createTempFile("tiff-compliance-report", ".txt");

		tempFile.deleteOnExit();

		try {
			je.dispatch(app, tiffModule, null, handler, tempFile.getAbsolutePath(), new String[] { tiffPath });
		}
		catch (NullPointerException e) {
			Assert.fail("JHove failed to generate a compliance report");
		}

		Scanner scanner = new Scanner(tempFile);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.contains(VALID)) {
				isValid = true;
				break;
			}
		}

		scanner.close();
		// file.deleteOnExit();

		Assert.assertTrue(isValid, "a GeoTIFF file SHALL be compliant with the TIFF 6.0 specification");
	}

}
