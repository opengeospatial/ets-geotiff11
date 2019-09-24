package org.opengis.cite.geotiff11.old.tiffBase;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

public class MultiBand extends CommonTiffMeta {
	private int extraSamplesCount;

	/**
	 * getter method for extraSamplesCount
	 * @return
	 */
	public int getExtraSamplesCount() {
		return this.extraSamplesCount;
	}

	/**
	 * setter method for extraSamplesCount
	 * @param extraSamplesCount
	 */
	public void setExtraSamplesCount(int extraSamplesCount) {
		this.extraSamplesCount = extraSamplesCount;
	}

	/**
	 * It is only calculated and set once.
	 */
	@BeforeClass
	@Override
	public void obtainTestSubject(ITestContext testContext) {		
		super.obtainTestSubject(testContext);
		
		setExtraSamplesCount(calculateExtraSamplesCount());
	}

	/**
	 * Calculates the extra samples count
	 * 
	 * @return
	 */
	private int calculateExtraSamplesCount() {
		String extraSamplesCountString = this.list.stream().filter(item -> item.contains("ExtraSamples")).findFirst()
				.get();

		int index = extraSamplesCountString.indexOf("<");
		String trueValue = extraSamplesCountString.substring(extraSamplesCountString.indexOf("<") - 1, index);
		System.out.println("ExtraSamples count is: " + trueValue);
		int extraSamplesCountInt = Integer.parseInt(trueValue);
		return extraSamplesCountInt;
	}

	/**
	 * Multi-band criteria: SamplesPerPixel = 4;PhotometricInterpretation = 2;
	 * ExtraSamplesCount = (# of bands - 3). In other words ExtraSamplesCount
	 * must be greater than or equal to 1 for class B (Base) and greater than or
	 * equal to 2 for class MB (multi-band).
	 * 
	 * This method specifically checks the first part of the criteria since we
	 * are testing for ExtraSamples in this class
	 * 
	 * @return if the image is set up for multi-band
	 */
	public boolean isMultiBandCriteria() {
		String samplesPerPixel = this.list.stream().filter(item -> item.contains("SamplesPerPixel")).findFirst().get();
		String photoInterp = this.list.stream().filter(item -> item.contains("Photometric")).findFirst().get();

		boolean ret = verifyValueExists(samplesPerPixel, "4") && verifyValueExists(photoInterp, "2");
		System.out.println("isMultiBandCriteria: " + ret);
		return ret;
	}

	/**
	 * Multi-band criteria: SamplesPerPixel = 4;PhotometricInterpretation = 2;
	 * ExtraSamplesCount = (# of bands - 3). In other words ExtraSamplesCount
	 * must be greater than or equal to 1 for class B (Base) and greater than or
	 * equal to 2 for class MB (multi-band).
	 * 
	 * @return if the image is multi-band for class MB (Multi-band)
	 */
	public boolean isMultiBandMB() {
		boolean ret = isMultiBandCriteria() && getExtraSamplesCount() >= 2;
		System.out.println("isMultiBandMB: " + ret);
		return ret;
	}

	/**
	 * SamplesPerPixel = 4;PhotometricInterpretation = 2; ExtraSamplesCount = (#
	 * of bands - 3). In other words ExtraSamplesCount must be greater than or
	 * equal to 1 for class B (Base) and greater than or equal to 2 for class MB
	 * (multi-band).
	 * 
	 * @return if the image is multi-band for the class B (Baseline profile)
	 */
	public boolean isMultiBandBase() {
		boolean ret = isMultiBandMB() || (isMultiBandCriteria() && getExtraSamplesCount() == 1);
		System.out.println("isMultiBandBase: " + ret);
		return ret;
	}

}
