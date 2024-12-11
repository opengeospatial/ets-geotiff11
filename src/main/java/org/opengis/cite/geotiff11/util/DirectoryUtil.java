package org.opengis.cite.geotiff11.util;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * <p>
 * DirectoryUtil class.
 * </p>
 *
 */
public class DirectoryUtil {

	/**
	 * <p>
	 * getDirectory.
	 * </p>
	 * @return a {@link java.lang.String} object
	 */
	public static String getDirectory() {
		try {
			return Paths
				.get(new File(DirectoryUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI())
					.getPath(), "../..")
				.toString();
		}
		catch (URISyntaxException e) {
			e.printStackTrace();
			return System.getProperty("user.dir");
		}
	}

	/**
	 * <p>
	 * getDirectory.
	 * </p>
	 * @param local a {@link java.lang.String} object
	 * @return a {@link java.lang.String} object
	 */
	public static String getDirectory(String local) {
		return Paths.get(getDirectory(), local).toString();

	}

}
