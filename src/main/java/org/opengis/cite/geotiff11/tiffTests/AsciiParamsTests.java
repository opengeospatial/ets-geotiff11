package org.opengis.cite.geotiff11.tiffTests;

import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOASCIIPARAMSTAG;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEODETICCITATIONGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GTCITATIONGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJECTEDCITATIONGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.VERTICALCITATIONGEOKEY;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// https://github.com/opengeospatial/geotiff/blob/master/GeoTIFF_Standard/standard/abstract_tests/TIFF_Tests/TEST_Double_Param.adoc

/**
 * <p>
 * AsciiParamsTests class.
 * </p>
 *
 */
public class AsciiParamsTests extends GeoKeysTests {

	/*
	 * ASCII Parameters Test Test id: TIFF_Test/AsciiParameters Requirements:
	 * http://www.opengis.net/spec/GeoTIFF/1.1/GeoKeyDirectoryTag.KeyEntry.ID
	 * http://www.opengis.net/spec/GeoTIFF/1.1/GeoKeyDirectoryTag.keyEntryTIFFTagLocation
	 * http://www.opengis.net/spec/GeoTIFF/1.1/GeoKeyDirectoryTag.keyEntryKeyCount
	 * http://www.opengis.net/spec/GeoTIFF/1.1/GeoKeyDirectoryTag.keyEntryValueOffset
	 * http://www.opengis.net/spec/GeoTIFF/1.1/GeoAsciiParamsTag.ID
	 * http://www.opengis.net/spec/GeoTIFF/1.1/GeoAsciiParamsTag.count
	 * http://www.opengis.net/spec/GeoTIFF/1.1/CitationGeoKeys.ID
	 * http://www.opengis.net/spec/GeoTIFF/1.1/CitationGeoKeys.type Purpose: Verify an
	 * ASCII parameter Pre-conditions: The GeoKeyDirectory, ASCIIValues and GeoKeyOffset
	 * values have been set. Test Variables: Variable Scope Description GeoKeyDirectory
	 * Global Location of the GeoTIFF GeoKey directory ASCIIValues Global Location of the
	 * ASCII values for GeoTIFF ASCII GeoKeys GeoKeyOffset Parameter Location of this Key
	 * Entry Set in the GeoKey directory GeoKey Local Temporary value of the GeoKey
	 * KeyLength Local Temporary value for the length of the value for the GeoKey
	 * KeyValueOffset Local The location of the GeoKey value in the file
	 *
	 */

	String asciiParamsSet;

	/**
	 * <p>
	 * setUpAsciiParamsSet.
	 * </p>
	 */
	@BeforeClass
	public void setUpAsciiParamsSet() {
		if (directory.hasTag(GEOASCIIPARAMSTAG)) {
			asciiParamsSet = directory.getTag(GEOASCIIPARAMSTAG).getValuesAsString().toString();
			asciiParamsSet = asciiParamsSet.replace("\\0", "\0");
		}
	}

	/**
	 * <p>
	 * verifyGeoAsciiParamsTagCount.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Ascii Params Tag Count Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGeoAsciiParamsTagCount() throws Exception {
		if (keyEntrySet.contains(GEOASCIIPARAMSTAG)) {
			// The GeoAsciiParamsTag SHALL contain the values of the key parameters of
			// type = ASCII referenced by the GeoKeyDirectoryTag.
			Assert.assertTrue(directory.hasTag(GEOASCIIPARAMSTAG),
					"the GeoAsciiParamsTag SHALL contain the values of the key parameters of type = ASCII referenced by the GeoKeyDirectoryTag");
		}
		else {
			// TODO: ??? If there is no key parameters of type = ASCII, it SHALL not be
			// present
			Assert.assertFalse(directory.hasTag(GEOASCIIPARAMSTAG),
					"if there is no key parameters of type = ASCII, it SHALL not be present");
		}
	}

	// TODO: this is redoing some TiffTagsTests stuff. Gotta decide how to properly
	// organize this.
	/**
	 * <p>
	 * verifyGeoAsciiParamsTagType.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Ascii Params Tag Type Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGeoAsciiParamsTagType() throws Exception {
		// the GeoAsciiParamsTag SHALL have type = ASCII
		if (directory.hasTag(GEOASCIIPARAMSTAG))
			Assert.assertTrue(directory.getTag(GEOASCIIPARAMSTAG).getTypeValue() == 2,
					"the GeoAsciiParamsTag SHALL have type = ASCII");
	}

	/**
	 * <p>
	 * verifyGeoAsciiParamsTagNULLWrite.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Ascii Params Tag NULLWrite Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGeoAsciiParamsTagNULLWrite() throws Exception {
		// NULL (ASCII code = 0) characters SHALL not be present in the string content
		// written in the GeoAsciiParamsTag
		if (asciiParamsSet == null)
			return;

		for (int i = 0; i < asciiParamsSet.length() - 1; i++) {
			Assert.assertTrue(asciiParamsSet.charAt(i) != '\0',
					"NULL (ASCII code = 0) characters SHALL not be present in the string content written in the GeoAsciiParamsTag");
		}

		Assert.assertTrue(asciiParamsSet.charAt(asciiParamsSet.length() - 1) == '\0',
				"the string content written in the GeoAsciiParamsTag should end in NULL (ASCII code = 0) characters ");
	}

	String processFourthShortForAscii(int index, int keyLength) {
		// process the fourth Short integer in the Key Entry Set
		int asciiIndex = (int) keyEntrySet.get(index + 3);

		// Assert.assertTrue(directory.hasTag(GEOASCIIPARAMSTAG));

		// SET KeyValueOffset to the value

		// Read the contents of the GeoTIFF file starting at KeyValueOffset up to and
		// including the first NULL.
		// Verify that the contents read consists of ASCII characters.
		// Verify that the contents read is KeyLength characters long not including the
		// NULL.
		String value = "";

		for (int i = asciiIndex;; i++) {
			Assert.assertTrue(i < asciiParamsSet.length());
			// Assert.assertTrue(asciiParamsSet.charAt(i) != '\0');

			// The pipe character | in the GeoAsciiParamsTag SHALL be used as the
			// character to terminate a string written in as ASCII tag
			if (asciiParamsSet.charAt(i) == '|') {
				Assert.assertTrue(value.length() == keyLength - 1,
						"the pipe character | in the GeoAsciiParamsTag SHALL be used as the character to terminate a string written in as ASCII tag");
				break;
			}
			value += asciiParamsSet.charAt(i);
		}

		return value;
	}

	// GeoKey Requirements Class

	// 1026 CitationGeoKeys/GTCitationGeoKey

	/**
	 * <p>
	 * verifyGTCitationGeoKey.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Ascii Params GTCitationGeoKey (1026) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGTCitationGeoKey() throws Exception {
		// the GTCitationGeoKey SHALL have ID = 1026
		int index = getKeyIndex(GTCITATIONGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		String value = processFourthShortForAscii(index, keyLength);

		// the GeogLinearUnitSizeGeoKey SHALL have type = ASCII
		Assert.assertTrue(type == GEOASCIIPARAMSTAG, "the GeogLinearUnitSizeGeoKey SHALL have type = ASCII");
	}

	// 2049 CitationGeoKeys/GeodeticCitationGeoKey

	/**
	 * <p>
	 * verifyGeodeticCitationGeoKey.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Ascii Params GeodeticCitationGeoKey (2049) Test",
			dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGeodeticCitationGeoKey() throws Exception {
		// the GeodeticCitationGeoKey SHALL have ID = 2049
		int index = getKeyIndex(GEODETICCITATIONGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		String value = processFourthShortForAscii(index, keyLength);

		// the GeogLinearUnitSizeGeoKey SHALL have type = ASCII
		Assert.assertTrue(type == GEOASCIIPARAMSTAG, "the GeogLinearUnitSizeGeoKey SHALL have type = ASCII");
	}

	// 3073 CitationGeoKeys/ProjectedCitationGeoKey

	/**
	 * <p>
	 * verifyProjectedCitationGeoKey.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Ascii Params ProjectedCitationGeoKey (3073) Test",
			dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyProjectedCitationGeoKey() throws Exception {
		// the ProjectedCitationGeoKey SHALL have ID = 3073
		int index = getKeyIndex(PROJECTEDCITATIONGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		String value = processFourthShortForAscii(index, keyLength);

		// the GeogLinearUnitSizeGeoKey SHALL have type = ASCII
		Assert.assertTrue(type == GEOASCIIPARAMSTAG, "the GeogLinearUnitSizeGeoKey SHALL have type = ASCII");
	}

	// 4097 CitationGeoKeys/VerticalCitationGeoKey

	/**
	 * <p>
	 * verifyVerticalCitationGeoKey.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Ascii Params VerticalCitationGeoKey (4097) Test",
			dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyVerticalCitationGeoKey() throws Exception {
		// the VerticalCitationGeoKey SHALL have ID = 4097
		int index = getKeyIndex(VERTICALCITATIONGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		String value = processFourthShortForAscii(index, keyLength);

		// the GeogLinearUnitSizeGeoKey SHALL have type = ASCII
		Assert.assertTrue(type == GEOASCIIPARAMSTAG, "the GeogLinearUnitSizeGeoKey SHALL have type = ASCII");
	}

}
