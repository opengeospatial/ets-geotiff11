package org.opengis.cite.geotiff10.transparencyMask;

import org.opengis.cite.geotiff10.tiffBase.CommonTiffMeta;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewSubfileType extends CommonTiffMeta {
	/**
	 * Checks if the NewSubFileType tag exists. The PhotometricInterpretation value must be 4
	 */
	@Test(description = "Checks NewSubfileType")
	public void verifyNewSubfileTypeExists() {
		
		String line = this.list.stream().filter(item -> item.contains("Photometric")).findFirst().get();
		if (line != null && verifyValueExists(line, "4")) {
			Assert.assertTrue(this.list.stream().anyMatch(item -> item.contains("NewSubfileType")));
		}

		
	}

	/**
	 * NewSubfileType: Bit 2 =1, all other bits = 0
	 */
	@Test(description = "Checks NewSubfileType Value")
	public void verifyNewSubfileTypeValue() {
		String line = this.list.stream().filter(item -> item.contains("NewSubfileType")).findFirst().get();
		if (line != null) {
			String[] separatedValues = extractValues(line);
			
			//this tag should only contain one value
			String value = separatedValues[0];
			
			//check bit 2 first 
			char charValue = value.charAt(value.length() - 3);
			Assert.assertEquals(charValue, '1');
			
			//check all other bits
			for (int i = 0; i < value.length(); i++){
				if (i == value.length() - 3){
					continue;
				}
			    char c = value.charAt(i);        
			    Assert.assertEquals(c, '0');
			}
			
			
		}
	}
}
