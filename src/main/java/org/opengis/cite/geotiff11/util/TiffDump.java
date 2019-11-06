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

//    public static void main(String[] args) { 
//        TiffDump td;
//		try {
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
//			
//	        		System.out.println(td);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//        
//        //System.out.println(td.getDirectory(0)); 
//        //System.out.println(td.getDirectory(0).getTagByName("ImageWidth")); 
//        //System.out.println(td.getDirectory(0).getTagByName("34735"));
//    } 
	
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
		private boolean geoKeyDirectory;
		
		public Directory(String directoryLine) {
			List<String> info = new ArrayList<String>(Arrays.asList(directoryLine.replace(":", "").split(" ")));
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
		private List<Object> values = new ArrayList<>();
		
		public Tag(String line) {
			this.line = line;
			//System.out.println(line);
			
			name = line.split(" ")[0];
			type = line.split(" ")[2];
			
			String nameValueString = line.split(" ")[1].replaceAll("[()]", ""); 
			nameValue = nameValueString.contains("x") ? Integer.parseInt(nameValueString.replace("0x", ""), 16) : Integer.parseInt(nameValueString);
			
			typeValue = Integer.parseInt(line.split(" ")[3].replaceAll("[()]", ""));
			
			count = (int) line.split(" ")[4].charAt(0);
			
			String[] values = line.substring(line.indexOf("<") + 1, line.indexOf(">")).split(" ");
			if(values[values.length - 1].equals("...")) {
				System.out.println("Value list has been truncated.");
				values = Arrays.copyOfRange(values, 0, values.length - 1);
			}
			
			// is this really necessary? probably not
			
			switch(type.toUpperCase()) {
				case "SHORT":
				case "RATIONAL":
					for(String value : values) {
						this.values.add(Integer.parseInt(value));
					}
					break;
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
			if(currentDirectory != null) {
				currentDirectory.addTag(line);
				if(line.contains("34735")) { // TODO: this needs to be a little safer
					currentDirectory.geoKeyDirectory = true; // TODO: a bit redundant here, probably remove this bool property
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
