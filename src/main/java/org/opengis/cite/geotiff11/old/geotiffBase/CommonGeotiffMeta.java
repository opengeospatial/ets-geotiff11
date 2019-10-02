package org.opengis.cite.geotiff11.old.geotiffBase;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.opengis.cite.geotiff11.CommonFixture;
import org.opengis.cite.geotiff11.SuiteAttribute;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

public class CommonGeotiffMeta extends CommonFixture {
	private File testSubject;
	List<String> list;

	/**
	 * TODO: Planning on redoing this. Prefer a testsubject in a geotiff class or dao.
	 * 
	 * Obtains the test subject from the ISuite context. The suite attribute
	 * {@link org.opengis.cite.geotiff11.SuiteAttribute#TEST_SUBJECT} should
	 * evaluate to a file node.
	 * 
	 * @param testContext
	 *            The test (group) context.
	 */
	@BeforeClass
	public void obtainTestSubject(ITestContext testContext) {
		Object obj = testContext.getSuite().getAttribute(SuiteAttribute.TEST_SUBJ_GEOTIFF.getName());
		
		if (null != obj) {			
			InputStream input = IOUtils.toInputStream(obj.toString(), StandardCharsets.UTF_8);
			if (input != null)
			{
				list = parseFile(input);
				//System.out.println(list);
			}
		}
	}

	/**
	 * Sets the test subject. This method is intended to facilitate unit
	 * testing.
	 *
	 * @param testSubject
	 *            A Document node representing the test subject or metadata
	 *            about it.
	 */
	public void setTestSubject(File testSubject) {
		this.testSubject = testSubject;
	}

	/**
	 * * The current geoTIFF file is formatted with a key per line and the value
	 * after the colon
	 * 
	 * For example: GTModelTypeGeoKey (Short,1): ModelTypeProjected
	 * 
	 * @param line
	 * 			line to extract value from
	 * @param value
	 * 			value to check in line
	 * @return
	 * 			return whether or not the value exists in the line
	 */
	public boolean verifyValueExists(String line, String value) {
		String[] separated = line.split(":");
		return separated[separated.length - 1].trim().contains(value);
	}
}
