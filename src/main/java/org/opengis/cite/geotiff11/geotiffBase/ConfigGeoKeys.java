package org.opengis.cite.geotiff10.geotiffBase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

/**
 * Includes various tests of Geotiff Config GeoKeys category
 */
public class ConfigGeoKeys extends CommonGeotiffMeta {

   /**
    * Verifies GTModelTypeGeoKey exists
    */
	@Test(description = "Checks GTModelTypeGeoKey")
	public void verifyGTModelTypeGeoKey()  {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("GTModelTypeGeoKey")));
	}

	/**
	 * Verify if the GTModelTypeGeoKey has at least one of the options
	 * implemented
	 * 
	 * ModelTypeGeographic or ModelTypeProjected
	 */
	@Test(description = "Check verifyGTModelTypeGeoValue")
	public void verifyGTModelTypeGeoValue() {
		String line = this.list.stream().filter(item -> item.contains("GTModelTypeGeoKey")).findFirst().get();
		if (line != null) {
			Assert.assertTrue(verifyValueExists(line, "Projected") || (verifyValueExists(line, "Geographic")));
		}
	}

	/**
	 * Verify the GTRasterTypeGeoKey
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks GTRasterTypeGeoKey")
	public void verifyGTRasterTypeGeoKey() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("GTRasterTypeGeoKey")));
	}

	/**
	 * Verify if the GTRasterTypeGeoKey has one of the options implemented
	 * 
	 * RasterPixelIsPoint or RasterPixelIsArea
	 */
	@Test(description = "Checks verifyGTRasterTypeGeoValue")
	public void verifyGTRasterTypeGeoValue() {
		String line = this.list.stream().filter(item -> item.contains("GTRasterTypeGeoKey")).findFirst().get();
		if (line != null) {
			Assert.assertTrue(verifyValueExists(line, "Area") || (verifyValueExists(line, "Point")));
		}
	}

	/**
	 * Verify the GTCitationGeoKey
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks GTCitationGeoKey")
	public void verifyGTCitationGeoKey() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("GTCitationGeoKey")));
	}

	/* TODO validate the value of this key
	public void verifyGTCitationGeoValue()
	{
		
	}*/
}
