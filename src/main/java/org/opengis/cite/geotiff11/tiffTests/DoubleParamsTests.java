package org.opengis.cite.geotiff11.tiffTests;

import java.util.Arrays;
import java.util.List;

import org.opengis.cite.geotiff11.util.TiffDump;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.opengis.cite.geotiff11.util.GeoKeyID.*;

// https://github.com/opengeospatial/geotiff/blob/master/GeoTIFF_Standard/standard/abstract_tests/TIFF_Tests/TEST_Double_Param.adoc

public class DoubleParamsTests extends GeoKeysTests {

	/*
	*	Double Parameters Test			
	*	Test id: http://www.opengis.net/spec/GeoTIFF/1.1/conf/DoubleParameters			
	*	Requirements:			
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.KeyEntry.ID		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyEntryTIFFTagLocation		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyEntryKeyCount		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.keyEntryValueOffset		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoDoubleParamsTag.ID		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoDoubleParamsTag.count		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/PrimeMeridianLongitudeGeoKey		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/UnitSizeGeoKey		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/EllipsoidSemiMajorAxisGeoKey		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/EllipsoidSemiMinorAxisGeoKey		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/EllipsoidInvFlatteningGeoKey		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjAngularParameterGeoKey		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjLinearParameterGeoKey		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjScalarParameterGeoKey		
	*		http://www.opengis.net/spec/GeoTIFF/1.1/req/ProjAzimuthAngleGeoKey		
	*	Purpose: Verify Double parameters			
	*	Pre-conditions The GeoKeyDirectory, DoubleValues and GeoKeyOffset values have been set.			
	*	Test Variables:			
	*		Variable		Scope		Description
	*		GeoKeyDirectory	Global		Location of the GeoTIFF GeoKey directory
	*		DoubleValues	Global		Location of the Double values for GeoTIFF Double GeoKeys
	*		GeoKeyOffset	Parameter	Location of this Key Entry Set in the GeoKey directory
	*		GeoKey			Local		Temporary value of the GeoKey
	*		KeyLength		Local		Temporary value for the length of the value for the GeoKey
	*		KeyValueOffset	Local		The location of the GeoKey value in the file
	*/
	
	int processFourthShort(int index, int keyLength) {
		// process the fourth Short integer in the Key Entry Set
		List<Object> doubleParamsSet = directory.getTag(GEODOUBLEPARAMSTAG).getValues();	
		Assert.assertTrue(doubleParamsSet != null);
		// SET KeyValueOffset = DoubleValues + (KeyValueOffset * 8)
		return (int) doubleParamsSet.get(keyLength); // TODO: verify this is a correct interpretation of the ats...
	}
	
	//	GeoKey	Requirements Class
	//	2053	UnitSizeGeoKey (Geog Linear)
	
	@Test(description = "Short Params GeogLinearUnitSizeGeoKey (2053) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGeogLinearUnitSizeGeoKey() throws Exception {
		// the GeogLinearUnitSizeGeoKey SHALL have ID = 2053
		int index = keyEntrySet.indexOf(GEOGLINEARUNITSIZEGEOKEY);
		
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		int value = processFourthShort(index, keyLength);
		
		// the GeogLinearUnitSizeGeoKey SHALL have type = DOUBLE		
		Assert.assertTrue(type == GEODOUBLEPARAMSTAG);
		// or TODO: which one should I use?
		if(!(type == GEODOUBLEPARAMSTAG)) {
			throw new Exception("GeogLinearUnitSizeGeoKey should be of type DOUBLE.");
		}
		
		// the units of the GeogLinearUnitSizeGeoKey value SHALL be meters
		// TODO: need to either 1) implement codes search tables 2) add listgeo to implementation...
		
	}
	
//	2055	UnitSizeGeoKey (Geog Angular)
//	2057	EllipsoidSemiMajorAxisGeoKey
//	2058	EllipsoidSemiMinorAxisGeoKey
//	2059	EllipsoidInvFlatteningGeoKey
//	2061	PrimeMeridianLongitudeGeoKey
//	3077	UnitSizeGeoKey (Projected Linear)
//	3078	ProjAngularParameters (Standard Parallel 1)
//	3079	ProjAngularParameters (Standard Parallel 2)
//	3080	ProjAngularParameters (Natural Origin Longitude)
//	3081	ProjAngularParameters (Natural Origin Latitude)
//	3082	ProjLinearParameters (False Easting)
//	3083	ProjLinearParameters (False Northing)
//	3084	ProjAngularParameters (False Origin Longitude)
//	3085	ProjAngularParameters (False Origin Latitude)
//	3086	ProjLinearParameters (False Origin Easting)
//	3087	ProjLinearParameters (False Origin Northing)
//	3088	ProjAngularParameters (Center Longitude)
//	3089	ProjAngularParameters (Center Latitude)
//	3090	ProjLinearParameters (Projection Center Easting)
//	3091	ProjLinearParameters (Projection Center Northing)
//	3092	ProjScalarParameters (Scale at Natural Origin)
//	3093	ProjScalarParameters (Scale at Center)
//	3094	ProjAzimuthAngleGeoKey
//	3095	ProjAngularParameters (Straight Vertical Pole)

	
	
	
}
