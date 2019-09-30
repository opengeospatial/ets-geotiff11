package org.opengis.cite.geotiff11.old.tiffBase;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Only required if image is multiband 
 * TODO: How to know if this image is multiband?
 * @author RDAGCMVC
 *
 */
public class PlanarConfiguration extends MultiBand {
	
	/**
	 * Verify if PlanarConfiguration exists.
	 * Required if image is multi-band
	 */
	@Test(description = "Checks PlanarConfiguration")
	public void verifyPlanarConfigurationExists() {
		if (isMultiBandBase()){
			Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("PlanarConfig")));
		}		
	}
	
	/**
	 * Checks if the value is 1 or 2.
	 * Required if image is multi-band
	 */
	@Test(description = "Checks PlanarConfiguration value")
	public void verifyPlanarConfigurationValue() {
		String line = this.list.stream().filter(item -> item.contains("PlanarConfig")).findFirst().get();
		if (line != null && isMultiBandBase()) {
			Assert.assertTrue(verifyValueExists(line, "1") || verifyValueExists(line, "2"));
		}
	}
}
