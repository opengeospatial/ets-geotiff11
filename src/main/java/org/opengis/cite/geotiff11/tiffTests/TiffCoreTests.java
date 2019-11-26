package org.opengis.cite.geotiff11.tiffTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import edu.harvard.hul.ois.jhove.App;
import edu.harvard.hul.ois.jhove.JhoveBase;
import edu.harvard.hul.ois.jhove.JhoveException;
import edu.harvard.hul.ois.jhove.Module;
import edu.harvard.hul.ois.jhove.OutputHandler;
import edu.harvard.hul.ois.jhove.handler.XmlHandler;
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
	@Test(description = "TIFF Core 6.0 Compliance Test")
	public void verifyTiffVersionSixCompliance() throws Exception {
//		TiffModule tiff = new TiffModule();
//		tiff.parse(raf, new RepInfo());
//		new TDump();
		
		// TODO: should I manually complete this or use Jhove or ?
		
		//TiffProfile tiffProfile = new TiffProfile();
		//tiffProfile.satisfiesProfile(ifd);

		// https://www.javatips.net/api/jhove-master/jhove-apps/src/main/java/Jhove.java
		
		//App app = new App ("NAME", "RELEASE", new int[] {2019, 11, 29}, "USAGE", "RIGHTS");
		App app = App.newAppWithName("TEST");
		
		JhoveBase je = new JhoveBase();
		
//        je.setEncoding (encoding);
//        je.setTempDirectory (tempDir);
//        je.setBufferSize (bufferSize);
//        je.setChecksumFlag (checksum);
//        je.setShowRawFlag (showRaw);
//        je.setSignatureFlag (signature);
        
		Module module = new TiffModule();
		
//		OutputHandler handler = je.getHandler("XML");
		OutputHandler handler = new XmlHandler();
		
		// je.dispatch(app, module, null, handler, "C:\\Users\\RDAGCDLJ\\Desktop\\yeet.txt", new String[] {"C:\\Users\\RDAGCDLJ\\Documents\\FY20\\GeoTIFF\\example_tiffs\\cea.tif"});	
	}
}