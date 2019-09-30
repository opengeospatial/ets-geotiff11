package org.opengis.cite.geotiff11.old.tiffBase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import java.util.List;

public class ImageCompression extends CommonTiffMeta {
	/**
	 * Verify the compression exists.
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks compression")
	public void verifyCompressionExists() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("Compression")));
	}

	/**
	 * Validates the various options compression can be. 1 is for those images
	 * that don't have a compression. TODO: How can I verify if the image has a
	 * compression? For the rest of the values that are not 1, That option is
	 * REQUIRED if the image is compressed. So I need to change the logic below
	 */
	@Test(description = "Checks verifyCompressionValue")
	public void verifyCompressionValue() {
		String line = this.list.stream().filter(item -> item.contains("Compression")).findFirst().get();
		if (line != null) {
			Assert.assertTrue(
					verifyValueExists(line, "1") || verifyValueExists(line, "2") || verifyValueExists(line, "32773")
							|| verifyValueExists(line, "5") || verifyValueExists(line, "6"));
		}
	}

}
