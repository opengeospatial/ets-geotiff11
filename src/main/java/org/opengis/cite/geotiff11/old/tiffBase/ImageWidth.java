package org.opengis.cite.geotiff11.old.tiffBase;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class ImageWidth extends CommonTiffMeta{
	/**
	 * Verify the image width exists.
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks Image Width")
	public void verifyImageWidthExists() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("ImageWidth")));
	}

	/**
	 * Check if image width is of short or long value
	 */
	@Test(description = "Checks verifyImageWidthValueType")
	public void verifyImageWidthValueType() {
		String line = this.list.stream().filter(item -> item.contains("ImageWidth")).findFirst().get();
		if (line != null) {
			String trueValue = line.substring(line.indexOf("<") + 1);
			trueValue = trueValue.substring(0, trueValue.indexOf(">"));
			trueValue = trueValue.trim();
			
			Assert.assertTrue(StringUtils.isNumeric(trueValue) && (line.contains("SHORT") || line.contains("LONG")));
		}
	}
}