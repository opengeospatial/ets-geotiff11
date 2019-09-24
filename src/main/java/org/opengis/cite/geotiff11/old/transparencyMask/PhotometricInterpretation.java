package org.opengis.cite.geotiff11.old.transparencyMask;

import org.opengis.cite.geotiff11.old.tiffBase.CommonTiffMeta;
import org.testng.Assert;
import org.testng.annotations.Test;


public class PhotometricInterpretation extends CommonTiffMeta  {

	/**
	 * Verify the PhotometricInterpretation exists specifically for a transparency mask requirement.
	 */
	@Test(description = "Checks PhotometricInterpretation")
	public void verifyPhotoInterprTranspMaskExists() {
		String bitsPerSampleLine = this.list.stream().filter(item -> item.contains("BitsPerSample")).findFirst().get();
		String samplesPerPixelLine = this.list.stream().filter(item -> item.contains("SamplesPerPixel")).findFirst().get();
		if (bitsPerSampleLine != null && verifyValueExists(bitsPerSampleLine, "1")
				&& samplesPerPixelLine != null && verifyValueExists(samplesPerPixelLine, "1")) {
			Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("Photometric")));
		}
	}
	
	/**
	 * This method verifies if the values of the photometric interpretation falls into one of the values. 
	 * The image has to have at least one of the options implemented for 1, 2, or 3
	 * The image has to be 4 which is a transparency mask only if the feature is present.
	 * 
	 *  This method checks the transparency mask portion
	 */
	@Test(description = "Checks verifyPhotomInterprTranspMaskValue")
	public void verifyPhotomInterprTranspMaskValue() {
		String line = this.list.stream().filter(item -> item.contains("Photometric")).findFirst().get();
		if (line != null) {
			Assert.assertTrue(verifyValueExists(line, "4"));
		}
	}
}
