package org.opengis.cite.geotiff10.geotiffBase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class ProjCSParamKeys extends CommonGeotiffMeta {

	/**
	 * Verify the ProjectedCSTypeGeoKey
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks ProjectedCSTypeGeoKey")
	public void verifyProjectedCSTypeGeoKey() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("ProjectedCSTypeGeoKey")));
	}

	/**
	 * Verify if the GeographicTypeGeoKey has at least one of the options
	 * implemented
	 * 
	 * PCS_WGS84_UTM_zone_1N or PCS_WGS84_UTM_zone_1S where the 1 can be any
	 * number between 1-60
	 */
	@Test(description = "Check ProjectedCSTypeGeoValue")
	public void verifyProjectedCSTypeGeoValue() {
		String line = this.list.stream().filter(item -> item.contains("ProjectedCSTypeGeoKey")).findFirst().get();
		if (line != null) {
			Assert.assertTrue(verifyValueExists(line, "UTM") || (verifyNorthorSouth(line)));
		}
	}

	/**
	 * This is a helper method for verifyProjectedCSTypeGeoValue method. Checks
	 * that the last character of the string is either N for North or S for
	 * South
	 * 
	 * @param line
	 * @return
	 */
	private boolean verifyNorthorSouth(String line) {
		char ch = line.charAt(line.length() - 1);
		return (ch == 'N') || (ch == 'S');
	}

	/**
	 * Verify the GeogAngularUnitsGeoKey
	 *
	 * @throws SAXException
	 *             If the resource cannot be parsed.
	 * @throws IOException
	 *             If the resource is not accessible.
	 */
	@Test(description = "Checks PCSCitationGeoKey")
	public void verifyPCSCitationGeoKey() throws SAXException, IOException {
		Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("PCSCitationGeoKey")));
	}
}