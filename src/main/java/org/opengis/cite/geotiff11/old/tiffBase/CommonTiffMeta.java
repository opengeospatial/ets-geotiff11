package org.opengis.cite.geotiff11.old.tiffBase;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.opengis.cite.geotiff11.CommonFixture;
import org.opengis.cite.geotiff11.SuiteAttribute;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

/**
 * Eventually can probably combine this class with CommonGeotiffMeta
 * 
 * @author RDAGCMVC
 *
 */
public class CommonTiffMeta extends CommonFixture {
	protected List<String> list;

	/**
	 * Obtains the test subject from the ISuite context. The suite attribute
	 * {@link org.opengis.cite.geotiff10.SuiteAttribute#TEST_SUBJECT} should
	 * evaluate to a file node.
	 * 
	 * @param testContext
	 *            The test (group) context.
	 */
	@BeforeClass
	public void obtainTestSubject(ITestContext testContext) {
		System.out.println("obtaining test subject.");
		Object obj = testContext.getSuite().getAttribute(SuiteAttribute.TEST_SUBJECT.getName());

		// TODO: put obj into tiffdump.class rather than inputstream
		
		if (null != obj) {
			InputStream input = IOUtils.toInputStream(obj.toString(), StandardCharsets.UTF_8);
			if (input != null) {
				list = parseFile(input);
				System.out.println(list);
			}
		}
	}

	/**
	 * * The current TIFF file is formatted in a specific way
	 * 
	 * For example: {@literal ImageWidth (256) SHORT (3) 1<514>}
	 * 
	 * @param line
	 *            line to extract value from
	 * @param value
	 *            value to check in line
	 * @return return whether or not the value exists in the line
	 */
	public boolean verifyValueExists(String line, String value) {
		System.out.println("The value to verify: " + value);
		
		String[] separated = extractValues(line);
		return Arrays.asList(separated).contains(value);
	}

	protected String[] extractValues(String line) {
		System.out.println("The line before parsing is: " + line);
		
		String trueValues = line.substring(line.indexOf("<") + 1);
		trueValues = trueValues.substring(0, trueValues.indexOf(">"));
		String[] separated = trueValues.split("\\s+");
		System.out.println("The value string array is: " + Arrays.toString(separated));
		return separated;

	}
}
