package org.opengis.cite.geotiff10.geotiffBase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

/**
 * Includes various tests of Geotiff Geographic Coordinate System Parameter Keys
 * category
 */
public class GeogCSParamKeys extends CommonGeotiffMeta {

	/**
	 * Verify the Geographic Type Geo Key
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks GeogTypeGeoKey")
	public void verifyGeogTypeGeoKey() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("GeographicTypeGeoKey")));
	}

	/**
	 * Verify if the GeographicTypeGeoKey has at least one of the options
	 * implemented
	 */
	@Test(description = "Check GeographicTypeGeoValue")
	public void verifyGeogTypeGeoValue() {
		String line = this.list.stream().filter(item -> item.contains("GeographicTypeGeoKey")).findFirst().get();
		if (line != null) {
			Assert.assertTrue(verifyValueExists(line, "GCS_WGS_84") || (verifyValueExists(line, "GCSE_WGS_84")));
		}
	}

	/**
	 * Verify the GeogCitationGeoKey
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks GeogCitationGeoKey")
	public void verifyGeogCitationGeoKey() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("GeogCitationGeoKey")));
	}

	/**
	 * Verify if the GeogCitationGeoKey has at least one of the options
	 * implemented
	 */
	@Test(description = "Check GeogCitationGeoValue")
	public void verifyGeogCitationGeoValue() {
		String line = this.list.stream().filter(item -> item.contains("GeogCitationGeoKey")).findFirst().get();
		if (line != null) {
			Assert.assertTrue(verifyValueExists(line, "WGS 84"));
		}
	}
	
}