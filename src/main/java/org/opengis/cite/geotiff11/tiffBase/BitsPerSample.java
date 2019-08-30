package org.opengis.cite.geotiff10.tiffBase;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class BitsPerSample extends CommonTiffMeta {

	/**
	 * Verify the bits per sample exists.
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks bits per sample")
	public void verifyBitsPerSampleExists() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("BitsPerSample")));
	}

	/**
	 * The bits per sample can be any of those options. For 32, this option is
	 * for gridded data other than image. For imagery, constrained to 8 and 16-bits-per-pixel-per-band.
	 * For other gridded data, constrained to 8, 16, and 32 bits per range (sample) value.
	 *  TODO: How to check for gridded data?
	 */
	@Test(description = "Checks verifyBitsPerSampleValue")
	public void verifyBitsPerSampleValue() {
		String line = this.list.stream().filter(item -> item.contains("BitsPerSample")).findFirst().get();
		if (line != null) {
			Assert.assertTrue(verifyValueExists(line, "1") || verifyValueExists(line, "8")
					|| verifyValueExists(line, "16") || verifyValueExists(line, "32"));
		}
	}
}