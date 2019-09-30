package org.opengis.cite.geotiff11.old.tiffBase;

import java.io.IOException;

import org.junit.Test;
import org.testng.Assert;
import org.xml.sax.SAXException;

/**
 * Verifies the behavior of the CommonTiffMeta class.
 */
public class VerifyCommonTiffMeta {
	@Test
	public void verifyMultipleValues() throws SAXException, IOException {
		CommonTiffMeta obj = new CommonTiffMeta();
		String[] separated = obj.extractValues("<this is a test>");
		Assert.assertEquals(separated, new String[] { "this", "is", "a", "test" });

	}
	
	@Test
	public void verifySingleValue() throws SAXException, IOException {
		CommonTiffMeta obj = new CommonTiffMeta();
		String[] separated = obj.extractValues("<test>");
		Assert.assertEquals(separated, new String[] { "test" });
	}
	
	@Test
	public void verifyValueExists() {
		CommonTiffMeta obj = new CommonTiffMeta();
		boolean valueExists = obj.verifyValueExists("StripOffsets (273) LONG (4) 35<7614 15324 23034 30744 ...>", "7614");
		Assert.assertEquals(valueExists, true);
	}
}
