package org.opengis.cite.geotiff11.tiffTests;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.opengis.cite.geotiff11.util.EPSGDataSet;
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
	
	//  https://github.com/opengeospatial/geotiff/blob/68d8f902293ad64526889daa055892ea30f9e9ea/GeoTIFF_Standard/Detailed%20Test%20Suite/abstract_tests/Requirements_Trace_Matrix.adoc
	//	GeoKey	Requirements Class
	//	0		ignore
	//	1024	GTModelTypeGeoKey
	
	// TODO: Parameter values stored in the GeoKeysDirectory SHALL appear after the last Key Entry ??? How to implement this?
	
	// tests
	
	@Test(description = "Short Params GTModelTypeGeoKey (1024) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGTModelTypeGeoKey() {
		// the GTModelTypeGeoKey SHALL have ID = 1024
		int index = getKeyIndex(GTMODELTYPEGEOKEY);
		// a GeoTIFF file SHALL include a GTModelTypeGeoKey
		Assert.assertTrue(index != -1);
		
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the GTModelTypeGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		if (value == 0 || value == 1 || value == 2 || value== 3 || value== 32767)
		{
			// the GTModelTypeGeoKey value SHALL be: ...
			Assert.assertTrue(Arrays.asList(0, 1, 2, 3, 32767).contains(value));
		}
		else
		{		
			// GTModelTypeGeoKey values in the range 4-32766 SHALL be reserved
			Assert.assertFalse(value >= 4 && value <= 32766);
			
			// GTModelTypeGeoKey values in the range 32768-65535 SHALL be private
			Assert.assertFalse(value > 65535 || value < 0);		
		}

		
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
				int gTCitationGeoKeyIndex = getKeyIndex(GTCITATIONGEOKEY);
				Assert.assertTrue(gTCitationGeoKeyIndex != -1);
				
				int citation_type = processSecondShort(gTCitationGeoKeyIndex);
				int citation_keyLength = processThirdShort(gTCitationGeoKeyIndex);
				int citation_value = (int) keyEntrySet.get(gTCitationGeoKeyIndex+3);
				
				Assert.assertTrue(GEOASCIIPARAMSTAG == citation_type);
				if (GEOASCIIPARAMSTAG == citation_type)
				{
					String gTCitationGeoKey = ((String) directory.getTag(GEOASCIIPARAMSTAG).getValues().get(0) ).substring(
							citation_value, 
							citation_keyLength - 1);
					System.out.println("shortparams:" + gTCitationGeoKey);
					Assert.assertFalse(gTCitationGeoKey.isEmpty()); // TODO: this is pretty rough...
					break;
				}
		}
	}

	//	1025	GTRasterTypeGeoKey
	
	@Test(description = "Short Params GTRasterTypeGeoKey (1025) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGTRasterTypeGeoKey() {
		// the GTRasterTypeGeoKey SHALL have ID = 1025
		int index = getKeyIndex(GTRASTERTYPEGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the GTRasterTypeGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		// or
		
		if (value == 0 || value == 1 || value == 2 || value== 3 || value== 32767)
		{
			// the GTRasterTypeGeoKey value SHALL be: ...
			Assert.assertTrue(Arrays.asList(0, 1, 2, 32767).contains(value));
		}
		else
		{
			// GTRasterTypeGeoKey values in the range 3-32766 SHALL be reserved
			Assert.assertFalse(value >= 3 && value <= 32766);
			
			// GTRasterTypeGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value < 0);
		}
	}
	
	//	2048	GeodeticCRSGeoKey
	
	@Test(description = "Short Params GeodeticCRSGeoKey (2048) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGeodeticCRSGeoKey() throws IOException {
		// the GeodeticCRSGeoKey SHALL have ID = 2048
		int index = getKeyIndex(GEODETICCRSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the GeodeticCRSGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		// if the GeodeticCRSGeoKey value is 32767 (User-Defined) then the GeodeticCitationGeoKey 2049, GeodeticDatumGeoKey 2050 and at least one of GeogAngularUnitsGeoKey 2054 or GeogLinearUnitsGeoKey 2052 SHALL be populated
		if(value == 32767) {
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(GEODETICDATUMGEOKEY) && (keyExists(GEOGANGULARUNITSGEOKEY) || keyExists(GEOGLINEARUNITSGEOKEY)));
		} else if(value >= 1024 && value <= 32766) {
			// GeodeticCRSGeoKey values in the range 1024-32766 SHALL be EPSG geodetic CRS codes (geographic 2D CRS, geographic 3D CRS, and geocentric CRS)
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.CRS, "COORD_REF_SYS_CODE", Integer.toString(value));
			Assert.assertTrue(record != null);
			Assert.assertTrue(minorRevision == 1);
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
			Assert.assertTrue(record.get("COORD_REF_SYS_KIND").equals("geographic 2D") || record.get("COORD_REF_SYS_KIND").equals("geographic 3D") || record.get("COORD_REF_SYS_KIND").equals("geocentric"));
		} else if(value >= 1 && value <= 1000) {
			// GeodeticCRSGeoKey values in the range 1-1000 SHALL be obsolete EPSG/POC Geographic Codes
			Assert.assertTrue(minorRevision == 0);
		} else {
			// GeodeticCRSGeoKey values in the range 1001-1023 SHALL be reserved
			Assert.assertFalse(value >= 1001 && value <= 1023);
					
			// GeodeticCRSGeoKeyvalues in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	2050	GeodeticDatumGeoKey
	
	@Test(description = "Short Params GeodeticDatumGeoKey (2050) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGeodeticDatumGeoKey() throws IOException {
		// the GeodeticDatumGeoKey SHALL have ID = 2050
		int index = getKeyIndex(GEODETICDATUMGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the GeodeticDatumGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		if(value == 32767) {
			// If the GeodeticDatumGeoKey value is 32767 (User-Defined) then the GeodeticCitationGeoKey 2049, PrimeMeridianGeoKey 2051 and EllipsoidGeoKey 2056 SHALL be populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(PRIMEMERIDIANGEOKEY) && keyExists(ELLIPSOIDGEOKEY));
		} else if(value >= 1024 && value <= 32766) {
			// GeodeticDatumGeoKey values in the range 1024-32766 SHALL be EPSG geodetic datum codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.DATUM, "DATUM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null);
			Assert.assertTrue(minorRevision == 1);
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
			Assert.assertTrue(record.get("DATUM_TYPE").equals("geodetic"));
		} else {	
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
	public void verifyPrimeMeridianGeoKey() throws IOException {
		// the PrimeMeridianGeoKey SHALL have ID = 2051
		int index = getKeyIndex(PRIMEMERIDIANGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the PrimeMeridianGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		if(value == 32767) {
			// If the PrimeMeridianGeoKey value is 32767 (User-Defined) then the GeodeticCitationGeoKey, and PrimeMeridianLongGeoKey SHALL be populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(PRIMEMERIDIANLONGITUDEGEOKEY));
		} else if(value >= 1024 && value <= 32766) {
			// PrimeMeridianGeoKey values in the range 1024-32766 SHALL be EPSG Prime Meridian Codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.PRIMEMERIDIAN, "PRIME_MERIDIAN_CODE", Integer.toString(value));
			Assert.assertTrue(record != null);
			Assert.assertTrue(minorRevision == 1);
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
		} else {		
			
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
	public void verifyGeogLinearUnitsGeoKey() throws IOException {
		// the GeogLinearUnitsGeoKey SHALL have ID = 2052
		int index = getKeyIndex(GEOGLINEARUNITSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey SHALL each have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		if(value == 32767) {
			// A GeogLinearUnitsGeoKey value of 32767 SHALL be a user-defined linear unit. If the value is 32767 (User-Defined) then the GeodeticCitationGeoKey and the GeogLinearUnitSizeGeoKey SHALL be populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(GEOGLINEARUNITSIZEGEOKEY));
		} else if(value >= 1024 && value <= 32766) {
			// GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.UOM, "UOM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null);
			Assert.assertTrue(minorRevision == 1);
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
			Assert.assertTrue(record.get("UNIT_OF_MEAS_TYPE").equals("length"));
		} else {						
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023);
			
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	2054	UnitsGeoKey (Angular Units) GeogAngularUnitsGeoKey
	
	@Test(description = "Short Params GeogAngularUnitsGeoKey (2054) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGeogAngularUnitsGeoKey() throws IOException {
		// the GeogAngularUnitsGeoKey SHALL have ID = 2054
		int index = getKeyIndex(GEOGANGULARUNITSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey SHALL each have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		if(value == 32767) {
			// A GeogAngularUnitsGeoKey or a GeogAzimuthUnitsGeoKey value of 32767 SHALL be a user-defined angular unit. If the value is 32767 (User-Defined) then the GeodeticCitationGeoKey and the GeogAngularUnitSizeGeoKey SHALL be populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(GEOGANGULARUNITSIZEGEOKEY));
		} else if(value >= 1024 && value <= 32766) {
			// GeogAngularUnitsGeoKey and GeogAzimuthUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = angle
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.UOM, "UOM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null);
			Assert.assertTrue(minorRevision == 1);
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
			Assert.assertTrue(record.get("UNIT_OF_MEAS_TYPE").equals("angle"));
		} else {					
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023);
			
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	2056	EllipsoidGeoKey
	
	@Test(description = "Short Params EllipsoidGeoKey (2056) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyEllipsoidGeoKey() throws IOException {
		// the PrimeMeridianGeoKey SHALL have ID = 2056
		int index = getKeyIndex(ELLIPSOIDGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the EllipsoidGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		if(value == 32767) {
			// If the EllipsoidGeoKey value is 32767 (User-Defined) then the GTCitationGeoKey and the EllipsoidSemiMajorAxisGeoKey SHALL be populated together with the one of either the EllipsoidSemiMinorAxisGeoKey or the EllipsoidInvFlatteningGeoKey
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(ELLIPSOIDSEMIMAJORAXISGEOKEY) && (keyExists(ELLIPSOIDSEMIMINORAXISGEOKEY) || keyExists(ELLIPSOIDINVFLATTENINGGEOKEY)));
		} else if(value >= 1024 && value <= 32766) {
			// EllipsoidGeoKey values in the range 1024-32766 SHALL be EPSG ellipsoid Codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.ELLIPSOID, "ELLIPSOID_CODE", Integer.toString(value));
			Assert.assertTrue(record != null);
			Assert.assertTrue(minorRevision == 1);
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
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
	public void verifyGeogAzimuthUnitsGeoKey() throws IOException {
		// the GeogAzimuthUnitsGeoKey SHALL have ID = 2060
		int index = getKeyIndex(GEOGAZIMUTHUNITSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey SHALL each have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		if(value == 32767) {
			// A GeogAngularUnitsGeoKey or a GeogAzimuthUnitsGeoKey value of 32767 SHALL be a user-defined angular unit. If the value is 32767 (User-Defined) then the GeodeticCitationGeoKey and the GeogAngularUnitSizeGeoKey SHALL be populated
			Assert.assertTrue(keyExists(GEODETICCITATIONGEOKEY) && keyExists(GEOGANGULARUNITSIZEGEOKEY));
		} else if(value >= 1024 && value <= 32766) {
			// GeogAngularUnitsGeoKey and GeogAzimuthUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = angle
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.UOM, "UOM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null);
			Assert.assertTrue(minorRevision == 1);
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
			Assert.assertTrue(record.get("UNIT_OF_MEAS_TYPE").equals("angle"));
		} else {								
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023);
			
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	3072	ProjectedCRSGeoKey
	
	@Test(description = "Short Params ProjectedCRSGeoKey (2056) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyProjectedCRSGeoKey() throws IOException {
		// the ProjectedCRSGeoKey SHALL have ID = 3072
		int index = getKeyIndex(PROJECTEDCRSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the ProjectedCRSGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		if(value == 32767) {
			// A ProjectedCRSGeoKey value of 32767 SHALL be a user-defined projected CRS. If the ProjectedCRSGeoKey value is 32767 (User-Defined) then the ProjectedCitationGeoKey, GeodeticCRSGeoKey and ProjectionGeoKey SHALL be populated
			Assert.assertTrue(keyExists(PROJECTEDCITATIONGEOKEY) && keyExists(GEODETICCRSGEOKEY) && keyExists(PROJECTIONGEOKEY));
		} else if(value >= 1024 && value <= 32766) {
			// ProjectedCRSGeoKey values in the range 1024-32766 SHALL be EPSG Projected CRS Codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.CRS, "COORD_REF_SYS_CODE", Integer.toString(value));
			Assert.assertTrue(record != null);
			Assert.assertTrue(minorRevision == 1);
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
			Assert.assertTrue(record.get("COORD_REF_SYS_KIND").equals("projected"));
		} else {		
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
	public void verifyProjectionGeoKey() throws IOException {
		// the ProjectionGeoKey SHALL have ID = 3074
		int index = getKeyIndex(PROJECTIONGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the ProjectionGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		if(value == 32767) {
			// If the ProjectionGeoKey value is 32767 (User-Defined) then the ProjectedCitationGeoKey, ProjectionMethodGeoKey, and ProjLinearUnitsGeoKey SHALL be populated
			Assert.assertTrue(keyExists(PROJECTEDCITATIONGEOKEY) && keyExists(PROJMETHODGEOKEY) && keyExists(PROJLINEARUNITSGEOKEY));
		} else if(value >= 1024 && value <= 32766) {
			// ProjectionGeoKey values in the range 1024-32766 SHALL be valid EPSG map projection (coordinate operation) codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.CO, "COORD_OP_CODE", Integer.toString(value));
			Assert.assertTrue(record != null);
			Assert.assertTrue(minorRevision == 1);
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
		} else {					
			// ProjectionGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023);
			
			// ProjectionGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0); // TODO: should value < 0 be value < 1?
		}
	}
	
	//	3075	ProjMethodGeoKey
	
	@Test(description = "Short Params ProjMethodGeoKey (3075) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyProjMethodGeoKey() {
		// the ProjMethodGeoKey SHALL have ID = 3075
		int index = getKeyIndex(PROJMETHODGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the ProjMethodGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		if(value == 32767) {
			// If the ProjectionMethodGeoKey value is 32767 (User-Defined) then the ProjectedCitationGeoKey 
			Assert.assertTrue(keyExists(PROJECTEDCITATIONGEOKEY));
			// and keys for each map projection parameter (coordinate operation parameter) appropriate to that method SHALL be populated
			// TODO
		} else {
			// ProjMethodGeoKey values in the range 1-27 SHALL be GeoTIFF map projection method codes
			// TODO: check minorRevision != 1?
			
			// ProjMethodGeoKey values in the range 28-32766 SHALL be reserved
			Assert.assertFalse(value >= 28 && value <= 32766);
			
			// ProjMethodGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0); // TODO: should value < 0 be value < 1?
		}
	}
	
	//	3076	UnitsGeoKey (Linear Units) ProjLinearUnitsGeoKey
	
	@Test(description = "Short Params ProjLinearUnitsGeoKey (3076) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyProjLinearUnitsGeoKey() throws IOException {
		// the GeogLinearUnitsGeoKey SHALL have ID = 3076
		int index = getKeyIndex(PROJLINEARUNITSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey SHALL each have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		if(value == 32767) {
			// A ProjLinearUnitsGeoKey value of 32767 SHALL be a user-defined linear unit. If the value is 32767 (User-Defined) then the ProjectedCitationGeoKey and the ProjLinearUnitSizeGeoKey SHALL be populated
			Assert.assertTrue(keyExists(PROJECTEDCITATIONGEOKEY) && keyExists(PROJLINEARUNITSIZEGEOKEY));
		} else if(value >= 1024 && value <= 32766) {
			// GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.UOM, "UOM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null);
			Assert.assertTrue(minorRevision == 1);
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
			Assert.assertTrue(record.get("UNIT_OF_MEAS_TYPE").equals("length"));
		} else {					
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023);
			
			// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	4096	VerticalGeoKey
	
	@Test(description = "Short Params VerticalGeoKey (4096) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyVerticalGeoKey() throws IOException {
		// the VerticalGeoKey SHALL have ID = 4096
		int index = getKeyIndex(VERTICALGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the VerticalGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		if(value == 32767) {
			// If the VerticalGeoKey value is 32767 (User-Defined) then the VerticalCitationGeoKey, the VerticalUnitsGeoKey and VerticalDatumGeoKey SHALL be populated
			Assert.assertTrue(keyExists(VERTICALCITATIONGEOKEY) && keyExists(VERTICALUNITSGEOKEY) && keyExists(VERTICALDATUMGEOKEY));
		} else if(value >= 1024 && value <= 32766) {
			// VerticalGeoKey values in the range 1024-32766 SHALL be either EPSG Vertical CRS Codes or EPSG geographic 3D CRS codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.CRS, "COORD_REF_SYS_CODE", Integer.toString(value));
			Assert.assertTrue(record != null);
			Assert.assertTrue(minorRevision == 1);
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
			Assert.assertTrue(record.get("COORD_REF_SYS_KIND").equals("vertical") || record.get("COORD_REF_SYS_KIND").equals("geographic 3D"));
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
	public void verifyVerticalDatumGeoKey() throws IOException {
		// the VerticalDatumGeoKey SHALL have ID = 4098
		int index = getKeyIndex(VERTICALDATUMGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the VerticalDatumGeoKey SHALL have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		if(value == 32767) {
			// If the VerticalDatumGeoKey value is 32767 (User-Defined) then the VerticalCitationGeoKey SHALL be populated.
			Assert.assertTrue(keyExists(VERTICALCITATIONGEOKEY));
		} else if(value >= 1024 && value <= 32766) {
			// VerticalDatumGeoKey values in the range 1024-32766 SHALL be EPSG vertical datum codes
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.DATUM, "DATUM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null);
			Assert.assertTrue(minorRevision == 1);
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
			Assert.assertTrue(record.get("DATUM_TYPE").equals("vertical"));
		} else {					
			// VerticalDatumGeoKey values in the range 1-1023 SHALL be reserved
			Assert.assertFalse(value >= 1 && value <= 1023);
			
			// VerticalDatumGeoKey values in the range 32768-65535 SHALL be private
			// value out of bounds
			Assert.assertFalse(value > 65535 || value <= 0);
		}
	}
	
	//	4099	UnitsGeoKey (Vertical Units) VerticalUnitsGeoKey

	@Test(description = "Short Params VerticalUnitsGeoKey (4099) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyVerticalUnitsGeoKey() throws IOException {
		// the VerticalUnitsGeoKey SHALL have ID = 4099
		int index = getKeyIndex(VERTICALUNITSGEOKEY);

		// not required
		if(index == -1) {
			return;
		}
		
		// process the second Short integer in the Key Entry Set
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShortForShort(index, keyLength);
		
		// the GeogAngularUnitsGeoKey, the GeogAzimuthUnitsGeoKey, the GeogLinearUnitsGeoKey, the ProjLinearUnitsGeoKey and the VerticalUnitsGeoKey SHALL each have type = SHORT		
		Assert.assertTrue(type == 0 || type == GEOKEYDIRECTORYTAG);
		
		// a VerticalUnitsGeoKey value of 32767 (user defined) SHALL not be used
		Assert.assertFalse(value == 32767);
	
		if(value >= 1024 && value <= 32766) {
			// GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1024-32766 SHALL be EPSG Unit Of Measure (UOM) codes with type = length
			CSVRecord record = EPSGDataSet.getRecord(EPSGDataSet.UOM, "UOM_CODE", Integer.toString(value));
			Assert.assertTrue(record != null);
			Assert.assertTrue(minorRevision == 1);
			Assert.assertTrue(record.get("DEPRECATED").equals("0"));
			Assert.assertTrue(record.get("UNIT_OF_MEAS_TYPE").equals("length"));
		}
				
		// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 1-1023 SHALL be reserved
		Assert.assertFalse(value >= 1 && value <= 1023);
		
		// GeogAngularUnitsGeoKey, GeogAzimuthUnitsGeoKey, GeogLinearUnitsGeoKey, ProjLinearUnitsGeoKey and VerticalUnitsGeoKey values in the range 32768-65535 SHALL be private
		// value out of bounds
		Assert.assertFalse(value > 65535 || value <= 0);
	
	}
}
