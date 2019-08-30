package org.opengis.cite.geotiff10.tiffMultiBand;

import org.opengis.cite.geotiff10.tiffBase.MultiBand;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtraSamplesMB extends MultiBand {

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
	 * Verifies value if image is 5 to 8 bands
	 */
	@Test(description = "Checks ExtraSamples Value for multiband criteria of 5 to 8 bands")
	public void verifyExtraSamplesValue() {
		if (this.list.stream().anyMatch(item -> item.contains("ExtraSamples")) && isMultiBandCriteria()) {
			String line = this.list.stream().filter(item -> item.contains("ExtraSamples")).findFirst().get();
			if (line != null) {
				Assert.assertTrue(isMultiBandMB());
			}
		}

	}

}
