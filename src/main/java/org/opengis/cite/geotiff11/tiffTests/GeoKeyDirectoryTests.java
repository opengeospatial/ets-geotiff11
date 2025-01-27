package org.opengis.cite.geotiff11.tiffTests;

import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOKEYDIRECTORYTAG;

import java.util.List;

import org.opengis.cite.geotiff11.util.TiffDump;
import org.testng.Assert;
import org.testng.annotations.Test;

// https://github.com/opengeospatial/geotiff/blob/5d6ab0ba54f1ed0174901dd84240817dc9dbe011/GeoTIFF_Standard/standard/abstract_tests/TIFF_Tests/TEST_GeoKeyDirectory.adoc
/**
 * <p>
 * GeoKeyDirectoryTests class.
 * </p>
 *
 */
public class GeoKeyDirectoryTests extends CommonTiffMeta {

	// TODO: execute tests [...]

	/*
	 * GeoKey Directory Test Test
	 * id: http://www.opengis.net/spec/GeoTIFF/1.1/conf/GeoKeyDirectory Requirements:
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyDirectoryVersion
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.
	 * keyDirectoryVersionValue
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyRevision
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyRevisionValue
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.minorRevision
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.minorRevisionValue
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.numberOfKeys
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyEntrySetCount
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.KeyEntry
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.KeyEntryKeyID
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.
	 * KeyEntryTIFFTagLocation http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeySort
	 * Purpose: Verify the TIFF header and prepare for processing the GeoTIFF tags.
	 * Pre-conditions: None Test Variables: Variable Scope Description IFD_Offset Local
	 * Location within the TIFF file of an IFD GeoKeyDirectory Global Location of the
	 * GeoTIFF GeoKey directory ASCIIValues Global Location of the ASCII values for
	 * GeoTIFF ASCII GeoKeys DoubleValues Global Location of the Double values for GeoTIFF
	 * Double GeoKeys GeoKeyOffset Parameter Location of this GeoKey in the GeoKey
	 * directory KeySetCount Local The number of entries in the GeoKey directory
	 */

	/**
	 * <p>
	 * verifyGeoKeyDirectory.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "GeoKey Directory Test", groups = { "verifyGeoKeyDirectory" })
	public void verifyGeoKeyDirectory() throws Exception {

		for (TiffDump.Directory directory : tiffDump.getDirectories()) {

			TiffDump.Tag geoKeyDirectory = directory.getTag(GEOKEYDIRECTORYTAG);

			// verify specific tag values
			if (directory.getOffset() != 0 && geoKeyDirectory != null) {

				// the GeoKeyDirectoryTag SHALL have ID = 34735
				List<Object> keyEntrySet = geoKeyDirectory.getValues();

				// the GeoKeyDirectoryTag SHALL have type = SHORT (2-byte unsigned
				// integer)
				Assert.assertTrue(geoKeyDirectory.getTypeValue() == 3,
						"the GeoKeyDirectoryTag SHALL have type = SHORT (2-byte unsigned integer)");

				if (keyEntrySet != null) {

					// the GeoKeyDirectoryTag SHALL include at least 4 keys (short
					// integers) as header information
					Assert.assertTrue(keyEntrySet.size() >= 4,
							"the GeoKeyDirectoryTag SHALL include at least 4 keys (short integers) as header information");

					// each Key Entry in the Key Entry Set SHALL include 4 unsigned short
					// integer values
					// TODO: is this comprehensive enough?
					Assert.assertTrue(keyEntrySet.size() % 4 == 0,
							"each Key Entry in the Key Entry Set SHALL include 4 unsigned short integer values");

					// verify that Bytes 0-1 = 1 (the value of KeyDirectoryVersion SHALL
					// be 1)
					// the first unsigned short integer in the GeoKeyDirectoryTag SHALL
					// hold the KeyDirectoryVersion
					Assert.assertTrue(keyEntrySet.get(0).equals(1), "the value of KeyDirectoryVersion SHALL be 1");

					// verify that Bytes 2-3 = 1 (the value of KeyRevision SHALL be 1)
					// the second unsigned short integer in the GeoKeyDirectoryTag SHALL
					// hold the KeyRevision
					Assert.assertTrue(keyEntrySet.get(1).equals(1), "the value of KeyRevision SHALL be 1");

					// verify that Bytes 4-5 = 0 or 1 (the MinorRevision for this standard
					// SHALL be O or 1)
					// the third unsigned short integer in the GeoKeyDirectoryTag SHALL
					// hold the MinorRevision
					Assert.assertTrue(keyEntrySet.get(2).equals(0) || keyEntrySet.get(2).equals(1),
							"the MinorRevision for this standard SHALL be O or 1");

					// bytes 6-7 contain the number of Key Entry Sets in this directory
					// (the GeoKeyDirectoryTag SHALL hold NumberOfKeys KeyEntry Sets in
					// addition to the header information)
					// the fourth unsigned short integer in the GeoKeyDirectoryTag SHALL
					// hold the NumberOfKeys defined in the rest of the GeoKeyDirectoryTag
					int keySetCount = (int) keyEntrySet.get(3);

					int count = 0;
					int previousGeoKey = -1000;
					for (int i = 4; i < keyEntrySet.size(); i += 4) {
						int geoKey = (int) keyEntrySet.get(i);

						// if the current entry is less than the last geokey, check that
						// it is not a key and is an additional param
						if (geoKey < previousGeoKey) {
							// the GeoKeyDirectoryTag SHALL hold NumberOfKeys KeyEntry
							// Sets in addition to the header information
							// validate that the number of Key Sets processed equal the
							// number specified in the header
							Assert.assertTrue(count == keySetCount,
									"the GeoKeyDirectoryTag SHALL hold NumberOfKeys KeyEntry Sets in addition to the header information");
							break;
						}

						// the GeoKey entries in a GeoTIFF file SHALL be written out to
						// the file with the key-IDs sorted in ascending order
						// verify that the GeoKey (first Short integer) is greater than
						// the previous GeoKey
						Assert.assertTrue(geoKey > previousGeoKey,
								"the GeoKey entries in a GeoTIFF file SHALL be written out to the file with the key-IDs sorted in ascending order");
						previousGeoKey = geoKey;
						count++;

						// process the second Short integer in the Key Entry Set
						int type = (int) keyEntrySet.get(i + 1);

						// this is all done elsewhere

						if (type == 0 || type == 34735) {
							// execute test
							// http://www.opengis.net/spec/GeoTIFF/1.1/conf/Short_Param
							// passing GeoKeyOffset as a parameter
						}
						if (type == 34736) {
							// execute test
							// http://www.opengis.net/spec/GeoTIFF/1.1/conf/Double_Param
							// passing GeoKeyOffset as a parameter
						}
						if (type == 34737) {
							// execute test
							// http://www.opengis.net/spec/GeoTIFF/1.1/conf/ASCII_Param
							// passing GeoKeyOffset as a parameter
						}
					}
				}
				return;
			}
		}
		Assert.fail("GeoKeyDirectoryTag not found");
	}

}
