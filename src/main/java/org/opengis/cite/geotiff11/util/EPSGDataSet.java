package org.opengis.cite.geotiff11.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/*
 * For referencing the path of an EPSG data set.
 */
public final class EPSGDataSet {

	// UOM types
	public static final String METERS = "9001";
	public static final String RADIANS = "9101";
	
	// path and extension
	//public static final String DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/epsg/";
//	private static final String DIRECTORY = DirectoryUtil.getDirectory("/src/main/resources/epsg/");
//	private static final String DIRECTORY = new File(EPSGDataSet.class.getResource("/epsg").getFile()).getAbsolutePath();
	private static final String EXTENSION = ".csv";
	
	// table paths
	public static final String UOM = "unit-of-measure";
	public static final String CRS = "coordinate-reference-system";
	public static final String DATUM = "datum";
	public static final String PRIMEMERIDIAN = "prime-meridian";
	public static final String ELLIPSOID = "ellipsoid";
	public static final String CO = "coordinate-operation";
	public static final String COM = "coordinate-operation-method";
	public static final String COP = "coordinate-operation-parameter";
	
	private static InputStreamReader getResourceISR(String name)
	{
		return new InputStreamReader(EPSGDataSet.class.getResourceAsStream("/epsg/" + name + EXTENSION));
	}
	
	protected static BufferedReader readTable(String tableName) throws IOException {
		return new BufferedReader(getResourceISR(tableName));
//		return Files.newBufferedReader(Paths.get(DIRECTORY, tableName + EXTENSION), Charset.forName("Cp1252"));
	}
	
	public static CSVRecord getRecord(String tableName, String column, String value) throws IOException {
        BufferedReader reader = readTable(tableName);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());
        for (CSVRecord csvRecord: csvParser) {

            if(csvRecord.get(column).equals(value)) {
                csvParser.close();
            	return csvRecord;
            }
        }
        csvParser.close();
        return null;
	}
	
	public static String getItem(String tableName, String column, String value, String returnColumn) throws IOException {
		return getRecord(tableName, column,  value).get(returnColumn);
	}
	public static String getItem(String tableName, String column, int value, String returnColumn) throws IOException {
		return getItem(tableName, column, Integer.toString(value), returnColumn);
	}
}
