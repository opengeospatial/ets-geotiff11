package org.opengis.cite.geotiff11.tiffTests;

import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOASCIIPARAMSTAG;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEODOUBLEPARAMSTAG;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GEOKEYDIRECTORYTAG;
import static org.opengis.cite.geotiff11.util.GeoKeyID.GTMODELTYPEGEOKEY;
import static org.opengis.cite.geotiff11.util.GeoKeyID.MODELPIXELSCALETAG;
import static org.opengis.cite.geotiff11.util.GeoKeyID.MODELTIEPOINTTAG;
import static org.opengis.cite.geotiff11.util.GeoKeyID.MODELTRANSFORMATIONTAG;

import org.opengis.cite.geotiff11.util.TiffDump;
import org.testng.Assert;
import org.testng.annotations.Test;

// https://github.com/opengeospatial/geotiff/blob/5d6ab0ba54f1ed0174901dd84240817dc9dbe011/GeoTIFF_Standard/standard/abstract_tests/TIFF_Tests/TEST_TIFF_Tags.adoc
/**
 * <p>
 * TiffTagsTests class.
 * </p>
 *
 */
public class TiffTagsTests extends CommonTiffMeta {

	/*
	 * TIFF Tags Test Test id: http://www.opengis.net/spec/GeoTIFF/1.1/conf/TIFF.Tags
	 * Requirements: http://www.opengis.net/spec/GeoTIFF/1.1/req/TIFF
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/DataGeoTags
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ByteOrder
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/TagSort
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.ID
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.type
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoKeyDirectoryTag.count
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoDoubleParamsTag.ID
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoDoubleParamsTag.count
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoAsciiParamsTag.ID
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/GeoAsciiParamsTag.type
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ModelTransformationTag.ID
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ModelTransformationTag.type
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ModelPixelScaleTag.ID
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ModelPixelScaleTag.type
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ModelTiepointTag.ID
	 * http://www.opengis.net/spec/GeoTIFF/1.1/req/ModelTiepointTag.type Purpose: Verify
	 * the TIFF header and prepare for processing the GeoTIFF tags. Pre-conditions: None
	 * Test Variables: Variable Scope Description IFD_Offset Local Location within the
	 * TIFF file of an IFD GeoKeyDirectory Global Location of the GeoTIFF GeoKey directory
	 * ASCIIValues Global Location of the ASCII values for GeoTIFF ASCII GeoKeys
	 * DoubleValues Global Location of the Double values for GeoTIFF Double GeoKeys
	 * TransformTag Local Location of the Model Transform Tag PixelScaleTag Local Location
	 * of the ModelPixelScale Tag TiepointTag Local Location of the ModelTiepoint Tag
	 * TagLength Parameter Length of the value of a TIFF Tag TagValue Parameter Location
	 * of the value of a TIFF tag in the GeoTIFF file
	 */

	// TODO: This duplicates a lot of test in order to more closely resemble the ATS. In
	// the future,
	// this may be replaced entirely?

	/**
	 * <p>
	 * verifyTiffTags.
	 * </p>
	 * @throws java.lang.Exception if any.
	 */
	@Test(description = "TIFF Tags Test")
	public void verifyTiffTags() throws Exception {

		for (TiffDump.Directory directory : tiffDump.getDirectories()) {

			// The TIFF tags in a GeoTIFF file SHALL be written out to the file with the
			// tag-IDs sorted in ascending order
			int previousValue = Integer.MIN_VALUE;
			for (TiffDump.Tag tag : directory.getTags()) {
				Assert.assertTrue(tag.getNameValue() > previousValue,
						"the TIFF tags in a GeoTIFF file SHALL be written out to the file with the tag-IDs sorted in ascending order");
				previousValue = tag.getNameValue();
			}

			// verify specific tag values
			// WHILE IFD_Offset is not 0, process this IFD
			if (directory.getOffset() != 0) {

				TiffDump.Tag geoKeyDirectory = directory.getTag(GEOKEYDIRECTORYTAG);
				if (geoKeyDirectory != null) {
					// validate that Bytes 2-3 = 3 (Short Integer)
					Assert.assertTrue(geoKeyDirectory.getTypeValue() == 3,
							"the GeoKeyDirectoryTag SHALL have type = SHORT");
					// validate that Bytes 4-7 represent an Integer value greater than or
					// equal to 4
					Assert.assertTrue(geoKeyDirectory.getCount() >= 4,
							"the GeoKeyDirectoryTag SHALL have at least 4 values");

					if (geoKeyDirectory.getValues() != null) {
						// validate that there is a GTModelType GeoKey in the GeoKey
						// Directory
						Assert.assertTrue(geoKeyDirectory.containsValue(GTMODELTYPEGEOKEY),
								"validate that there is a GTModelType GeoKey in the GeoKey Directory");

						// execute test
						// http://www.opengis.net/spec/GeoTIFF/1.1/conf/GeoKeyDirectory

					}
					else {
						throw new Exception("GeoKeyDirectory does not exist.");
					}

					// the GeoDoubleParamsTag SHALL have ID = 34736
					TiffDump.Tag doubles = directory.getTag(GEODOUBLEPARAMSTAG);
					if (doubles != null) {
						// the GeoDoubleParamsTag MAY hold any number of key parameters
						// with type = double
						Assert.assertTrue(doubles.getTypeValue() == 12,
								"the GeoDoubleParamsTag MAY hold any number of key parameters with type = double");
						// List<Object> doubleValues = doubles.getValues();
					}

					// the GeoAsciiParamsTag SHALL have ID = 34737
					TiffDump.Tag asciis = directory.getTag(GEOASCIIPARAMSTAG);
					if (asciis != null) {
						Assert.assertTrue(asciis.getTypeValue() == 2, "the GeoAsciiParamsTag SHALL have type = ASCII");
						// asciiValues = asciis.getValues();
					}

					// the ModelTiepointTag SHALL have ID = 33922
					TiffDump.Tag tiepointTag = directory.getTag(MODELTIEPOINTTAG);
					if (tiepointTag != null) {
						Assert.assertTrue(tiepointTag.getTypeValue() == 12,
								"the ModelTiepointTag SHALL have type = DOUBLE");

						// execute test
						// http://www.opengis.net/spec/GeoTIFF/1.1/conf/Raster2Model_CoordinateTransformation_GeoKey/ModelTiepointTag

						// the ModelTiepointTag SHALL have 6 values for each of the K
						// tiepoints
						Assert.assertTrue(tiepointTag.getCount() == 6,
								"the ModelTiepointTag SHALL have 6 values for each of the K tiepoints");
					}

					// the ModelPixelScaleTag SHALL have ID = 33550
					TiffDump.Tag pixelScaleTag = directory.getTag(MODELPIXELSCALETAG);
					if (pixelScaleTag != null) {
						Assert.assertTrue(pixelScaleTag.getTypeValue() == 12,
								"the ModelPixelScaleTag SHALL have type = DOUBLE");

						// validate that this IFD contains a ModelTiepointTag
						Assert.assertTrue(tiepointTag != null,
								"validate that this IFD (containing ModelPixelScaleTag) contains a ModelTiepointTag");

						// execute test
						// http://www.opengis.net/spec/GeoTIFF/1.1/conf/Raster2Model_CoordinateTransformation_GeoKey/ModelPixelScaleTag

						// the ModelPixelScaleTag SHALL have 3 values representing the
						// scale factor in the X, Y, and Z directions
						Assert.assertTrue(pixelScaleTag.getCount() == 3,
								"the ModelPixelScaleTag SHALL have 3 values representing the scale factor in the X, Y, and Z directions");
					}

					// the ModelTransformationTag SHALL have ID = 34264
					TiffDump.Tag transformTag = directory.getTag(MODELTRANSFORMATIONTAG);
					if (transformTag != null) {
						Assert.assertTrue(transformTag.getTypeValue() == 12,
								"the ModelTransformationTag SHALL have type = DOUBLE");

						// validate that this IFD does not contain a ModelPixelScaleTag
						Assert.assertTrue(pixelScaleTag == null,
								"validate that this IFD (containing ModelTransformationTag) does not contain a ModelPixelScaleTag");

						// execute test
						// http://www.opengis.net/spec/GeoTIFF/1.1/conf/Raster2Model_CoordinateTransformation_GeoKey/ModelTransformationTag

						// The ModelTransformationTag SHALL have 16 values representing
						// the terms of the 4 by 4 transformation matrix. The terms SHALL
						// be in row-major order
						Assert.assertTrue(transformTag.getValues().size() == 16,
								"the ModelTransformationTag SHALL have 16 values representing the terms of the 4 by 4 transformation matrix");
						// TODO: The terms SHALL be in row-major order. Is this possible
						// to ensure?
					}

					// validate that this IFD contains either a ModelTransformationTag or
					// a ModelTiepointTag
					// this could probably be it's own test?
					Assert.assertTrue(transformTag != null || tiepointTag != null,
							"validate that this IFD contains either a ModelTransformationTag or a ModelTiepointTag");
				}
			}
		}
	}

}
