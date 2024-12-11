package org.opengis.cite.geotiff11.tiffTests;

import static org.opengis.cite.geotiff11.util.GeoKeyID.ELLIPSOIDGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.ELLIPSOIDINVFLATTENINGGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.ELLIPSOIDSEMIMAJORAXISGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.ELLIPSOIDSEMIMINORAXISGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEODETICCITATIONGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEODETICCRSGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEODETICDATUMGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOGANGULARUNITSGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOGANGULARUNITSIZEGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOGAZIMUTHUNITSGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOGLINEARUNITSGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOGLINEARUNITSIZEGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOKEYDIRECTORYTAG;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GTCITATIONGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GTMODELTYPEGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GTRASTERTYPEGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PRIMEMERIDIANGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PRIMEMERIDIANLONGITUDEGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJECTEDCITATIONGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJECTEDCRSGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJECTIONGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJLINEARUNITSGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJLINEARUNITSIZEGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJMETHODGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.VERTICALCITATIONGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.VERTICALDATUMGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.VERTICALGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.VERTICALUNITSGEOKEY;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.csv.CSVRecord;
import org.opengis.cite.geotiff11.util.EPSGDataSet;
import org.testng.Assert;
import org.testng.annotations.Test;

// https://github.com/opengeospatial/geotiff/blob/5d6ab0ba54f1ed0174901dd84240817dc9dbe011/GeoTIFF_Standard/standard/abstract_tests/TIFF_Tests/TEST_Short_Param.adoc

/**
 * <p>
 * ShortParamsTests class.
 * </p>
 *
 */
public class ShortParamsTests extends GeoKeysTests {

	/*
	 * GeoKey Directory Test Test id:
	 * http://www.opengis.net/spec/GeoTIFF/1.1/conf/ShortParameters Requirements:
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.KeyEntry.ID
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.
	 * keyEntryTIFFTagLocation
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyEntryKeyCount
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyEntryValueOffset
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoShortParams.Criteria
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoShortParams.Location
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GTModelTypeGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GTRasterTypeGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeodeticCRSGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeodeticDatumGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/PrimeMeridianGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/UnitsGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/EllipsoidGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjectedCRSGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjectionGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjMethodGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/VerticalGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/VerticalDatumGeoKey Purpose: Verify a
	 * Short parameter Pre-conditions: The GeoKeyDirectory and GeoKeyOffset values have
	 * been set Test Variables: Test Variables: ...
	 */

	// In the case where a Parameter of type Short has more than one value, those values
	// SHALL be stored in the GeoKeysDirectory
	// TODO: Parameter values stored in the GeoKeysDirectory SHALL appear after the last
	// Key Entry ??? How to implement this?

	// GeoKeys with a value of 0 SHALL indicate intentionally omitted parameters
	// GeoKeys with a value of 32767 SHALL indicate user-defined parameters

	// https://github.com/opengeospatial/geotiff/blob/68d8f902293ad64526889daa055892ea30f9e9ea/GeoTIFF_Standard/Detailed%20Test%20Suite/abstract_tests/Requirements_Trace_Matrix.adoc
	// GeoKey Requirements Class
	// 0 ignore
	// 1024 GTModelTypeGeoKey

	/**
	 * <p>
	 * verifyGTModelTypeGeoKey.
	 * </p>
	 */
	@Test(description = "Short Params GTModelTypeGeoKey (1024) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGTModelTypeGeoKey() {
		// the GTModelTypeGeoKey SHALL have ID = 1024
		int index = getKeyIndex(GTMODELTYPEGEOKEY);
		// a GeoTIFF file SHALL include a GTModelTypeGeoKey
		Assert.assertTrue(index != -1, "a GeoTIFF file SHALL include a GTModelTypeGeoKey");

		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the GTModelTypeGeoKey SHALL have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the GTModelTypeGeoKey SHALL have type = SHORT");

		// GTModelTypeGeoKey values in the range 4-32766 SHALL be reserved
		Assert.assertFalse(value >= 4 && value <= 32766,
				"GTModelTypeGeoKey values in the range 4-32766 SHALL be reserved");

		// GTModelTypeGeoKey values in the range 32768-65535 SHALL be private
		Assert.assertFalse(value > 65535 || value < 0,
				"GTModelTypeGeoKey values in the range 32768-65535 SHALL be private");

		switch (value) {
			// if the GTModelTypeGeoKey value is 1 (Model CRS is a projected 2D CRS) then
			// the GeoTIFF file SHALL include a ProjectedCRSGeoKey 3072
			case 1:
				Assert.assertTrue(keyExists(PROJECTEDCRSGEOKEY),
						"if the GTModelTypeGeoKey value is 1 (Model CRS is a projected 2D CRS) then the GeoTIFF file SHALL include a ProjectedCRSGeoKey 3072");
				break;
			// if the GTModelTypeGeoKey value is 2 (Model CRS is a geographic 2D CRS) then
			// the GeoTIFF file SHALL include a GeodeticCRSGeoKey 2048
			// if the GTModelTypeGeoKey value is 3 (Model CRS is a geocentric CRS) then
			// the GeoTIFF file SHALL include a GeodeticCRSGeoKey 2048
			case 2:
			case 3:
				// TODO: check model?
				Assert.assertTrue(keyExists(GEODETICCRSGEOKEY),
						"if the GTModelTypeGeoKey value is 2/3 then the GeoTIFF file SHALL include a GeodeticCRSGeoKey");
				break;
			// if the GTModelTypeGeoKey value is 32767 (user-defined) then the
			// GTCitationGeoKey SHALL be populated
			case 32767:
				Assert.assertTrue(keyExists(GTCITATIONGEOKEY),
						"if the GTModelTypeGeoKey value is 32767 (user-defined) then the GTCitationGeoKey SHALL be populated");
				break;
		}
	}

	// 1025 GTRasterTypeGeoKey

	/**
	 * <p>
	 * verifyGTRasterTypeGeoKey.
	 * </p>
	 */
	@Test(description = "Short Params GTRasterTypeGeoKey (1025) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGTRasterTypeGeoKey() {
		// the GTRasterTypeGeoKey SHALL have ID = 1025
		int index = getKeyIndex(GTRASTERTYPEGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the GTRasterTypeGeoKey SHALL have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the GTRasterTypeGeoKey SHALL have type = SHORT");

		// the GTRasterTypeGeoKey value SHALL be: 0, 1, 2, 32767, else
		if (!Arrays.asList(0, 1, 2, 32767).contains(value)) {
			// GTRasterTypeGeoKey values in the range 3-32766 SHALL be reserved
			Assert.assertFalse(value >= 3 && value <= 32766,
					"GTRasterTypeGeoKey values in the range 3-32766 SHALL be reserved");

			// GTRasterTypeGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "GTRasterTypeGeoKey value out of bounds");
		}
	}

	// 2048 GeodeticCRSGeoKey

	/**
	 * <p>
	 * verifyGeodeticCRSGeoKey.
	 * </p>
	 * @throws java.io.IOException if any.
	 */
	@Test(description = "Short Params GeodeticCRSGeoKey (2048) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGeodeticCRSGeoKey() throws IOException {
		// the GeodeticCRSGeoKey SHALL have ID = 2048
		int index = getKeyIndex(GEODETICCRSGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the GeodeticCRSGeoKey SHALL have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the GeodeticCRSGeoKey SHALL have type = SHORT");

		// if the GeodeticCRSGeoKey value is 32767 (User-Defined) then the
		// GeodeticCitationGeoKey 2049, GeodeticDatumGeoKey 2050 and at least one of
		// GeogAngularUnitsGeoKey 2054 or GeogLinearUnitsGeoKey 2052 SHALL be populated
		if (value == 32767) {
			Assert.assertTrue(
					keyExists(GEODETICCITATIONGEOKEY) && keyExists(GEODETICDATUMGEOKEY)
							&& (keyExists(GEOGANGULARUNITSGEOKEY) || keyExists(GEOGLINEARUNITSGEOKEY)),
					"if the GeodeticCRSGeoKey value is 32767 (User-Defined) then the GeodeticCitationGeoKey 2049, GeodeticDatumGeoKey 2050 and at least one of GeogAngularUnitsGeoKey 2054 or GeogLinearUnitsGeoKey 2052 SHALL be populated");
		}
		else if (value >= 1024 && value <= 32766) {
			// GeodeticCRSGeoKey values in the range 1024-32766 SHALL be EPSG geodetic CRS
			// codes (geographic 2D CRS, geographic 3D CRS, and geocentric CRS)
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.CRS, "COORD_REF_SYS_CODE", Integer.toString(value));
			Assert.assertTrue(record != null,
					"GeodeticCRSGeoKey values in the range 1024-32766 SHALL be EPSG geodetic CRS codes (geographic 2D CRS, geographic 3D CRS, and geocentric CRS)");
			// Assert.assertTrue(minorRevision == 1); // not necessary
			Assert.assertTrue(record.get("DEPRECATED").equals("0"),
					"GeodeticCRSGeoKey values in the range 1024-32766 SHALL be EPSG geodetic CRS codes (geographic 2D CRS, geographic 3D CRS, and geocentric CRS)");
			Assert.assertTrue(
					record.get("COORD_REF_SYS_KIND").equals("geographic 2D")
							|| record.get("COORD_REF_SYS_KIND").equals("geographic 3D")
							|| record.get("COORD_REF_SYS_KIND").equals("geocentric"),
					"GeodeticCRSGeoKey values in the range 1024-32766 SHALL be EPSG geodetic CRS codes (geographic 2D CRS, geographic 3D CRS, and geocentric CRS)");
		}
		else if (value >= 1 && value <= 1000) {
			// GeodeticCRSGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POC
			// Geographic Codes
			Assert.assertTrue(minorRevision == 0,
					"GeodeticCRSGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POC Geographic Codes");
		}
		else {
			// GeodeticCRSGeoKey values in the range 1001-1023 SHALL be reserved
			Assert.assertFalse(value >= 1001 && value <= 1023,
					"GeodeticCRSGeoKey values in the range 1001-1023 SHALL be reserved");

			// GeodeticCRSGeoKeyvalues in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "GeodeticCRSGeoKeyvalues value out of bounds");
		}
	}

	// 2050 GeodeticDatumGeoKey

	/**
	 * <p>
	 * verifyGeodeticDatumGeoKey.
	 * </p>
	 * @throws java.io.IOException if any.
	 */
	@Test(description = "Short Params GeodeticDatumGeoKey (2050) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGeodeticDatumGeoKey() throws IOException {
		// the GeodeticDatumGeoKey SHALL have ID = 2050
		int index = getKeyIndex(GEODETICDATUMGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the GeodeticDatumGeoKey SHALL have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the GeodeticDatumGeoKey SHALL have type = SHORT");

		if (value == 32767) {
			// If the GeodeticDatumGeoKey value is 32767 (User-Defined) then the
			// GeodeticCitationGeoKey 2049, PrimeMeridianGeoKey 2051 and EllipsoidGeoKey
			// 2056 SHALL be populated
			Assert.assertTrue(
					keyExists(GEODETICCITATIONGEOKEY) && keyExists(PRIMEMERIDIANGEOKEY) && keyExists(ELLIPSOIDGEOKEY),
					"if the GeodeticDatumGeoKey value is 32767 (User-Defined) then the GeodeticCitationGeoKey 2049, PrimeMeridianGeoKey 2051 and EllipsoidGeoKey 2056 SHALL be populated");
		}
		else if (value >= 1024 && value <= 32766) {
			// GeodeticDatumGeoKey values in the range 1024-32766 SHALL be EPSG geodetic
			// datum codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.DATUM, "DATUM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null,
					"GeodeticDatumGeoKey values in the range 1024-32766 SHALL be EPSG geodetic datum codes");
			// Assert.assertTrue(minorRevision == 1); // not necessary
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
			Assert.assertTrue(record.get("DATUM_TYPE").equals("geodetic"));
		}
		else {
			// GeodeticDatumGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POS
			// Datum Codes
			Assert.assertFalse(minorRevision != 0 && value >= 1 && value <= 1000,
					"GeodeticDatumGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POS Datum Codes");

			// GeodeticDatumGeoKey values in the range 1001-1023 SHALL be reserved
			Assert.assertFalse(value >= 1001 && value <= 1023,
					"GeodeticDatumGeoKey values in the range 1001-1023 SHALL be reserved");

			// GeodeticDatumGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "GeodeticDatumGeoKey value out of bounds");
		}
	}

	// 2051 PrimeMeridianGeoKey

	/**
	 * <p>
	 * verifyPrimeMeridianGeoKey.
	 * </p>
	 * @throws java.io.IOException if any.
	 */
	@Test(description = "Short Params PrimeMeridianGeoKey (2051) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyPrimeMeridianGeoKey() throws IOException {
		// the PrimeMeridianGeoKey SHALL have ID = 2051
		int index = getKeyIndex(PRIMEMERIDIANGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the PrimeMeridianGeoKey SHALL have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the PrimeMeridianGeoKey SHALL have type = SHORT");

		if (value == 32767) {
			// If the PrimeMeridianGeoKey value is 32767 (User-Defined) then the
			// GeodeticCitationGeoKey, and PrimeMeridianLongGeoKey SHALL be populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(PRIMEMERIDIANLONGITUDEGEOKEY),
					"if the PrimeMeridianGeoKey value is 32767 (User-Defined) then the GeodeticCitationGeoKey, and PrimeMeridianLongGeoKey SHALL be populated");
		}
		else if (value >= 1024 && value <= 32766) {
			// PrimeMeridianGeoKey values in the range 1024-32766 SHALL be EPSG Prime
			// Meridian Codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.PRIMEMERIDIAN, "PRIME_MERIDIAN_CODE",
					Integer.toString(value));
			Assert.assertTrue(record != null,
					"PrimeMeridianGeoKey values in the range 1024-32766 SHALL be EPSG Prime Meridian Codes");
			// Assert.assertTrue(minorRevision == 1); // not necessary
			Assert.assertTrue(record.get("DEPRECATED").equals("0"),
					"PrimeMeridianGeoKey values in the range 1024-32766 SHALL be EPSG Prime Meridian Codes");
		}
		else {

			// PrimeMeridianGeoKey values in the range 1-100 SHALL be obsolete EPSG/POSC
			// Datum Codes
			Assert.assertFalse(minorRevision != 0 && value >= 1 && value <= 100,
					"PrimeMeridianGeoKey values in the range 1-100 SHALL be obsolete EPSG/POSC Datum Codes");

			// PrimeMeridianGeoKey values in the range 101-1023 SHALL be reserved
			Assert.assertFalse(value >= 101 && value <= 1023,
					"PrimeMeridianGeoKey values in the range 101-1023 SHALL be reserved");

			// PrimeMeridianGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "PrimeMeridianGeoKey value out of bounds");
		}
	}

	// 2052 UnitsGeoKey (Linear Units) GeogLinearUnitsGeoKey

	/**
	 * <p>
	 * verifyGeogLinearUnitsGeoKey.
	 * </p>
	 * @throws java.io.IOException if any.
	 */
	@Test(description = "Short Params GeogLinearUnitsGeoKey (2052) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGeogLinearUnitsGeoKey() throws IOException {
		// the GeogLinearUnitsGeoKey SHALL have ID = 2052
		int index = getKeyIndex(GEOGLINEARUNITSGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the
		// GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey
		// SHALL each have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the GeogLinearUnitsGeoKey SHALL have type = SHORT");

		if (value == 32767) {
			// A GeogLinearUnitsGeoKey value of 32767 SHALL be a user-defined linear unit.
			// If the value is 32767 (User-Defined) then the GeodeticCitationGeoKey and
			// the GeogLinearUnitSizeGeoKey SHALL be populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(GEOGLINEARUNITSIZEGEOKEY),
					"a GeogLinearUnitsGeoKey value of 32767 SHALL be a user-defined linear unit. If the value is 32767 (User-Defined) then the GeodeticCitationGeoKey and the GeogLinearUnitSizeGeoKey SHALL be populated");
		}
		else if (value >= 1024 && value <= 32766) {
			// GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values
			// in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type
			// = length
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.UOM, "UOM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null,
					"GeogLinearUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length");
			// Assert.assertTrue(minorRevision == 1); // not necessary
			Assert.assertTrue(record.get("DEPRECATED").equals("0"),
					"GeogLinearUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length");
			Assert.assertTrue(record.get("UNIT_OF_MEAS_TYPE").equals("length"),
					"GeogLinearUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length");
		}
		else {
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey,
			// ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023
			// SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023,
					"GeogLinearUnitsGeoKey values in the range 1-1023 SHALL be reserved");

			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey,
			// ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range
			// 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "GeogLinearUnitsGeoKey value out of bounds");
		}
	}

	// 2054 UnitsGeoKey (Angular Units) GeogAngularUnitsGeoKey

	/**
	 * <p>
	 * verifyGeogAngularUnitsGeoKey.
	 * </p>
	 * @throws java.io.IOException if any.
	 */
	@Test(description = "Short Params GeogAngularUnitsGeoKey (2054) Test",
			dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGeogAngularUnitsGeoKey() throws IOException {
		// the GeogAngularUnitsGeoKey SHALL have ID = 2054
		int index = getKeyIndex(GEOGANGULARUNITSGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the
		// GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey
		// SHALL each have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG,
				"the GeogAngularUnitsGeoKey SHALL have type = SHORT");

		if (value == 32767) {
			// A GeogAngularUnitsGeoKey or a GeogAzimuthUnitsGeoKey value of 32767 SHALL
			// be a user-defined angular unit. If the value is 32767 (User-Defined) then
			// the GeodeticCitationGeoKey and the GeogAngularUnitSizeGeoKey SHALL be
			// populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(GEOGANGULARUNITSIZEGEOKEY),
					"A GeogAngularUnitsGeoKey or a GeogAzimuthUnitsGeoKey value of 32767 SHALL be a user-defined angular unit. If the value is 32767 (User-Defined) then the GeodeticCitationGeoKey and the GeogAngularUnitSizeGeoKey SHALL be populated");
		}
		else if (value >= 1024 && value <= 32766) {
			// GeogAngularUnitsGeoKey and GeogAzimuthUnitsGeoKey values in the range
			// 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = angle
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.UOM, "UOM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null,
					"GeogAzimuthUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = angle");
			// Assert.assertTrue(minorRevision == 1); // not necessary
			Assert.assertTrue(record.get("DEPRECATED").equals("0"),
					"GeogAzimuthUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = angle");
			Assert.assertTrue(record.get("UNIT_OF_MEAS_TYPE").equals("angle"),
					"GeogAzimuthUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = angle");
		}
		else {
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey,
			// ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023
			// SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023,
					"GeogAngularUnitsGeoKey values in the range 1-1023 SHALL be reserved");

			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey,
			// ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range
			// 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "GeogAngularUnitsGeoKey value out of bounds");
		}
	}

	// 2056 EllipsoidGeoKey

	/**
	 * <p>
	 * verifyEllipsoidGeoKey.
	 * </p>
	 * @throws java.io.IOException if any.
	 */
	@Test(description = "Short Params EllipsoidGeoKey (2056) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyEllipsoidGeoKey() throws IOException {
		// the PrimeMeridianGeoKey SHALL have ID = 2056
		int index = getKeyIndex(ELLIPSOIDGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the EllipsoidGeoKey SHALL have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the EllipsoidGeoKey SHALL have type = SHORT");

		if (value == 32767) {
			// If the EllipsoidGeoKey value is 32767 (User-Defined) then the
			// GTCitationGeoKey and the EllipsoidSemiMajorAxisGeoKey SHALL be populated
			// together with the one of either the EllipsoidSemiMinorAxisGeoKey or the
			// EllipsoidInvFlatteningGeoKey
			Assert.assertTrue(
					keyExists(GEODETICCITATIONGEOKEY) && keyExists(ELLIPSOIDSEMIMAJORAXISGEOKEY)
							&& (keyExists(ELLIPSOIDSEMIMINORAXISGEOKEY) || keyExists(ELLIPSOIDINVFLATTENINGGEOKEY)),
					"if the EllipsoidGeoKey value is 32767 (User-Defined) then the GTCitationGeoKey and the EllipsoidSemiMajorAxisGeoKey SHALL be populated together with the one of either the EllipsoidSemiMinorAxisGeoKey or the EllipsoidInvFlatteningGeoKey");
		}
		else if (value >= 1024 && value <= 32766) {
			// EllipsoidGeoKey values in the range 1024-32766 SHALL be EPSG ellipsoid
			// Codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.ELLIPSOID, "ELLIPSOID_CODE", Integer.toString(value));
			Assert.assertTrue(record != null,
					"EllipsoidGeoKey values in the range 1024-32766 SHALL be EPSG ellipsoid Codes");
			// Assert.assertTrue(minorRevision == 1); // not necessary
			Assert.assertTrue(record.get("DEPRECATED").equals("0"),
					"EllipsoidGeoKey values in the range 1024-32766 SHALL be EPSG ellipsoid Codes");
		}
		else {
			// EllipsoidGeoKey values in the range 1024-32766 SHALL be EPSG ellipsoid
			// Codes
			Assert.assertFalse(minorRevision != 1 && value >= 1024 && value <= 32766,
					"EllipsoidGeoKey values in the range 1024-32766 SHALL be EPSG ellipsoid Codes");

			// EllipsoidGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POSC
			// Datum Codes
			// Chuck said code lookup wasn't necessary here
			Assert.assertFalse(minorRevision != 0 && value >= 1 && value <= 1000,
					"EllipsoidGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POSC Datum Codes");

			// EllipsoidGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "EllipsoidGeoKey value out of bounds"); // TODO:
																									// Check
																									// if
																									// between
																									// 1000-1023?
		}
	}

	// 2060 UnitsGeoKey (Azimuth Units) GeogAzimuthUnitsGeoKey

	/**
	 * <p>
	 * verifyGeogAzimuthUnitsGeoKey.
	 * </p>
	 * @throws java.io.IOException if any.
	 */
	@Test(description = "Short Params GeogAzimuthUnitsGeoKey (2060) Test",
			dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGeogAzimuthUnitsGeoKey() throws IOException {
		// the GeogAzimuthUnitsGeoKey SHALL have ID = 2060
		int index = getKeyIndex(GEOGAZIMUTHUNITSGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the
		// GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey
		// SHALL each have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "GeogAzimuthUnitsGeoKey SHALL have type = SHORT");

		if (value == 32767) {
			// A GeogAngularUnitsGeoKey or a GeogAzimuthUnitsGeoKey value of 32767 SHALL
			// be a user-defined angular unit. If the value is 32767 (User-Defined) then
			// the GeodeticCitationGeoKey and the GeogAngularUnitSizeGeoKey SHALL be
			// populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(GEOGANGULARUNITSIZEGEOKEY),
					"a GeogAngularUnitsGeoKey or a GeogAzimuthUnitsGeoKey value of 32767 SHALL be a user-defined angular unit. If the value is 32767 (User-Defined) then the GeodeticCitationGeoKey and the GeogAngularUnitSizeGeoKey SHALL be populated");
		}
		else if (value >= 1024 && value <= 32766) {
			// GeogAngularUnitsGeoKey and GeogAzimuthUnitsGeoKey values in the range
			// 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = angle
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.UOM, "UOM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null,
					"GeogAzimuthUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = angle");
			// Assert.assertTrue(minorRevision == 1); // not necessary
			Assert.assertTrue(record.get("DEPRECATED").equals("0"),
					"GeogAzimuthUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = angle");
			Assert.assertTrue(record.get("UNIT_OF_MEAS_TYPE").equals("angle"),
					"GeogAzimuthUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = angle");
		}
		else {
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey,
			// ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023
			// SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023,
					"GeogAzimuthUnitsGeoKey values in the range 1-1023 SHALL be reserved");

			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey,
			// ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range
			// 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "GeogAzimuthUnitsGeoKey value out of bounds");
		}
	}

	// 3072 ProjectedCRSGeoKey

	/**
	 * <p>
	 * verifyProjectedCRSGeoKey.
	 * </p>
	 * @throws java.io.IOException if any.
	 */
	@Test(description = "Short Params ProjectedCRSGeoKey (2056) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyProjectedCRSGeoKey() throws IOException {
		// the ProjectedCRSGeoKey SHALL have ID = 3072
		int index = getKeyIndex(PROJECTEDCRSGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the ProjectedCRSGeoKey SHALL have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the ProjectedCRSGeoKey SHALL have type = SHORT");

		if (value == 32767) {
			// A ProjectedCRSGeoKey value of 32767 SHALL be a user-defined projected CRS.
			// If the ProjectedCRSGeoKey value is 32767 (User-Defined) then the
			// ProjectedCitationGeoKey, GeodeticCRSGeoKey and ProjectionGeoKey SHALL be
			// populated
			Assert.assertTrue(
					keyExists(PROJECTEDCITATIONGEOKEY) && keyExists(GEODETICCRSGEOKEY) && keyExists(PROJECTIONGEOKEY),
					"a ProjectedCRSGeoKey value of 32767 SHALL be a user-defined projected CRS. If the ProjectedCRSGeoKey value is 32767 (User-Defined) then the ProjectedCitationGeoKey, GeodeticCRSGeoKey and ProjectionGeoKey SHALL be populated");
		}
		else if (value >= 1024 && value <= 32766) {
			// ProjectedCRSGeoKey values in the range 1024-32766 SHALL be EPSG Projected
			// CRS Codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.CRS, "COORD_REF_SYS_CODE", Integer.toString(value));
			Assert.assertTrue(record != null,
					"ProjectedCRSGeoKey values in the range 1024-32766 SHALL be EPSG Projected CRS Codes");
			// Assert.assertTrue(minorRevision == 1); // not necessary
			Assert.assertTrue(record.get("DEPRECATED").equals("0"),
					"ProjectedCRSGeoKey values in the range 1024-32766 SHALL be EPSG Projected CRS Codes");
			Assert.assertTrue(record.get("COORD_REF_SYS_KIND").equals("projected"),
					"ProjectedCRSGeoKey values in the range 1024-32766 SHALL be EPSG Projected CRS Codes");
		}
		else {
			// ProjectedCRSGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POC
			// Datum Codes
			Assert.assertFalse(minorRevision != 0 && value >= 1 && value <= 1000,
					"ProjectedCRSGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POC Datum Codes");

			// ProjectedCRSGeoKey values in the range 1001-1023 SHALL be reserved
			Assert.assertFalse(value >= 1001 && value <= 1023,
					"ProjectedCRSGeoKey values in the range 1001-1023 SHALL be reserved");

			// ProjectedCRSGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "ProjectedCRSGeoKey value out of bounds");
		}
	}

	// 3074 ProjectionGeoKey

	/**
	 * <p>
	 * verifyProjectionGeoKey.
	 * </p>
	 * @throws java.io.IOException if any.
	 */
	@Test(description = "Short Params ProjectionGeoKey (3074) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyProjectionGeoKey() throws IOException {
		// the ProjectionGeoKey SHALL have ID = 3074
		int index = getKeyIndex(PROJECTIONGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the ProjectionGeoKey SHALL have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the ProjectionGeoKey SHALL have type = SHORT");

		if (value == 32767) {
			// If the ProjectionGeoKey value is 32767 (User-Defined) then the
			// ProjectedCitationGeoKey, ProjectionMethodGeoKey, and ProjLinearUnitsGeoKey
			// SHALL be populated
			Assert.assertTrue(
					keyExists(PROJECTEDCITATIONGEOKEY) && keyExists(PROJMETHODGEOKEY)
							&& keyExists(PROJLINEARUNITSGEOKEY),
					"If the ProjectionGeoKey value is 32767 (User-Defined) then the ProjectedCitationGeoKey, ProjectionMethodGeoKey, and ProjLinearUnitsGeoKey SHALL be populated");
		}
		else if (value >= 1024 && value <= 32766) {
			// ProjectionGeoKey values in the range 1024-32766 SHALL be valid EPSG map
			// projection (coordinate operation) codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.CO, "COORD_OP_CODE", Integer.toString(value));
			Assert.assertTrue(record != null,
					"ProjectionGeoKey values in the range 1024-32766 SHALL be valid EPSG map projection (coordinate operation) codes");
			// Assert.assertTrue(minorRevision == 1); // not necessary
			Assert.assertTrue(record.get("DEPRECATED").equals("0"),
					"ProjectionGeoKey values in the range 1024-32766 SHALL be valid EPSG map projection (coordinate operation) codes");
		}
		else {
			// ProjectionGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023,
					"ProjectionGeoKey values in the range 1-1023 SHALL be reserved");

			// ProjectionGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "ProjectionGeoKey value out of bounds");
		}
	}

	// 3075 ProjMethodGeoKey

	/**
	 * <p>
	 * verifyProjMethodGeoKey.
	 * </p>
	 */
	@Test(description = "Short Params ProjMethodGeoKey (3075) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyProjMethodGeoKey() {
		// the ProjMethodGeoKey SHALL have ID = 3075
		int index = getKeyIndex(PROJMETHODGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the ProjMethodGeoKey SHALL have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the ProjMethodGeoKey SHALL have type = SHORT");

		if (value == 32767) {
			// If the ProjectionMethodGeoKey value is 32767 (User-Defined) then the
			// ProjectedCitationGeoKey
			Assert.assertTrue(keyExists(PROJECTEDCITATIONGEOKEY),
					"if the ProjectionMethodGeoKey value is 32767 (User-Defined) then the ProjectedCitationGeoKey ");
			// and keys for each map projection parameter (coordinate operation parameter)
			// appropriate to that method SHALL be populated
			// TODO: can I even test this?
		}
		else {
			// ProjMethodGeoKey values in the range 1-27 SHALL be GeoTIFF map projection
			// method codes
			// nothing to check here, is valid if passes

			// ProjMethodGeoKey values in the range 28-32766 SHALL be reserved
			Assert.assertFalse(value >= 28 && value <= 32766,
					"ProjMethodGeoKey values in the range 28-32766 SHALL be reserved");

			// ProjMethodGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "ProjMethodGeoKey value out of bounds");
		}
	}

	// 3076 UnitsGeoKey (Linear Units) ProjLinearUnitsGeoKey

	/**
	 * <p>
	 * verifyProjLinearUnitsGeoKey.
	 * </p>
	 * @throws java.io.IOException if any.
	 */
	@Test(description = "Short Params ProjLinearUnitsGeoKey (3076) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyProjLinearUnitsGeoKey() throws IOException {
		// the ProjLinearUnitsGeoKey SHALL have ID = 3076
		int index = getKeyIndex(PROJLINEARUNITSGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the
		// GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey
		// SHALL each have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the ProjLinearUnitsGeoKey SHALL have type = SHORT");

		if (value == 32767) {
			// A ProjLinearUnitsGeoKey value of 32767 SHALL be a user-defined linear unit.
			// If the value is 32767 (User-Defined) then the ProjectedCitationGeoKey and
			// the ProjLinearUnitSizeGeoKey SHALL be populated
			Assert.assertTrue(keyExists(PROJECTEDCITATIONGEOKEY) && keyExists(PROJLINEARUNITSIZEGEOKEY),
					"a ProjLinearUnitsGeoKey value of 32767 SHALL be a user-defined linear unit. If the value is 32767 (User-Defined) then the ProjectedCitationGeoKey and the ProjLinearUnitSizeGeoKey SHALL be populated");
		}
		else if (value >= 1024 && value <= 32766) {
			// GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values
			// in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type
			// = length
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.UOM, "UOM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null,
					"ProjLinearUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length");
			// Assert.assertTrue(minorRevision == 1); // not necessary
			Assert.assertTrue(record.get("DEPRECATED").equals("0"),
					"ProjLinearUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length");
			Assert.assertTrue(record.get("UNIT_OF_MEAS_TYPE").equals("length"),
					"ProjLinearUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length");
		}
		else {
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey,
			// ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023
			// SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023,
					"ProjLinearUnitsGeoKey values in the range 1-1023 SHALL be reserved");

			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey,
			// ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range
			// 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "ProjLinearUnitsGeoKey value out of bounds");
		}
	}

	// 4096 VerticalGeoKey

	/**
	 * <p>
	 * verifyVerticalGeoKey.
	 * </p>
	 * @throws java.io.IOException if any.
	 */
	@Test(description = "Short Params VerticalGeoKey (4096) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyVerticalGeoKey() throws IOException {
		// the VerticalGeoKey SHALL have ID = 4096
		int index = getKeyIndex(VERTICALGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the VerticalGeoKey SHALL have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the VerticalGeoKey SHALL have type = SHORT");

		if (value == 32767) {
			// If the VerticalGeoKey value is 32767 (User-Defined) then the
			// VerticalCitationGeoKey, the VerticalUnitsGeoKey and VerticalDatumGeoKey
			// SHALL be populated
			Assert.assertTrue(
					keyExists(VERTICALCITATIONGEOKEY) && keyExists(VERTICALUNITSGEOKEY)
							&& keyExists(VERTICALDATUMGEOKEY),
					"if the VerticalGeoKey value is 32767 (User-Defined) then the VerticalCitationGeoKey, the VerticalUnitsGeoKey and VerticalDatumGeoKey SHALL be populated");
		}
		else if (value >= 1024 && value <= 32766) {
			// VerticalGeoKey values in the range 1024-32766 SHALL be either EPSG Vertical
			// CRS Codes or EPSG geographic 3D CRS codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.CRS, "COORD_REF_SYS_CODE", Integer.toString(value));
			Assert.assertTrue(record != null,
					"VerticalGeoKey values in the range 1024-32766 SHALL be either EPSG Vertical CRS Codes or EPSG geographic 3D CRS codes");
			// Assert.assertTrue(minorRevision == 1); // not necessary
			Assert.assertTrue(record.get("DEPRECATED").equals("0"),
					"VerticalGeoKey values in the range 1024-32766 SHALL be either EPSG Vertical CRS Codes or EPSG geographic 3D CRS codes");
			Assert.assertTrue(
					record.get("COORD_REF_SYS_KIND").equals("vertical")
							|| record.get("COORD_REF_SYS_KIND").equals("geographic 3D"),
					"VerticalGeoKey values in the range 1024-32766 SHALL be either EPSG Vertical CRS Codes or EPSG geographic 3D CRS codes");
		}
		else {
			// VerticalGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023,
					"VerticalGeoKey values in the range 1-1023 SHALL be reserved");

			// VerticalGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "VerticalGeoKey value out of bounds");
		}
	}

	// 4098 VerticalDatumGeoKey

	/**
	 * <p>
	 * verifyVerticalDatumGeoKey.
	 * </p>
	 * @throws java.io.IOException if any.
	 */
	@Test(description = "Short Params VerticalDatumGeoKey (2050) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyVerticalDatumGeoKey() throws IOException {
		// the VerticalDatumGeoKey SHALL have ID = 4098
		int index = getKeyIndex(VERTICALDATUMGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the VerticalDatumGeoKey SHALL have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the VerticalDatumGeoKey SHALL have type = SHORT");

		if (value == 32767) {
			// If the VerticalDatumGeoKey value is 32767 (User-Defined) then the
			// VerticalCitationGeoKey SHALL be populated.
			Assert.assertTrue(keyExists(VERTICALCITATIONGEOKEY),
					"if the VerticalDatumGeoKey value is 32767 (User-Defined) then the VerticalCitationGeoKey SHALL be populated");
		}
		else if (value >= 1024 && value <= 32766) {
			// VerticalDatumGeoKey values in the range 1024-32766 SHALL be EPSG vertical
			// datum codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.DATUM, "DATUM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null,
					"VerticalDatumGeoKey values in the range 1024-32766 SHALL be EPSG vertical datum codes");
			// Assert.assertTrue(minorRevision == 1); // not necessary
			Assert.assertTrue(record.get("DEPRECATED").equals("0"),
					"VerticalDatumGeoKey values in the range 1024-32766 SHALL be EPSG vertical datum codes");
			Assert.assertTrue(record.get("DATUM_TYPE").equals("vertical"),
					"VerticalDatumGeoKey values in the range 1024-32766 SHALL be EPSG vertical datum codes");
		}
		else {
			// VerticalDatumGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023,
					"VerticalDatumGeoKey values in the range 1-1023 SHALL be reserved");

			// VerticalDatumGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0, "VerticalDatumGeoKey value out of bounds");
		}
	}

	// 4099 UnitsGeoKey (Vertical Units) VerticalUnitsGeoKey

	/**
	 * <p>
	 * verifyVerticalUnitsGeoKey.
	 * </p>
	 * @throws java.io.IOException if any.
	 */
	@Test(description = "Short Params VerticalUnitsGeoKey (4099) Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyVerticalUnitsGeoKey() throws IOException {
		// the VerticalUnitsGeoKey SHALL have ID = 4099
		int index = getKeyIndex(VERTICALUNITSGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);

		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the
		// GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey
		// SHALL each have type = SHORT
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG, "the VerticalUnitsGeoKey SHALL have type = SHORT");

		// a VerticalUnitsGeoKey value of 32767 (user defined) SHALL not be used
		Assert.assertFalse(value == 32767, "a VerticalUnitsGeoKey value of 32767 (user defined) SHALL not be used");

		if (value >= 1024 && value <= 32766) {
			// GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values
			// in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type
			// = length
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.UOM, "UOM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null,
					"VerticalUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length");
			// Assert.assertTrue(minorRevision == 1); // not necessary
			Assert.assertTrue(record.get("DEPRECATED").equals("0"),
					"VerticalUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length");
			Assert.assertTrue(record.get("UNIT_OF_MEAS_TYPE").equals("length"),
					"VerticalUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length");
		}

		// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey,
		// ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023 SHALL
		// be reserved
		Assert.assertFalse(value >= 1 && value <= 1023,
				"VerticalUnitsGeoKey values in the range 1-1023 SHALL be reserved");

		// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey,
		// ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 32768-65535
		// SHALL be private
		// value out of bounds
		Assert.assertFalse(value > 65535 || value < 0, "VerticalUnitsGeoKey value out of bounds");

	}

}
