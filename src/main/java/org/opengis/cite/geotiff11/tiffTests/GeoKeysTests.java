package org.opengis.cite.geotiff11.tiffTests;

import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOKEYDIRECTORYTAG;

import java.util.List;

import org.opengis.cite.geotiff11.util.TiffDump;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

/*
 * Holds common functionalities of param tests
 */
/**
 * <p>
 * Abstract GeoKeysTests class.
 * </p>
 *
 */
public abstract class GeoKeysTests extends CommonTiffMeta {

	TiffDump.Directory directory;

	List<Object> keyEntrySet;

	int minorRevision;

	/*
	 * Prepare directory and key entry set, fail the test if the geotiff is invalid.
	 */
	/**
	 * <p>
	 * setUpGeoKeyDirectory.
	 * </p>
	 */
	@BeforeClass
	public void setUpGeoKeyDirectory() {
		Assert.assertTrue(tiffDump != null, "TiffDump wrapping returned null");
		directory = tiffDump.getGeoKeyDirectory();
		Assert.assertTrue(directory != null, "GeoKeyDirectory not found");
		Assert.assertTrue(directory.getTag(GEOKEYDIRECTORYTAG) != null, "GeoKeyDirectoryTag not found");
		keyEntrySet = directory.getTag(GEOKEYDIRECTORYTAG).getValues();
		Assert.assertTrue(keyEntrySet != null, "GeoKeyDirectoryTag does not contain any values");
		minorRevision = (int) keyEntrySet.get(2);
	}

	// helper functions

	// the fourth unsigned short integer in the Key Entry SHALL hold either the key value
	// (if TIFF Tag location = or the index into the tag indicated by the TIFF Tag
	// Location value.
	int processFourthShortForShort(int index, int keyLength) {
		// process the fourth Short integer in the Key Entry Set
		if (keyLength == 1) {
			// SET KeyValueOffset = GeoKeyDirectory + GeoKeyOffset + 6
			return (int) keyEntrySet.get(index + 3);
		}
		else {
			// SET KeyValueOffset = GeoKeyDirectory + (KeyValueOffset * 2)
			return (int) keyEntrySet.get(keyLength);
		}
	}

	// the Third unsigned short integer in the Key Entry SHALL indicate the number of
	// values associated with this key.
	int processThirdShort(int index) {
		// process the third Short integer in the Key Entry Set
		return (int) keyEntrySet.get(index + 2);
	}

	// the second unsigned short integer in the Key Entry SHALL hold the TIFF Tag
	// Location. The value of this entry shall be a valid GeoTIFF tag identifier or a zero
	// (0)
	int processSecondShort(int index) {
		// process the second Short integer in the Key Entry Set
		return (int) keyEntrySet.get(index + 1);
	}

	// the first unsigned short integer in the Key Entry SHALL hold the key identifier.
	int processFirstShort(int index) {
		// process the first Short integer in the Key Entry Set
		return (int) keyEntrySet.get(index);
	}

	boolean keyExists(int key) {
		return getKeyIndex(key) != -1;
	}

	int getKeyIndex(int key) {
		int index = keyEntrySet.indexOf(key);

		if (index % 4 != 0) { // keys occur every 4th short
			return -1;
		}
		return index;
	}

}
