package org.opengis.cite.geotiff11.old.tiffBase;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This class checks the XY Resolution
 * @author RDAGCMVC
 *
 */
public class XYResolution extends CommonTiffMeta {
	/**
	 * Checking if the X resolution exists
	 */
	@Test(description = "Checks XResolution")
	public void verifyXResolutionExists() {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("XResolution")));
	}
	
/**
 * Checking if the Y resolution exists
 */
	@Test(description = "Checks YResolution")
	public void verifyYResolutionExists() {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("YResolution")));
	}
}
