package org.opengis.cite.geotiff11.tiffTests;

import java.util.Arrays;
import java.util.List;

import org.opengis.cite.geotiff11.util.TiffDump;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.opengis.cite.geotiff11.util.GeoKeyID.*;

// https://github.com/opengeospatial/geotiff/blob/5d6ab0ba54f1ed0174901dd84240817dc9dbe011/GeoTIFF_Standard/standard/abstract_tests/TIFF_Tests/TEST_Short_Param.adoc

public class ShortParamsTests extends GeoKeysTests {
	
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
	
	int processFourthShort(int index, int keyLength) {
		// process the fourth Short integer in the Key Entry Set
		if(keyLength == 1) {
			// SET KeyValueOffset = GeoKeyDirectory + GeoKeyOffset + 6
			return (int) keyEntrySet.get(index+3);
		} else {
			// SET KeyValueOffset = GeoKeyDirectory + (KeyValueOffset * 2)
			return (int) keyEntrySet.get(keyLength); // TODO: verify this is a correct interpretation of the ats...
		}
	}
	
	//  https://github.com/opengeospatial/geotiff/blob/68d8f902293ad64526889daa055892ea30f9e9ea/GeoTIFF_Standard/Detailed%20Test%20Suite/abstract_tests/Requirements_Trace_Matrix.adoc
	//	GeoKey	Requirements Class
	//	0		ignore
	//	1024	GTModelTypeGeoKey
	
	// TODO: value testing - needs code lookup tables
	
	// tests
	
	@Test(description = "Short Params GTModelTypeGeoKey (1024) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGTModelTypeGeoKey() throws Exception {
		// the GTModelTypeGeoKey SHALL have ID = 1024
		int index = keyEntrySet.indexOf(GTMODELTYPEGEOKEY);
		// a GeoTIFF file SHALL include a GTModelTypeGeoKey
		Assert.assertTrue(index != -1);
		
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the GTModelTypeGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or TODO: which one should I use?
		if(!(type == 0 || type == 34735)) {
			throw new Exception("GTModelTypeGeoKey should be of type SHORT.");
		}
		
		// the GTModelTypeGeoKey value SHALL be: ...
		Assert.assertTrue(Arrays.asList(0, 1, 2, 3, 32767).contains(value));

		// GTModelTypeGeoKey values in the range 4-32766 SHALL be reserved
		Assert.assertFalse(value >= 4 && value <= 32766);
		
		// GTModelTypeGeoKey values in the range 32768-65535 SHALL be private
		Assert.assertFalse(value > 65535 || value < 0);
		
		switch(value) {		
			// if the GTModelTypeGeoKey value is 1 (Model CRS is a projected 2D CRS) then the GeoTIFF file SHALL include a ProjectedCRSGeoKey 3072
			case 1:
				Assert.assertTrue(keyExists(PROJECTEDCRSGEOKEY));
				break;
			// if the GTModelTypeGeoKey value is 2 (Model CRS is a geographic 2D CRS) then the GeoTIFF file SHALL include a GeodeticCRSGeoKey 2048
			// if the GTModelTypeGeoKey value is 3 (Model CRS is a geocentric CRS) then the GeoTIFF file SHALL include a GeodeticCRSGeoKey 2048
			case 2:
			case 3:
				Assert.assertTrue(keyExists(GEODETICCRSGEOKEY));
				break;
			// if the GTModelTypeGeoKey value is 32767 (user-defined) then the GTCitationGeoKey SHALL be populated
			case 32767:
				int gTCitationGeoKeyIndex = keyEntrySet.indexOf(GTCITATIONGEOKEY);
				Assert.assertTrue(gTCitationGeoKeyIndex != -1);
				String gTCitationGeoKey = ((String) directory.getTag(GEOASCIIPARAMSTAG).getValues().get(0)).substring(
						keyEntrySet.indexOf(gTCitationGeoKeyIndex + 3), 
						keyEntrySet.indexOf(gTCitationGeoKeyIndex + 3) + keyEntrySet.indexOf(gTCitationGeoKeyIndex + 2));
				System.out.println("shortparams:" + gTCitationGeoKey);
				Assert.assertFalse(gTCitationGeoKey.isEmpty()); // TODO: this is pretty rough...
				break;
		}
	}

	//	1025	GTRasterTypeGeoKey
	
	@Test(description = "Short Params GTRasterTypeGeoKey (1025) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGTRasterTypeGeoKey() throws Exception {
		// the GTRasterTypeGeoKey SHALL have ID = 1025
		int index = keyEntrySet.indexOf(GTRASTERTYPEGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the GTRasterTypeGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("GTRasterTypeGeoKey should be of type SHORT.");
		}
		
		// the GTRasterTypeGeoKey value SHALL be: ...
		Assert.assertTrue(Arrays.asList(0, 1, 2, 32767).contains(value));
		
		// GTRasterTypeGeoKey values in the range 3-32766 SHALL be reserved
		Assert.assertFalse(value >= 3 && value <= 32766);
		
		// GTRasterTypeGeoKey values in the range 32768-65535 SHALL be private
		// value out of bounds
		Assert.assertFalse(value > 65535 || value < 0);
	}
	
	//	2048	GeodeticCRSGeoKey
	
	@Test(description = "Short Params GeodeticCRSGeoKey (2048) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGeodeticCRSGeoKey() throws Exception {
		// the GeodeticCRSGeoKey SHALL have ID = 2048
		int index = keyEntrySet.indexOf(GEODETICCRSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the GeodeticCRSGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("GeodeticCRSGeoKey should be of type SHORT.");
		}
		
		// if the GeodeticCRSGeoKey value is 32767 (User-Defined) then the GeodeticCitationGeoKey 2049, GeodeticDatumGeoKey 2050 and at least one of GeogAngularUnitsGeoKey 2054 or GeogLinearUnitsGeoKey 2052 SHALL be populated
		if(value == 32767) {
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(GEODETICDATUMGEOKEY) && (keyExists(GEOGANGULARUNITSGEOKEY) || keyExists(GEOGLINEARUNITSGEOKEY)));
		} else {
			// GeodeticCRSGeoKey values in the range 1001-1023 SHALL be reserved
			Assert.assertFalse(value >= 1001 && value <= 1023);
			
			// GeodeticCRSGeoKey values in the range 1024-32766 SHALL be EPSG geodetic CRS codes (geographic 2D CRS, geographic 3D CRS, and geocentric CRS)
			Assert.assertFalse(minorRevision != 1 && value >= 1024 && value <= 32766);
			
			// GeodeticCRSGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POC Geographic Codes
			Assert.assertFalse(minorRevision != 0 && value >= 1 && value <= 1000);
					
			// GeodeticCRSGeoKeyvalues in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	2050	GeodeticDatumGeoKey
	
	@Test(description = "Short Params GeodeticDatumGeoKey (2050) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGeodeticDatumGeoKey() throws Exception {
		// the GeodeticDatumGeoKey SHALL have ID = 2050
		int index = keyEntrySet.indexOf(GEODETICDATUMGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the GeodeticDatumGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("GeodeticDatumGeoKey should be of type SHORT.");
		}
		
		if(value == 32767) {
			// If the GeodeticDatumGeoKey value is 32767 (User-Defined) then the GeodeticCitationGeoKey 2049, PrimeMeridianGeoKey 2051 and EllipsoidGeoKey 2056 SHALL be populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(PRIMEMERIDIANGEOKEY) && keyExists(ELLIPSOIDGEOKEY));
		} else {		
			// GeodeticDatumGeoKey values in the range 1024-32766 SHALL be EPSG geodetic datum codes
			Assert.assertFalse(minorRevision != 1 && value >= 1024 && value <= 32766);
			
			// GeodeticDatumGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POS Datum Codes
			Assert.assertFalse(minorRevision != 0 && value >= 1 && value <= 1000);
					
			// GeodeticDatumGeoKey values in the range 1001-1023 SHALL be reserved
			Assert.assertFalse(value >= 1001 && value <= 1023);
			
			// GeodeticDatumGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	2051	PrimeMeridianGeoKey
	
	@Test(description = "Short Params PrimeMeridianGeoKey (2051) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyPrimeMeridianGeoKey() throws Exception {
		// the PrimeMeridianGeoKey SHALL have ID = 2051
		int index = keyEntrySet.indexOf(PRIMEMERIDIANGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the PrimeMeridianGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("PrimeMeridianGeoKey should be of type SHORT.");
		}
		
		if(value == 32767) {
			// If the PrimeMeridianGeoKey value is 32767 (User-Defined) then the GeodeticCitationGeoKey, and PrimeMeridianLongGeoKey SHALL be populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(PRIMEMERIDIANLONGITUDEGEOKEY));
		} else {		
			// PrimeMeridianGeoKey values in the range 1024-32766 SHALL be EPSG Prime Meridian Codes
			Assert.assertFalse(minorRevision != 1 && value >= 1024 && value <= 32766);
			
			// PrimeMeridianGeoKey values in the range 1-100 SHALL be obsolete EPSG/POSC Datum Codes
			Assert.assertFalse(minorRevision != 0 && value >= 1 && value <= 100);
					
			// PrimeMeridianGeoKey values in the range 101-1023 SHALL be reserved
			Assert.assertFalse(value >= 101 && value <= 1023);
			
			// PrimeMeridianGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	2052	UnitsGeoKey (Linear Units) GeogLinearUnitsGeoKey
	
	@Test(description = "Short Params GeogLinearUnitsGeoKey (2052) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGeogLinearUnitsGeoKey() throws Exception {
		// the GeogLinearUnitsGeoKey SHALL have ID = 2052
		int index = keyEntrySet.indexOf(GEOGLINEARUNITSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey SHALL each have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("GeogLinearUnitsGeoKey should be of type SHORT.");
		}
		
		if(value == 32767) {
			// A GeogLinearUnitsGeoKey value of 32767 SHALL be a user-defined linear unit. If the value is 32767 (User-Defined) then the GeodeticCitationGeoKey and the GeogLinearUnitSizeGeoKey SHALL be populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(GEOGLINEARUNITSIZEGEOKEY));
		} else {		
			// GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length
			// TODO: how to check type? need some sort of lookup table for epsg uom codes
			// TODO: what about obsolete?
			Assert.assertFalse(minorRevision != 1 && value >= 1024 && value <= 32766);
					
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023);
			
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	2054	UnitsGeoKey (Angular Units) GeogAngularUnitsGeoKey
	
	@Test(description = "Short Params GeogAngularUnitsGeoKey (2052) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGeogAngularUnitsGeoKey() throws Exception {
		// the GeogAngularUnitsGeoKey SHALL have ID = 2054
		int index = keyEntrySet.indexOf(GEOGANGULARUNITSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey SHALL each have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("GeogAngularUnitsGeoKey should be of type SHORT.");
		}
		
		if(value == 32767) {
			// A GeogAngularUnitsGeoKey or a GeogAzimuthUnitsGeoKey value of 32767 SHALL be a user-defined angular unit. If the value is 32767 (User-Defined) then the GeodeticCitationGeoKey and the GeogAngularUnitSizeGeoKey SHALL be populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(GEOGANGULARUNITSIZEGEOKEY));
		} else {		
			// GeogAngularUnitsGeoKey and GeogAzimuthUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = angle
			// TODO: how to check type? need some sort of lookup table for epsg uom codes
			// TODO: what about obsolete?
			// TODO: check minorrevision?
			Assert.assertFalse(minorRevision != 1 && value >= 1024 && value <= 32766);
					
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023);
			
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	2056	EllipsoidGeoKey
	
	@Test(description = "Short Params EllipsoidGeoKey (2056) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyEllipsoidGeoKey() throws Exception {
		// the PrimeMeridianGeoKey SHALL have ID = 2056
		int index = keyEntrySet.indexOf(ELLIPSOIDGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the EllipsoidGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("EllipsoidGeoKey should be of type SHORT.");
		}
		
		if(value == 32767) {
			// If the EllipsoidGeoKey value is 32767 (User-Defined) then the GTCitationGeoKey and the EllipsoidSemiMajorAxisGeoKey SHALL be populated together with the one of either the EllipsoidSemiMinorAxisGeoKey or the EllipsoidInvFlatteningGeoKey
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(ELLIPSOIDSEMIMAJORAXISGEOKEY) && (keyExists(ELLIPSOIDSEMIMINORAXISGEOKEY) || keyExists(ELLIPSOIDINVFLATTENINGGEOKEY)));
		} else {		
			// EllipsoidGeoKey values in the range 1024-32766 SHALL be EPSG ellipsoid Codes
			Assert.assertFalse(minorRevision != 1 && value >= 1024 && value <= 32766);
			
			// EllipsoidGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POSC Datum Codes
			Assert.assertFalse(minorRevision != 0 && value >= 1 && value <= 1000);
			
			// no reserved
			
			// EllipsoidGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0); // TODO: Check if between 1000-1023?
		}
	}
	
	//	2060	UnitsGeoKey (Azimuth Units) GeogAzimuthUnitsGeoKey
	
	@Test(description = "Short Params GeogAzimuthUnitsGeoKey (2060) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGeogAzimuthUnitsGeoKey() throws Exception {
		// the GeogAzimuthUnitsGeoKey SHALL have ID = 2060
		int index = keyEntrySet.indexOf(GEOGAZIMUTHUNITSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey SHALL each have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("GeogAzimuthUnitsGeoKey should be of type SHORT.");
		}
		
		if(value == 32767) {
			// A GeogAngularUnitsGeoKey or a GeogAzimuthUnitsGeoKey value of 32767 SHALL be a user-defined angular unit. If the value is 32767 (User-Defined) then the GeodeticCitationGeoKey and the GeogAngularUnitSizeGeoKey SHALL be populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(GEOGANGULARUNITSIZEGEOKEY));
		} else {		
			// GeogAngularUnitsGeoKey and GeogAzimuthUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = angle
			// TODO: how to check type? need some sort of lookup table for epsg uom codes
			// TODO: what about obsolete?
			Assert.assertFalse(minorRevision != 1 && value >= 1024 && value <= 32766);
					
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023);
			
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	3072	ProjectedCRSGeoKey
	
	@Test(description = "Short Params ProjectedCRSGeoKey (2056) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyProjectedCRSGeoKey() throws Exception {
		// the ProjectedCRSGeoKey SHALL have ID = 3072
		int index = keyEntrySet.indexOf(PROJECTEDCRSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the ProjectedCRSGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("ProjectedCRSGeoKey should be of type SHORT.");
		}
		
		if(value == 32767) {
			// A ProjectedCRSGeoKey value of 32767 SHALL be a user-defined projected CRS. If the ProjectedCRSGeoKey value is 32767 (User-Defined) then the ProjectedCitationGeoKey, GeodeticCRSGeoKey and ProjectionGeoKey SHALL be populated
			Assert.assertTrue(keyExists(PROJECTEDCITATIONGEOKEY) && keyExists(GEODETICCRSGEOKEY) && keyExists(PROJECTIONGEOKEY));
		} else {		
			// ProjectedCRSGeoKey values in the range 1024-32766 SHALL be EPSG Projected CRS Codes
			Assert.assertFalse(minorRevision != 1 && value >= 1024 && value <= 32766);
			
			// ProjectedCRSGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POC Datum Codes
			Assert.assertFalse(minorRevision != 0 && value >= 1 && value <= 1000);
			
			// ProjectedCRSGeoKey values in the range 1001-1023 SHALL be reserved
			Assert.assertFalse(value >= 1001 && value <= 1023);
			
			// ProjectedCRSGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0); // TODO: should value < 0 be value < 1?
		}
	}
	
	//	3074	ProjectionGeoKey
	
	@Test(description = "Short Params ProjectionGeoKey (3074) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyProjectionGeoKey() throws Exception {
		// the ProjectionGeoKey SHALL have ID = 3074
		int index = keyEntrySet.indexOf(PROJECTIONGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the ProjectionGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("ProjectionGeoKey should be of type SHORT.");
		}
		
		if(value == 32767) {
			// If the ProjectionGeoKey value is 32767 (User-Defined) then the ProjectedCitationGeoKey, ProjectionMethodGeoKey, and ProjLinearUnitsGeoKey SHALL be populated
			Assert.assertTrue(keyExists(PROJECTEDCITATIONGEOKEY) && keyExists(PROJMETHODGEOKEY) && keyExists(PROJLINEARUNITSGEOKEY));
		} else {		
			// ProjectionGeoKey values in the range 1024-32766 SHALL be valid EPSG map projection (coordinate operation) codes
			// TODO: check validity of EPSG codes
			// TODO: check minorRevision != 1?
			Assert.assertFalse(value >= 1024 && value <= 32766);
			
			// ProjectionGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023);
			
			// ProjectionGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0); // TODO: should value < 0 be value < 1?
		}
	}
	
	//	3075	ProjMethodGeoKey
	
	@Test(description = "Short Params ProjMethodGeoKey (3075) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyProjMethodGeoKey() throws Exception {
		// the ProjMethodGeoKey SHALL have ID = 3075
		int index = keyEntrySet.indexOf(PROJMETHODGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the ProjMethodGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("ProjMethodGeoKey should be of type SHORT.");
		}
		
		if(value == 32767) {
			// If the ProjectionMethodGeoKey value is 32767 (User-Defined) then the ProjectedCitationGeoKey 
			Assert.assertTrue(keyExists(PROJECTEDCITATIONGEOKEY));
			// and keys for each map projection parameter (coordinate operation parameter) appropriate to that method SHALL be populated
		
		} else {		
			// ProjMethodGeoKey values in the range 1-27 SHALL be GeoTIFF map projection method codes
			// TODO: check codes
			// TODO: check minorRevision != 1?
			Assert.assertFalse(value >= 1 && value <= 27);
			
			// ProjMethodGeoKey values in the range 28-32766 SHALL be reserved
			Assert.assertFalse(value >= 28 && value <= 32766);
			
			// ProjMethodGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0); // TODO: should value < 0 be value < 1?
		}
	}
	
	//	3076	UnitsGeoKey (Linear Units) ProjLinearUnitsGeoKey
	
	@Test(description = "Short Params ProjLinearUnitsGeoKey (3076) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyProjLinearUnitsGeoKey() throws Exception {
		// the GeogLinearUnitsGeoKey SHALL have ID = 3076
		int index = keyEntrySet.indexOf(PROJLINEARUNITSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey SHALL each have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("ProjLinearUnitsGeoKey should be of type SHORT.");
		}
		
		if(value == 32767) {
			// A ProjLinearUnitsGeoKey value of 32767 SHALL be a user-defined linear unit. If the value is 32767 (User-Defined) then the ProjectedCitationGeoKey and the ProjLinearUnitSizeGeoKey SHALL be populated
			Assert.assertTrue(keyExists(PROJECTEDCITATIONGEOKEY) && keyExists(PROJLINEARUNITSIZEGEOKEY));
		} else {		
			// GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length
			// TODO: how to check type? need some sort of lookup table for epsg uom codes
			// TODO: what about obsolete?
			Assert.assertFalse(minorRevision != 1 && value >= 1024 && value <= 32766);
					
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023);
			
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	4096	VerticalGeoKey
	
	@Test(description = "Short Params VerticalGeoKey (4096) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyVerticalGeoKey() throws Exception {
		// the VerticalGeoKey SHALL have ID = 4096
		int index = keyEntrySet.indexOf(VERTICALGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the VerticalGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("VerticalGeoKey should be of type SHORT.");
		}
		
		if(value == 32767) {
			// If the VerticalGeoKey value is 32767 (User-Defined) then the VerticalCitationGeoKey, the VerticalUnitsGeoKey and VerticalDatumGeoKey SHALL be populated
			Assert.assertTrue(keyExists(VERTICALCITATIONGEOKEY) && keyExists(VERTICALUNITSGEOKEY) && keyExists(VERTICALDATUMGEOKEY));
		} else {		
			// VerticalGeoKey values in the range 1024-32766 SHALL be either EPSG Vertical CRS Codes or EPSG geographic 3D CRS codes
			// TODO: check codes
			Assert.assertFalse(minorRevision != 1 && value >= 1024 && value <= 32766);
			
			// VerticalGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023);
			
			// VerticalGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	4098	VerticalDatumGeoKey
	
	@Test(description = "Short Params VerticalDatumGeoKey (2050) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyVerticalDatumGeoKey() throws Exception {
		// the VerticalDatumGeoKey SHALL have ID = 4098
		int index = keyEntrySet.indexOf(VERTICALDATUMGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the VerticalDatumGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("VerticalDatumGeoKey should be of type SHORT.");
		}
		
		if(value == 32767) {
			// If the VerticalDatumGeoKey value is 32767 (User-Defined) then the VerticalCitationGeoKey SHALL be populated.
			Assert.assertTrue(keyExists(VERTICALCITATIONGEOKEY));
		} else {		
			// VerticalDatumGeoKey values in the range 1024-32766 SHALL be EPSG vertical datum codes
			Assert.assertFalse(minorRevision != 1 && value >= 1024 && value <= 32766);
					
			// VerticalDatumGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023);
			
			// VerticalDatumGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	4099	UnitsGeoKey (Vertical Units) VerticalUnitsGeoKey

	@Test(description = "Short Params VerticalUnitsGeoKey (4099) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyVerticalUnitsGeoKey() throws Exception {
		// the VerticalUnitsGeoKey SHALL have ID = 4099
		int index = keyEntrySet.indexOf(VERTICALUNITSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey SHALL each have type = SHORT		
		Assert.assertTrue(type == 0 || type == 34735);
		// or
		if(!(type == 0 || type == 34735)) {
			throw new Exception("VerticalUnitsGeoKey should be of type SHORT.");
		}
		
		// a VerticalUnitsGeoKey value of 32767 (user defined) SHALL not be used
		Assert.assertFalse(value == 32767);
	
		// GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length
		// TODO: how to check type? need some sort of lookup table for epsg uom codes
		// TODO: what about obsolete?
		Assert.assertFalse(minorRevision != 1 && value >= 1024 && value <= 32766);
				
		// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023 SHALL be reserved
		Assert.assertFalse(value >= 1 && value <= 1023);
		
		// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 32768-65535 SHALL be private
		// value out of bounds
		Assert.assertFalse(value > 65535 || value <= 0);
	
	}
}