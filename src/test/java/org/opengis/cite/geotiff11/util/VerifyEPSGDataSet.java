package org.opengis.cite.geotiff11.util;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

public class VerifyEPSGDataSet {

	// TODO: coverage of EPSGDataSet tools

	String[] tables = new String[] { EPSGDataSet.UOM, EPSGDataSet.CRS, EPSGDataSet.DATUM, EPSGDataSet.PRIMEMERIDIAN,
			EPSGDataSet.ELLIPSOID, EPSGDataSet.CO, EPSGDataSet.COM, EPSGDataSet.COP };

	@BeforeClass
	public static void setUpClass() {
	}

	@Test
	public void verifyTablesExist() throws IOException {
		for (String table : tables) {
			assertTrue(EPSGDataSet.readTable(table) != null);
		}
	}

	// etc.

}
