package org.opengis.cite.geotiff11.tiffTests;

import java.util.List;

import org.opengis.cite.geotiff11.util.TiffDump;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// https://github.com/opengeospatial/geotiff/blob/5d6ab0ba54f1ed0174901dd84240817dc9dbe011/GeoTIFF_Standard/standard/abstract_tests/TIFF_Tests/TEST_Short_Param.adoc
public class ShortParamsTests extends CommonTiffMeta {
	
	/*
	 * GeoKey Directory Test Test
	 * id: http://www.opengis.net/spec/GeoTIFF/1.1/conf/ShortParameters
	 * Requirements:
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.KeyEntry.ID
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyEntryTIFFTagLocation
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyEntryKeyCount
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyEntryValueOffset
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoShortParams.Criteria
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoShortParams.Location
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/GTModelTypeGeoKey
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/GTRasterTypeGeoKey
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeodeticCRSGeoKey
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeodeticDatumGeoKey
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/PrimeMeridianGeoKey
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/UnitsGeoKey
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/EllipsoidGeoKey
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjectedCRSGeoKey
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjectionGeoKey
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjMethodGeoKey
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/VerticalGeoKey
	 *  http://www.opengis.net/spec/GeoTIFF/1.1/req/VerticalDatumGeoKey
	 * Purpose: Verify a Short parameter
	 * Pre-conditions: The GeoKeyDirectory and GeoKeyOffset values have been set Test Variables:
	 * Test Variables: ...
	 */

	private TiffDump.Directory directory;
	private List<Object> keyEntrySet;
	
	@BeforeClass
	public void SetUpGeoKeyDirectory() {
		directory = tiffDump.getGeoKeyDirectory();
		Assert.assertTrue(directory != null);
		keyEntrySet = directory.getTag(34735).getValues();	
		Assert.assertTrue(keyEntrySet != null);
	}
	
/*	@Test(description = "GeoKey Directory Test	")
	public void verifyGeoKeyDirectoryTests() throws Exception {

		TiffDump.Directory directory = tiffDump.getGeoKeyDirectory();

		List<Object> keyEntrySet = directory.getTag(34735).getValues();
		if (keyEntrySet != null) {
			for(int i = 4; i < keyEntrySet.size(); i += 4) {							
				// process the second Short integer in the Key Entry Set
				int type = (int) keyEntrySet.get(i+1);
				
				// get the Short Params
				if(type == 0 || type == 34735) {
					// process the first Short integer in the Key Entry Set
					int geoKey = (int) keyEntrySet.get(i);
					// process the third Short integer in the Key Entry Set
					int keyLength = (int) keyEntrySet.get(i+2);
					
					int value;
					
					if(keyLength == 1) {
						// SET KeyValueOffset = GeoKeyDirectory + GeoKeyOffset + 6
						value = (int) keyEntrySet.get(i+3);
					} else {
						// SET KeyValueOffset = GeoKeyDirectory + (KeyValueOffset * 2)
						value = (int) keyEntrySet.get(keyLength); // TODO: verify this is a correct interpretation of the ats...
					}
					
					// validate
					if(geoKey == 0) {
						continue;
					}
					if(geoKey == 1024) {
						continue;
					}
				}	
			}
		}
	}*/
	
//	GeoKey	Requirements Class
//	0		ignore
//	1024	GTModelTypeGeoKey
	
	@Test(description = "GeoKey Directory - GTModelTypeGeoKey Test")
	public void GTModelTypeGeoKeyTest() {
		System.out.println(keyEntrySet.indexOf(1024));
	}
	
	


//	1025	GTRasterTypeGeoKey
//	2048	GeodeticCRSGeoKey
//	2050	GeodeticDatumGeoKey
//	2051	PrimeMeridianGeoKey
//	2052	UnitsGeoKey (Linear Units)
//	2054	UnitsGeoKey (Angular Units)
//	2056	EllipsoidGeoKey
//	2060	UnitsGeoKey (Azimuth Units)
//	3072	ProjectedCRSGeoKey
//	3074	ProjectionGeoKey
//	3075	ProjMethodGeoKey
//	3076	UnitsGeoKey (Linear Units)
//	4096	VerticalGeoKey
//	4098	VerticalDatumGeoKey
//	4099	UnitsGeoKey (Vertical Units)

}