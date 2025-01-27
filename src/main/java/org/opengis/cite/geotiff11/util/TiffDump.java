package org.opengis.cite.geotiff11.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A (rough) wrapper class for the output of TiffDump.exe
 *
 * @author Dustin Jutras, AGC
 */
public class TiffDump {

	/**
	 * <p>
	 * main.
	 * </p>
	 * @param args an array of {@link java.lang.String} objects
	 */
	public static void main(String[] args) {
		TiffDump td;
		String test = "Tile L(ength (0x323) SHORT (3) 231<256 789 87 45>\\r\\n";

		try {
			td = new TiffDump(new String(
					"c:/Users/RDAGCDLJ/Documents/FY20/GeoTIFF/example_tiffs/COG_DEF_OR/LC08_L1TP_016030_20140602_20170305_01_T1_B1.TIF:\r\n"
							+ "Magic: 0x4949 <little-endian> Version: 0x2a <ClassicTIFF>\r\n"
							+ "Directory 0: offset 8 (0x8) next 3034 (0xbda)\r\n"
							+ "ImageWidth (256) SHORT (3) 1<7901>\r\n" + "ImageLength (257) SHORT (3) 1<8011>\r\n"
							+ "BitsPerSample (258) SHORT (3) 1<16>\r\n" + "Compression (259) SHORT (3) 1<8>\r\n"
							+ "Photometric (262) SHORT (3) 1<1>\r\n" + "SamplesPerPixel (277) SHORT (3) 1<1>\r\n"
							+ "PlanarConfig (284) SHORT (3) 1<1>\r\n" + "Predictor (317) SHORT (3) 1<1>\r\n"
							+ "TileWidth (322) SHORT (3) 1<512>\r\n" + "TileLength (323) SHORT (3) 1<512>\r\n"
							+ "SampleFormat (339) SHORT (3) 1<1>\r\n" + "33550 (0x830e) DOUBLE (12) 3<30 30 0>\r\n"
							+ "33922 (0x8482) DOUBLE (12) 6<0 0 0 217200 4.9029e+06 0>\r\n"
							+ "34735 (0x87af) SHORT (3) 64<1 1 0 14 1024 0 1 1 1025 0 1 2 1026 34737 33 0 2048 0 1 32767 2049 34737 84 33 2054 0 1 9102 2055 34736 1 0 2057 34736 1 1 2059 34736 1 2 2061 34736 1 3 3072 0 1 32767 3073 34737 406 117 3074 0 1 16018 3076 0 1 9001 0 0 0 0>\r\n"
							+ "34736 (0x87b0) DOUBLE (12) 4<0.0174533 6.37814e+06 298.257 0>\r\n"
							+ "34737 (0x87b1) ASCII (2) 524<PCS Name = WGS_1984_UTM_zone_18N|GCS Name = GCS_WGS_1984|Datum = D_WGS_1984|Ellipsoid = WGS_1984|Primem = Greenwich||ESRI PE String = PROJCS[\"WGS_1984_UTM_zone_18N\",GEOGCS[\"GCS_WGS_1984\",DATUM[\"D_WGS_1984\",SPHEROID[\"WGS_1984\",6378137,298.257223563]],PRIMEM[\"Greenwich\",0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"latitude_of_origin\",0],PARAMETER[\"central_meridian\",-75],PARAMETER[\"scale_factor\",0.9996],PARAMETER[\"false_easting\",500000],PARAMETER[\"false_northing\",0],UNIT[\"Meter\",1]]|\\0>\r\n"));

			System.out.println(td);

			System.out.println(td.getDirectory(0));
			System.out.println(td.getDirectory(0).getTag("34737"));
			System.out.println();

		}
		catch (Exception e) {
			e.printStackTrace();
		}

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
				if (tag.getName().equals(s.toLowerCase())) {
					return tag;
				}
			}
			return null;
		}

		public Tag getTag(int value) {
			for (Tag tag : tags) {
				if (tag.getNameValue() == value) {
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

			for (Tag tag : tags) {
				tagsString += tag.toString() + "\n";
			}

			return String.format("Directory %d:\nOffset: %d\tNext: %d\nTags:\n%s\n%s\n", index, offset, next,
					tagHeaderString(), tagsString);
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

			Matcher matcher;

			String regexNameValue = ".+?(?= \\([0-9])";
			String regexNumbers = "\\((0x[0-9a-f]+?|[0-9]+?)\\)";
			String regexCount = "([0-9]+)\\<";
			String regexValues = "[0-9]\\<(.*?)\\>\\s*$";

			matcher = Pattern.compile(regexNumbers).matcher(line);
			// if(matcher.f) != 2) {
			// System.out.println("BIG ERROR");
			// }
			matcher.find();
			String nameValueString = matcher.group(1);
			nameValue = nameValueString.contains("x") ? Integer.parseInt(nameValueString.replace("0x", ""), 16)
					: Integer.parseInt(nameValueString);

			matcher.find();
			typeValue = Integer.parseInt(matcher.group(1));

			matcher = Pattern.compile(regexCount).matcher(line);
			matcher.find();
			count = Integer.parseInt(matcher.group(1));

			matcher = Pattern.compile(regexValues).matcher(line);
			matcher.find();
			valuesAsString = matcher.group(1).trim();
			String[] stringValues;

			name = line.split(regexNumbers)[0].trim();
			type = line.split(regexNumbers)[1].trim();

			switch (type.toUpperCase()) {
				case "ASCII":
					stringValues = valuesAsString.split("\\|");
					break;
				case "SHORT":
				case "RATIONAL":
				case "DOUBLE":
				default:
					stringValues = valuesAsString.split(" ");
					break;
			}

			if (stringValues[stringValues.length - 1].contains("...")) {
				System.out.println("Value list has been truncated.");
				stringValues = Arrays.copyOfRange(stringValues, 0, stringValues.length - 1);
			}

			// is this really necessary? probably not

			switch (type.toUpperCase()) {
				case "SHORT":
					for (String value : stringValues) {
						values.add(Integer.parseInt(value));
					}
					break;
				case "RATIONAL":
				case "DOUBLE":
					for (String value : stringValues) {
						values.add(Float.parseFloat(value));
					}
					break;
				case "ASCII":
				default:
					for (String value : stringValues) {
						values.add(value);
					}
					break;
			}
			values.removeIf(s -> s.equals("")); // remove blank values
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
			return String.format("%20s (%d)\t%10s (%d)\t%10d %s", name.toUpperCase(), nameValue, type, typeValue, count,
					values.toString());
		}

	}

	private String filePath;

	private String magic;

	private String version;

	private List<Directory> directories = new ArrayList<>();

	private Directory geoKeyDirectory;

	/**
	 * <p>
	 * Constructor for TiffDump.
	 * </p>
	 * @param contents a {@link java.lang.String} object
	 * @throws java.lang.Exception if any.
	 */
	public TiffDump(String contents) throws Exception {
		Directory currentDirectory = null;
		for (String line : contents.toLowerCase().split("\n")) {
			if (currentDirectory == null && (line.contains(".tif") || line.contains(".tmp"))) {
				filePath = line.substring(0, line.length() - 2);
				continue;
			}
			if (currentDirectory == null && line.contains("magic:") && line.contains("version:")) {
				List<String> info = Arrays.asList(line.replace(":", "").split(" "));
				magic = info.get(info.indexOf("magic") + 1).trim();
				version = info.get(info.indexOf("version") + 1).trim();
				continue;
			}
			if (line.toLowerCase().indexOf("directory") == 0 && line.toLowerCase().contains("offset")
					&& line.toLowerCase().contains("next")) {
				currentDirectory = new Directory(line);
				directories.add(currentDirectory);
				continue;
			}
			if (currentDirectory != null && !line.trim().equals("")) {
				currentDirectory.addTag(line);
				if (currentDirectory.hasTag(GeoKeyID.GEOKEYDIRECTORYTAG)) {
					geoKeyDirectory = currentDirectory;
				}
			}
		}

		if (!valid()) {
			System.out.println("Tiff contents invalid/parsed incorrectly:\n" + contents);
			throw new Exception("Tiff contents invalid/parsed incorrectly.");
		}
	}

	/**
	 * <p>
	 * valid.
	 * </p>
	 * @return a boolean
	 */
	public boolean valid() {
		if (magic == null || version == null || directories.size() < 1) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * getDirectory.
	 * </p>
	 * @param index a int
	 * @return a {@link org.opengis.cite.geotiff11.util.TiffDump.Directory} object
	 */
	public Directory getDirectory(int index) {
		return directories.get(index);
	}

	/**
	 * <p>
	 * Getter for the field <code>directories</code>.
	 * </p>
	 * @return a {@link java.util.List} object
	 */
	public List<Directory> getDirectories() {
		return directories;
	}

	/**
	 * <p>
	 * Getter for the field <code>geoKeyDirectory</code>.
	 * </p>
	 * @return a {@link org.opengis.cite.geotiff11.util.TiffDump.Directory} object
	 */
	public Directory getGeoKeyDirectory() {
		return geoKeyDirectory;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format("TiffDump:\n%s\nMagic: %s Version: %s\n%s",
				filePath == null ? "filepath unavailable" : filePath, magic, version, directories.toString());
	}

	/**
	 * <p>
	 * Getter for the field <code>magic</code>.
	 * </p>
	 * @return a {@link java.lang.String} object
	 */
	public String getMagic() {
		return magic;
	}

	/**
	 * <p>
	 * Getter for the field <code>version</code>.
	 * </p>
	 * @return a {@link java.lang.String} object
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <p>
	 * tagHeaderString.
	 * </p>
	 * @return a {@link java.lang.String} object
	 */
	static public String tagHeaderString() {
		return String.format("%20s (%s)\t%10s (%s)\t%10s %s", "NAME", "ID", "TYPE", "T", "CNT", "[VALUES]");
	}

}
