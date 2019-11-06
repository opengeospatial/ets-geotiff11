package org.opengis.cite.geotiff11.tiffTests;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.opengis.cite.geotiff11.util.EPSGDataSet;
import org.opengis.cite.geotiff11.util.TiffDump;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.opengis.cite.geotiff11.util.GeoKeyID.*;

// https://github.com/opengeospatial/geotiff/blob/master/GeoTIFF_Standard/standard/abstract_tests/TIFF_Tests/TEST_Double_Param.adoc

public class AsciiParamsTests extends GeoKeysTests {

	/*
	*	ASCII  Parameters Test			
	*	Test id: TIFF_Test/AsciiParameters		
	*	Requirements:			
	*	http://www.opengis.net/spec/GeoTIFF/1.1/GeoKeyDirectoryTag.KeyEntry.ID
	*	http://www.opengis.net/spec/GeoTIFF/1.1/GeoKeyDirectoryTag.keyEntryTIFFTagLocation
	*	http://www.opengis.net/spec/GeoTIFF/1.1/GeoKeyDirectoryTag.keyEntryKeyCount
	*	http://www.opengis.net/spec/GeoTIFF/1.1/GeoKeyDirectoryTag.keyEntryValueOffset
	*	http://www.opengis.net/spec/GeoTIFF/1.1/GeoAsciiParamsTag.ID
	*	http://www.opengis.net/spec/GeoTIFF/1.1/GeoAsciiParamsTag.count
	*	http://www.opengis.net/spec/GeoTIFF/1.1/CitationGeoKeys.ID
	*	http://www.opengis.net/spec/GeoTIFF/1.1/CitationGeoKeys.type
	*	Purpose: Verify an ASCII parameter		
	*	Pre-conditions: The GeoKeyDirectory, ASCIIValues and GeoKeyOffset values have been set.		
	*	Test Variables:			
	*		Variable		Scope		Description
	*		GeoKeyDirectory	Global		Location of the GeoTIFF GeoKey directory
	*		ASCIIValues		Global		Location of the ASCII values for GeoTIFF ASCII GeoKeys
	*		GeoKeyOffset	Parameter	Location of this Key Entry Set in the GeoKey directory
	*		GeoKey			Local		Temporary value of the GeoKey
	*		KeyLength		Local		Temporary value for the length of the value for the GeoKey
	*		KeyValueOffset	Local		The location of the GeoKey value in the file

	*/
	
	String processFourthShortForAscii(int index, int keyLength) {
		int asciiIndex = (int) keyEntrySet.get(index+3);
		// process the fourth Short integer in the Key Entry Set
		Assert.assertTrue(directory.hasTag(GEOASCIIPARAMSTAG));
		String asciiParamsSet = directory.getTag(GEOASCIIPARAMSTAG).getValues().get(0).toString();	
		
		System.out.println(asciiParamsSet);
		
		// SET KeyValueOffset to the value
		
		// Read the contents of the GeoTIFF file starting at KeyValueOffset up to and including the first NULL.
		// Verify that the contents read consists of ASCII characters.
		// Verify that the contents read is KeyLength characters long not including the NULL.
		String value = "";
		
		for(int i = asciiIndex; i < asciiIndex + keyLength - 1; i++)
		{
			Assert.assertTrue(i < asciiParamsSet.length());
			Assert.assertTrue(asciiParamsSet.charAt(i) != '\0');
			value += asciiParamsSet.charAt(i);
		}
		
		System.out.println(value);
		
		return value;
	}
	
	
//	GeoKey	Requirements Class
	
//	1026	CitationGeoKeys/GTCitationGeoKey
	
	@Test(description = "Ascii Params GTCitationGeoKey (1026) Test", dependsOnGroups ={"verifyGeoKeyDirectory"})
	public void verifyGTCitationGeoKey() throws Exception {
		// the GTCitationGeoKey SHALL have ID = 1026
		int index = getKeyIndex(GTCITATIONGEOKEY);
		
		// not required
		if(index == -1) {
			return;
		}
		
		int type = processSecondShort(index);
		int geoKey = processFirstShort(index);
		int keyLength = processThirdShort(index);
		String value = processFourthShortForAscii(index, keyLength);
		
		// the GeogLinearUnitSizeGeoKey SHALL have type = ASCII		
		Assert.assertTrue(type == GEOASCIIPARAMSTAG);
	}
	
//	2049	CitationGeoKeys/GeodeticCitationGeoKey
//	3073	CitationGeoKeys/ProjectedCitationGeoKey
//	4097	CitationGeoKeys/VerticalCitationGeoKey

}
