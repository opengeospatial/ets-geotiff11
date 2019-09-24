package org.opengis.cite.geotiff11.old.tiffBase;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

/**
 * Includes various tests of capability 1.
 */
public class ImageDimensions extends CommonTiffMeta {
	/**
	 * Verify the image width exists. Image width is automatically in pixels
	 * according to the exiv2 specifications
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks image width")
	public void verifyImageWidthExists() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("ImageWidth")));
	}

	/**
	 * Verify the image length exists. Image length is automatically in pixels
	 * according to the exiv2 specifications
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks image length")
	public void verifyImageLengthExists() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("ImageLength")));
	}

}
