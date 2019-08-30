package org.opengis.cite.geotiff10.tiffBase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class SamplesPerPixel extends CommonTiffMeta {
	/**
	 * Verifies if SamplesPerPixel exists
	 */
	@Test(description = "Checks SamplesPerPixel")
	public void verifySamplesPerPixelExists() {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("SamplesPerPixel")));
	}

	/**
	 * Verifies at least one of the options are implemented
	 */
	@Test(description = "Checks verifySamplesPerPixelValue")
	public void verifySamplesPerPixelValue() {
		String line = this.list.stream().filter(item -> item.contains("SamplesPerPixel")).findFirst().get();
		if (line != null) {
			Assert.assertTrue(
					verifyValueExists(line, "1") || verifyValueExists(line, "3") || verifyValueExists(line, "4"));
		}
	}
}
