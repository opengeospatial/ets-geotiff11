package org.opengis.cite.geotiff11.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A (rough) wrapper class for the output of TiffDump.exe
 * 
 * @author Dustin Jutras, AGC
 *
 */
public class TiffDump {

    public static void main(String[] args) { 
        TiffDump td;
		try {
//			td = new TiffDump(new String("C:/Users/RDAGCDLJ/Documents/FY19/GEOTIFF/ets-geotiff11/target/test-classes/tif/cea.tif:\r\n" + 
//					"Magic: 0x4949 <little-endian> Version: 0x2a\r\n" + 
//					"Directory 0: offset 270276 (0x41fc4) next 0 (0)\r\n" + 
//					"ImageWidth (256) SHORT (3) 1<514 ...>\r\n" + 
//					"ImageLength (257) SHORT (3) 1<515>\r\n" + 
//					"BitsPerSample (258) SHORT (3) 1<8>\r\n" + 
//					"Compression (259) SHORT (3) 1<1>\r\n" + 
//					"Photometric (262) SHORT (3) 1<1>\r\n" + 
//					"StripOffsets (273) LONG (4) 35<426 8136 15846 23556 31266 38976 46686 54396 62106 69816 77526 85236 92946 100656 108366 116076 123786 131496 139206 146916 154626 162336 170046 177756>\r\n" + 
//					"SamplesPerPixel (277) SHORT (3) 1<1>\r\n" + 
//					"RowsPerStrip (278) SHORT (3) 1<15>\r\n" + 
//					"StripByteCounts (279) LONG (4) 35<7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 7710 ...>\r\n" + 
//					"PlanarConfig (284) SHORT (3) 1<1>\r\n" + 
//					"SampleFormat (339) SHORT (3) 1<1>\r\n" + 
//					"33550 (0x830e) DOUBLE (12) 3<60.0221 60.0221 0>\r\n" + 
//					"33922 (0x8482) DOUBLE (12) 6<0 0 0 -28493.2 4.25588e+006 0>\r\n" + 
//					"34735 (0x87af) SHORT (3) 60<1 1 0 14 1024 0 1 1 1025 0 1 1 1026 34737 8 0 2048 0 1 4267 2049 34737 6 8 >\r\n" + 
//					"34736 (0x87b0) DOUBLE (12) 4<-117.333 33.75 0 0>\r\n" + 
//					"34737 (0x87b1) ASCII (2) 15<unnamed|NAD27|\\0>"));
			
			td = new TiffDump(new String("C:\\Users\\RDAGCDLJ\\Documents\\FY20\\GeoTIFF\\example_tiffs\\USGS\\USGS_1_n06e162.tif:\r\n" + 
					"Magic: 0x4949 <little-endian> Version: 0x2a <ClassicTIFF>\r\n" + 
					"Directory 0: offset 1542364 (0x1788dc) next 2412 (0x96c)\r\n" + 
					"ImageWidth (256) SHORT (3) 1<3612>\r\n" + 
					"ImageLength (257) SHORT (3) 1<3612>\r\n" + 
					"BitsPerSample (258) SHORT (3) 1<32>\r\n" + 
					"Compression (259) SHORT (3) 1<5>\r\n" + 
					"Photometric (262) SHORT (3) 1<1>\r\n" + 
					"SamplesPerPixel (277) SHORT (3) 1<1>\r\n" + 
					"PlanarConfig (284) SHORT (3) 1<1>\r\n" + 
					"Predictor (317) SHORT (3) 1<3>\r\n" + 
					"TileWidth (322) SHORT (3) 1<256>\r\n" + 
					"TileLength (323) SHORT (3) 1<256>\r\n" + 
					"TileOffsets (324) LONG (4) 225<549289 552139 554989 557839 560689 563539 566389 569239 572089 574939 577789 580639 583489 586339 589189 592580 595430 598280 601130 603980 606830 609680 612530 615380 ...>\r\n" + 
					"TileByteCounts (325) LONG (4) 225<2850 2850 2850 2850 2850 2850 2850 2850 2850 2850 2850 2850 2850 2850 3391 2850 2850 2850 2850 2850 2850 2850 2850 2850 ...>\r\n" + 
					"SampleFormat (339) SHORT (3) 1<3>\r\n" + 
					"33550 (0x830e) DOUBLE (12) 3<0.000277778 0.000277778 0>\r\n" + 
					"33922 (0x8482) DOUBLE (12) 6<0 0 0 161.998 6.00167 0>\r\n" + 
					"34735 (0x87af) SHORT (3) 36<1 1 0 8 1024 0 1 2 1025 0 1 1 2048 0 1 4269 2049 34737 6 0 2054 0 1 9102 ...>\r\n" + 
					"34736 (0x87b0) DOUBLE (12) 5<298.257 6.37814e+06 0 0 0>\r\n" + 
					"34737 (0x87b1) ASCII (2) 7<NAD83|\\0>\r\n" + 
					"42112 (0xa480) ASCII (2) 493<<GDALMetadata>\\n  <Item n ...>\r\n" + 
					"42113 (0xa481) ASCII (2) 8<-999999\\0>\r\n" + 
					"\r\n" + 
					"Directory 1: offset 2412 (0x96c) next 4406 (0x1136)\r\n" + 
					"SubFileType (254) LONG (4) 1<1>\r\n" + 
					"ImageWidth (256) SHORT (3) 1<1806>\r\n" + 
					"ImageLength (257) SHORT (3) 1<1806>\r\n" + 
					"BitsPerSample (258) SHORT (3) 1<32>\r\n" + 
					"Compression (259) SHORT (3) 1<5>\r\n" + 
					"Photometric (262) SHORT (3) 1<1>\r\n" + 
					"SamplesPerPixel (277) SHORT (3) 1<1>\r\n" + 
					"PlanarConfig (284) SHORT (3) 1<1>\r\n" + 
					"Predictor (317) SHORT (3) 1<3>\r\n" + 
					"TileWidth (322) SHORT (3) 1<128>\r\n" + 
					"TileLength (323) SHORT (3) 1<128>\r\n" + 
					"TileOffsets (324) LONG (4) 225<155101 156453 157805 159157 160509 161861 163213 164565 165917 167269 168621 169973 171325 172677 174029 175615 176967 178319 179671 181023 182375 183727 185079 186431 ...>\r\n" + 
					"TileByteCounts (325) LONG (4) 225<1352 1352 1352 1352 1352 1352 1352 1352 1352 1352 1352 1352 1352 1352 1586 1352 1352 1352 1352 1352 1352 1352 1352 1352 ...>\r\n" + 
					"SampleFormat (339) SHORT (3) 1<3>\r\n" + 
					"42113 (0xa481) ASCII (2) 8<-999999\\0>\r\n" + 
					"\r\n" + 
					"Directory 2: offset 4406 (0x1136) next 5112 (0x13f8)\r\n" + 
					"SubFileType (254) LONG (4) 1<1>\r\n" + 
					"ImageWidth (256) SHORT (3) 1<903>\r\n" + 
					"ImageLength (257) SHORT (3) 1<903>\r\n" + 
					"BitsPerSample (258) SHORT (3) 1<32>\r\n" + 
					"Compression (259) SHORT (3) 1<5>\r\n" + 
					"Photometric (262) SHORT (3) 1<1>\r\n" + 
					"SamplesPerPixel (277) SHORT (3) 1<1>\r\n" + 
					"PlanarConfig (284) SHORT (3) 1<1>\r\n" + 
					"Predictor (317) SHORT (3) 1<3>\r\n" + 
					"TileWidth (322) SHORT (3) 1<128>\r\n" + 
					"TileLength (323) SHORT (3) 1<128>\r\n" + 
					"TileOffsets (324) LONG (4) 64<47310 48662 50014 51366 52718 54070 55422 56774 58293 59645 60997 62349 63701 65053 66405 67757 69276 70628 71980 73332 74684 76036 77388 78740 ...>\r\n" + 
					"TileByteCounts (325) LONG (4) 64<1352 1352 1352 1352 1352 1352 1352 1519 1352 1352 1352 1352 1352 1352 1352 1519 1352 1352 1352 1352 1352 1352 1352 1519 ...>\r\n" + 
					"SampleFormat (339) SHORT (3) 1<3>\r\n" + 
					"42113 (0xa481) ASCII (2) 8<-999999\\0>\r\n" + 
					"\r\n" + 
					"Directory 3: offset 5112 (0x13f8) next 5434 (0x153a)\r\n" + 
					"SubFileType (254) LONG (4) 1<1>\r\n" + 
					"ImageWidth (256) SHORT (3) 1<452>\r\n" + 
					"ImageLength (257) SHORT (3) 1<452>\r\n" + 
					"BitsPerSample (258) SHORT (3) 1<32>\r\n" + 
					"Compression (259) SHORT (3) 1<5>\r\n" + 
					"Photometric (262) SHORT (3) 1<1>\r\n" + 
					"SamplesPerPixel (277) SHORT (3) 1<1>\r\n" + 
					"PlanarConfig (284) SHORT (3) 1<1>\r\n" + 
					"Predictor (317) SHORT (3) 1<3>\r\n" + 
					"TileWidth (322) SHORT (3) 1<128>\r\n" + 
					"TileLength (323) SHORT (3) 1<128>\r\n" + 
					"TileOffsets (324) LONG (4) 16<16442 17794 19146 20498 22528 23880 25232 26584 28614 29966 31318 32670 42713 43704 44695 45686>\r\n" + 
					"TileByteCounts (325) LONG (4) 16<1352 1352 1352 2030 1352 1352 1352 2030 1352 1352 1352 10043 991 991 991 1624>\r\n" + 
					"SampleFormat (339) SHORT (3) 1<3>\r\n" + 
					"42113 (0xa481) ASCII (2) 8<-999999\\0>\r\n" + 
					"\r\n" + 
					"Directory 4: offset 5434 (0x153a) next 5660 (0x161c)\r\n" + 
					"SubFileType (254) LONG (4) 1<1>\r\n" + 
					"ImageWidth (256) SHORT (3) 1<226>\r\n" + 
					"ImageLength (257) SHORT (3) 1<226>\r\n" + 
					"BitsPerSample (258) SHORT (3) 1<32>\r\n" + 
					"Compression (259) SHORT (3) 1<5>\r\n" + 
					"Photometric (262) SHORT (3) 1<1>\r\n" + 
					"SamplesPerPixel (277) SHORT (3) 1<1>\r\n" + 
					"PlanarConfig (284) SHORT (3) 1<1>\r\n" + 
					"Predictor (317) SHORT (3) 1<3>\r\n" + 
					"TileWidth (322) SHORT (3) 1<128>\r\n" + 
					"TileLength (323) SHORT (3) 1<128>\r\n" + 
					"TileOffsets (324) LONG (4) 4<8239 9591 11332 12536>\r\n" + 
					"TileByteCounts (325) LONG (4) 4<1352 1741 1204 3906>\r\n" + 
					"SampleFormat (339) SHORT (3) 1<3>\r\n" + 
					"42113 (0xa481) ASCII (2) 8<-999999\\0>\r\n" + 
					"\r\n" + 
					"Directory 5: offset 5660 (0x161c) next 0 (0)\r\n" + 
					"SubFileType (254) LONG (4) 1<1>\r\n" + 
					"ImageWidth (256) SHORT (3) 1<113>\r\n" + 
					"ImageLength (257) SHORT (3) 1<113>\r\n" + 
					"BitsPerSample (258) SHORT (3) 1<32>\r\n" + 
					"Compression (259) SHORT (3) 1<5>\r\n" + 
					"Photometric (262) SHORT (3) 1<1>\r\n" + 
					"SamplesPerPixel (277) SHORT (3) 1<1>\r\n" + 
					"PlanarConfig (284) SHORT (3) 1<1>\r\n" + 
					"Predictor (317) SHORT (3) 1<3>\r\n" + 
					"TileWidth (322) SHORT (3) 1<128>\r\n" + 
					"TileLength (323) SHORT (3) 1<128>\r\n" + 
					"TileOffsets (324) LONG (4) 1<5854>\r\n" + 
					"TileByteCounts (325) LONG (4) 1<2385>\r\n" + 
					"SampleFormat (339) SHORT (3) 1<3>\r\n" + 
					"42113 (0xa481) ASCII (2) 8<-999999\\0>"));
			
	        		System.out.println(td);
		} catch (Exception e) {
			e.printStackTrace();
		} 
        
        //System.out.println(td.getDirectory(0)); 
        //System.out.println(td.getDirectory(0).getTagByName("ImageWidth")); 
        //System.out.println(td.getDirectory(0).getTagByName("34735"));
    } 
	
    /**
     * A class representing a directory within a tiff
     * 
     * @author Dustin Jutras, AGC
     *
     */
	public class Directory {
		
		private int index;
		private int offset;
		private int next;
		private List<Tag> tags = new ArrayList<>();
		
		public Directory(String directoryLine) {
			List<String> info = new ArrayList<String>(Arrays.asList(directoryLine.replace(":", "").split(" ")));
			index = Integer.parseInt(info.get(info.indexOf("directory") + 1).trim());
			offset = Integer.parseInt(info.get(info.indexOf("offset") + 1).trim());
			next = Integer.parseInt(info.get(info.indexOf("next") + 1).trim());
		}
		
		public void addTag(String line) {
			this.tags.add(new Tag(line));
		}
		
		public Tag getTag(String s) {
			for (Tag tag : tags) {
				if(tag.getName().equals(s.toLowerCase())) {
					return tag;
				}
			}
			return null;
		}
		
		public Tag getTag(int value) {
			for (Tag tag : tags) {
				if(tag.getNameValue() == value) {
					return tag;
				}
			}
			return null;
		}	
		
		public List<Tag> getTags() {
			return tags;
		}

		public int getOffset() {
			return offset;
		}

		public int getNext() {
			return next;
		}

		@Override
		public String toString() {
			
			String tagsString = "";
			
			for(Tag tag : tags) {
				tagsString += tag.toString() + "\n";
			}
			
	        return String.format("Directory %d:\nOffset: %d\tNext: %d\nTags:\n%s\n%s\n", index, offset, next, tagHeaderString(), tagsString);
		}

		public boolean hasTag(int tag) {
			return getTag(tag) != null;
		}
	}
	
    /**
     * A class representing a tag within a tiff
     * 
     * @author Dustin Jutras, AGC
     *
     */
	public class Tag {
		
		private String line;
		private String name, type;
		private int nameValue, typeValue, count;
		private String valuesAsString;
		private List<Object> values = new ArrayList<>();
		
		public Tag(String line) {
			this.line = line;
			//System.out.println(line);
			
			name = line.split(" ")[0];
			type = line.split(" ")[2];
			
			String nameValueString = line.split(" ")[1].replaceAll("[()]", ""); 
			nameValue = nameValueString.contains("x") ? Integer.parseInt(nameValueString.replace("0x", ""), 16) : Integer.parseInt(nameValueString);
			
			typeValue = Integer.parseInt(line.split(" ")[3].replaceAll("[()]", ""));
			
			count = Integer.parseInt(line.split(" ")[4].substring(0, line.split(" ")[4].indexOf('<')));
			
			valuesAsString = line.substring(line.indexOf("<") + 1, line.indexOf(">"));
			String[] values = valuesAsString.split(" ");
			if(values[values.length - 1].equals("...")) {
				System.out.println("Value list has been truncated.");
				values = Arrays.copyOfRange(values, 0, values.length - 1);
			}
			
			// is this really necessary? probably not
			
			switch(type.toUpperCase()) {
				case "SHORT":	
					for(String value : values) {
						this.values.add(Integer.parseInt(value));
					}
					break;
				case "RATIONAL":
				case "DOUBLE":
					for(String value : values) {
						this.values.add(Float.parseFloat(value));
					}
					break;
				case "ASCII":
				default:
					for(String value : values) {
						this.values.add(value);
					}
					break;
			}
		}

		public String getLine() {
			return line;
		}

		public String getName() {
			return name;
		}
		
		public int getNameValue() {
			return nameValue;
		}
		
		public int getTypeValue() {
			return typeValue;
		}

		public int getCount() {
			return count;
		}
		
		public boolean containsValue(Object value) {
			return values.contains(value);
		}
		
		public List<Object> getValues() {
			return values;
		}
				
		public String getValuesAsString() {
			return valuesAsString;
		}

		@Override
	    public String toString() { 
	        return String.format("%20s (%d)\t%10s (%d)\t%10d %s", name.toUpperCase(), nameValue, type, typeValue, count, values.toString());
	    } 
	}
	
	private String filePath;
	private String magic;
	private String version;
	private List<Directory> directories = new ArrayList<>();
	private Directory geoKeyDirectory;
	
	public TiffDump(String contents) throws Exception {
		//System.out.println(contents);
		
		Directory currentDirectory = null;
		for(String line : contents.toLowerCase().split("\n")) {
			if((line.contains(".tif") || line.contains(".tmp"))  && currentDirectory == null) {
				filePath = line.substring(0, line.length() - 2);
				continue;
			}
			if(line.contains("magic")  && currentDirectory == null) {
				List<String> info = Arrays.asList(line.replace(":", "").split(" "));
				magic = info.get(info.indexOf("magic") + 1).trim();
				version = info.get(info.indexOf("version") + 1).trim();
				continue;
			}
			if(line.toLowerCase().contains("directory")) {
				currentDirectory = new Directory(line);
				directories.add(currentDirectory);
				continue;
			}
			if(currentDirectory != null && !line.trim().equals("")) {
				currentDirectory.addTag(line);
				if(line.contains("34735")) { // TODO: this needs to be a little safer
					geoKeyDirectory = currentDirectory;
				}
			}
		}
		
		//System.out.println(toString());
		
		if(!validate()) {
			System.out.println("Tiff contents invalid/parsed incorrectly");
			throw new Exception(); // TODO: fix this
		}
	}
	
	public boolean validate() {
		if(magic == null || version == null || directories.size() < 1) {
			return false;
		}
		return true;
	}
	
	public Directory getDirectory(int index) {
		return directories.get(index);
	}
	public List<Directory> getDirectories() {
		return directories;
	}
	public Directory getGeoKeyDirectory() {
		return geoKeyDirectory;
	}
	
	@Override
    public String toString() { 
        return String.format("TiffDump:\n%s\nMagic: %s Version: %s\n%s", filePath == null ? "filepath unavailable" : filePath, magic, version, directories.toString());
    }

	public String getMagic() {
		return magic;
	}

	public String getVersion() {
		return version;
	}
	
	static public String tagHeaderString() {
		return String.format("%20s (%s)\t%10s (%s)\t%10s %s", "NAME", "ID", "TYPE", "T", "CNT", "[VALUES]");
	}
}
