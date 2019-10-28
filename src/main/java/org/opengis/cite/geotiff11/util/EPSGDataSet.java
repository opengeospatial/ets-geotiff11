package org.opengis.cite.geotiff11.util;

import java.io.BufferedReader;
import java.io.IOException;
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
	public static final String METER = "9001";
	public static final String RADIAN = "9101";
	
	// path and extension
	public static final String DIRECTORY = "/src/main/resources/epsg/";
	public static final String EXTENSION = ".csv";
	
	// table paths
	public static final String UOM = System.getProperty("user.dir") + DIRECTORY + "Unit of Measure" + EXTENSION;
	public static final String CRS = System.getProperty("user.dir") + DIRECTORY + "Coordinate Reference System" + EXTENSION;

	
//	public static String getItem(String path, String column, String value, String returnColumn) throws IOException {
//		System.out.println(Paths.get(path));
//        BufferedReader reader = Files.newBufferedReader(Paths.get(path), Charset.forName("Cp1252"));
//        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());
//        System.out.println(csvParser.toString());
//        for (CSVRecord csvRecord: csvParser) {
//
//            if(csvRecord.get(column).equals(value)) {
//                csvParser.close();
//            	return csvRecord.get(returnColumn);
//            }
//        }
//        csvParser.close();
//        return null;
//	}
	
	public static CSVRecord getRecord(String path, String column, String value) throws IOException {
		System.out.println(Paths.get(path));
        BufferedReader reader = Files.newBufferedReader(Paths.get(path), Charset.forName("Cp1252"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());
        System.out.println(csvParser.toString());
        for (CSVRecord csvRecord: csvParser) {

            if(csvRecord.get(column).equals(value)) {
                csvParser.close();
            	return csvRecord;
            }
        }
        csvParser.close();
        return null;
	}
	
	public static String getItem(String path, String column, String value, String returnColumn) throws IOException {
		return getRecord(path, column,  value).get(returnColumn);
	}
	
	public static void main(String[] args) throws IOException { 
		//System.out.println(getItem(UOM, "UOM_CODE", "1024", "TARGET_UOM_CODE"));
		
		//CSVRecord record = EPSGDataSet.getRecord(CRS, "COORD_REF_SYS_CODE", Integer.toString(2015));
		
		//System.out.println(record.get("DEPRECATED").equals("0"));
		
		
	}
}
