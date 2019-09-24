package org.opengis.cite.geotiff11.old.tiffBase;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class PhotometricInterpretation extends CommonTiffMeta {
	/**
	 * Verify the PhotometricInterpretation exists.
	 */
	@Test(description = "Checks PhotometricInterpretation")
	public void verifyPhotoInterprExists() {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("Photometric")));
	}
	
	/**
	 * This method verifies if the values of the photometric interpretation falls into one of the values. 
	 * The image has to have at least one of the options implemented for 1, 2, or 3
	 * The image has to be 4 which is a transparency mask only if the feature is present 
	 */
	@Test(description = "Checks verifyPhotometricInterpretationValue")
	public void verifyPhotometricInterpretationValue() {
		String line = this.list.stream().filter(item -> item.contains("Photometric")).findFirst().get();
		if (line != null) {
			Assert.assertTrue(verifyValueExists(line, "1") || verifyValueExists(line, "2")
					|| verifyValueExists(line, "3") || verifyValueExists(line, "4"));
		}
	}
}
