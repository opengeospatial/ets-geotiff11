package org.opengis.cite.geotiff10.tiffBase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class SampleFormat extends CommonTiffMeta{
	
	/**
	 * Checking if the SampleFormat tag exists
	 */
	@Test(description = "Checks SampleFormat")
	public void verifySampleFormatExists() {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("SampleFormat")));
	}
	
	/**
	 * Sample format must have at least one of the options implemented
	 */
	@Test(description = "Checks verifySampleFormatValue")
	public void verifySampleFormatValue() {
		String line = this.list.stream().filter(item -> item.contains("SampleFormat")).findFirst().get();
		if (line != null) {
			Assert.assertTrue(
					verifyValueExists(line, "1") || verifyValueExists(line, "2") || verifyValueExists(line, "3"));
		}
	}
}
