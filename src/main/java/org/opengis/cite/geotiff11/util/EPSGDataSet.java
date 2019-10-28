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

	public static final String PATH = "/src/main/resources/epsg/";
	public static final String EXTENSION = ".csv";
	
	public static final String UNITOFMEASURE = System.getProperty("user.dir") + PATH + "Unit of Measure" + EXTENSION;

	
	public static String getItem(String path, String column, String value, String returnColumn) throws IOException {
		System.out.println(Paths.get(UNITOFMEASURE));
        BufferedReader reader = Files.newBufferedReader(Paths.get(UNITOFMEASURE), Charset.forName("Cp1252"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());
        System.out.println(csvParser.toString());
        for (CSVRecord csvRecord: csvParser) {

            if(csvRecord.get(column).equals(value)) {
                csvParser.close();
            	return csvRecord.get(returnColumn);
            }
        }
        csvParser.close();
        return null;
	}
}
