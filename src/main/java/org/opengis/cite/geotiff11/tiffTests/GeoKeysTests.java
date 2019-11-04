package org.opengis.cite.geotiff11.tiffTests;

import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOKEYDIRECTORYTAG;

import java.util.List;

import org.opengis.cite.geotiff11.util.TiffDump;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

/*
 * Holds common functionalities of param tests
 */
public abstract class GeoKeysTests extends CommonTiffMeta {

	TiffDump.Directory directory;
	List<Object> keyEntrySet;
	int minorRevision;
	
	/*
	 * Prepare directory and key entry set, fail the test if the geotiff is invalid.
	 */
	@BeforeClass
	public void setUpGeoKeyDirectory() {
		directory = tiffDump.getGeoKeyDirectory();
		Assert.assertTrue(directory != null);
		keyEntrySet = directory.getTag(GEOKEYDIRECTORYTAG).getValues();	
		Assert.assertTrue(keyEntrySet != null);
		minorRevision = (int) keyEntrySet.get(2);
	}
	
	// helper functions
	
	int processFourthShortForShort(int index, int keyLength) {
		// process the fourth Short integer in the Key Entry Set
		if(keyLength == 1) {
			// SET KeyValueOffset = GeoKeyDirectory + GeoKeyOffset + 6
			return (int) keyEntrySet.get(index+3);
		} else {
			// SET KeyValueOffset = GeoKeyDirectory + (KeyValueOffset * 2)
			return (int) keyEntrySet.get(keyLength); // TODO: verify this is a correct interpretation of the ats...
		}
	}
	
	int processThirdShort(int index) {
		// process the third Short integer in the Key Entry Set
		return (int) keyEntrySet.get(index + 2);
	}
	
	int processSecondShort(int index) {
		// process the second Short integer in the Key Entry Set
		return (int) keyEntrySet.get(index + 1);
	}
	
	int processFirstShort(int index) {
		// process the first Short integer in the Key Entry Set
		return (int) keyEntrySet.get(index);
	}
	
	boolean keyExists(int key) {
		return getKeyIndex(key) != -1;
	}
	
	int getKeyIndex(int key) {
		int index = keyEntrySet.indexOf(key);
		
		if(index % 4 != 0) { // keys occur every 4th short
			return -1;
		}
		return index;
	}
	
}
