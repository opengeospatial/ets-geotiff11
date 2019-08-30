package org.opengis.cite.geotiff10.tiffBase;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class ImageLength extends CommonTiffMeta{
	/**
	 * Verify the image length exists.
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks Image Length")
	public void verifyImageLengthExists() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("ImageLength")));
	}

	/**
	 * Check if image length is of short or long value
	 */
	@Test(description = "Checks verifyImageLengthValueType")
	public void verifyImageLengthValueType() {
		String line = this.list.stream().filter(item -> item.contains("ImageLength")).findFirst().get();
		if (line != null) {
			String trueValue = line.substring(line.indexOf("<") + 1);
			trueValue = trueValue.substring(0, trueValue.indexOf(">"));
			trueValue = trueValue.trim();
			
			Assert.assertTrue(StringUtils.isNumeric(trueValue) && (line.contains("SHORT") || line.contains("LONG")));
		}
	}
}