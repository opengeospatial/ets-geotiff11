package org.opengis.cite.geotiff11.tiffTests;

import org.testng.Assert;

import java.io.IOException;

import org.opengis.cite.geotiff11.tiffTests.CommonTiffMeta;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

// https://github.com/opengeospatial/geotiff/blob/5d6ab0ba54f1ed0174901dd84240817dc9dbe011/GeoTIFF_Standard/standard/abstract_tests/TIFF_Tests/TEST_TIFF_Core.adoc
public class TiffCoreTests extends CommonTiffMeta {

	/*
	 * TIFF Core Tests
	 * 
	 * Test id: http://www.opengis.net/spec/GeoTIFF/1.1/conf/Core
	 * 
	 * Requirements:
	 * 
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/TIFF
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/ByteOrder
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/DataTypes
	 * 
	 * Purpose: Verify that the GeoTIFF file conforms to the TIFF specification.
	 * Pre-conditions: None
	 * Test Variables: None
	 */
	
	@Test(description = "TIFF Core Endianness Test")
	public void verifyTiffEndianness() {		
		// verify endianness is 0x4949 or 0X4D4D
		Assert.assertTrue(tiffDump.getMagic().equals("0x4949") || tiffDump.getMagic().equals("0x4d4d"));	
	}
	
	@Test(description = "TIFF Core Version Test")
	public void verifyTiffVersion() {
		// TODO: will have to revisit this
		
		// verify version is 42
		Assert.assertTrue(tiffDump.getVersion().contains("2a") || tiffDump.getVersion().contains("a2"));
	}
}