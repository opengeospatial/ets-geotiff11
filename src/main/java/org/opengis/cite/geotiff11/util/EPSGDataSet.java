package org.opengis.cite.geotiff11.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/*
 * For referencing the path of an EPSG data set.
 */
/**
 * <p>
 * EPSGDataSet class.
 * </p>
 *
 */
public final class EPSGDataSet {

	// UOM types
	/** Constant <code>METERS="9001"</code> */
	public static final String METERS = "9001";

	/** Constant <code>RADIANS="9101"</code> */
	public static final String RADIANS = "9101";

	// path and extension
	// public static final String DIRECTORY = System.getProperty("user.dir") +
	// "/src/main/resources/epsg/";
	// private static final String DIRECTORY =
	// DirectoryUtil.getDirectory("/src/main/resources/epsg/");
	// private static final String DIRECTORY = new
	// File(EPSGDataSet.class.getResource("/epsg").getFile()).getAbsolutePath();
	private static final String EXTENSION = ".csv";

	// table paths
	/** Constant <code>UOM="unit-of-measure"</code> */
	public static final String UOM = "unit-of-measure";

	/** Constant <code>CRS="coordinate-reference-system"</code> */
	public static final String CRS = "coordinate-reference-system";

	/** Constant <code>DATUM="datum"</code> */
	public static final String DATUM = "datum";

	/** Constant <code>PRIMEMERIDIAN="prime-meridian"</code> */
	public static final String PRIMEMERIDIAN = "prime-meridian";

	/** Constant <code>ELLIPSOID="ellipsoid"</code> */
	public static final String ELLIPSOID = "ellipsoid";

	/** Constant <code>CO="coordinate-operation"</code> */
	public static final String CO = "coordinate-operation";

	/** Constant <code>COM="coordinate-operation-method"</code> */
	public static final String COM = "coordinate-operation-method";

	/** Constant <code>COP="coordinate-operation-parameter"</code> */
	public static final String COP = "coordinate-operation-parameter";

	private static InputStreamReader getResourceISR(String name) {
		return new InputStreamReader(EPSGDataSet.class.getResourceAsStream("/epsg/" + name + EXTENSION));
	}

	/**
	 * <p>
	 * readTable.
	 * </p>
	 * @param tableName a {@link java.lang.String} object
	 * @return a {@link java.io.BufferedReader} object
	 * @throws java.io.IOException if any.
	 */
	protected static BufferedReader readTable(String tableName) throws IOException {
		return new BufferedReader(getResourceISR(tableName));
		// return Files.newBufferedReader(Paths.get(DIRECTORY, tableName + EXTENSION),
		// Charset.forName("Cp1252"));
	}

	/**
	 * <p>
	 * getRecord.
	 * </p>
	 * @param tableName a {@link java.lang.String} object
	 * @param column a {@link java.lang.String} object
	 * @param value a {@link java.lang.String} object
	 * @return a {@link org.apache.commons.csv.CSVRecord} object
	 * @throws java.io.IOException if any.
	 */
	public static CSVRecord getRecord(String tableName, String column, String value) throws IOException {
		BufferedReader reader = readTable(tableName);
		CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());
		for (CSVRecord csvRecord : csvParser) {

			if (csvRecord.get(column).equals(value)) {
				csvParser.close();
				return csvRecord;
			}
		}
		csvParser.close();
		return null;
	}

	/**
	 * <p>
	 * getItem.
	 * </p>
	 * @param tableName a {@link java.lang.String} object
	 * @param column a {@link java.lang.String} object
	 * @param value a {@link java.lang.String} object
	 * @param returnColumn a {@link java.lang.String} object
	 * @return a {@link java.lang.String} object
	 * @throws java.io.IOException if any.
	 */
	public static String getItem(String tableName, String column, String value, String returnColumn)
			throws IOException {
		return getRecord(tableName, column, value).get(returnColumn);
	}

	/**
	 * <p>
	 * getItem.
	 * </p>
	 * @param tableName a {@link java.lang.String} object
	 * @param column a {@link java.lang.String} object
	 * @param value a int
	 * @param returnColumn a {@link java.lang.String} object
	 * @return a {@link java.lang.String} object
	 * @throws java.io.IOException if any.
	 */
	public static String getItem(String tableName, String column, int value, String returnColumn) throws IOException {
		return getItem(tableName, column, Integer.toString(value), returnColumn);
	}

}
