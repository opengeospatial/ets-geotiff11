package org.opengis.cite.geotiff11.tiffTests;

import org.testng.Assert;
import org.testng.annotations.Test;

//import edu.harvard.hul.ois.jhove.RepInfo;
import edu.harvard.hul.ois.jhove.module.TiffModule;
import edu.harvard.hul.ois.jhove.module.tiff.TiffProfile;
//import edu.harvard.hul.ois.jhove.*;
import edu.harvard.hul.ois.jhove.module.tiff.TiffProfileGeoTIFF;

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
	
	// a GeoTIFF file SHALL be compliant with the TIFF 6.0 specification
	public void verifyTiffVersionSixCompliance() {
//		TiffModule tiff = new TiffModule();
//		//RepInfo.
//		tiff.parse(raf, new RepInfo());
//		new TDump();
		
		// TODO: should I manually complete this or use Jhove or ?
		
		//TiffProfile tiffProfile = new TiffProfile();
		
		//tiffProfile.satisfiesProfile(ifd);
	}
}