package org.opengis.cite.geotiff11.old.tiffBase;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtraSamples extends MultiBand {
	/**
	 * Verifies if ExtraSamples exists
	 */
	@Test(description = "Checks ExtraSamples")
	public void verifyExtraSamplesExists() {
		if (isMultiBandCriteria()) {
			Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("ExtraSamples")));
		}
	}

	/**
	 * Multi-band criteria for Profile Baseline Class
	 */
	@Test(description = "Checks ExtraSamples Value; Multiband criteria for Profile Baseline Class")
	public void verifyExtraSamplesValue() {
		if (this.list.stream().anyMatch(item -> item.contains("ExtraSamples")) && isMultiBandCriteria()) {
			String line = this.list.stream().filter(item -> item.contains("ExtraSamples")).findFirst().get();
			if (line != null) {
				System.out.println("ExtraSamples detected and now testing if it follows class B");
				Assert.assertTrue(isMultiBandBase());
			}
		}/* else {
			org.testng.Assert.fail("no value exists to verify or is not multiband");
		}*/

	}
}
