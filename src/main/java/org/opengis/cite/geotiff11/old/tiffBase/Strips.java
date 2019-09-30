package org.opengis.cite.geotiff11.old.tiffBase;

import java.io.IOException;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

/**
 * STRIPS is only required if the feature is present. (TODO: How do I check this??)
 * @author RDAGCMVC
 *
 */
public class Strips extends CommonTiffMeta {
	/**
	 * Verify the StripOffsets exists.
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks StripOffsets")
	public void verifyStripOffsetsExists() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("StripOffsets")));
	}
	
	/**
	 * Verify the RowsPerStrip exists.
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks RowsPerStrip")
	public void verifyRowsPerStripExists() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("RowsPerStrip")));
	}

	/**
	 * Verify the StripByteCounts exists.
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks StripByteCounts")
	public void verifyStripByteCountsExists() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("StripByteCounts")));
	}
}
