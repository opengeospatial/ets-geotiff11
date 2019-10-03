package org.opengis.cite.geotiff11.tiffTests;

import org.opengis.cite.geotiff11.util.TiffDump;
import org.testng.Assert;
import org.testng.annotations.Test;

// https://github.com/opengeospatial/geotiff/blob/5d6ab0ba54f1ed0174901dd84240817dc9dbe011/GeoTIFF_Standard/standard/abstract_tests/TIFF_Tests/TEST_TIFF_Tags.adoc
public class TiffTagsTests extends CommonTiffMeta {

	// TODO: Split this into multiple tests?
	
	/*
	* TIFF Tags Test		
	* Test id: http://www.opengis.net/spec/GeoTIFF/1.1/conf/TIFF.Tags		
	* Requirements:		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/TIFF		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/DataGeoTags		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/ByteOrder		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/TagSort		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.ID		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.type		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.count		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoDoubleParamsTag.ID		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoDoubleParamsTag.count		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoAsciiParamsTag.ID		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoAsciiParamsTag.type		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/ModelTransformationTag.ID		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/ModelTransformationTag.type		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/ModelPixelScaleTag.ID		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/ModelPixelScaleTag.type		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/ModelTiepointTag.ID		
	*  http://www.opengis.net/spec/GeoTIFF/1.1/req/ModelTiepointTag.type		
	* Purpose: Verify the TIFF header and prepare for processing the GeoTIFF tags.		
	* Pre-conditions: None		
	* Test Variables:		
	* 	Variable		Scope		Description
	* 	IFD_Offset		Local		Location within the TIFF file of an IFD
	* 	GeoKeyDirectory	Global		Location of the GeoTIFF GeoKey directory
	* 	ASCIIValues		Global		Location of the ASCII values for GeoTIFF ASCII GeoKeys
	* 	DoubleValues	Global		Location of the Double values for GeoTIFF Double GeoKeys
	* 	TransformTag	Local		Location of the Model Transform Tag
	* 	PixelScaleTag	Local		Location of the ModelPixelScale Tag
	* 	TiepointTag		Local		Location of the ModelTiepoint Tag
	* 	TagLength		Parameter	Length of the value of a TIFF Tag
	* 	TagValue		Parameter	Location of the value of a TIFF tag in the GeoTIFF file
	*/
	
	@Test(description = "TIFF Tags Test")
	public void verifyTiffTags() throws Exception {		
		
		//List<Object> geoKeyDirectory = null;
		//List<Object> doubleValues = null;
		//List<Object> asciiValues = null;
		
		for(TiffDump.Directory directory : tiffDump.getDirectories()) {
			
			// verify tag order is ascending
			int previousValue = -99999;
			for (TiffDump.Tag tag : directory.getTags()) {
				Assert.assertTrue(tag.getNameValue() > previousValue);
				previousValue = tag.getNameValue();
			}			
			
			// verify specific tag values
			if(directory.getOffset() != 0) {
				
				TiffDump.Tag geoKeyDirectory = directory.getTag(34735);
				if(geoKeyDirectory != null) {
					// validate that Bytes 2-3 = 3 (Short Integer)
					Assert.assertTrue(geoKeyDirectory.getTypeValue() == 3);
					// validate that Bytes 4-7 represent an Integer value greater than or equal to 4
					Assert.assertTrue(geoKeyDirectory.getCount() >= 4);
										
					if(geoKeyDirectory.getValues() != null) {					
						// validate that there is a GTModelType GeoKey in the GeoKey Directory
						Assert.assertTrue(geoKeyDirectory.containsValue(1024));
						
						// execute test http://www.opengis.net/spec/GeoTIFF/1.1/conf/GeoKeyDirectory
						
					} else {
						throw new Exception("GeoKeyDirectory does not exist.");
					}
						
				}
				
				TiffDump.Tag doubles = directory.getTag(34736);
				if(doubles != null) {
					Assert.assertTrue(doubles.getTypeValue() == 12);
					//doubleValues = doubles.getValues();
				}
				
				TiffDump.Tag asciis = directory.getTag(34737);
				if(asciis != null) {
					Assert.assertTrue(asciis.getTypeValue() == 2);
					//asciiValues = asciis.getValues();
				}
				
				TiffDump.Tag tiepointTag  = directory.getTag(33922);
				if(tiepointTag != null) {
					Assert.assertTrue(tiepointTag.getTypeValue() == 12);

					// execute test http://www.opengis.net/spec/GeoTIFF/1.1/conf/Raster2Model_CoordinateTransformation_GeoKey/ModelTiepointTag
				}
				
				TiffDump.Tag pixelScaleTag = directory.getTag(33550);
				if(pixelScaleTag != null) {
					Assert.assertTrue(pixelScaleTag.getTypeValue() == 12);
					
					// validate that this IFD contains a ModelTiepointTag
					Assert.assertTrue(tiepointTag != null);
					
				    // execute test http://www.opengis.net/spec/GeoTIFF/1.1/conf/Raster2Model_CoordinateTransformation_GeoKey/ModelPixelScaleTag
				}
				
				TiffDump.Tag transformTag = directory.getTag(34264);
				if(transformTag != null) {
					Assert.assertTrue(transformTag.getTypeValue() == 12);
					
					// validate that this IFD contains a ModelTiepointTag
					Assert.assertTrue(pixelScaleTag == null);
					
				    // execute test http://www.opengis.net/spec/GeoTIFF/1.1/conf/Raster2Model_CoordinateTransformation_GeoKey/ModelTransformationTag
				}

				// validate that this IFD contains either a ModelTransformationTag or a ModelTiepointTag
				// this could probably be it's own test?
				Assert.assertTrue(transformTag != null || tiepointTag != null);
			}		
		}

// going to do above instead. may return to this structure
//		if(geoKeyDirectory != null) {
//			// do geokeydirectory test
//			// validate things
//			Assert.assertTrue(geoKeyDirectory.);

	}
}