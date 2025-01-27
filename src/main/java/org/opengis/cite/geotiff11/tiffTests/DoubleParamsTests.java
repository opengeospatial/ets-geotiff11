package org.opengis.cite.geotiff11.tiffTests;

import static org.opengis.cite.geotiff11.util.GeoKeyID.ELLIPSOIDINVFLATTENINGGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.ELLIPSOIDSEMIMAJORAXISGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.ELLIPSOIDSEMIMINORAXISGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEODOUBLEPARAMSTAG;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOGANGULARUNITSGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOGANGULARUNITSIZEGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOGAZIMUTHUNITSGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOGLINEARUNITSGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOGLINEARUNITSIZEGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PRIMEMERIDIANLONGITUDEGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJAZIMUTHANGLEGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJCENTEREASTINGGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJCENTERLATGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJCENTERLONGGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJCENTERNORTHINGGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJFALSEEASTINGGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJFALSENORTHINGGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJFALSEORIGINEASTINGGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJFALSEORIGINLATGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJFALSEORIGINLONGGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJFALSEORIGINNORTHINGGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJLINEARUNITSGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJLINEARUNITSIZEGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJNATORIGINLATGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJNATORIGINLONGGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJSCALEATCENTERGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJSCALEATNATORIGINGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJSTDPARALLEL1GEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJSTDPARALLEL2GEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.PROJSTRAIGHTVERTPOLELONGGEOKEY;

import java.util.List;

import org.opengis.cite.geotiff11.util.EPSGDataSet;
import org.testng.Assert;
import org.testng.annotations.Test;

// https://github.com/opengeospatial/geotiff/blob/master/GeoTIFF_Standard/standard/abstract_tests/TIFF_Tests/TEST_Double_Param.adoc

/**
 * <p>
 * DoubleParamsTests class.
 * </p>
 *
 */
public class DoubleParamsTests extends GeoKeysTests {

	/*
	 * Double Parameters Test Test
	 * id: http://www.opengis.net/spec/GeoTIFF/1.1/conf/DoubleParameters Requirements:
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.KeyEntry.ID
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.
	 * keyEntryTIFFTagLocation
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyEntryKeyCount
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyEntryValueOffset
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoDoubleParamsTag.ID
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoDoubleParamsTag.count
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/PrimeMeridianLongitudeGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/UnitSizeGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/EllipsoidSemiMajorAxisGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/EllipsoidSemiMinorAxisGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/EllipsoidInvFlatteningGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjAngularParameterGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjLinearParameterGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjScalarParameterGeoKey
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjAzimuthAngleGeoKey Purpose: Verify
	 * Double parameters Pre-conditions The GeoKeyDirectory, DoubleValues and GeoKeyOffset
	 * values have been set. Test Variables: Variable Scope Description GeoKeyDirectory
	 * Global Location of the GeoTIFF GeoKey directory DoubleValues Global Location of the
	 * Double values for GeoTIFF Double GeoKeys GeoKeyOffset Parameter Location of this
	 * Key Entry Set in the GeoKey directory GeoKey Local Temporary value of the GeoKey
	 * KeyLength Local Temporary value for the length of the value for the GeoKey
	 * KeyValueOffset Local The location of the GeoKey value in the file
	 */

	float processFourthShortForDouble(int index) {
		int doubleIndex = (int) keyEntrySet.get(index + 3);
		// process the fourth Short integer in the Key Entry Set
		List<Object> doubleParamsSet = directory.getTag(GEODOUBLEPARAMSTAG).getValues();
		Assert.assertTrue(doubleParamsSet != null);
		// SET KeyValueOffset = DoubleValues + (KeyValueOffset * 8)
		return (float) doubleParamsSet.get(doubleIndex);
	}

	// GeoKey Requirements Class

	// 2053 UnitSizeGeoKey (Geog Linear)

	/**
	 * <p>
	 * verifyGeogLinearUnitSizeGeoKey.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Double Params GeogLinearUnitSizeGeoKey (2053) Test",
			dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGeogLinearUnitSizeGeoKey() throws Exception {
		// the GeogLinearUnitSizeGeoKey SHALL have ID = 2053
		int index = getKeyIndex(GEOGLINEARUNITSIZEGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		float value = processFourthShortForDouble(index);

		// the GeogLinearUnitSizeGeoKey SHALL have type = DOUBLE
		Assert.assertTrue(type == GEODOUBLEPARAMSTAG, "the GeogLinearUnitSizeGeoKey SHALL have type = DOUBLE");

		// the units of the GeogLinearUnitSizeGeoKey value SHALL be meters
		Assert.assertTrue(keyExists(GEOGLINEARUNITSGEOKEY),
				"the units of the GeogLinearUnitSizeGeoKey value SHALL be meters");
		int unitsValue = (int) processFourthShortForShort(getKeyIndex(GEOGLINEARUNITSGEOKEY),
				processThirdShort(getKeyIndex(GEOGLINEARUNITSGEOKEY)));
		if (unitsValue != 32767)
			Assert.assertTrue(EPSGDataSet.getItem(EPSGDataSet.UOM, "UOM_CODE", unitsValue, "TARGET_UOM_CODE")
				.equals(EPSGDataSet.METERS), "the units of the GeogLinearUnitSizeGeoKey value SHALL be meters");
	}

	// 2055 UnitSizeGeoKey (Geog Angular)

	/**
	 * <p>
	 * verifyGeogAngularUnitSizeGeoKey.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Double Params GeogAngularUnitSizeGeoKey (2055) Test",
			dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyGeogAngularUnitSizeGeoKey() throws Exception {
		// the GeogAngularUnitSizeGeoKey SHALL have ID = 2055
		int index = getKeyIndex(GEOGANGULARUNITSIZEGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		float value = processFourthShortForDouble(index);

		// the GeogAngularUnitSizeGeoKey SHALL have type = DOUBLE
		Assert.assertTrue(type == GEODOUBLEPARAMSTAG, "the GeogAngularUnitSizeGeoKey SHALL have type = DOUBLE");

		// the units of the GeogAngularUnitSizeGeoKey value SHALL be radians
		Assert.assertTrue(keyExists(GEOGANGULARUNITSGEOKEY));
		int unitsValue = (int) processFourthShortForShort(getKeyIndex(GEOGANGULARUNITSGEOKEY),
				processThirdShort(getKeyIndex(GEOGANGULARUNITSGEOKEY)));
		if (unitsValue != 32767)
			Assert.assertTrue(
					EPSGDataSet.getItem(EPSGDataSet.UOM, "UOM_CODE", unitsValue, "TARGET_UOM_CODE")
						.equals(EPSGDataSet.RADIANS),
					"the units of the GeogAngularUnitSizeGeoKey value SHALL be radians");
	}

	// 2057 EllipsoidSemiMajorAxisGeoKey

	/**
	 * <p>
	 * verifyEllipsoidSemiMajorAxisGeoKey.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Double Params EllipsoidSemiMajorAxisGeoKey (2057) Test",
			dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyEllipsoidSemiMajorAxisGeoKey() throws Exception {
		// the EllipsoidSemiMajorAxisGeoKey SHALL have ID = 2057
		int index = getKeyIndex(ELLIPSOIDSEMIMAJORAXISGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		float value = processFourthShortForDouble(index);

		// the EllipsoidSemiMajorAxisGeoKey SHALL have type = DOUBLE
		Assert.assertTrue(type == GEODOUBLEPARAMSTAG, "the EllipsoidSemiMajorAxisGeoKey SHALL have type = DOUBLE");

		// the units of the EllipsoidSemiMajorAxisGeoKey SHALL be defined by the value of
		// GeogLinearUnitsGeoKey
		Assert.assertTrue(keyExists(GEOGLINEARUNITSGEOKEY),
				"the units of the EllipsoidSemiMajorAxisGeoKey SHALL be defined by the value of GeogLinearUnitsGeoKey");
	}

	// 2058 EllipsoidSemiMinorAxisGeoKey

	/**
	 * <p>
	 * verifyEllipsoidSemiMinorAxisGeoKey.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Double Params EllipsoidSemiMinorAxisGeoKey (2058) Test",
			dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyEllipsoidSemiMinorAxisGeoKey() throws Exception {
		// the EllipsoidSemiMinorAxisGeoKey SHALL have ID = 2058
		int index = getKeyIndex(ELLIPSOIDSEMIMINORAXISGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		float value = processFourthShortForDouble(index);

		// the EllipsoidSemiMinorAxisGeoKey SHALL have type = DOUBLE
		Assert.assertTrue(type == GEODOUBLEPARAMSTAG, "the EllipsoidSemiMinorAxisGeoKey SHALL have type = DOUBLE");

		// the units of the EllipsoidSemiMinorAxisGeoKey SHALL be defined by the value of
		// GeogLinearUnitsGeoKey
		Assert.assertTrue(keyExists(GEOGLINEARUNITSGEOKEY),
				"the units of the EllipsoidSemiMinorAxisGeoKey SHALL be defined by the value of GeogLinearUnitsGeoKey");
	}

	// 2059 EllipsoidInvFlatteningGeoKey

	/**
	 * <p>
	 * verifyEllipsoidInvFlatteningGeoKey.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Double Params EllipsoidInvFlatteningGeoKey (2059) Test",
			dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyEllipsoidInvFlatteningGeoKey() throws Exception {
		// The EllipsoidInvFlatteningGeoKey SHALL have ID = 2059
		int index = getKeyIndex(ELLIPSOIDINVFLATTENINGGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		float value = processFourthShortForDouble(index);

		// the EllipsoidInvFlatteningGeoKey SHALL have type = DOUBLE
		Assert.assertTrue(type == GEODOUBLEPARAMSTAG, "the EllipsoidInvFlatteningGeoKey SHALL have type = DOUBLE");
	}

	// 2061 PrimeMeridianLongitudeGeoKey

	/**
	 * <p>
	 * verifyPrimeMeridianLongitudeGeoKey.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Double Params PrimeMeridianLongitudeGeoKey (2061) Test",
			dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyPrimeMeridianLongitudeGeoKey() throws Exception {
		// the PrimeMeridianLongitudeGeoKey SHALL have ID = 2061
		int index = getKeyIndex(PRIMEMERIDIANLONGITUDEGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		float value = processFourthShortForDouble(index);

		// the PrimeMeridianLongitudeGeoKey SHALL have type = DOUBLE
		Assert.assertTrue(type == GEODOUBLEPARAMSTAG, "the PrimeMeridianLongitudeGeoKey SHALL have type = DOUBLE");

		// the unit for the PrimeMeridianLongitudeGeoKey value SHALL be GeogAngularUnits
		Assert.assertTrue(keyExists(GEOGANGULARUNITSGEOKEY),
				"the unit for the PrimeMeridianLongitudeGeoKey value SHALL be GeogAngularUnits");
	}

	// 3077 UnitSizeGeoKey (Projected Linear)

	/**
	 * <p>
	 * verifyProjLinearUnitSizeGeoKey.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Double Params ProjLinearUnitSizeGeoKey (3077) Test",
			dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyProjLinearUnitSizeGeoKey() throws Exception {
		// the ProjLinearUnitSizeGeoKey SHALL have ID = 3077
		int index = getKeyIndex(PROJLINEARUNITSIZEGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		float value = processFourthShortForDouble(index);

		// the ProjLinearUnitSizeGeoKey SHALL have type = DOUBLE
		Assert.assertTrue(type == GEODOUBLEPARAMSTAG, "the ProjLinearUnitSizeGeoKey SHALL have type = DOUBLE");

		// the units of the ProjLinearUnitSizeGeoKey value SHALL be meters
		Assert.assertTrue(keyExists(PROJLINEARUNITSGEOKEY),
				"the units of the ProjLinearUnitSizeGeoKey value SHALL be meters");
		int unitsValue = (int) processFourthShortForShort(getKeyIndex(PROJLINEARUNITSGEOKEY),
				processThirdShort(getKeyIndex(PROJLINEARUNITSGEOKEY)));
		if (unitsValue != 32767)
			Assert.assertTrue(EPSGDataSet.getItem(EPSGDataSet.UOM, "UOM_CODE", unitsValue, "TARGET_UOM_CODE")
				.equals(EPSGDataSet.METERS), "the units of the ProjLinearUnitSizeGeoKey value SHALL be meters");
	}

	// 3092 ProjScalarParameters (Scale at Natural Origin)
	// 3093 ProjScalarParameters (Scale at Center)

	/**
	 * <p>
	 * verifyProjScalarParameters.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "ProjScalarParameters Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyProjScalarParameters() throws Exception {

		// the ProjScalarParameters SHALL have ID = XXX
		int[] projScalarParameters = { PROJSCALEATNATORIGINGEOKEY, PROJSCALEATCENTERGEOKEY };

		for (int geoKey : projScalarParameters) {

			int index = getKeyIndex(geoKey);

			// not required
			if (index == -1) {
				continue;
			}

			int type = processSecondShort(index);

			// all parameters in this requirements class SHALL have type = DOUBLE
			Assert.assertTrue(type == GEODOUBLEPARAMSTAG, "the ProjScalarParameters SHALL have type = DOUBLE");
		}
	}

	// 3094 ProjAzimuthAngleGeoKey

	/**
	 * <p>
	 * verifyProjAzimuthAngleGeoKey.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "Double Params ProjAzimuthAngleGeoKey (3094) Test",
			dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyProjAzimuthAngleGeoKey() throws Exception {
		// the ProjAzimuthAngleGeoKey SHALL have ID = 3094
		int index = getKeyIndex(PROJAZIMUTHANGLEGEOKEY);

		// not required
		if (index == -1) {
			return;
		}

		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		float value = processFourthShortForDouble(index);

		// the ProjAzimuthAngleGeoKey SHALL have type = DOUBLE
		Assert.assertTrue(type == GEODOUBLEPARAMSTAG, "the ProjAzimuthAngleGeoKey SHALL have type = DOUBLE");

		// the ProjAzimuthAngleGeoKey SHALL have units as specified by the
		// GeogAzimuthUnitsGeoKey
		Assert.assertTrue(keyExists(GEOGAZIMUTHUNITSGEOKEY),
				"the ProjAzimuthAngleGeoKey SHALL have units as specified by the GeogAzimuthUnitsGeoKey");
	}

	// 3078 ProjAngularParameters (Standard Parallel 1)
	// 3079 ProjAngularParameters (Standard Parallel 2)
	// 3080 ProjAngularParameters (Natural Origin Longitude)
	// 3081 ProjAngularParameters (Natural Origin Latitude)
	// 3084 ProjAngularParameters (False Origin Longitude)
	// 3085 ProjAngularParameters (False Origin Latitude)
	// 3088 ProjAngularParameters (Center Longitude)
	// 3089 ProjAngularParameters (Center Latitude)
	// 3095 ProjAngularParameters (Straight Vertical Pole)

	/**
	 * <p>
	 * verifyProjAngularParameters.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "ProjAngularParameters Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyProjAngularParameters() throws Exception {

		// the ProjAngularParameter SHALL have ID = XXX
		int[] projAngularParameters = { PROJSTDPARALLEL1GEOKEY, PROJSTDPARALLEL2GEOKEY, PROJNATORIGINLONGGEOKEY,
				PROJNATORIGINLATGEOKEY, PROJFALSEORIGINLONGGEOKEY, PROJFALSEORIGINLATGEOKEY, PROJCENTERLONGGEOKEY,
				PROJCENTERLATGEOKEY, PROJSTRAIGHTVERTPOLELONGGEOKEY };

		for (int geoKey : projAngularParameters) {

			int index = getKeyIndex(geoKey);

			// not required
			if (index == -1) {
				continue;
			}

			int type = processSecondShort(index);

			// all parameters in this requirements class SHALL have type = DOUBLE
			Assert.assertTrue(type == GEODOUBLEPARAMSTAG, "the ProjAngularParameters SHALL have type = DOUBLE");

			// all parameters in this requirements class SHALL have units as specified by
			// the GeogAngularUnitsGeoKey
			Assert.assertTrue(keyExists(GEOGANGULARUNITSGEOKEY),
					"all parameters in this requirements class SHALL have units as specified by the GeogAngularUnitsGeoKey");
		}
	}

	// 3082 ProjLinearParameters (False Easting)
	// 3083 ProjLinearParameters (False Northing)
	// 3086 ProjLinearParameters (False Origin Easting)
	// 3087 ProjLinearParameters (False Origin Northing)
	// 3090 ProjLinearParameters (Projection Center Easting)
	// 3091 ProjLinearParameters (Projection Center Northing)

	/**
	 * <p>
	 * verifyProjLinearParameters.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "ProjLinearParameters Test", dependsOnGroups = { "verifyGeoKeyDirectory" })
	public void verifyProjLinearParameters() throws Exception {

		// the ProjLinearParameter SHALL have ID = XXX
		int[] projLinearParameters = { PROJFALSEEASTINGGEOKEY, PROJFALSENORTHINGGEOKEY, PROJFALSEORIGINEASTINGGEOKEY,
				PROJFALSEORIGINNORTHINGGEOKEY, PROJCENTEREASTINGGEOKEY, PROJCENTERNORTHINGGEOKEY };

		for (int geoKey : projLinearParameters) {

			int index = getKeyIndex(geoKey);

			// not required
			if (index == -1) {
				continue;
			}

			int type = processSecondShort(index);

			// all parameters in this requirements class SHALL have type = DOUBLE
			Assert.assertTrue(type == GEODOUBLEPARAMSTAG, "the ProjLinearParameters SHALL have type = DOUBLE");

			// all parameters in this requirements class SHALL have units as specified by
			// the ProjLinearUnitsGeoKey
			Assert.assertTrue(keyExists(PROJLINEARUNITSGEOKEY),
					"all parameters in this requirements class SHALL have units as specified by the ProjLinearUnitsGeoKey");
		}
	}

}
