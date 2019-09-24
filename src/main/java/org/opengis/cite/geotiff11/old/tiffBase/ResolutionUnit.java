package org.opengis.cite.geotiff11.old.tiffBase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class ResolutionUnit extends CommonTiffMeta {

	/**
	 * Checks if the resolution unit exists
	 */
	@Test(description = "Checks ResolutionUnit")
	public void verifyResolutionUnitExists() {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("ResolutionUnit")));
	}

	/**
	 * Resolution Unit can only be a value of 2 (dot per inches)
	 */
	@Test(description = "Checks ResolutionUnit Value")
	public void verifyResolutionUnitValue() {
		String line = this.list.stream().filter(item -> item.contains("ResolutionUnit")).findFirst().get();
		if (line != null) {
			Assert.assertTrue(
					verifyValueExists(line, "2"));
		}
	}
}
