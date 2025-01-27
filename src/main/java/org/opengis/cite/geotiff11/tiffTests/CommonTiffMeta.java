package org.opengis.cite.geotiff11.tiffTests;

import org.opengis.cite.geotiff11.CommonFixture;
import org.opengis.cite.geotiff11.SuiteAttribute;
import org.opengis.cite.geotiff11.util.TiffDump;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

/**
 * <p>
 * CommonTiffMeta class.
 * </p>
 *
 */
public class CommonTiffMeta extends CommonFixture {

	protected TiffDump tiffDump;

	/**
	 * Obtains the test subject from the ISuite context. The suite attribute
	 * {@link org.opengis.cite.geotiff11.SuiteAttribute#TEST_SUBJECT} should evaluate to a
	 * file node.
	 * @param testContext The test (group) context.
	 */
	@BeforeClass
	public void obtainTestSubject(ITestContext testContext) {
		// System.out.println("obtaining test subject.");
		Object obj = testContext.getSuite().getAttribute(SuiteAttribute.TEST_SUBJECT.getName());

		if (obj != null && obj instanceof String) {
			try {
				tiffDump = new TiffDump((String) obj);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
